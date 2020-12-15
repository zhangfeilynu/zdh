package com.htf.zdh.controller;

import com.htf.zdh.controller.vo.Result;
import com.htf.zdh.service.ApiVisitService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author zhangfei
 * @version 1.0.0
 * @className ApiVisitController.java
 * @description TODO
 * @createTime 2020/12/15 17:02
 */

@Api(tags = { "api访问历史" })
@RestController("api")
@RequestMapping(value = "api")
public class ApiVisitController {

    private static final Logger logger = LoggerFactory.getLogger(ApiVisitController.class);

    @Autowired
    private ApiVisitService apiVisitService;


    @ApiOperation(value = "统计接口访问次数", notes = "统计接口访问次数")
    @ApiImplicitParam(name = "uri", value = "uri，默认getQrCode（二维码下载）", required = false, paramType = "query", dataType = "string")
    @RequestMapping(value = "/count", method = { RequestMethod.GET })
    @ResponseBody
    public Result count(@RequestParam(required = false) String uri){
        Result result=new Result();
        result.setCode(1);
        result.setMessage("请求成功");
        if("".equals(uri)||null==uri){
            uri="getQrCode";
        }
        int count=apiVisitService.count(uri);
        result.setData(count);
        return result;
    }



}
