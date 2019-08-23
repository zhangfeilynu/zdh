package com.htf.zdh.jdbc.dao;

import org.apache.ibatis.annotations.Mapper;

import com.htf.zdh.jdbc.po.Chengji;

public interface ChengjiMapper {
	int deleteByPrimaryKey(Byte id);

	int insert(Chengji record);

	int insertSelective(Chengji record);

	Chengji selectByPrimaryKey(Byte id);

	int updateByPrimaryKeySelective(Chengji record);

	int updateByPrimaryKey(Chengji record);
}