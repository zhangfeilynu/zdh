package com.htf.zdh.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.htf.zdh.jdbc.dao.AppInfoListMapper;
import com.htf.zdh.jdbc.po.AppInfoList;
import com.htf.zdh.service.bo.AppInfoListBo;

@Service
public class AppInfoServiceImpl implements AppInfoService {

	private static final Logger logger = LoggerFactory.getLogger(AppInfoServiceImpl.class);

	@Autowired
	private AppInfoListMapper appInfoListMapper;

	@Override
	public AppInfoListBo selectApps(AppInfoList appInfoList, Integer pageNum, Integer pageSize) {

		AppInfoListBo result = new AppInfoListBo();
		// 设置分页
		PageHelper.startPage(pageNum, pageSize);
		result.setPageNum(pageNum);
		result.setPageSize(pageSize);

		List<AppInfoList> list = appInfoListMapper.selectApps(appInfoList);

		/*
		 * if (list == null || list.size() < 1) { return null; }
		 */

		// 获取总记录数
		PageInfo<AppInfoList> pageInfo = new PageInfo<AppInfoList>(list);
		Long total = pageInfo.getTotal();
		result.setTotal(total);

		// 数据处理
		List<AppInfoList> results = new ArrayList<>();
		// 如果pageNmu超过最后一页
		if (pageNum > pageInfo.getLastPage()) {
			result.setList(results);
			return result;
		}
		// 转换数据
		for (AppInfoList item : list) {
			results.add(item);
		}
		result.setList(results);
		return result;

	}

}
