package com.htf.zdh.service;

import com.htf.zdh.common.Results;
import com.htf.zdh.jdbc.po.TestDaily;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

public interface TestDailyService {
    Results uploadFile(MultipartFile file, HttpServletRequest request) throws Exception;

}
