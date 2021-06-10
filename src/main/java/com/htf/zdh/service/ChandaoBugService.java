package com.htf.zdh.service;

import com.htf.zdh.common.Results;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

public interface ChandaoBugService {
    Results uploadChandaoBugFile(MultipartFile file, HttpServletRequest request) throws Exception;
}
