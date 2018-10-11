package com.example.demo.salary.controller;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.salary.domain.Salary;
import com.example.demo.salary.service.ISalaryService;

@RestController
@RequestMapping(value = "/salary/excel")
public class ExcelController {

    @Autowired
    private ISalaryService salaryService;

    //创建表头
    private void createTitle(HSSFWorkbook workbook,HSSFSheet sheet){
        HSSFRow row = sheet.createRow(0);
        //设置列宽，setColumnWidth的第二个参数要乘以256，这个参数的单位是1/256个字符宽度
        sheet.setColumnWidth(1,12*256);
        sheet.setColumnWidth(3,17*256);

        //设置为居中加粗
        HSSFCellStyle style = workbook.createCellStyle();
        HSSFFont font = workbook.createFont();
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        style.setFont(font);

        HSSFCell cell;
        cell = row.createCell(0);
        cell.setCellValue("员工编号");
        cell.setCellStyle(style);


        cell = row.createCell(1);
        cell.setCellValue("员工姓名");
        cell.setCellStyle(style);

        cell = row.createCell(2);
        cell.setCellValue("员工部门");
        cell.setCellStyle(style);

        cell = row.createCell(3);
        cell.setCellValue("员工职位");
        cell.setCellStyle(style);
        
        cell = row.createCell(4);
        cell.setCellValue("基本工资");
        cell.setCellStyle(style);
        
        cell = row.createCell(5);
        cell.setCellValue("绩效工资");
        cell.setCellStyle(style);
        
        cell = row.createCell(6);
        cell.setCellValue("月度工资");
        cell.setCellStyle(style);
        
        cell = row.createCell(7);
        cell.setCellValue("创建日期");
        cell.setCellStyle(style);
    }

    //生成salary表excel
    @GetMapping(value = "/getSalary")
    public String getUser(HttpServletResponse response) throws Exception{
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("薪酬表");
        createTitle(workbook,sheet);
		List<Salary> rows = salaryService.findAll();

        //设置日期格式
        HSSFCellStyle style = workbook.createCellStyle();
        style.setDataFormat(HSSFDataFormat.getBuiltinFormat("m/d/yy"));

        //新增数据行，并且设置单元格数据
        int rowNum=1;
        for(Salary salary:rows){
            HSSFRow row = sheet.createRow(rowNum);
            row.createCell(0).setCellValue(salary.getUserId());
            row.createCell(1).setCellValue(salary.getUserName());
            row.createCell(2).setCellValue(salary.getDepartment());
            row.createCell(3).setCellValue(salary.getPosition());
            row.createCell(4).setCellValue(salary.getBaseSalary());
            row.createCell(5).setCellValue(salary.getMeritPay());
            row.createCell(6).setCellValue(salary.getMonthlySalary());
            HSSFCell cell = row.createCell(7);
            cell.setCellValue(salary.getCreateTime());
            cell.setCellStyle(style);
            rowNum++;
        }

        String fileName = "薪酬表.xls";

        //生成excel文件
        buildExcelFile(fileName, workbook);

        //浏览器下载excel
        buildExcelDocument(fileName,workbook,response);

        return "download excel";
    }

    //生成excel文件
    protected void buildExcelFile(String filename,HSSFWorkbook workbook) throws Exception{
        FileOutputStream fos = new FileOutputStream(filename);
        workbook.write(fos);
        fos.flush();
        fos.close();
    }

    //浏览器下载excel
    protected void buildExcelDocument(String filename,HSSFWorkbook workbook,HttpServletResponse response) throws Exception{
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-Disposition", "attachment;filename="+URLEncoder.encode(filename, "utf-8"));
        OutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        outputStream.flush();
        outputStream.close();
    }


}
