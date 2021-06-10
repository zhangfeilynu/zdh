package com.htf.zdh.controller;

import com.htf.zdh.common.Results;
import com.htf.zdh.service.ChandaoBugService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName:ChandaoBugController类
 * @Description:TODO
 * @Author wb-zzwb
 * @Date 2021/6/10 14:36
 * @Version 1.0
 **/
@Api(tags = "上传禅道数据存入数据库")
@RestController
@RequestMapping("chandaoBug")
public class ChandaoBugController {

    @Autowired
    ChandaoBugService chandaoBugService;

    @ApiOperation(value = "导入禅道数据", notes = "导入禅道数据")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "file", value = "文件", required = true, paramType = "form", dataType = "file") })
    @RequestMapping(value="/chandaoUpload",method= RequestMethod.POST)
    @ResponseBody
    public Results itilUpload(@RequestParam("file") MultipartFile file, HttpServletRequest request) throws Exception {
        return chandaoBugService.uploadChandaoBugFile(file,request);
    }

}
