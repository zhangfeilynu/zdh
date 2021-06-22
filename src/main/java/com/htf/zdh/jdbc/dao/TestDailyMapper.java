package com.htf.zdh.jdbc.dao;

import com.htf.zdh.jdbc.po.TestDaily;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TestDailyMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TestDaily record);

    int insertSelective(TestDaily record);

    TestDaily selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TestDaily record);

    int updateByPrimaryKey(TestDaily record);

    List<TestDaily> findTestDailyByWorkDate(TestDaily record);

    int updateByWorkDate(String workDate);
}