package com.htf.zdh.service.impl;

import com.htf.zdh.controller.vo.Result;
import com.htf.zdh.jdbc.dao.TestDailyMapper;
import com.htf.zdh.jdbc.dao.UseCasesMapper;
import com.htf.zdh.jdbc.po.ChandaoBug;
import com.htf.zdh.jdbc.po.UseCasesWithBLOBs;
import com.htf.zdh.service.UseCasesService;
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

/**
 * @ClassName:UseCasesServiceImpl类
 * @Description:TODO
 * @Author wb-zzwb
 * @Date 2021/6/17 15:45
 * @Version 1.0
 **/
@Service
public class UseCasesServiceImpl implements UseCasesService {
    private static final Logger logger = LoggerFactory.getLogger(UseCasesServiceImpl.class);
    private static final String EXCEL_XLS = "xls";
    private static final String EXCEL_XLSX = "xlsx";

    //EXCEL中每个字段的列位置
    private static final int CASE_NUMBER_COLUMN = 0;            //用例编号
    private static final int CASE_TITLE_COLUMN = 1;                //用例标题
    private static final int PRECONDITIONS_COLUMN = 2;                //前置条件
    private static final int STEP_COLUMN = 3;        //步骤
    private static final int EXPECT_COLUMN = 4;            //预期
    private static final int KEYWORD_COLUMN = 5;        //关键词
    private static final int FUNCTIONAL_MODULE_COLUMN = 6;        //功能模块
    private static final int REMARKS_COLUMN = 7;        //备注

    @Autowired
    UseCasesMapper useCasesMapper;

    /**
     * 用例导入数据库
     * @param file
     * @param request
     * @return
     */
    @Override
    public Result<UseCasesWithBLOBs> UseCasesFileImport(MultipartFile file, HttpServletRequest request) {
        Result<UseCasesWithBLOBs> result=new Result<UseCasesWithBLOBs>();
        if (!(file.getOriginalFilename().endsWith(EXCEL_XLS) || file.getOriginalFilename().endsWith(EXCEL_XLSX))) {
            logger.warn("文件不是Excel");
            result.setCode(500);
            result.setMessage("文件不是Excel");
            return result;
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
            //比较数据格式是否一致
            if(!"用例编号".equals(row0)||!"用例标题".equals(row1)||!"前置条件".equals(row2)||!"步骤".equals(row3)
                    ||!"预期".equals(row4)||!"关键词".equals(row5)||!"功能模块".equals(row6)|| !"备注".equals(row7)){
                result.setCode(202);
                result.setMessage("上传文件不是用例数据格式!");
                logger.warn("上传文件不是用例数据格式!");
                return result;
            }
            UseCasesWithBLOBs useCases = new UseCasesWithBLOBs();
            for (int i = 0; i <= sheet.getLastRowNum(); i++) {
                Row casesInfoRow = sheet.getRow(i+1);
                if (checkRowEmpty(casesInfoRow)) { continue;}
                //获取excel中的Bug编号
                String caseNumber=getCellValue(casesInfoRow, CASE_NUMBER_COLUMN);
                useCases.setCaseNumber(caseNumber);
                //根据编号查询
                UseCasesWithBLOBs oldUseCases = useCasesMapper.selectByCaseNumber(caseNumber);
                //如果数据库中此数据不为空，就根据当前excel的数据修改
                if(oldUseCases!=null){
                    useCases.setCaseNumber(oldUseCases.getCaseNumber());
                    useCases.setCaseTitle(getCellValue(casesInfoRow,CASE_TITLE_COLUMN));
                    useCases.setPreconditions(getCellValue(casesInfoRow,PRECONDITIONS_COLUMN));
                    useCases.setStep(getCellValue(casesInfoRow,STEP_COLUMN));
                    useCases.setExpect(getCellValue(casesInfoRow,EXPECT_COLUMN));
                    useCases.setKeyword(getCellValue(casesInfoRow,KEYWORD_COLUMN));
                    useCases.setFunctionalModule(getCellValue(casesInfoRow,FUNCTIONAL_MODULE_COLUMN));
                    useCases.setRemarks(getCellValue(casesInfoRow,REMARKS_COLUMN));
                    updateUseCases(useCases, file);
                }
                //添加数据
                else{
                    addUseCases(useCases, file, casesInfoRow);
                }
            }
            logger.info("用例数据导入数据库成功");
            result.setCode(200);
            result.setMessage("用例数据导入数据库成功");
            return result;
        }catch (Exception e) {
            logger.warn("文件中的表头数据不正确：" + e.getMessage());
            result.setCode(203);
            result.setMessage("文件中表头标题列不正确，正确格式为：用例编号 用例标题 前置条件 步骤 预期 关键字 功能模块 备注");
            return result;
        }
    }


    private int updateUseCases(UseCasesWithBLOBs useCasesWithBLOBs, MultipartFile file) {
        return useCasesMapper.updateByCaseNumber(useCasesWithBLOBs);
    }

    private int addUseCases(UseCasesWithBLOBs useCases, MultipartFile file, Row row) {
        useCases.setCaseNumber(getCellValue(row, CASE_NUMBER_COLUMN));
        useCases.setCaseTitle(getCellValue(row,CASE_TITLE_COLUMN));
        useCases.setPreconditions(getCellValue(row,PRECONDITIONS_COLUMN));
        useCases.setStep(getCellValue(row,STEP_COLUMN));
        useCases.setExpect(getCellValue(row,EXPECT_COLUMN));
        useCases.setKeyword(getCellValue(row,KEYWORD_COLUMN));
        useCases.setFunctionalModule(getCellValue(row,FUNCTIONAL_MODULE_COLUMN));
        useCases.setRemarks(getCellValue(row,REMARKS_COLUMN));
        return useCasesMapper.insert(useCases);
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
