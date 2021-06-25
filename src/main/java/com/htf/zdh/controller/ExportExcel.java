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
public class ExportExcel {
    @Autowired
    PortalTaskService portalTaskService;

    @ApiOperation(value = "testIn导出", notes = "testIn导出")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "taskDescr", value = "任务名称", required = false, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "startTime", value = "开始时间1", required = false, paramType = "query", dataType = "Data"),
            @ApiImplicitParam(name = "endTime", value = "开始时间2", required = false, paramType = "query", dataType = "Data"),
            @ApiImplicitParam(name = "pageNum", value = "页码", required = true, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "pageSize", value = "每页显示的数量", required = true, paramType = "query", dataType = "int") })
    @RequestMapping(value = "/exportExcel",method = RequestMethod.GET)
    @ResponseBody
    public void export(HttpServletRequest request,HttpServletResponse response) throws Exception {
        String taskDescr = request.getParameter("taskDescr");
        String startTime = request.getParameter("startTime");
        String endTime = request.getParameter("endTime");
        //获取需要导出的数据
        PortalTaskBo portalTaskBo = portalTaskService.selectTestIn(taskDescr,startTime,endTime,1,20);
        WriteCellStyle headWriteCellStyle = new WriteCellStyle();
        //设置头居中
        headWriteCellStyle.setHorizontalAlignment(HorizontalAlignment.CENTER);
        //内容策略
        WriteCellStyle contentWriteCellStyle = new WriteCellStyle();
        //设置 水平居中
        contentWriteCellStyle.setHorizontalAlignment(HorizontalAlignment.LEFT);
        HorizontalCellStyleStrategy horizontalCellStyleStrategy = new HorizontalCellStyleStrategy(headWriteCellStyle, contentWriteCellStyle);
        response.setContentType("application/vnd.ms-excel;charset=utf-8");
        //response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setCharacterEncoding("utf-8");
        // 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
        String fileName = URLEncoder.encode("testIn信息表", "UTF-8");
        response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xls");
        // 这里需要设置不关闭流
        EasyExcel.write(response.getOutputStream(), TaskExcel.class).autoCloseStream(Boolean.FALSE).registerWriteHandler(horizontalCellStyleStrategy).sheet("testIn信息表").doWrite(portalTaskBo.getResultList());



//        String taskDescr = request.getParameter("taskDescr");
//        String startTime = request.getParameter("startTime");
//        String endTime = request.getParameter("endTime");
//        PortalTaskBo portalTaskBo = portalTaskService.selectTestIn(taskDescr,startTime,endTime,1,100);
//        ServletOutputStream out = response.getOutputStream();
//        ExcelWriter writer = new ExcelWriter(out, ExcelTypeEnum.XLSX, true);
//        String fileName = "测试exportExcel";
//        WriteSheet sheet = new WriteSheet();
//        //设置自适应宽度
//        sheet.setAutoTrim(Boolean.TRUE);
//        // 第一个 sheet 名称
//        sheet.setSheetName("第一个sheet");
//        writer.write(portalTaskBo.getResultList(), sheet);
//        //通知浏览器以附件的形式下载处理，设置返回头要注意文件名有中文
//        response.setHeader("Content-disposition", "attachment;filename=" + new String( fileName.getBytes("gb2312"), "ISO8859-1" ) + ".xlsx");
//        writer.finish();
//        response.setContentType("multipart/form-data");
//        response.setCharacterEncoding("utf-8");
//        out.flush();
    }

}
