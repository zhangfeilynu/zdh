package com.htf.zdh.service.bo;

import java.io.Serializable;
import java.util.List;

import com.htf.zdh.jdbc.po.AppInfoList;

public class AppInfoListBo implements Serializable {

	private static final long serialVersionUID = -4084863574906471865L;

	private Integer pageNum;// 第几页
	private Integer pageSize;// 页面size
	private Long total;// 总数

	private List<AppInfoList> list;

	public Integer getPageNum() {
		return pageNum;
	}

	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Long getTotal() {
		return total;
	}

	public void setTotal(Long total) {
		this.total = total;
	}

	public List<AppInfoList> getList() {
		return list;
	}

	public void setList(List<AppInfoList> list) {
		this.list = list;
	}

}
