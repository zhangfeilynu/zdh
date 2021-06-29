package com.htf.zdh.jdbc.po;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.*;
import lombok.Data;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;

/**
 * @ClassName:Task类
 * @Description:TODO
 * @Author wb-zzwb
 * @Date 2021/6/25 15:14
 * @Version 1.0
 **/
@Data
@ExcelIgnoreUnannotated //忽视无注解的属性
@ContentRowHeight(20) //文本高度
@HeadRowHeight(20) //标题高度
public class TaskExcel{
    @ColumnWidth(20) //列宽
    @ExcelProperty(value =  {"任务名称"}, index = 0)
    private String taskDescr;
    @ColumnWidth(30) //列宽
    @ExcelProperty(value = {"开始时间"}, index = 1)
    private String startTime;
    @ColumnWidth(30) //列宽
    @ExcelProperty(value = {"结束时间"}, index = 2)
    private String endTime;
    @ColumnWidth(30) //列宽
    @ExcelProperty(value = {"总用例数"}, index = 3)
    private Integer totalCases;
    @ColumnWidth(20) //列宽
    @ExcelProperty(value = {"成功数"}, index = 4)
    private Integer successNumber;
    @ColumnWidth(20) //列宽
    @ExcelProperty(value = {"失败数"}, index = 5)
    private Integer failNumber;
    @ColumnWidth(20) //列宽
    @ExcelProperty(value = {"运行成功率"}, index = 6)
    private String successRate;
    @ColumnWidth(20) //列宽
    @ExcelProperty(value = {"备注"}, index = 7)
    private String remarks;

    public TaskExcel(String taskDescr, String startTime, String endTime, Integer totalCases, Integer successNumber, Integer failNumber, String successRate, String remarks) {
        this.taskDescr = taskDescr;
        this.startTime = startTime;
        this.endTime = endTime;
        this.totalCases = totalCases;
        this.successNumber = successNumber;
        this.failNumber = failNumber;
        this.successRate = successRate;
        this.remarks = remarks;
    }
}
