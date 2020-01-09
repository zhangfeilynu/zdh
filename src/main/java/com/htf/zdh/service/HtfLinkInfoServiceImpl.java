package com.htf.zdh.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.htf.zdh.jdbc.dao.HtfLinkInfoMapper;
import com.htf.zdh.jdbc.po.AppInfoList;
import com.htf.zdh.jdbc.po.HtfLinkInfo;
import com.htf.zdh.service.bo.HtfLinkInfoBo;
import com.htf.zdh.utils.DateUtils;

@Service
public class HtfLinkInfoServiceImpl implements HtfLinkInfoService {
	private static final Logger logger = LoggerFactory.getLogger(HtfLinkInfoServiceImpl.class);

	@Autowired
	private HtfLinkInfoMapper htfLinkInfoMapper;

	@Override
	public HtfLinkInfoBo selectAll(Integer pageNum, Integer pageSize) {

		if (pageNum == null || pageNum.toString().equals("")) {
			pageNum = 1;
		}
		if (pageSize == null || pageSize.toString().equals("")) {
			pageSize = 10;
		}

		HtfLinkInfoBo result = new HtfLinkInfoBo();

		// 设置分页
		PageHelper.startPage(pageNum, pageSize);
		result.setPageNum(pageNum);
		result.setPageSize(pageSize);

		List<HtfLinkInfo> linkList = htfLinkInfoMapper.selectAll();

		// 获取总记录数
		PageInfo<HtfLinkInfo> pageInfo = new PageInfo<HtfLinkInfo>(linkList);
		Long total = pageInfo.getTotal();
		result.setTotal(total);

		// 数据处理
		List<HtfLinkInfo> results = new ArrayList<>();
		// 如果pageNmu超过最后一页
		if (pageNum > pageInfo.getLastPage()) {
			result.setList(results);
			return result;
		}
		// 转换数据
		for (HtfLinkInfo item : linkList) {
			results.add(item);
		}
		result.setList(results);

		return result;
	}

	@Override
	public HtfLinkInfo selectByPrimaryKey(Integer id) {
		return htfLinkInfoMapper.selectByPrimaryKey(id);
	}

	@Override
	public int update(HtfLinkInfo record) {
		if (record == null)
			return -999;

		if (record.getId() != null) {
			return htfLinkInfoMapper.updateByPrimaryKeySelective(record);
		} else {
			record.setCreatedDate(DateUtils.getYMDHMSStd());
			return htfLinkInfoMapper.insertSelective(record);
		}

	}

}
