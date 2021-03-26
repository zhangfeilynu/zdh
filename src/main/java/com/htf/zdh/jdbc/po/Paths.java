package com.htf.zdh.jdbc.po;

import java.io.Serializable;

public class Paths implements Serializable {
    private static final long serialVersionUID = -7939816645847195177L;
    private Integer id;

    private String path;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path == null ? null : path.trim();
    }
}