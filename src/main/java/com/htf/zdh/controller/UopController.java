package com.htf.zdh.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.htf.zdh.uop.Vip;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@Api(tags = { "包装高端接口" })
@RestController("vip")
@RequestMapping(value = "vip")
public class UopController {

	@ApiOperation(value = "根据基金id获取预约码", notes = "根据基金id获取预约码，即流水号")
	@ApiImplicitParam(name = "fundId", value = "基金id", required = true, paramType = "query", dataType = "string")
	@RequestMapping(value = "/v1/reserve-code/all", method = { RequestMethod.GET })
	@ResponseBody
	public String getreserveNo(@RequestParam String fundId) {
		return Vip.getreserveNo(fundId);
	}

	@ApiOperation(value = "配置额度、设置状态", notes = "配置额度、设置状态")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "fundId", value = "基金id", required = true, paramType = "query", dataType = "string"),
			@ApiImplicitParam(name = "auditStatus", value = "审核状态，N未审核，Y审核通过生效中，F审核通过已失效，C审核未通过", required = false, paramType = "query", dataType = "string"),
			@ApiImplicitParam(name = "realAmt", value = "配置额度", required = true, paramType = "query", dataType = "string"),
			@ApiImplicitParam(name = "reserveNo", value = "预约码，即流水号", required = true, paramType = "query", dataType = "string") })
	@RequestMapping(value = "/v1/reserve-code/update", method = { RequestMethod.POST })
	@ResponseBody
	public Map<String, Integer> update(@RequestParam String fundId, @RequestParam(required = false) String auditStatus,
			@RequestParam String realAmt, @RequestParam String reserveNo) {
		int i = Vip.update(fundId, auditStatus, realAmt, reserveNo);
		Map<String, Integer> map = new HashMap<>();
		map.put("code", i);
		return map;
	}

	@ApiOperation(value = "设置失效日期并发送", notes = "设置失效日期并发送")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "deadlineDate", value = "失效日期，默认明天", required = false, paramType = "query", dataType = "string"),
			@ApiImplicitParam(name = "reserveNo", value = "预约码，即流水号", required = true, paramType = "query", dataType = "string") })
	@RequestMapping(value = "/v1/reserve-code/send-message", method = { RequestMethod.POST })
	@ResponseBody
	public Map<String, Integer> sendMessage(@RequestParam(required = false) String deadlineDate,
			@RequestParam String reserveNo) {
		int i = Vip.sendMessage(deadlineDate, reserveNo);
		Map<String, Integer> map = new HashMap<>();
		map.put("code", i);
		return map;
	}

	@ApiOperation(value = "单笔管理", notes = "单笔管理-发送预约码给用户手机")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "custNm", value = "用户姓名", required = false, paramType = "query", dataType = "string"),
			@ApiImplicitParam(name = "fundId", value = "产品ID", required = false, paramType = "query", dataType = "string"),
			@ApiImplicitParam(name = "mobileNo", value = "手机号", required = false, paramType = "query", dataType = "string"),
			@ApiImplicitParam(name = "realAmt", value = "金额，单位：百万元", required = false, paramType = "query", dataType = "string") })
	@RequestMapping(value = "/v1/reserve-code/add", method = { RequestMethod.POST })
	@ResponseBody
	public Map<String, Integer> add(@RequestParam String custNm, @RequestParam String fundId,
			@RequestParam String mobileNo, @RequestParam String realAmt) {
		int i = Vip.add(custNm, fundId, mobileNo, realAmt);
		Map<String, Integer> map = new HashMap<>();
		map.put("code", i);
		return map;
	}

}
