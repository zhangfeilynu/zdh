package com.htf.zdh.service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.htf.zdh.controller.vo.Result;
import com.htf.zdh.jdbc.dao.AppInfoListMapper;
import com.htf.zdh.jdbc.po.AppInfoList;
import com.htf.zdh.service.bo.AppInfoBo;
import com.htf.zdh.service.bo.AppInfoListBo;
import com.htf.zdh.utils.IOSUtil;

@Service
public class AppInfoServiceImpl implements AppInfoService {

	private static final Logger logger = LoggerFactory.getLogger(AppInfoServiceImpl.class);

	@Autowired
	private AppInfoListMapper appInfoListMapper;

	@Value("${basePath}")
	private String basePath;

	@Value("${env}")
	private String env;

	@Value("${baseDownloadUrl}")
	private String baseDownloadUrl;

	@Override
	public AppInfoListBo selectApps(AppInfoList appInfoList, Integer pageNum, Integer pageSize) {

		AppInfoListBo result = new AppInfoListBo();
		// 设置分页
		PageHelper.startPage(pageNum, pageSize);
		result.setPageNum(pageNum);
		result.setPageSize(pageSize);

		List<AppInfoList> list = appInfoListMapper.selectApps(appInfoList);

		for (int i = 0; i < list.size(); i++) {
			if ("1".equals(list.get(i).getAutotest())) {
				list.get(i).setAutotest("是");
			} else {
				list.get(i).setAutotest("否");
			}

		}

		// 获取总记录数
		PageInfo<AppInfoList> pageInfo = new PageInfo<AppInfoList>(list);
		Long total = pageInfo.getTotal();
		result.setTotal(total);

		// 数据处理
		List<AppInfoList> results = new ArrayList<>();
		// 如果pageNmu超过最后一页
		if (pageNum > pageInfo.getLastPage()) {
			result.setList(results);
			return result;
		}
		// 转换数据
		for (AppInfoList item : list) {
			results.add(item);
		}
		result.setList(results);
		return result;

	}

