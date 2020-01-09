package com.htf.zdh.jdbc.dao;

import java.util.List;

import com.htf.zdh.jdbc.po.HtfLinkInfo;

public interface HtfLinkInfoMapper {
	int deleteByPrimaryKey(Integer id);

	int insert(HtfLinkInfo record);

	int insertSelective(HtfLinkInfo record);

	HtfLinkInfo selectByPrimaryKey(Integer id);

	List<HtfLinkInfo> selectAll();

	int updateByPrimaryKeySelective(HtfLinkInfo record);

	int updateByPrimaryKey(HtfLinkInfo record);
}