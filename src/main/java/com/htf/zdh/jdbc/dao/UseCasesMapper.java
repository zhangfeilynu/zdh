package com.htf.zdh.jdbc.dao;

import com.htf.zdh.jdbc.po.ChandaoBug;
import com.htf.zdh.jdbc.po.UseCases;
import com.htf.zdh.jdbc.po.UseCasesWithBLOBs;

public interface UseCasesMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UseCasesWithBLOBs record);

    int insertSelective(UseCasesWithBLOBs record);

    UseCasesWithBLOBs selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UseCasesWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(UseCasesWithBLOBs record);

    int updateByPrimaryKey(UseCases record);

    UseCasesWithBLOBs selectByCaseNumber(String caseNumber);

    int updateByCaseNumber(UseCasesWithBLOBs record);
}