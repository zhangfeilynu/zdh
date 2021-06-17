package com.htf.zdh.jdbc.po;

public class UseCasesWithBLOBs extends UseCases {
    private String step;

    private String expect;

    public String getStep() {
        return step;
    }

    public void setStep(String step) {
        this.step = step == null ? null : step.trim();
    }

    public String getExpect() {
        return expect;
    }

    public void setExpect(String expect) {
        this.expect = expect == null ? null : expect.trim();
    }
}