package com.htf.zdh.jdbc.dao;

import com.htf.zdh.jdbc.po.Paths;

public interface PathsMapper {
	int deleteByPrimaryKey(Integer id);

	int insert(Paths record);

	int insertSelective(Paths record);

	String[] selectPaths();

	Paths selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(Paths record);

	int updateByPrimaryKey(Paths record);
}