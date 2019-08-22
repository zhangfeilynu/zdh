package com.htf.zdh.service;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.htf.zdh.controller.vo.Result;

@Service
public class FileServiceImpl implements FileService {
	private static final Logger logger = LoggerFactory.getLogger(FileServiceImpl.class);

	@Value("${basePath}")
	private String basePath;

	@Value("${env}")
	private String env;

	@Override
	public Result<AppInfoBo> uploadFile(AppInfoBo appInfo, MultipartFile file) {

		// Map<String, String> result = new HashMap<>();

		Result<AppInfoBo> result = new Result<AppInfoBo>();

		if (appInfo.getEnv() == null || "".equals(appInfo.getEnv())) {
			result.setCode(40001);
			result.setMessage("缺少请求参数env");
			// result.put("code", "40001");// 40001缺少字段
			// result.put("message", "缺少请求参数env");
			return result;
		}
		if (appInfo.getType() == null || "".equals(appInfo.getType())) {
			result.setCode(40001);
			result.setMessage("缺少请求参数type");
			// result.put("code", "40001");// 40001缺少字段
			// result.put("message", "缺少请求参数type");
			return result;
		}

		if (appInfo.getVersion() == null || "".equals(appInfo.getVersion())) {
			result.setCode(40001);
			result.setMessage("缺少请求参数version");
			// result.put("code", "40001");// 40001缺少字段
			// result.put("message", "缺少请求参数version");
			return result;
		}

		// Boolean appType = "ios".equals(appInfo.getType()) ||
		// "android".equals(appInfo.getType());
		if (!("ios".equals(appInfo.getType()) || "android".equals(appInfo.getType()))) {
			result.setCode(40002);
			result.setMessage("app类型错误，只能填写android或者ios");
			// result.put("code", "40002");// 字段取值范围错误
			// result.put("message", "app类型错误，只能填写android或者ios");
			return result;
		}

		String filePath = "";

		if ("dev".equals(env)) {
			filePath = basePath + "\\" + appInfo.getEnv() + "\\" + appInfo.getType() + "\\" + appInfo.getVersion()
					+ "\\";
		} else {
			filePath = basePath + "/" + appInfo.getEnv() + "/" + appInfo.getType() + "/" + appInfo.getVersion() + "/";
		}

		if (file.isEmpty()) {
			result.setCode(40004);
			result.setMessage("上传失败，文件不存在");
			// result.put("code", "40004");
			// result.put("message", "上传失败，文件不存在");
			return result;
		}

		String fileName = file.getOriginalFilename(); // 获取上传文件原名
		logger.info("文件原名是：" + fileName);

		int length = fileName.length();
		if (length <= 3) {
			result.setCode(40004);
			result.setMessage("上传失败，文件名称错误");
			// result.put("code", "40004");
			// result.put("message", "上传失败，文件名称错误");
			return result;
		}

		String ext = fileName.substring(length - 3, length);
		if (!("apk".equalsIgnoreCase(ext) || "ipa".equalsIgnoreCase(ext))) {
			result.setCode(40004);
			result.setMessage("上传失败，只能上传apk或者ipa文件");
			// result.put("code", "40004");
			// result.put("message", "上传失败，只能上传apk或者ipa文件");
			return result;
		}

		if ("android".equalsIgnoreCase(appInfo.getType())) {
			if (!"apk".equalsIgnoreCase(ext)) {
				result.setCode(40003);
				result.setMessage("上传失败，类型为android时，文件扩展名必须为apk");
				// result.put("code", "40003");
				// result.put("message", "上传失败，类型为android时，文件扩展名必须为apk");
				return result;
			}
		}

		if ("ios".equalsIgnoreCase(appInfo.getType())) {
			if (!"ipa".equalsIgnoreCase(ext)) {
				result.setCode(40003);
				result.setMessage("上传失败，类型为ios时，文件扩展名必须为ipa");
				// result.put("code", "40003");
				// result.put("message", "上传失败，类型为ios时，文件扩展名必须为ipa");
				return result;
			}
		}
		File dir = new File(filePath);
		if (!dir.exists()) {
			dir.mkdirs();
		}

		File dest = new File(filePath + fileName);
		if (dest.exists()) {
			result.setCode(40005);
			result.setMessage("上传失败，文件已存在");
			// result.put("code", "40005");
			// result.put("message", "上传失败，文件已存在");
			return result;
		}

		try {
			file.transferTo(dest);
			logger.info("上传成功");
			result.setCode(1);
			result.setMessage("上传成功");
			// result.put("code", "1");
			// result.put("message", "上传成功");
			return result;
		} catch (Exception e) {
			logger.error("上传文件失败：" + e.getMessage());
		}

		result.setCode(40006);
		result.setMessage("上传文件出现异常");
		// result.put("code", "40006");
		// result.put("message", "上传文件出现异常");
		return result;

	}

	// @Override
	// public String uploadFile(CommonsMultipartFile file) {
	//
	// String path = "F:/htf/upload" + new Date().getTime() +
	// file.getOriginalFilename();
	// File newFile = new File(path);
	// try {
	// file.transferTo(newFile);
	// } catch (IllegalStateException | IOException e) {
	// logger.error("保存文件出现异常：" + e.getMessage());
	// }
	// return path;
	// }

}
