package com.htf.zdh.controller;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.write.metadata.style.WriteCellStyle;
import com.alibaba.excel.write.style.HorizontalCellStyleStrategy;
import com.htf.zdh.jdbc.po.PortalTaskPo;
import com.htf.zdh.jdbc.po.TaskExcel;
import com.htf.zdh.service.PortalTaskService;
import com.htf.zdh.service.bo.PortalTaskBo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;


/**
 * @ClassName:ExportExcel类
 * @Description:TODO
 * @Author wb-zzwb
 * @Date 2021/6/25 14:45
 * @Version 1.0
 **/
@Api(tags = { "testIn导出" })
@RestController("testIn导出")
@RequestMapping(value = "testIn")
public class ExportExcelController {
    @Autowired
    PortalTaskService portalTaskService;

    @ApiOperation(value = "testIn导出", notes = "testIn导出")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "startTime", value = "开始时间1", required = false, paramType = "query", dataType = "Data"),
            @ApiImplicitParam(name = "endTime", value = "开始时间2", required = false, paramType = "query", dataType = "Data")})
    @RequestMapping(value = "/exportExcel",method = RequestMethod.GET)
    @ResponseBody
    public void export(HttpServletRequest request,HttpServletResponse response) throws Exception {
        String startTime = request.getParameter("startTime");
        String endTime = request.getParameter("endTime");
        //获取需要导出的数据
        PortalTaskBo portalTaskBo = portalTaskService.selectTestIn(startTime,endTime,1,2520);
        WriteCellStyle headWriteCellStyle = new WriteCellStyle();
        //设置头居中
        headWriteCellStyle.setHorizontalAlignment(HorizontalAlignment.CENTER);
        //内容策略
        WriteCellStyle contentWriteCellStyle = new WriteCellStyle();
        //设置 水平居中
        contentWriteCellStyle.setHorizontalAlignment(HorizontalAlignment.LEFT);
        HorizontalCellStyleStrategy horizontalCellStyleStrategy = new HorizontalCellStyleStrategy(headWriteCellStyle, contentWriteCellStyle);
        response.setContentType("application/vnd.ms-excel;charset=utf-8");
        response.setCharacterEncoding("utf-8");
        // 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
        String fileName = URLEncoder.encode("testIn信息表", "UTF-8");
        response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xls");
        // 这里需要设置不关闭流
        EasyExcel.write(response.getOutputStream(), TaskExcel.class).autoCloseStream(Boolean.FALSE).registerWriteHandler(horizontalCellStyleStrategy).sheet("testIn信息表").doWrite(portalTaskBo.getResultList());
    }

}
