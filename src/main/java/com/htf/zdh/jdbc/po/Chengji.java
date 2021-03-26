package com.htf.zdh.jdbc.po;

import java.io.Serializable;

public class Chengji implements Serializable {
    private static final long serialVersionUID = 2283635765489529147L;
    private Byte id;

    private String name;

    private String kecheng;

    private Integer fenshu;

    public Byte getId() {
        return id;
    }

    public void setId(Byte id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getKecheng() {
        return kecheng;
    }

    public void setKecheng(String kecheng) {
        this.kecheng = kecheng == null ? null : kecheng.trim();
    }

    public Integer getFenshu() {
        return fenshu;
    }

    public void setFenshu(Integer fenshu) {
        this.fenshu = fenshu;
    }
}