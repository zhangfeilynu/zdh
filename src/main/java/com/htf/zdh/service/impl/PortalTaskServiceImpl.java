package com.htf.zdh.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.htf.zdh.jdbc.dao.PortalTask01Mapper;
import com.htf.zdh.jdbc.po.PortalTask01WithBLOBs;
import com.htf.zdh.jdbc.po.PortalTaskPo;
import com.htf.zdh.service.PortalTaskService;
import com.htf.zdh.service.bo.PortalTaskBo;
import io.restassured.path.json.JsonPath;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @ClassName:ProtalTaskServiceImpl类
 * @Description:TODO
 * @Author wb-zzwb
 * @Date 2021/6/21 11:03
 * @Version 1.0
 **/
@Service
public class PortalTaskServiceImpl implements PortalTaskService {

    private static final Logger logger = LoggerFactory.getLogger(PortalTaskServiceImpl.class);
    @Autowired
    PortalTask01Mapper portalTask01Mapper;

    @Override
    public PortalTaskBo selectTestIn(String taskDescr, String startTime,String endTime, Integer pageNum, Integer pageSize) {
        PortalTaskBo result = new PortalTaskBo();
        // 设置分页
        PageHelper.startPage(pageNum, pageSize);
        result.setPageNum(pageNum);
        result.setPageSize(pageSize);

        List<PortalTask01WithBLOBs> list = portalTask01Mapper.selectTasks(taskDescr,startTime,endTime);

        // 获取总记录数
        PageInfo<PortalTask01WithBLOBs> pageInfo = new PageInfo<>(list);
        Long total = pageInfo.getTotal();
        result.setTotal(total);

        // 数据处理
        List<PortalTask01WithBLOBs> results = new ArrayList<>();

        List<PortalTaskPo> protalTaskPoList = new ArrayList<PortalTaskPo>();
        // 如果pageNmu超过最后一页
        if (pageNum > pageInfo.getLastPage()) {
            result.setResultList(protalTaskPoList);
            return result;
        }

        // 转换数据
        for (PortalTask01WithBLOBs item : list) {
            PortalTaskPo portalTaskPo=new PortalTaskPo();
            //results.add(item);
            portalTaskPo.setTaskDescr(item.getTaskDescr());
            logger.info("content::"+item.getContent());
            JsonPath jsonPath = new JsonPath(item.getContent());
            //获取开始时间
            String sTime = jsonPath.getString("createtime");
            SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String createtime = sdf.format(new Date(Long.parseLong(sTime)));
            portalTaskPo.setStartTime(createtime);
            //获取结束时间
            String eTime=jsonPath.getString("finishTime");
            String endTimes = sdf.format(new Date(Long.parseLong(eTime)));
            portalTaskPo.setEndTime(endTimes);

            //获取总用例数
            Integer totalCases=jsonPath.getInt("scriptTotal");
            logger.info("scriptTotal::"+totalCases);
            portalTaskPo.setTotalCases(totalCases);
            //获取成功数
            Integer successNumber=jsonPath.getInt("taskSummary.testResult[0].val");
            logger.info("successNumber::"+successNumber);
            portalTaskPo.setSuccessNumber(successNumber);
            //获取失败数
            if(jsonPath.getString("taskSummary.testResult[1].val")==null){
                logger.info("fail 0::"+0);
                portalTaskPo.setFailNumber(0);
            }
            if(jsonPath.getString("taskSummary.testResult[1].val")!=null){
                int failNumber=jsonPath.getInt("taskSummary.testResult[1].val");
                logger.info("failNumber::"+failNumber);
                portalTaskPo.setFailNumber(failNumber);
            }
            if(jsonPath.getString("taskSummary.testResult[2].val")!=null){
                int failNumber1=jsonPath.getInt("taskSummary.testResult[1].val");
                int failNumber2=jsonPath.getInt("taskSummary.testResult[2].val");
                int failSum=failNumber1+failNumber2;
                logger.info("failNumber::"+failNumber1+failNumber2);
                portalTaskPo.setFailNumber(failSum);
            }
            if(jsonPath.getString("taskSummary.testResult[3].val")!=null){
                int failNumber1=jsonPath.getInt("taskSummary.testResult[1].val");
                int failNumber2=jsonPath.getInt("taskSummary.testResult[2].val");
                int failNumber3=jsonPath.getInt("taskSummary.testResult[3].val");
                int failSum=failNumber1+failNumber2+failNumber3;
                logger.info("failSum::"+failSum);
                portalTaskPo.setFailNumber(failSum);
            }

            //计算成功率
            DecimalFormat df = new DecimalFormat("0.00");
            String s = df.format((float)successNumber/totalCases*100);
            portalTaskPo.setSuccessRate(s+"%");
            logger.info("xxxx::"+portalTaskPo.getSuccessRate());
            protalTaskPoList.add(portalTaskPo);

        }
//        result.setPortalTask01WithBLOBsList(results);
        result.setResultList(protalTaskPoList);
        return result;
    }
}
