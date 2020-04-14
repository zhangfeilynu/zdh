package com.htf.zdh.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.htf.zdh.jdbc.dao.PathsMapper;
import com.htf.zdh.service.CommonService;

@Service
public class CommonServiceImpl implements CommonService {
	private static final Logger logger = LoggerFactory.getLogger(CommonServiceImpl.class);

	@Autowired
	private PathsMapper pathsMapper;

	@Override
	public String[] selectPaths() {
		return pathsMapper.selectPaths();
	}

}
