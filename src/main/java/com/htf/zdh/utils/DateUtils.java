package com.htf.zdh.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {

	public static void main(String[] args) {
		// Calendar cal = Calendar.getInstance();
		// int y = cal.get(Calendar.YEAR);
		// int m = cal.get(Calendar.MONTH) + 1;
		// int d = cal.get(Calendar.DATE);
		// int h = cal.get(Calendar.HOUR_OF_DAY);
		// int mi = cal.get(Calendar.MINUTE);
		// int s = cal.get(Calendar.SECOND);
		//
		// System.out.println(m + "月" + d + "日");
		//
		// System.out.println("现在时刻是" + y + "年" + m + "月" + d + "日" + h + "时" + mi + "分"
		// + s + "秒");
		//
		// System.out.println(getWeek());
		String s = "2019-10-15";

		String[] sArray = s.split("-");
		System.out.println(getDateStr1());

	}

	// 获取当前日期，格式2019-10-15

	public static String getDateStr1() {
		Calendar cal = Calendar.getInstance();
		int y = cal.get(Calendar.YEAR);
		int m = cal.get(Calendar.MONTH) + 1;
		int d = cal.get(Calendar.DATE);
		return y + "-" + m + "-" + d;
	}

	// 截取日期2019-10-14，转变为10月14日
	public static String getDateStr2(String dateStr) {
		String[] sArray = dateStr.split("-");
		if (sArray.length != 3)
			return null;
		return sArray[1] + "月" + sArray[2] + "日";

	}

	// 截取日期2019-10-14，转变为10-14
	public static String getDateStr3(String dateStr) {
		String[] sArray = dateStr.split("-");
		if (sArray.length != 3)
			return null;
		return sArray[1] + "-" + sArray[2];

	}

	// 获取当前小时数
	public static int getHour() {
		Calendar cal = Calendar.getInstance();
		return cal.get(Calendar.HOUR_OF_DAY);

	}

	// 获取今天是星期几
	public static String getWeek() {
		Calendar cal = Calendar.getInstance();
		int week = cal.get(Calendar.DAY_OF_WEEK);
		String weekArr = new String("日一二三四五六");
		String weekIndexStr = weekArr.substring(week - 1, week);
		return "星期" + weekIndexStr;
	}

	/**
	 * 根据日期获取 星期 （2019-05-06 ——> 星期一）
	 * 
	 * @param datetime
	 * @return
	 */
	public static String dateToWeek(String datetime) {

		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
		String[] weekDays = { "星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六" };
		Calendar cal = Calendar.getInstance();
		Date date;
		try {
			date = f.parse(datetime);
			cal.setTime(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		// 一周的第几天
		int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
		if (w < 0)
			w = 0;
		return weekDays[w];
	}

}
