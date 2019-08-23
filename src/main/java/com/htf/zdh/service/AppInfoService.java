package com.htf.zdh.service;

import com.htf.zdh.jdbc.po.AppInfoList;
import com.htf.zdh.service.bo.AppInfoListBo;

public interface AppInfoService {

	public AppInfoListBo selectApps(AppInfoList appInfoList, Integer pageNum, Integer pageSize);

}
