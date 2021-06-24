package com.htf.zdh.controller;

import com.htf.zdh.controller.vo.Result;
import com.htf.zdh.jdbc.po.AppInfoList;
import com.htf.zdh.jdbc.po.PortalTask01WithBLOBs;
import com.htf.zdh.service.PortalTaskService;
import com.htf.zdh.service.bo.AppInfoListBo;
import com.htf.zdh.service.bo.PortalTaskBo;
import com.mysql.fabric.xmlrpc.base.Data;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @ClassName:PortalTaskController类
 * @Description:TODO
 * @Author wb-zzwb
 * @Date 2021/6/21 15:38
 * @Version 1.0
 **/
@Api(tags = { "testIn数据" })
@RestController("testIn")
@RequestMapping(value = "testIn")
public class PortalTaskController {
    private static final Logger logger = LoggerFactory.getLogger(PortalTaskController.class);

    @Autowired
    PortalTaskService portalTaskService;

    @ApiOperation(value = "查询testIn数据", notes = "查询testIn数据")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "taskDescr", value = "任务名称", required = false, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "startTime", value = "开始时间1", required = false, paramType = "query", dataType = "Data"),
            @ApiImplicitParam(name = "endTime", value = "开始时间2", required = false, paramType = "query", dataType = "Data"),
            @ApiImplicitParam(name = "pageNum", value = "页码", required = true, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "pageSize", value = "每页显示的数量", required = true, paramType = "query", dataType = "int") })
    @RequestMapping(value = "/tasklist", method = { RequestMethod.GET })
    @ResponseBody
    public Result<PortalTaskBo> getTaskInList(@RequestParam(required = false) String taskDescr,
                                                    @RequestParam(required = false) String startTime, @RequestParam(required = false) String endTime,
                                                    @RequestParam Integer pageNum, @RequestParam Integer pageSize) throws Exception{
        Result<PortalTaskBo> result = new Result<PortalTaskBo>();
        result.setCode(1);
        result.setMessage("查询成功");
        PortalTaskBo portalTaskBo = portalTaskService.selectTestIn(taskDescr,startTime,endTime,pageNum,pageSize);
        result.setData(portalTaskBo);
        return result;
    }
}
