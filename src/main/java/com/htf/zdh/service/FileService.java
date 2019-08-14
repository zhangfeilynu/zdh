package com.htf.zdh.service;

import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

public interface FileService {

	public Map<String, String> uploadFile(AppInfoBo appInfo, MultipartFile file);

}
