package com.htf.zdh.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.htf.zdh.controller.vo.Result;
import com.htf.zdh.service.AuthService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@Api(tags = { "登录相关" })
@RestController("login")
@RequestMapping()
public class AuthController {

	@Autowired
	private AuthService authService;

	/**
	 * 登录
	 */
	@ApiOperation(value = "登录", notes = "登录")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "username", value = "用户名", required = true, paramType = "form", dataType = "string"),
			@ApiImplicitParam(name = "password", value = "密码", required = true, paramType = "form", dataType = "string") })
	@PostMapping(value = "/auth/login")
	@ResponseBody
	public Result login(@RequestParam(value = "username") String username,
			@RequestParam(value = "password") String password) throws AuthenticationException {
		Result result = new Result();
		result.setCode(1);
		result.setMessage("登录成功");
		// 登录成功会返回Token给用户
		String token = authService.login(username, password);
		Map<String, String> map = new HashMap<>();
		map.put("token", token);
		result.setData(map);
		return result;
		// return authService.login(username, password);
	}

	@PostMapping(value = "/user/hi")
	public String userHi(@RequestParam(value = "name") String name) throws AuthenticationException {
		return "hi " + name + " , you have 'user' role";
	}

	@PostMapping(value = "/admin/hi")
	public String adminHi(@RequestParam(value = "name") String name) throws AuthenticationException {
		return "hi " + name + " , you have 'admin' role";
	}

}
