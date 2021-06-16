package com.htf.zdh.service.impl;


import com.htf.zdh.common.Results;
import com.htf.zdh.controller.vo.Result;
import com.htf.zdh.jdbc.dao.TestDailyMapper;
import com.htf.zdh.jdbc.po.TestDaily;
import com.htf.zdh.service.TestDailyService;
import com.htf.zdh.service.bo.AppInfoBo;
import com.htf.zdh.utils.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private static final Logger logger = LoggerFactory.getLogger(TestDailyServiceImpl.class);
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
    public Result<TestDaily> uploadFile(MultipartFile file, HttpServletRequest request) {
        Result<TestDaily> result=new Result<TestDaily>();
        if (file.isEmpty()) {
            result.setCode(221);
            result.setMessage("请选择文件");
            return result;
        }
        if (!(file.getOriginalFilename().endsWith(EXCEL_XLS) || file.getOriginalFilename().endsWith(EXCEL_XLSX))) {
//            throw new Exception("文件不是Excel");
            logger.warn("文件不是Excel");
            result.setCode(500);
            result.setMessage("文件不是Excel");
            return result;
        }
        try{
            InputStream in = file.getInputStream();
            Workbook workbook = getWorkbok(in, file);
            Sheet sheet = workbook.getSheetAt(0); //获取第一个Sheet
            //获取表头标题行数据
            String row1=sheet.getRow(0).getCell(0).toString();
            String row2=sheet.getRow(0).getCell(1).toString();
            String row3=sheet.getRow(0).getCell(2).toString();
            String row4=sheet.getRow(0).getCell(3).toString();
            String row5=sheet.getRow(0).getCell(4).toString();
            String row6=sheet.getRow(0).getCell(5).toString();
            String row7=sheet.getRow(0).getCell(6).toString();
            //比较数据格式是否一致
            if(!"脚本ID".equals(row1)||!"脚本描述".equals(row2)||!"渠道".equals(row3)||!"版本".equals(row4)
                    ||!"失败原因".equals(row5)||!"备注".equals(row6)||!"根本原因".equals(row7)){
                result.setCode(202);
                result.setMessage("选择的文件不是测试日报数据格式!");
                logger.error("此文件不是测试日报数据格式!");
                return result;
            }
            TestDaily testDaily = new TestDaily();
            Long startTs = System.currentTimeMillis()/1000;//版本号为10位的时间戳
            testDaily.setVersion(startTs.toString());
            String workDate=file.getOriginalFilename().replace(".xlsx","");
            testDaily.setWorkDate(workDate);
            //根据标号列WorkDate查找 测试日报
            List<TestDaily> oldTestDaily = testDailyMapper.findTestDailyByWorkDate(testDaily);
            //没有该测试日报就直接添加数据，否则修改isDel状态为1，再添加数据
            if(oldTestDaily==null || oldTestDaily.size()<1){
                for (int i = 0; i <= sheet.getLastRowNum(); i++) {
                    Row scriptInfoRow = sheet.getRow(i+1);
                    if (checkRowEmpty(scriptInfoRow)) { continue;}
                    int num = addTestDaily(testDaily, file, scriptInfoRow);
                    if(num>0){
                        result.setCode(200);
                        result.setMessage("测试日报导入数据库成功");
                    }
                }
            }else{
                updateTestDaily(workDate,file);
                for (int i = 0; i <= sheet.getLastRowNum(); i++) {
                    Row scriptInfoRow = sheet.getRow(i+1);
                    if (checkRowEmpty(scriptInfoRow)) { continue;}
                    int num = addTestDaily(testDaily, file, scriptInfoRow);
                    if(num>0){
                        result.setCode(200);
                        result.setMessage("测试日报导入数据库成功");
                    }
                }
            }
            return result;
        } catch (Exception e) {
            logger.warn("上传文件出现异常：" + e.getMessage());
            result.setCode(203);
            result.setMessage("上传文件出现异常");
            return result;
        }
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