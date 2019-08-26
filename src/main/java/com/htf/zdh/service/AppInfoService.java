package com.htf.zdh.service;

import org.springframework.web.multipart.MultipartFile;

import com.htf.zdh.controller.vo.Result;
import com.htf.zdh.jdbc.po.AppInfoList;
import com.htf.zdh.service.bo.AppInfoBo;
import com.htf.zdh.service.bo.AppInfoListBo;

public interface AppInfoService {

	public AppInfoListBo selectApps(AppInfoList appInfoList, Integer pageNum, Integer pageSize);

	public Result<AppInfoBo> uploadFile(AppInfoBo appInfo, MultipartFile file);

	public int saveAppInfo(AppInfoList record);

}
