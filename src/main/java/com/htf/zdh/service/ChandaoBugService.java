package com.htf.zdh.service;

import com.htf.zdh.common.Results;
import com.htf.zdh.controller.vo.Result;
import com.htf.zdh.jdbc.po.ChandaoBug;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

public interface ChandaoBugService {
    Result<ChandaoBug> uploadChandaoBugFile(MultipartFile file, HttpServletRequest request) ;
}
