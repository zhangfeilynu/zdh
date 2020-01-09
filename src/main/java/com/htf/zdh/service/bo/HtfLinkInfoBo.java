package com.htf.zdh.service.bo;

import java.io.Serializable;
import java.util.List;

import com.htf.zdh.jdbc.po.HtfLinkInfo;

public class HtfLinkInfoBo implements Serializable {

	private static final long serialVersionUID = -46336942079851804L;

	private Integer pageNum;// 第几页
	private Integer pageSize;// 页面size
	private Long total;// 总数

	private List<HtfLinkInfo> list;

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

	public List<HtfLinkInfo> getList() {
		return list;
	}

	public void setList(List<HtfLinkInfo> list) {
		this.list = list;
	}

}
