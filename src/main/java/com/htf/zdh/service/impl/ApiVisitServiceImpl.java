package com.htf.zdh.service.impl;

import com.htf.zdh.jdbc.dao.ApiVisitMapper;
import com.htf.zdh.jdbc.po.ApiVisit;
import com.htf.zdh.service.ApiVisitService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author zhangfei
 * @version 1.0.0
 * @className ApiVisitServiceImpl.java
 * @description TODO
 * @createTime 2020/12/15 16:45
 */

@Service
public class ApiVisitServiceImpl implements ApiVisitService {
    private static final Logger logger = LoggerFactory.getLogger(ApiVisitServiceImpl.class);

    @Autowired
    private ApiVisitMapper apiVisitMapper;

    @Override
    public int insert(String uri) {
        ApiVisit apiVisit=new ApiVisit();
        apiVisit.setUri(uri);
        return apiVisitMapper.insertSelective(apiVisit);
    }

    @Override
    public int count(String uri) {
        return apiVisitMapper.count(uri);
    }
}
