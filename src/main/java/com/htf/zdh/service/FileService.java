package com.htf.zdh.service;

import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.htf.zdh.controller.vo.Result;
import com.htf.zdh.jdbc.po.AppInfoList;

public interface FileService {

	public Result<AppInfoBo> uploadFile(AppInfoBo appInfo, MultipartFile file);

	public int saveAppInfo(AppInfoList record);

}
