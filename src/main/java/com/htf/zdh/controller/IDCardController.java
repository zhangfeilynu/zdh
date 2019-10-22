package com.htf.zdh.controller;

import java.util.HashMap;
import java.util.Map;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.htf.zdh.service.IdCardService;

@Api(tags = { "生成随机身份证号码" })
@RestController("id")
@RequestMapping(value = "id")
public class IDCardController {
	private static final Logger logger = LoggerFactory.getLogger(IDCardController.class);

	@Autowired
	private IdCardService idCardService;

	@ApiOperation(value = "查询APP", notes = "查询APP")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "areaCode", value = "区域代码，6位数字，例如411521", required = false, paramType = "query", dataType = "int"),
			@ApiImplicitParam(name = "birth", value = "出生年份", required = false, paramType = "query", dataType = "string") })
	@RequestMapping(value = "/randomIdNo", method = { RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public Map<String, String> getIDCard(@RequestParam(value = "areaCode", required = false) Integer areaCode,
			@RequestParam(value = "birth", required = false) String birth) {

		logger.info("获取随机身份证号码...");
		Map<String, String> map = new HashMap<>();
		map.put("idNo", idCardService.generate2(areaCode, birth));
		return map;
	}

}
