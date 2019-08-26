package com.htf.zdh.jdbc.dao;

import java.util.List;

import com.htf.zdh.jdbc.po.AppInfoList;

public interface AppInfoListMapper {
	int deleteByPrimaryKey(Integer id);

	int insert(AppInfoList record);

	int insertSelective(AppInfoList record);

	AppInfoList selectByPrimaryKey(Integer id);

	List<AppInfoList> selectApps(AppInfoList appInfoList);

	int updateByPrimaryKeySelective(AppInfoList record);

	int updateByPrimaryKey(AppInfoList record);
}