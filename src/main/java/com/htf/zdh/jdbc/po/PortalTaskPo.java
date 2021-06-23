package com.htf.zdh.jdbc.po;

/**
 * @ClassName:PortalTaskPo类
 * @Description:TODO
 * @Author wb-zzwb
 * @Date 2021/6/22 15:34
 * @Version 1.0
 **/
public class PortalTaskPo {
    private String taskDescr;//任务名称

    private String startTime;//开始时间

    private String endTime;//结束时间

    private Integer totalCases;//总用例数

    private Integer successNumber;//成功数

    private Integer failNumber;//失败数

    private String successRate;//运行成功率

    private String remarks;//备注

    public String getTaskDescr() {
        return taskDescr;
    }

    public void setTaskDescr(String taskDescr) {
        this.taskDescr = taskDescr;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public Integer getTotalCases() {
        return totalCases;
    }

    public void setTotalCases(Integer totalCases) {
        this.totalCases = totalCases;
    }

    public Integer getSuccessNumber() {
        return successNumber;
    }

    public void setSuccessNumber(Integer successNumber) {
        this.successNumber = successNumber;
    }

    public Integer getFailNumber() {
        return failNumber;
    }

    public void setFailNumber(Integer failNumber) {
        this.failNumber = failNumber;
    }

    public String getSuccessRate() {
        return successRate;
    }

    public void setSuccessRate(String successRate) {
        this.successRate = successRate;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
