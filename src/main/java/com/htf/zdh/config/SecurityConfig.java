package com.htf.zdh.config;

import com.htf.zdh.common.Const;
import com.htf.zdh.filter.JwtTokenFilter;
import com.htf.zdh.service.CommonService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.util.DigestUtils;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	private static final Logger logger = LoggerFactory.getLogger(SecurityConfig.class);

	@Autowired
	private CommonService commonService;

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private AuthEntryPoint authEntryPoint; // 未登录异常处理器

	@Autowired
	private RestAccessDeniedHandler restAccessDeniedHandler;// 权限认证异常处理器

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {

		// 校验用户
		auth.userDetailsService(userDetailsService).passwordEncoder(new PasswordEncoder() {
			// 对密码进行加密
			@Override
			public String encode(CharSequence charSequence) {
				logger.info(charSequence.toString());
				return DigestUtils.md5DigestAsHex(charSequence.toString().getBytes());
			}

			// 对密码进行判断匹配
			@Override
			public boolean matches(CharSequence charSequence, String s) {
				String encode = DigestUtils.md5DigestAsHex(charSequence.toString().getBytes());
				boolean res = s.equals(encode);
				return res;
			}
		});

	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.csrf().disable()
				// 因为使用JWT，所以不需要HttpSession
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().authorizeRequests()
				// OPTIONS请求全部放行
				.antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
				// 放行的接口，数据里配置的
				.antMatchers(commonService.selectPaths()).permitAll()
				// 放行swagger
				.antMatchers(HttpMethod.GET, "/v2/api-docs", "/swagger-resources", "/swagger-resources/**",
						"/configuration/ui", "/configuration/security", "/swagger-ui.html/**", "/webjars/**")
				.permitAll()
				// 其他接口全部接受验证
				.anyRequest().authenticated();

		// 使用自定义的 Token过滤器 验证请求的Token是否合法
		http.addFilterBefore(authenticationTokenFilterBean(), UsernamePasswordAuthenticationFilter.class);
		// 自定义401、403
		http.exceptionHandling().authenticationEntryPoint(authEntryPoint).accessDeniedHandler(restAccessDeniedHandler);
		// 禁用缓存
		http.headers().cacheControl();

	}

	@Bean
	public JwtTokenFilter authenticationTokenFilterBean() throws Exception {
		return new JwtTokenFilter();
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

}
