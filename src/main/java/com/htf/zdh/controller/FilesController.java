package com.htf.zdh.controller;

import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.htf.zdh.controller.vo.Result;
import com.htf.zdh.service.AppInfoBo;
import com.htf.zdh.service.FileService;

@RestController
@RequestMapping(value = "file")
public class FilesController {
	private static final Logger logger = LoggerFactory.getLogger(FilesController.class);

	@Autowired
	private FileService fileService;

	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public Result uploadFile(@RequestParam(value = "env") String env, @RequestParam(value = "type") String type,
			@RequestParam(value = "version") String version, @RequestParam(value = "file") MultipartFile file) {
		AppInfoBo appInfo = new AppInfoBo();
		appInfo.setEnv(env);
		appInfo.setType(type);
		appInfo.setVersion(version);
		return fileService.uploadFile(appInfo, file);
	}

}
