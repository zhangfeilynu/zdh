package com.htf.zdh.service.bo;

import com.htf.zdh.jdbc.po.PortalTask01WithBLOBs;
import com.htf.zdh.jdbc.po.PortalTaskPo;

import java.io.Serializable;
import java.util.List;

/**
 * @ClassName:PortalTaskBo类
 * @Description:TODO
 * @Author wb-zzwb
 * @Date 2021/6/21 14:48
 * @Version 1.0
 **/
public class PortalTaskBo implements Serializable {
    private static final long serialVersionUID = -4084863574906471865L;

    private Integer pageNum;// 第几页
    private Integer pageSize;// 页面size
    private Long total;// 总数

    private List<PortalTaskPo> resultList;

//    private List<PortalTask01WithBLOBs> portalTask01WithBLOBsList;

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public List<PortalTaskPo> getResultList() {
        return resultList;
    }

    public void setResultList(List<PortalTaskPo> resultList) {
        this.resultList = resultList;
    }

//    public List<PortalTask01WithBLOBs> getPortalTask01WithBLOBsList() {
//        return portalTask01WithBLOBsList;
//    }
//
//    public void setPortalTask01WithBLOBsList(List<PortalTask01WithBLOBs> portalTask01WithBLOBsList) {
//        this.portalTask01WithBLOBsList = portalTask01WithBLOBsList;
//    }
}
