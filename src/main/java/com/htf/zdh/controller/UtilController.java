package com.htf.zdh.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.htf.zdh.utils.MathUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

@Api(tags = { "小工具" })
@RestController("util")
@RequestMapping(value = "util")
public class UtilController {

	@ApiOperation(value = "四舍五入保留2位小数", notes = "四舍五入保留2位小数")
	@ApiImplicitParam(name = "num", value = "小数", required = true, paramType = "query", dataType = "double")
	@RequestMapping(value = "/formatDoubleLeafTwo", method = { RequestMethod.GET })
	@ResponseBody
	public String formatDoubleLeafTwo(@RequestParam double num) {

		return MathUtil.formatDoubleLeafTwo(num);

	}

}
