package com.htf.zdh.jdbc.po;

import java.io.Serializable;
import java.util.Date;

public class TradingDay implements Serializable {
    private static final long serialVersionUID = 1643596225268437971L;
    private Integer id;

    private Date dateStr;

    private Integer flag;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDateStr() {
        return dateStr;
    }

    public void setDateStr(Date dateStr) {
        this.dateStr = dateStr;
    }

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }
}