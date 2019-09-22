package com.htf.zdh.service;

public interface IdCardService {

	public String generate(Integer areaCcode, String birth);// 生成随机身份证号码，birth指的是出生年月日

	public String generate2(Integer areaCcode, String year);// 生成随机身份证号码，year，指的是出生年份

}
