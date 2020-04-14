package com.baizhi;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import com.baizhi.entity.Student;
import org.apache.poi.ss.usermodel.Workbook;
import org.junit.Test;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author: wangjiangwei
 * @Date 2020/4/7 21:03
 * @Version 1.0
 */
public class EasyPoiTest {

    @Test
    public void test1(){
        List<Student> students = new ArrayList<>();
        students.add(new Student("1","张三",1,new Date(),new Date(),"src/main/webapp/headImg/15854667030261d994b364e6cbf1ebc55c983a1acc408.png"));
        students.add(new Student("2","李四",2,new Date(),new Date(),"src/main/webapp/headImg/15854667030261d994b364e6cbf1ebc55c983a1acc408.png"));
        students.add(new Student("3","王五",1,new Date(),new Date(),"src/main/webapp/headImg/15854667030261d994b364e6cbf1ebc55c983a1acc408.png"));
        students.add(new Student("4","赵六",2,new Date(),new Date(),"src/main/webapp/headImg/15854667030261d994b364e6cbf1ebc55c983a1acc408.png"));
        students.add(new Student("5","田七",1,new Date(),new Date(),"src/main/webapp/headImg/15854667030261d994b364e6cbf1ebc55c983a1acc408.png"));

        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams("java186", "学员"), Student.class, students);
        try {
            workbook.write(new FileOutputStream("E:\\easypoi.xls"));
            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
