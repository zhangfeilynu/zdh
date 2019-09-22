package com.htf.zdh.controller;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.htf.zdh.service.IdCardService;

@RestController
@RequestMapping(value = "id")
public class IDCardController {
	private static final Logger logger = LoggerFactory.getLogger(IDCardController.class);

	@Autowired
	private IdCardService idCardService;

	@RequestMapping(value = "/getUser", method = RequestMethod.GET)
	public Map<String, Object> getUserByGet(@RequestParam(value = "userName") String userName) {

		Map<String, Object> map = new HashMap<>();
		map.put("userName", userName);
		return map;
	}

	@RequestMapping(value = "/randomIdNo", method = { RequestMethod.POST, RequestMethod.GET })
	public Map<String, String> getIDCard(@RequestParam(value = "areaCode", required = false) Integer areaCode,
			@RequestParam(value = "birth", required = false) String birth) {

		logger.info("获取随机身份证号码...");
		Map<String, String> map = new HashMap<>();
		map.put("idNo", idCardService.generate2(areaCode, birth));
		return map;
	}

}
