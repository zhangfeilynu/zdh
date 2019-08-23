package com.htf.zdh.jdbc.dao;

import org.apache.ibatis.annotations.Mapper;

import com.htf.zdh.jdbc.po.AppInfoList;

public interface AppInfoListMapper {
	int deleteByPrimaryKey(Integer id);

	int insert(AppInfoList record);

	int insertSelective(AppInfoList record);

	AppInfoList selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(AppInfoList record);

	int updateByPrimaryKey(AppInfoList record);
}