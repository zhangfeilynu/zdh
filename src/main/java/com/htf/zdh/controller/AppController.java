package com.htf.zdh.controller;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.htf.zdh.controller.vo.Result;
import com.htf.zdh.jdbc.po.AppInfoList;
import com.htf.zdh.service.AppInfoService;
import com.htf.zdh.service.TradingService;
import com.htf.zdh.service.bo.AppInfoBo;
import com.htf.zdh.service.bo.AppInfoListBo;
import com.htf.zdh.utils.QrCodeUtil;

@Api(tags = { "app相关" })
@RestController("app")
@RequestMapping(value = "app")
public class AppController {
	private static final Logger logger = LoggerFactory.getLogger(AppController.class);

	@Autowired
	private AppInfoService appInfoService;

	@Autowired
	private TradingService tradingService;

	@ApiOperation(value = "上传APP", notes = "上传APP到服务器")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "env", value = "测试环境，默认UAT", required = false, paramType = "form", dataType = "string"),
			@ApiImplicitParam(name = "version", value = "app版本号，例如5.50", required = true, paramType = "form", dataType = "string"),
			@ApiImplicitParam(name = "appName", value = "app名称，现金宝、汇富小i，默认现金宝", required = false, paramType = "form", dataType = "string"),
			@ApiImplicitParam(name = "remark", value = "备注", required = false, paramType = "form", dataType = "string"),
			@ApiImplicitParam(name = "autotest", value = "0非自动化测试包，1自动化测试包，默认0", required = false, paramType = "form", dataType = "string"),
			@ApiImplicitParam(name = "file", value = "文件", required = true, paramType = "form", dataType = "file") })
	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	@ResponseBody
	public Result<AppInfoBo> uploadFile(@RequestParam(value = "env", required = false) String env,
			@RequestParam(value = "version") String version,
			@RequestParam(value = "appName", required = false) String appName,
			@RequestParam(value = "remark", required = false) String remark,
			@RequestParam(value = "autotest", required = false) String autotest,
			@RequestParam(value = "file") MultipartFile file) {
		AppInfoBo appInfo = new AppInfoBo();
		appInfo.setEnv(env);
		appInfo.setVersion(version);
		appInfo.setRemark(remark);
		appInfo.setAutotest(autotest);
		appInfo.setAppName(appName);
		return appInfoService.uploadFile(appInfo, file);
	}

	@ApiOperation(value = "查询APP", notes = "查询APP")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "env", value = "测试环境，例如UAT、SIT", required = false, paramType = "query", dataType = "string"),
			@ApiImplicitParam(name = "appName", value = "app名称，现金宝或者汇富小i", required = false, paramType = "query", dataType = "string"),
			@ApiImplicitParam(name = "type", value = "app类型，android或者ios", required = false, paramType = "query", dataType = "string"),
			@ApiImplicitParam(name = "version", value = "app版本号，例如5.50", required = false, paramType = "query", dataType = "string"),
			@ApiImplicitParam(name = "autotest", value = "0非自动化测试包，1自动化测试包，默认0", required = false, paramType = "query", dataType = "string"),
			@ApiImplicitParam(name = "pageNum", value = "页码", required = true, paramType = "query", dataType = "int"),
			@ApiImplicitParam(name = "pageSize", value = "每页显示的数量", required = true, paramType = "query", dataType = "int") })
	@RequestMapping(value = "/applist", method = { RequestMethod.GET })
	@ResponseBody
	public Result<AppInfoListBo> getApiList(@RequestParam(required = false) String env,
			@RequestParam(required = false) String appName, @RequestParam(required = false) String type,
			@RequestParam(required = false) String version, @RequestParam(required = false) String autotest,
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

		if ("".equals(autotest)) {
			autotest = null;
		}

		if ("".equals(appName)) {
			appName = null;
		}

		AppInfoList appInfoList = new AppInfoList();
		appInfoList.setEnv(env);
		appInfoList.setType(type);
		appInfoList.setVersion(version);
		appInfoList.setAutotest(autotest);
		appInfoList.setAppName(appName);

		AppInfoListBo apps = appInfoService.selectApps(appInfoList, pageNum, pageSize);

		result.setData(apps);
		return result;
	}

	@ApiOperation(value = "生成二维码", notes = "把下载地址转为二维码")
	@ApiImplicitParam(name = "url", value = "下载地址", required = true, paramType = "query", dataType = "string")
	@RequestMapping(value = "/qrcode", method = { RequestMethod.GET })
	@ResponseBody
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

	@ApiOperation(value = "获取交易日", notes = "获取交易日")
	@ApiImplicitParam(name = "tag", value = "0：T+0,1：T+1,2：T+2", required = true, paramType = "query", dataType = "int")
	@RequestMapping(value = "/trading", method = { RequestMethod.GET })
	@ResponseBody
	public String getTrDay(@RequestParam int tag) {

		return tradingService.getTradingDay(tag);

	}

}
