package com.htf.zdh.service;

import com.htf.zdh.jdbc.po.AppInfoList;
import com.htf.zdh.jdbc.po.PortalTask01WithBLOBs;
import com.htf.zdh.service.bo.AppInfoListBo;
import com.htf.zdh.service.bo.PortalTaskBo;
import com.mysql.fabric.xmlrpc.base.Data;

public interface PortalTaskService {
    PortalTaskBo selectTestIn(String startTime, String endTime, Integer pageNum, Integer pageSize) throws Exception;

}
