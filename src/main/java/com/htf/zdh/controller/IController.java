package com.htf.zdh.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.htf.zdh.i.IApi;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@Api(tags = { "包装汇富小i接口" })
@RestController("iapp")
@RequestMapping(value = "iapp")
public class IController {

	@ApiOperation(value = "消息推送", notes = "消息推送")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "acceptUserId", value = "用户id", required = true, paramType = "query", dataType = "string"),
			@ApiImplicitParam(name = "messageType", value = "消息类型", required = true, paramType = "query", dataType = "int") })
	@RequestMapping(value = "/api/upcoming", method = { RequestMethod.POST }, produces = {
			"application/json;charset=utf-8" })
	@ResponseBody
	public String getreserveNo(@RequestParam String acceptUserId, @RequestParam Integer messageType) {
		return IApi.upComing(acceptUserId, messageType);
	}

}
