package com.htf.zdh.service.impl;

import com.htf.zdh.common.Results;
import com.htf.zdh.controller.vo.Result;
import com.htf.zdh.jdbc.dao.ChandaoBugMapper;
import com.htf.zdh.jdbc.po.ChandaoBug;
import com.htf.zdh.jdbc.po.TestDaily;
import com.htf.zdh.service.ChandaoBugService;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.List;

/**
 * @ClassName:ChandaoBugServiceImpl类
 * @Description:TODO
 * @Author wb-zzwb
 * @Date 2021/6/10 14:29
 * @Version 1.0
 **/
@Service
public class ChandaoBugServiceImpl implements ChandaoBugService {
    private static final Logger logger = LoggerFactory.getLogger(ChandaoBugServiceImpl.class);
    private static final String EXCEL_XLS = "xls";
    private static final String EXCEL_XLSX = "xlsx";

    //EXCEL中每个字段的列位置
    private static final int BUG_NUMBER_COLUMN = 0;            //bug编号
    private static final int BUG_TITLE_COLUMN = 1;             //bug标题
    private static final int BUG_STATUS_COLUMN = 2;            //bug状态
    private static final int IS_SURE_COLUMN = 3;        //是否确认
    private static final int CREATED_COLUMN = 4;            //由谁创建
    private static final int CREATE_DATE_COLUMN = 5;        //创建日期
    private static final int ASSIGNED_COLUMN = 6;        //指派给
    private static final int ASSIGNED_DATE_COLUMN = 7;    //指派日期
    private static final int SOLVERS_COLUMN = 8;        //解决者
    private static final int SOLUTION_COLUMN = 9;            //解决方案
    private static final int SOLUTION_VERSION_COLUMN = 10;        //解决版本
    private static final int SETTLEMENT_DATE_COLUMN = 11;        //解决日期
    private static final int FUNCTIONAL_MODULE_COLUMN = 12;        //功能模块
    private static final int REMARKS_COLUMN = 13;        //备注

    @Autowired
    ChandaoBugMapper chandaoBugMapper;

    @Override
    public Result<ChandaoBug> uploadChandaoBugFile(MultipartFile file, HttpServletRequest request){
        Result<ChandaoBug> result=new Result<ChandaoBug>();
        if (file.isEmpty()) {
            result.setCode(221);
            result.setMessage("请选择文件");
            return result;
        }
        if (!(file.getOriginalFilename().endsWith(EXCEL_XLS) || file.getOriginalFilename().endsWith(EXCEL_XLSX))) {
            logger.warn("文件不是Excel");
        }
        try {
            InputStream in = file.getInputStream();
            Workbook workbook = getWorkbok(in, file);
            Sheet sheet = workbook.getSheetAt(0); //获取第一个Sheet
            //获取表头(标题）
            String row0=sheet.getRow(0).getCell(0).toString();
            String row1=sheet.getRow(0).getCell(1).toString();
            String row2=sheet.getRow(0).getCell(2).toString();
            String row3=sheet.getRow(0).getCell(3).toString();
            String row4=sheet.getRow(0).getCell(4).toString();
            String row5=sheet.getRow(0).getCell(5).toString();
            String row6=sheet.getRow(0).getCell(6).toString();
            String row7=sheet.getRow(0).getCell(7).toString();
            String row8=sheet.getRow(0).getCell(8).toString();
            String row9=sheet.getRow(0).getCell(9).toString();
            String row10=sheet.getRow(0).getCell(10).toString();
            String row11=sheet.getRow(0).getCell(11).toString();
            String row12=sheet.getRow(0).getCell(12).toString();
            String row13=sheet.getRow(0).getCell(13).toString();
            logger.info(row0+row1+row2+row3+row4+row5+row6);
            //比较数据格式是否一致
            if(!"Bug编号".equals(row0)||!"Bug标题".equals(row1)||!"Bug状态".equals(row2)||!"是否确认".equals(row3)
                    ||!"由谁创建".equals(row4)||!"创建日期".equals(row5)||!"指派给".equals(row6)|| !"指派日期".equals(row7)
                    ||!"解决者".equals(row8)||!"解决方案".equals(row9)|| !"解决版本".equals(row10)
                    |!"解决日期".equals(row11)||!"功能模块".equals(row12)||!"备注".equals(row13)){
                result.setCode(202);
                result.setMessage("上传文件不是禅道bug数据格式!");
                logger.error("上传文件不是禅道bug数据格式!");
                return result;
            }
            ChandaoBug chandaoBug = new ChandaoBug();
            for (int i = 0; i <= sheet.getLastRowNum(); i++) {
                Row scriptInfoRow = sheet.getRow(i+1);
                if (checkRowEmpty(scriptInfoRow)) { continue;}
                Boolean isAddChandaoBug = true;
                //获取excel中的Bug编号
                String bugNumber=getCellValue(scriptInfoRow, BUG_NUMBER_COLUMN);
                chandaoBug.setBugNumber(bugNumber);
                //根据编号查询
                ChandaoBug oldChandaoBug= chandaoBugMapper.selectByBugNumber(bugNumber);
                //如果数据库中此数据不为空，就根据当前excel的数据修改
                if(oldChandaoBug!=null){
                    chandaoBug.setBugNumber(oldChandaoBug.getBugNumber());
                    chandaoBug.setBugTitle(getCellValue(scriptInfoRow,BUG_TITLE_COLUMN));
                    chandaoBug.setBugStatus(getCellValue(scriptInfoRow,BUG_STATUS_COLUMN));
                    chandaoBug.setIsSure(getCellValue(scriptInfoRow,IS_SURE_COLUMN));
                    chandaoBug.setCreated(getCellValue(scriptInfoRow,CREATED_COLUMN));
                    chandaoBug.setCreateDate(getCellValue(scriptInfoRow,CREATE_DATE_COLUMN));
                    chandaoBug.setAssigned(getCellValue(scriptInfoRow,ASSIGNED_COLUMN));
                    chandaoBug.setAssignedDate(getCellValue(scriptInfoRow,ASSIGNED_DATE_COLUMN));
                    chandaoBug.setSolvers(getCellValue(scriptInfoRow,SOLVERS_COLUMN));
                    chandaoBug.setSolution(getCellValue(scriptInfoRow,SOLUTION_COLUMN));
                    chandaoBug.setSolutionVersion(getCellValue(scriptInfoRow,SOLUTION_VERSION_COLUMN));
                    chandaoBug.setSettlementDate(getCellValue(scriptInfoRow,SETTLEMENT_DATE_COLUMN));
                    chandaoBug.setFunctionalModule(getCellValue(scriptInfoRow,FUNCTIONAL_MODULE_COLUMN));
                    chandaoBug.setRemarks(getCellValue(scriptInfoRow,REMARKS_COLUMN));
                    int num = updateChandaoBug(chandaoBug, file);
                    if(num>0){
                        result.setCode(200);
                        result.setMessage("禅道bug数据导入数据库成功");
                    }
                    isAddChandaoBug = false;
                }
                //添加数据
                if (isAddChandaoBug) {
                    int num = addChandaoBug(chandaoBug, file, scriptInfoRow);
                    if(num>0){
                        result.setCode(200);
                        result.setMessage("禅道bug数据导入数据库成功");
                    }
                }
            }
            return result;
        }catch (Exception e) {
            logger.warn("上传文件出现异常：" + e.getMessage());
            result.setCode(203);
            result.setMessage("上传文件出现异常");
            return result;
        }
    }

