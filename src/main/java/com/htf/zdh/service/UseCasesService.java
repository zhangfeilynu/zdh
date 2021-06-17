package com.htf.zdh.service;

import com.htf.zdh.controller.vo.Result;
import com.htf.zdh.jdbc.po.ChandaoBug;
import com.htf.zdh.jdbc.po.UseCasesWithBLOBs;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

public interface UseCasesService {
    Result<UseCasesWithBLOBs> UseCasesFileImport(MultipartFile file, HttpServletRequest request) ;
}
