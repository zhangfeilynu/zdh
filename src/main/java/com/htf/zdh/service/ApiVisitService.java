package com.htf.zdh.service;

import com.htf.zdh.jdbc.po.ApiVisit;

/**
 * @author zhangfei
 * @version 1.0.0
 * @className ApiVisitService.java
 * @description TODO
 * @createTime 2020/12/15 16:38
 */
public interface ApiVisitService {
  public  int insert(String uri);
  public int count(String uri);
}
