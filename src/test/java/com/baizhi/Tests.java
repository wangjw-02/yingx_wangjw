package com.baizhi;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import com.baizhi.dao.CategoryDAO;
import com.baizhi.dao.UserDao;
import com.baizhi.entity.User;
import com.baizhi.po.CategoryPo;
import org.apache.poi.ss.usermodel.Workbook;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = YingxWangjwApplication.class)
public class Tests {



   @Autowired
   private CategoryDAO categoryDAO;
   @Autowired
   private UserDao userDao;

   @Test
   public void test1(){
      List<CategoryPo> categoryPos = categoryDAO.queryAllCategory();
      for (CategoryPo categoryPo : categoryPos) {
         System.out.println(categoryPo);
      }
   }
   @Test
   public void test2(){
      List<User> users = userDao.queryAll(new User());

      ExportParams exportParams = new ExportParams("adasd", "sdsd");

      Workbook workbook = ExcelExportUtil.exportExcel(exportParams, User.class, users);
      try {
         workbook.write(new FileOutputStream(new File("E:\\aaa.xls")));
         workbook.close();
      } catch (IOException e) {
         e.printStackTrace();
      }
   }
}
