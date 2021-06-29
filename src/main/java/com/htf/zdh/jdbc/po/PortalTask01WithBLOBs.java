package com.htf.zdh.jdbc.po;

public class PortalTask01WithBLOBs extends PortalTask01 {
    private String taskDescr;

    private String content;

    public String getTaskDescr() {
        return taskDescr;
    }

    public void setTaskDescr(String taskDescr) {
        this.taskDescr = taskDescr == null ? null : taskDescr.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }
}