	@Override
	public Result<AppInfoBo> uploadFile(AppInfoBo appInfo, MultipartFile file) {

		Result<AppInfoBo> result = new Result<AppInfoBo>();

		if (!("1".equals(appInfo.getAutotest()))) {
			appInfo.setAutotest("0");
		}

		if (appInfo.getEnv() == null || "".equals(appInfo.getEnv())) {
			appInfo.setEnv("UAT");
		}
		appInfo.setEnv(appInfo.getEnv().toUpperCase());

		if (appInfo.getAppName() == null || "".equals(appInfo.getAppName())) {
			appInfo.setAppName("现金宝");
		}

		if (appInfo.getVersion() == null || "".equals(appInfo.getVersion())) {
			result.setCode(40001);
			result.setMessage("缺少请求参数：版本version");
			return result;
		}

		String filePath = "";

		String env = appInfo.getEnv();
		String version = appInfo.getVersion();

		if (!IOSUtil.testStr(env)) {
			appInfo.setEnv(IOSUtil.encryption(env));
		}

		if (!IOSUtil.testNum(version)) {
			appInfo.setVersion(IOSUtil.encryption(version));
		}

		if (file.isEmpty()) {
			result.setCode(40004);
			result.setMessage("上传失败，文件不存在");
			return result;
		}

		String fileName = file.getOriginalFilename(); // 获取上传文件原名
		logger.info("文件原名是：" + fileName);

		int length = fileName.length();
		if (length <= 3) {
			result.setCode(40004);
			result.setMessage("上传失败，文件名称错误");
			return result;
		}

		String ext = fileName.substring(length - 3, length);
		if (!("apk".equalsIgnoreCase(ext) || "ipa".equalsIgnoreCase(ext))) {
			result.setCode(40003);
			result.setMessage("上传失败，只能上传.apk或者.ipa文件");
			return result;
		}

		String pre = fileName.substring(0, length - 4);
		String md5Pre = IOSUtil.encryption(pre);
		String type = "";
		if ("apk".equalsIgnoreCase(ext)) {
			type = "android";
		}
		if ("ipa".equalsIgnoreCase(ext)) {
			type = "ios";
		}

		if ("dev".equals(env)) {

			if ("汇富小i".equals(appInfo.getAppName())) {
				filePath = basePath + "\\" + "\\i\\" + appInfo.getEnv() + "\\" + type + "\\" + appInfo.getVersion()
						+ "\\";
			} else {
				filePath = basePath + "\\" + appInfo.getEnv() + "\\" + type + "\\" + appInfo.getVersion() + "\\";
			}

		} else {

			if ("汇富小i".equals(appInfo.getAppName())) {
				filePath = basePath + "/i/" + appInfo.getEnv() + "/" + type + "/" + appInfo.getVersion() + "/";
			} else {
				filePath = basePath + "/" + appInfo.getEnv() + "/" + type + "/" + appInfo.getVersion() + "/";
			}
		}

		if (!("apk".equalsIgnoreCase(ext) || "ipa".equalsIgnoreCase(ext))) {
			result.setCode(40004);
			result.setMessage("上传失败，只能上传apk或者ipa文件");
			return result;
		}

		File dir = new File(filePath);
		if (!dir.exists()) {
			dir.mkdirs();
		}

		String md5Filename = md5Pre + "." + ext;

		File dest = new File(filePath + md5Filename);
		if (dest.exists()) {
			result.setCode(40005);
			result.setMessage("上传失败，文件已存在");
			return result;
		}

		try {
			file.transferTo(dest);
			logger.info("上传成功");
			result.setCode(1);
			result.setMessage("上传成功");

			/**
			 * 上传成功后，APP信息写入mysql
			 */
			String downloadUrl = "";

			if ("汇富小i".equals(appInfo.getAppName())) {
				downloadUrl = baseDownloadUrl + "/i/" + appInfo.getEnv() + "/" + type + "/" + appInfo.getVersion() + "/"
						+ md5Filename;
			} else {
				downloadUrl = baseDownloadUrl + "/" + appInfo.getEnv() + "/" + type + "/" + appInfo.getVersion() + "/"
						+ md5Filename;
			}
			logger.info("下载地址是" + downloadUrl);
			AppInfoList appInfoList = new AppInfoList();
			appInfoList.setEnv(env);
			appInfoList.setType(type);
			appInfoList.setVersion(version);
			appInfoList.setDownloadUrl(downloadUrl);
			appInfoList.setRemark(appInfo.getRemark());
			appInfoList.setName(fileName);
			appInfoList.setAutotest(appInfo.getAutotest());
			appInfoList.setAppName(appInfo.getAppName());
			saveAppInfo(appInfoList);// 上传APP信息写入数据库

			// APP上传成功后写入plist文件和html文件
			if ("ios".equals(type)) {
				String bundleId = "";
				String plistUrl = "";
				if ("汇富小i".equals(appInfo.getAppName())) {
					bundleId = "com.HTF.HTFIMobilePortal";
					plistUrl = "https://10.50.16.230" + "/i/" + appInfo.getEnv() + "/" + type + "/"
							+ appInfo.getVersion() + "/" + md5Pre + ".plist";
				} else {
					bundleId = "com.htf.mclient.Uat35";
					plistUrl = "https://10.50.16.230" + "/" + appInfo.getEnv() + "/" + type + "/" + appInfo.getVersion()
							+ "/" + md5Pre + ".plist";
				}
				IOSUtil.createPlist(filePath, md5Pre, bundleId, appInfo.getAppName(), downloadUrl);
				IOSUtil.createHtml(filePath, md5Pre, plistUrl);
			}

			return result;
		} catch (Exception e) {
			logger.error("上传文件出现异常：" + e.getMessage());
			result.setCode(40006);
			result.setMessage("上传文件出现异常");
			return result;
		}

	}

	@Override
	public int saveAppInfo(AppInfoList record) {
		int i = appInfoListMapper.insertSelective(record);
		return i;
	}

}
