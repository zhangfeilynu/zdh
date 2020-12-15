package com.htf.zdh.jdbc.dao;

import com.htf.zdh.jdbc.po.ApiVisit;

public interface ApiVisitMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ApiVisit record);

    int insertSelective(ApiVisit record);

    ApiVisit selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ApiVisit record);

    int count(String uri);

    int updateByPrimaryKey(ApiVisit record);
}