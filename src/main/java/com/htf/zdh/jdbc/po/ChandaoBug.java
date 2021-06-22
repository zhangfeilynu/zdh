package com.htf.zdh.jdbc.po;

public class ChandaoBug {
    private Integer id;

    private String bugNumber;

    private String bugTitle;

    private String bugStatus;

    private String isSure;

    private String created;

    private String createDate;

    private String assigned;

    private String assignedDate;

    private String solvers;

    private String solution;

    private String solutionVersion;

    private String settlementDate;

    private String functionalModule;

    private String remarks;

    public ChandaoBug() {
    }

    public String getFunctionalModule() {
        return functionalModule;
    }

    public void setFunctionalModule(String functionalModule) {
        this.functionalModule = functionalModule;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBugNumber() {
        return bugNumber;
    }

    public void setBugNumber(String bugNumber) {
        this.bugNumber = bugNumber;
    }

    public String getBugTitle() {
        return bugTitle;
    }

    public void setBugTitle(String bugTitle) {
        this.bugTitle = bugTitle == null ? null : bugTitle.trim();
    }

    public String getBugStatus() {
        return bugStatus;
    }

    public void setBugStatus(String bugStatus) {
        this.bugStatus = bugStatus == null ? null : bugStatus.trim();
    }

    public String getIsSure() {
        return isSure;
    }

    public void setIsSure(String isSure) {
        this.isSure = isSure == null ? null : isSure.trim();
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created == null ? null : created.trim();
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate == null ? null : createDate.trim();
    }

    public String getAssigned() {
        return assigned;
    }

    public void setAssigned(String assigned) {
        this.assigned = assigned == null ? null : assigned.trim();
    }

    public String getAssignedDate() {
        return assignedDate;
    }

    public void setAssignedDate(String assignedDate) {
        this.assignedDate = assignedDate == null ? null : assignedDate.trim();
    }

    public String getSolvers() {
        return solvers;
    }

    public void setSolvers(String solvers) {
        this.solvers = solvers == null ? null : solvers.trim();
    }

    public String getSolution() {
        return solution;
    }

    public void setSolution(String solution) {
        this.solution = solution == null ? null : solution.trim();
    }

    public String getSolutionVersion() {
        return solutionVersion;
    }

    public void setSolutionVersion(String solutionVersion) {
        this.solutionVersion = solutionVersion == null ? null : solutionVersion.trim();
    }

    public String getSettlementDate() {
        return settlementDate;
    }

    public void setSettlementDate(String settlementDate) {
        this.settlementDate = settlementDate == null ? null : settlementDate.trim();
    }


}