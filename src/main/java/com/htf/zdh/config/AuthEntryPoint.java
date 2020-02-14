package com.htf.zdh.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.htf.zdh.controller.vo.Response;
import com.htf.zdh.controller.vo.Result;

/**
 * @description: 自定义401
 * @author fei.zhang
 * @date 2020年2月14日
 */
@Component
public class AuthEntryPoint implements AuthenticationEntryPoint {

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException {
		response.setHeader("Access-Control-Allow-Origin", "*");

		response.setContentType("application/json;charset=UTF-8");

		// response.getWriter().write(JSON.toJSONString(new
		// Response.Builder().setStatus(401).setMessage("未登录").build()));
		response.getWriter().write("{\"code\":401,\"message\":\"未登录\",\"data\":null}");

	}

}
