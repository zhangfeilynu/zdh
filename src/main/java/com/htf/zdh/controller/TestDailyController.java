package com.htf.zdh.controller;

import com.htf.zdh.common.Results;
import com.htf.zdh.controller.vo.Result;
import com.htf.zdh.service.TestDailyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName:UploadTestReportController类
 * @Description:TODO
 * @Author wb-zzwb
 * @Date 2021/6/3 16:58
 * @Version 1.0
 **/
@Api(tags = "上传测试日报存入数据库")
@RestController
@RequestMapping("testDaily")
public class TestDailyController {
    private static final Logger logger= LoggerFactory.getLogger(TestDailyController.class);


    @Autowired
    TestDailyService testDailyService;

    /**
     * 设备ITIL信息上传
     * @return
     */

    @ApiOperation(value = "导入测试日报", notes = "导入测试日报")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "file", value = "文件", required = true, paramType = "form", dataType = "file") })
    @RequestMapping(value="/testDailyUpload",method= RequestMethod.POST)
    @ResponseBody
    public Results itilUpload(@RequestParam("file") MultipartFile file, HttpServletRequest request) throws Exception {
       return testDailyService.uploadFile(file,request);
    }



}
