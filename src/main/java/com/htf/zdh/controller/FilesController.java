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
import com.htf.zdh.jdbc.po.AppInfoList;
import com.htf.zdh.service.AppInfoService;
import com.htf.zdh.service.FileService;
import com.htf.zdh.service.bo.AppInfoBo;
import com.htf.zdh.service.bo.AppInfoListBo;

@RestController
@RequestMapping(value = "file")
public class FilesController {
	private static final Logger logger = LoggerFactory.getLogger(FilesController.class);

	@Autowired
	private FileService fileService;

	@Autowired
	private AppInfoService appInfoService;

	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public Result<AppInfoBo> uploadFile(@RequestParam(value = "env") String env,
			@RequestParam(value = "type") String type, @RequestParam(value = "version") String version,
			@RequestParam(value = "file") MultipartFile file) {
		AppInfoBo appInfo = new AppInfoBo();
		appInfo.setEnv(env);
		appInfo.setType(type);
		appInfo.setVersion(version);
		return fileService.uploadFile(appInfo, file);
	}

	@RequestMapping(value = "/applist", method = { RequestMethod.GET })
	public Result<AppInfoListBo> getApiList(@RequestParam(required = false) String env,
			@RequestParam(required = false) String type, @RequestParam(required = false) String version,
			@RequestParam Integer pageNum, @RequestParam Integer pageSize) {
		Result<AppInfoListBo> result = new Result<AppInfoListBo>();
		result.setCode(1);
		result.setMessage("查询成功");

		if ("".equals(env)) {
			env = null;
		}
		if ("".equals(type)) {
			type = null;
		}
		if ("".equals(version)) {
			version = null;
		}

		AppInfoList appInfoList = new AppInfoList();
		appInfoList.setEnv(env);
		appInfoList.setType(type);
		appInfoList.setVersion(version);

		AppInfoListBo apps = appInfoService.selectApps(appInfoList, pageNum, pageSize);
		// if (apps == null) {
		// result.setCode(5000);
		// result.setMessage("内部服务错误");
		// return result;
		// }
		result.setData(apps);
		return result;
	}

}
