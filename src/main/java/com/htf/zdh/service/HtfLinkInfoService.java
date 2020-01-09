package com.htf.zdh.service;

import com.htf.zdh.jdbc.po.HtfLinkInfo;
import com.htf.zdh.service.bo.HtfLinkInfoBo;

public interface HtfLinkInfoService {

	public HtfLinkInfoBo selectAll(Integer pageNum, Integer pageSize);

	public HtfLinkInfo selectByPrimaryKey(Integer id);

	public int update(HtfLinkInfo record);

}