    private int addChandaoBug(ChandaoBug chandaoBug, MultipartFile file, Row row) {
        chandaoBug.setBugNumber(getCellValue(row, BUG_NUMBER_COLUMN));
        chandaoBug.setBugTitle(getCellValue(row,BUG_TITLE_COLUMN));
        chandaoBug.setBugStatus(getCellValue(row,BUG_STATUS_COLUMN));
        chandaoBug.setIsSure(getCellValue(row,IS_SURE_COLUMN));
        chandaoBug.setCreated(getCellValue(row,CREATED_COLUMN));
        chandaoBug.setCreateDate(getCellValue(row,CREATE_DATE_COLUMN));
        chandaoBug.setAssigned(getCellValue(row,ASSIGNED_COLUMN));
        chandaoBug.setAssignedDate(getCellValue(row,ASSIGNED_DATE_COLUMN));
        chandaoBug.setSolvers(getCellValue(row,SOLVERS_COLUMN));
        chandaoBug.setSolution(getCellValue(row,SOLUTION_COLUMN));
        chandaoBug.setSolutionVersion(getCellValue(row,SOLUTION_VERSION_COLUMN));
        chandaoBug.setSettlementDate(getCellValue(row,SETTLEMENT_DATE_COLUMN));
        chandaoBug.setFunctionalModule(getCellValue(row,FUNCTIONAL_MODULE_COLUMN));
        chandaoBug.setRemarks(getCellValue(row,REMARKS_COLUMN));
        int i=chandaoBugMapper.insert(chandaoBug);
        return i;
    }

    private int updateChandaoBug(ChandaoBug chandaoBug, MultipartFile file) {
        int i=chandaoBugMapper.updateByBugNumber(chandaoBug);
        return i;
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

    /**
     * 判断Excel的版本,获取Workbook
     * @param in
     * @return
     * @throws IOException
     */
    public static Workbook getWorkbok(InputStream in, MultipartFile file) throws IOException {
        Workbook wb = null;
        if(file.getOriginalFilename().endsWith(EXCEL_XLS)){  //Excel 2003
            wb = new HSSFWorkbook(in);
        }else if(file.getOriginalFilename().endsWith(EXCEL_XLSX)){  // Excel 2007/2010
            wb = new XSSFWorkbook(in);
        }
        return wb;
    }
}
