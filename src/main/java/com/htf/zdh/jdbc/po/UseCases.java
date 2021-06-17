package com.htf.zdh.jdbc.po;

public class UseCases {
    private Integer id;

    private String caseNumber;

    private String caseTitle;

    private String preconditions;

    private String keyword;

    private String functionalModule;

    private String remarks;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCaseNumber() {
        return caseNumber;
    }

    public void setCaseNumber(String caseNumber) {
        this.caseNumber = caseNumber;
    }

    public String getCaseTitle() {
        return caseTitle;
    }

    public void setCaseTitle(String caseTitle) {
        this.caseTitle = caseTitle == null ? null : caseTitle.trim();
    }

    public String getPreconditions() {
        return preconditions;
    }

    public void setPreconditions(String preconditions) {
        this.preconditions = preconditions == null ? null : preconditions.trim();
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword == null ? null : keyword.trim();
    }

    public String getFunctionalModule() {
        return functionalModule;
    }

    public void setFunctionalModule(String functionalModule) {
        this.functionalModule = functionalModule == null ? null : functionalModule.trim();
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks == null ? null : remarks.trim();
    }
}