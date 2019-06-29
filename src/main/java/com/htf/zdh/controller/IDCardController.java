package com.htf.zdh.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.htf.zdh.service.IdCardGeneration;

@RestController
@RequestMapping(value = "test")
public class IDCardController {

	@Autowired
	private IdCardGeneration idCardGenerationService;

	@RequestMapping(value = "/getUser", method = RequestMethod.GET)
	public Map<String, Object> getUserByGet(@RequestParam(value = "userName") String userName) {
		Map<String, Object> map = new HashMap<>();
		map.put("userName", userName);
		return map;
	}

	@RequestMapping(value = "/randomIdNo", method = { RequestMethod.POST, RequestMethod.GET })
	public Map<String, String> getIDCard(@RequestParam(value = "areaCode", required = false) Integer areaCode,
			@RequestParam(value = "birth", required = false) String birth) {

		Map<String, String> map = new HashMap<>();
		map.put("idNo", idCardGenerationService.generate(areaCode, birth));
		return map;
	}

}
