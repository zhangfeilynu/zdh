package com.htf.zdh.jdbc.po;

import java.io.Serializable;
import java.util.Date;

public class ApiVisit  implements Serializable {
    private static final long serialVersionUID = 904269594616202860L;
    private Integer id;

    private Date createTime;

    private String uri;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri == null ? null : uri.trim();
    }
}