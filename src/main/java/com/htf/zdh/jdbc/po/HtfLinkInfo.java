package com.htf.zdh.jdbc.po;

import java.util.Date;

public class HtfLinkInfo {
	private Integer id;

	private String functionType;

	private String functionName;

	private String linkDetails;

	private String linkStatus;

	private String remark;

	private String createdBy;

	private String updatedBy;

	private String createdDate;

	private Date updatedDate;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFunctionType() {
		return functionType;
	}

	public void setFunctionType(String functionType) {
		this.functionType = functionType == null ? null : functionType.trim();
	}

	public String getFunctionName() {
		return functionName;
	}

	public void setFunctionName(String functionName) {
		this.functionName = functionName == null ? null : functionName.trim();
	}

	public String getLinkDetails() {
		return linkDetails;
	}

	public void setLinkDetails(String linkDetails) {
		this.linkDetails = linkDetails == null ? null : linkDetails.trim();
	}

	public String getLinkStatus() {
		return linkStatus;
	}

	public void setLinkStatus(String linkStatus) {
		this.linkStatus = linkStatus == null ? null : linkStatus.trim();
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy == null ? null : createdBy.trim();
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy == null ? null : updatedBy.trim();
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}

}