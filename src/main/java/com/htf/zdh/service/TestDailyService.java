package com.htf.zdh.service;

import com.htf.zdh.common.Results;
import com.htf.zdh.controller.vo.Result;
import com.htf.zdh.jdbc.po.TestDaily;
import com.htf.zdh.service.bo.AppInfoBo;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

public interface TestDailyService {
    Result<TestDaily> uploadFile(MultipartFile file, HttpServletRequest request);

}
