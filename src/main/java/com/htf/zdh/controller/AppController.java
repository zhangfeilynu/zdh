package com.htf.zdh.controller;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;

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
import com.htf.zdh.service.bo.AppInfoBo;
import com.htf.zdh.service.bo.AppInfoListBo;
import com.htf.zdh.utils.QrCodeUtil;

@RestController
@RequestMapping(value = "app")
public class AppController {
	private static final Logger logger = LoggerFactory.getLogger(AppController.class);

	@Autowired
	private AppInfoService appInfoService;

	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public Result<AppInfoBo> uploadFile(@RequestParam(value = "env", required = false) String env,
			@RequestParam(value = "type") String type, @RequestParam(value = "version") String version,
			@RequestParam(value = "remark", required = false) String remark,
			@RequestParam(value = "file") MultipartFile file) {
		AppInfoBo appInfo = new AppInfoBo();
		appInfo.setEnv(env);
		appInfo.setType(type);
		appInfo.setVersion(version);
		appInfo.setRemark(remark);
		return appInfoService.uploadFile(appInfo, file);
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

		result.setData(apps);
		return result;
	}

	@RequestMapping(value = "/qrcode", method = { RequestMethod.GET })
	public void getQrCode(@RequestParam String url, HttpServletResponse response) {
		QrCodeUtil qrCodeUtil = new QrCodeUtil();
		BufferedImage image = qrCodeUtil.createQrCode(url);
		response.setHeader("Content-Type", "image/jpeg");
		response.setCharacterEncoding("UTF-8");
		try {
			ImageIO.write(image, "jpg", response.getOutputStream());
		} catch (IOException e) {
			logger.error("二维码写入输出流失败：" + e.getMessage());
		}

	}

}
