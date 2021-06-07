package com.htf.zdh.jdbc.po;

import java.util.Date;

public class TestDaily {
    private Integer id;

    private String scriptid;

    private String scriptDescription;

    private String channel;

    private String edition;

    private String failReasons;

    private String remarks;

    private String basicReason;

    private String workDate;

    private Date createTime;

    private Integer isDel;

    private String version;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getScriptid() {
        return scriptid;
    }

    public void setScriptid(String scriptid) {
        this.scriptid = scriptid == null ? null : scriptid.trim();
    }

    public String getScriptDescription() {
        return scriptDescription;
    }

    public void setScriptDescription(String scriptDescription) {
        this.scriptDescription = scriptDescription == null ? null : scriptDescription.trim();
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel == null ? null : channel.trim();
    }

    public String getEdition() {
        return edition;
    }

    public void setEdition(String edition) {
        this.edition = edition == null ? null : edition.trim();
    }

    public String getFailReasons() {
        return failReasons;
    }

    public void setFailReasons(String failReasons) {
        this.failReasons = failReasons == null ? null : failReasons.trim();
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks == null ? null : remarks.trim();
    }

    public String getBasicReason() {
        return basicReason;
    }

    public void setBasicReason(String basicReason) {
        this.basicReason = basicReason == null ? null : basicReason.trim();
    }

    public String getWorkDate() {
        return workDate;
    }

    public void setWorkDate(String workDate) {
        this.workDate = workDate == null ? null : workDate.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getIsDel() {
        return isDel;
    }

    public void setIsDel(Integer isDel) {
        this.isDel = isDel;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version == null ? null : version.trim();
    }
}