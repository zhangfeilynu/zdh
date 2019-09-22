package com.htf.zdh.service.bo;

import java.io.Serializable;

public class AppInfoBo implements Serializable {

	private static final long serialVersionUID = -4092913755552100581L;

	private String env;
	// private String type;
	private String version;
	private String remark;
	private String autotest;

	public String getEnv() {
		return env;
	}

	public String getAutotest() {
		return autotest;
	}

	public void setAutotest(String autotest) {
		this.autotest = autotest;
	}

	public void setEnv(String env) {
		this.env = env;
	}

	// public String getType() {
	// return type;
	// }
	//
	// public void setType(String type) {
	// this.type = type;
	// }

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}
