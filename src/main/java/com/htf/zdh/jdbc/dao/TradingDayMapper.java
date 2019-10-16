package com.htf.zdh.jdbc.dao;

import com.htf.zdh.jdbc.po.TradingDay;

public interface TradingDayMapper {
	int deleteByPrimaryKey(Integer id);

	int insert(TradingDay record);

	int insertSelective(TradingDay record);

	TradingDay selectByPrimaryKey(Integer id);

	String selectTradingDay(String dateStr, int no);// 根据时间dateStr，获取之后第no+1个交易日

	int getFlag();// 当天是否交易日

	int updateByPrimaryKeySelective(TradingDay record);

	int updateByPrimaryKey(TradingDay record);
}