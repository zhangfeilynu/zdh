package com.htf.zdh.controller;

import com.htf.zdh.controller.vo.Result;
import com.htf.zdh.jdbc.po.ChandaoBug;
import com.htf.zdh.jdbc.po.UseCasesWithBLOBs;
import com.htf.zdh.service.ChandaoBugService;
import com.htf.zdh.service.UseCasesService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName:UseCasesController类
 * @Description:TODO
 * @Author wb-zzwb
 * @Date 2021/6/17 15:47
 * @Version 1.0
 **/
@Api(tags ="上传用例数据存入数据库")
@RestController
@RequestMapping("useCases")
public class UseCasesController {
    @Autowired
    UseCasesService useCasesService;

    @ApiOperation(value = "导入用例数据", notes = "导入用例数据")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "file", value = "文件", required = true, paramType = "form", dataType = "file") })
    @RequestMapping(value="/useCasesImport",method= RequestMethod.POST)
    @ResponseBody
    public Result<UseCasesWithBLOBs> itilUpload(@RequestParam("file") MultipartFile file, HttpServletRequest request) throws Exception {
        return useCasesService.UseCasesFileImport(file,request);
    }
}
