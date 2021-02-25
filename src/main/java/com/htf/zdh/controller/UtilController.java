package com.htf.zdh.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.htf.zdh.utils.DateUtils;
import com.htf.zdh.utils.MathUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@Api(tags = { "小工具" })
@RestController("util")
@RequestMapping(value = "util")
public class UtilController {



	@ApiOperation(value = "保留几位小数", notes = "保留几位小数")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "num", value = "原数字", required = true, paramType = "query", dataType = "double"),
			@ApiImplicitParam(name = "digit", value = "小数点位数", required = false, paramType = "query", dataType = "integer") })
	@RequestMapping(value = "/formatDouble", method = {  RequestMethod.GET })
	@ResponseBody
	public String formatDouble(@RequestParam double num,@RequestParam(required = false) Integer digit) {
		if(digit==null){
			digit=2;
		}
		return MathUtil.formatDouble(num,digit);

	}

	@ApiOperation(value = "四舍五入保留4位小数", notes = "四舍五入保留4位小数")
	@ApiImplicitParam(name = "num", value = "小数", required = true, paramType = "query", dataType = "double")
	@RequestMapping(value = "/formatDoubleLeafFour", method = { RequestMethod.GET })
	@ResponseBody
	public String formatDoubleLeafFour(@RequestParam double num) {

		return MathUtil.formatDoubleLeafFour(num);

	}

	@ApiOperation(value = "2个double相除四舍五入保留4位小数", notes = "2个double相除四舍五入保留4位小数")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "dividend", value = "被除数", required = true, paramType = "query", dataType = "double"),
			@ApiImplicitParam(name = "divisor", value = "除数", required = true, paramType = "query", dataType = "double") })
	@RequestMapping(value = "/formatDoubleLeafN", method = { RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public String formatDoubleLeafN(@RequestParam double dividend, @RequestParam double divisor) {

		return MathUtil.formatDoubleLeafN(dividend, divisor);

	}

	@ApiOperation(value = "获取当前日期下一个1号", notes = "获取当前日期下一个1号")
	// @ApiImplicitParam(name = "num", value = "小数", required = true, paramType =
	// "query", dataType = "double")
	@RequestMapping(value = "/nextMonthFirstDay", method = { RequestMethod.GET })
	@ResponseBody
	public String nextMonthFirstDay() {

		return DateUtils.nextMonthFirstDay();

	}

}
