package com.htf.zdh.service.impl;


import com.htf.zdh.common.Results;
import com.htf.zdh.jdbc.dao.TestDailyMapper;
import com.htf.zdh.jdbc.po.TestDaily;
import com.htf.zdh.service.TestDailyService;
import com.htf.zdh.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * @ClassName:TestReportServiceImpl类
 * @Description:TODO
 * @Author wb-zzwb
 * @Date 2021/6/3 17:20
 * @Version 1.0
 **/
@Service
public class TestDailyServiceImpl implements TestDailyService {
    private static final String EXCEL_XLS = "xls";
    private static final String EXCEL_XLSX = "xlsx";

    //EXCEL中每个字段的列位置
    private static final int SCRIPTID_COLUMN = 0;            //脚本ID
    private static final int DESCRIPTION_COLUMN = 1;                //脚本描述
    private static final int CHANNEL_COLUMN = 2;                //渠道
    private static final int EDITION_COLUMN = 3;        //版本
    private static final int FAIL_REASONS_COLUMN = 4;            //失败原因
    private static final int REMARKS_COLUMN = 5;        //备注
    private static final int BASIC_REASON_COLUMN = 6;        //根本原因

    @Autowired
    TestDailyMapper testDailyMapper;

    @Override
    public Results uploadFile(MultipartFile file, HttpServletRequest request) throws Exception {
        if (!(file.getOriginalFilename().endsWith(EXCEL_XLS) || file.getOriginalFilename().endsWith(EXCEL_XLSX))) {
            throw new Exception("文件不是Excel");
        }
        InputStream in = file.getInputStream();
        Workbook workbook = getWorkbok(in, file);
        Sheet sheet = workbook.getSheetAt(0); //获取第一个Sheet
        Long startTs = System.currentTimeMillis()/1000;
        TestDaily testDaily = new TestDaily();
        testDaily.setVersion(startTs.toString());
        String workDate=file.getOriginalFilename().replace(".xlsx","");
        testDaily.setWorkDate(workDate);
        List<TestDaily> oldTestDaily = testDailyMapper.findTestDailyByWorkDate(testDaily);
        if(oldTestDaily==null || oldTestDaily.size()<1){
            for (int i = 0; i <= sheet.getLastRowNum(); i++) {
                Row scriptInfoRow = sheet.getRow(i+1);
                if (checkRowEmpty(scriptInfoRow)) { continue;}
                addTestDaily(testDaily,file,scriptInfoRow);
            }
        }else{
            updateTestDaily(workDate,file);
            for (int i = 0; i <= sheet.getLastRowNum(); i++) {
                Row scriptInfoRow = sheet.getRow(i+1);
                if (checkRowEmpty(scriptInfoRow)) { continue;}
                addTestDaily(testDaily,file,scriptInfoRow);
            }
        }
        return Results.ok(200,"操作成功");
    }


    private String getCellValue(Row row, int i) {
        if(row.getCell(i) != null && row.getCell(i).toString().trim().length() != 0){
            row.getCell(i).setCellType(Cell.CELL_TYPE_STRING);
            return row.getCell(i).toString().trim();
        }
        return "";
    }


    private Boolean checkRowEmpty(Row row) {
        if(row == null || row.getCell(0).toString().trim().length() == 0){
            return true;
        }
        return false;
    }

    private int addTestDaily(TestDaily testDaily,MultipartFile file,Row row) {
        testDaily.setScriptid(getCellValue(row, SCRIPTID_COLUMN));
        testDaily.setScriptDescription(getCellValue(row, DESCRIPTION_COLUMN));
        testDaily.setChannel(getCellValue(row, CHANNEL_COLUMN));//渠道
        testDaily.setEdition(getCellValue(row, EDITION_COLUMN)); //版本
        testDaily.setFailReasons(getCellValue(row, FAIL_REASONS_COLUMN)); //失败原因
        testDaily.setRemarks(getCellValue(row, REMARKS_COLUMN));//备注
        testDaily.setBasicReason(getCellValue(row, BASIC_REASON_COLUMN));
        testDaily.setIsDel(0);
        testDaily.setWorkDate(file.getOriginalFilename().replace(".xlsx",""));
        int i=testDailyMapper.insert(testDaily);
        return i;
    }

    private int updateTestDaily(String workDate,MultipartFile file) {
        int i=testDailyMapper.updateByWorkDate(workDate);
        return i;
    }



    /**
     * 判断Excel的版本,获取Workbook
     * @param in
     * @return
     * @throws IOException
     */
    public static Workbook getWorkbok(InputStream in,MultipartFile file) throws IOException {
        Workbook wb = null;
        if(file.getOriginalFilename().endsWith(EXCEL_XLS)){  //Excel 2003
            wb = new HSSFWorkbook(in);
        }else if(file.getOriginalFilename().endsWith(EXCEL_XLSX)){  // Excel 2007/2010
            wb = new XSSFWorkbook(in);
        }
        return wb;
    }

}