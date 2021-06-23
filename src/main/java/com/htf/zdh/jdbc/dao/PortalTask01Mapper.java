package com.htf.zdh.jdbc.dao;

import com.htf.zdh.jdbc.po.AppInfoList;
import com.htf.zdh.jdbc.po.PortalTask01;
import com.htf.zdh.jdbc.po.PortalTask01WithBLOBs;
import com.htf.zdh.service.bo.PortalTaskBo;
import com.mysql.fabric.xmlrpc.base.Data;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PortalTask01Mapper {
    int deleteByPrimaryKey(String taskid);

    int insert(PortalTask01WithBLOBs record);

    int insertSelective(PortalTask01WithBLOBs record);

    PortalTask01WithBLOBs selectByPrimaryKey(String taskid);

    int updateByPrimaryKeySelective(PortalTask01WithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(PortalTask01WithBLOBs record);

    int updateByPrimaryKey(PortalTask01 record);

    List<PortalTask01WithBLOBs> selectTasks(@Param("taskDescr") String taskDes, @Param("startTime") String startTime, @Param("endTime") String endTime);
}