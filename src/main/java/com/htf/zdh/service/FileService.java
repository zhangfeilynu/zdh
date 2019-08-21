package com.htf.zdh.service;

import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.htf.zdh.controller.vo.Result;

public interface FileService {

	public Result uploadFile(AppInfoBo appInfo, MultipartFile file);

}
