package com.htf.zdh.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.htf.zdh.jdbc.dao.TradingDayMapper;
import com.htf.zdh.utils.DateUtils;

@Service
public class TradingServiceImpl implements TradingService {
	private static final Logger logger = LoggerFactory.getLogger(TradingServiceImpl.class);

	@Autowired
	private TradingDayMapper tradingDayMapper;

	@Override
	public String getTradingDay(int tag) {

		String todayStr = DateUtils.getDateStr1();
		int h = 0;
		h = DateUtils.getHour();
		int flag = 0;
		flag = tradingDayMapper.getFlag();
		logger.info(todayStr + "是否交易日：" + flag);
		if (0 == tag) {// T+0基金
			String date01 = DateUtils.getDateStr2(todayStr);// 购买页预计确认份额时间
			String dateStr01 = "";// 预计首笔交易到账时间
			if (1 == flag && h < 15) {
				logger.info("交易日15点前购买T0");
				dateStr01 = tradingDayMapper.selectTradingDay(todayStr, 0);// 下一个交易日
			} else {
				logger.info("交易日15点后购买T0，或者非交易日购买T0");
				dateStr01 = tradingDayMapper.selectTradingDay(todayStr, 1);// 下下一个交易日
			}
			String result01 = "";
			result01 = "a预计将于" + date01 + "确认份额b" + "c首笔收益到账时间" + dateStr01 + "d买入时间" + todayStr;

			return result01;

		}
		String dateStr = "";
		if (1 == tag) {// T+1基金
			if (1 == flag && h < 15) {// 交易日15点前购买
				logger.info("交易日15点前购买T1");
				dateStr = tradingDayMapper.selectTradingDay(todayStr, 0);// 下一个交易日
			} else {// 非交易日买，或者交易日15点之后买
				logger.info("交易日15点后购买T1，或者非交易日购买T1");
				dateStr = tradingDayMapper.selectTradingDay(todayStr, 1);// 下下个交易日
			}
		}
		if (2 == tag) {// T+2基金
			if (1 == flag && h < 15) {// 交易日15点前购买
				logger.info("交易日15点前购买T2");
				dateStr = tradingDayMapper.selectTradingDay(todayStr, 1);// 下下个交易日
			} else {// 非交易日买，或者交易日15点之后买
				logger.info("交易日15点后购买T2，或者非交易日购买T2");
				dateStr = tradingDayMapper.selectTradingDay(todayStr, 2);// 下下下个交易日
			}
		}
		String date1 = "";// 购买页预计确认份额时间
		date1 = DateUtils.getDateStr2(dateStr);
		String date2 = "";// 交易明细页确认时间，格式为10-15
		date2 = DateUtils.getDateStr3(dateStr);
		String week = DateUtils.dateToWeek(dateStr);
		String result = "";
		result = "a预计将于" + date1 + "确认份额b，成功页c" + dateStr + "d日进行确认，明细页e预计" + date2 + " " + week + " " + "确认份额f明细买入时间"
				+ todayStr;
		return result;
	}

	@Override
	public String getTradingDay2(int tag) {//app6.30接口重构，返回日期格式变更
		String todayStr = DateUtils.getDateStr1();
		int h = 0;
		h = DateUtils.getHour();
		int flag = 0;
		flag = tradingDayMapper.getFlag();
		logger.info(todayStr + "是否交易日：" + flag);
		if (0 == tag) {// T+0基金
			String date01 = DateUtils.getDateStr4(todayStr);// 购买页预计确认份额时间
			String dateStr01 = "";// 预计首笔交易到账时间
			if (1 == flag && h < 15) {
				logger.info("交易日15点前购买T0");
				dateStr01 = tradingDayMapper.selectTradingDay(todayStr, 0);// 下一个交易日
			} else {
				logger.info("交易日15点后购买T0，或者非交易日购买T0");
				dateStr01 = tradingDayMapper.selectTradingDay(todayStr, 1);// 下下一个交易日
			}
			String result01 = "";
			result01 = "a预计将于" + date01 + "确认份额b" + "c首笔收益到账时间" + dateStr01 + "d买入时间" + todayStr;

			return result01;

		}
		String dateStr = "";
		if (1 == tag) {// T+1基金
			if (1 == flag && h < 15) {// 交易日15点前购买
				logger.info("交易日15点前购买T1");
				dateStr = tradingDayMapper.selectTradingDay(todayStr, 0);// 下一个交易日
			} else {// 非交易日买，或者交易日15点之后买
				logger.info("交易日15点后购买T1，或者非交易日购买T1");
				dateStr = tradingDayMapper.selectTradingDay(todayStr, 1);// 下下个交易日
			}
		}
		if (2 == tag) {// T+2基金
			if (1 == flag && h < 15) {// 交易日15点前购买
				logger.info("交易日15点前购买T2");
				dateStr = tradingDayMapper.selectTradingDay(todayStr, 1);// 下下个交易日
			} else {// 非交易日买，或者交易日15点之后买
				logger.info("交易日15点后购买T2，或者非交易日购买T2");
				dateStr = tradingDayMapper.selectTradingDay(todayStr, 2);// 下下下个交易日
			}
		}
		String date1 = "";// 购买页预计确认份额时间
		date1 = DateUtils.getDateStr4(dateStr);
		String date2 = "";// 交易明细页确认时间，格式为10-15
		date2 = DateUtils.getDateStr3(dateStr);
		String week = DateUtils.dateToWeek(dateStr);
		String result = "";
		result = "a预计将于" + date1 + "确认份额b，成功页c" + dateStr + "d日进行确认，明细页e预计" + date2 + " " + week + " " + "确认份额f明细买入时间"
				+ todayStr;
		return result;
	}

	@Override
	public int isTradingDay() {
		logger.info("测试jacoco增量覆盖");
		return tradingDayMapper.getFlag();
	}

}
