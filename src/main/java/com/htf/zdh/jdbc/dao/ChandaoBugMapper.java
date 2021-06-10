package com.htf.zdh.jdbc.dao;

import com.htf.zdh.jdbc.po.ChandaoBug;
import jdk.internal.dynalink.linker.LinkerServices;

import java.util.List;

public interface ChandaoBugMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ChandaoBug record);

    int insertSelective(ChandaoBug record);

    ChandaoBug selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ChandaoBug record);

    int updateByPrimaryKey(ChandaoBug record);

    int updateByBugNumber(ChandaoBug record);

    ChandaoBug selectByBugNumber(String bugNumber);

    List<ChandaoBug> selectChandaoBug();

}