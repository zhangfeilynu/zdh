package com.htf.zdh.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

/**
 * @description:自定义403
 * @author fei.zhang
 * @date 2020年2月14日
 */

@Component
public class RestAccessDeniedHandler implements AccessDeniedHandler {

	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response,
			AccessDeniedException accessDeniedException) throws IOException, ServletException {

		response.setContentType("application/json;charset=UTF-8");
		response.getWriter().write("{\"code\":403,\"message\":\"权限不足\",\"data\":null}");

	}

}
