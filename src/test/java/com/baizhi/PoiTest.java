package com.baizhi;

import com.baizhi.entity.Admin;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.*;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: wangjiangwei
 * @Date 2020/4/7 19:17
 * @Version 1.0
 */
public class PoiTest {
    @Test
    public void test1(){
        // 创建一个Excel表格
        Workbook workbook = new HSSFWorkbook();

        // 创建一个工作簿  参数：sheet名字(sheet1，sheet2...)
        // 不写参数，默认sheet1，sheet2...
        Sheet workbookSheet = workbook.createSheet("186-wangjw");

        // 创建一行  参数：行下标，从0下标开始
        Row row = workbookSheet.createRow(0);

        // 创建一个单元格  单元格下标，从0开始
        Cell cell = row.createCell(0);

        //  给单元格设置内容
        cell.setCellValue("第一行第一列");
        // 导出单元格
        try {
            workbook.write(new FileOutputStream(new File("E:\\testpoi.xls")));
            // 释放资源
            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    @Test
    public void test2(){

        List<Admin> admins = new ArrayList<>();
        admins.add(new Admin("1","测试一","123456","12221"));
        admins.add(new Admin("2","测试二","3626262","12221"));
        admins.add(new Admin("3","测试三","986586","12221"));
        admins.add(new Admin("4","测试四","5151","12221"));
        // 创建一个Excel表格
        HSSFWorkbook workbook = new HSSFWorkbook();

        // 创建一个工作簿  参数：sheet名字(sheet1，sheet2...)
        // 不写参数，默认sheet1，sheet2...
        HSSFSheet workbookSheet = workbook.createSheet("186-wangjw");

        // 构建字体
        HSSFFont font = workbook.createFont();
        font.setBold(true); // 字体加粗
        font.setColor(Font.COLOR_RED); // 字体颜色
        font.setFontHeightInPoints((short) 10); // 字体字号
        font.setFontName("宋体"); // 字体
        font.setItalic(true); // 斜体
        font.setUnderline(FontFormatting.U_SINGLE); // 下划线

        // 创建标题行
        HSSFRow row = workbookSheet.createRow(0);

        String[] title = {"id","姓名","密码","验证码","日期"};


        // 处理单元格对象
        HSSFCell hssfCell=null;

        // 创建样式对象
        CellStyle cellStyle =workbook.createCellStyle();
        cellStyle.setFont(font); // 将字体样式引入
        cellStyle.setAlignment(HorizontalAlignment.CENTER); // 文字居中
        // 设置日期格式
        HSSFDataFormat dataFormat = workbook.createDataFormat();
        cellStyle.setDataFormat(dataFormat.getFormat("yyyy年MM月dd日"));





        for (int i = 0; i < title.length; i++) {
            hssfCell=row.createCell(i);
            hssfCell.setCellValue(title[i]);
        }
        // 设置行高
        row.setHeight((short) 250);

        // 处理数据行
        for (int i = 0; i < admins.size(); i++) {
            // 每一个对象创建一行数据
            HSSFRow row1 = workbookSheet.createRow(i + 1);
            row1.createCell(0).setCellValue(admins.get(i).getId());
            row1.createCell(1).setCellValue(admins.get(i).getName());
            row1.createCell(2).setCellValue(admins.get(i).getPassword());
            row1.createCell(3).setCellValue(admins.get(i).getEnCode());

            HSSFCell cell = row1.createCell(4);

            cell.setCellStyle(cellStyle);
        }
        // 设置单元格宽度  参数：列索引，列宽
        workbookSheet.setColumnWidth(4,15*256);




        // 导出单元格
        try {
            workbook.write(new FileOutputStream(new File("E:\\testpoi.xls")));
            // 释放资源
            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    @Test
    public void test3(){
        /*
         *  表格导入
         */

        try {
            // 获取要导入的文件
            HSSFWorkbook workbook = new HSSFWorkbook(new FileInputStream(new File("E:\\testpoi.xls")));

            // 获取工作簿
            HSSFSheet sheet = workbook.getSheet("186-wangjw");

            for (int i = 1; i < sheet.getLastRowNum(); i++) {
                Admin admin = new Admin();

                // 获取行
                HSSFRow row = sheet.getRow(i);

                // 获取id
                admin.setId(row.getCell(0).getStringCellValue());
                // 获取name
                admin.setName(row.getCell(1).getStringCellValue());
                // 获取密码
                admin.setPassword(row.getCell(2).getStringCellValue());
                // 获取验证码
                admin.setEnCode(row.getCell(3).getStringCellValue());

                // 调用存入数据库方法
                System.out.println(admin);

            }

            // 关闭资源
            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    @Test
    public void yyyyy(){
        Map<String,Object> map = new HashMap<>();
        map.put("id","1");
        Object name = map.get("name");
        boolean name1 = map.containsKey("name");
        System.out.println(name1);
    }


}
