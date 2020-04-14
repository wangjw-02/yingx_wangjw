package com.baizhi.controller;


import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import com.baizhi.common.CommonResult;
import com.baizhi.entity.User;
import com.baizhi.service.UserService;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * (User)表控制层
 *
 * @author makejava
 * @since 2020-03-28 15:02:46
 */
@Controller
@RequestMapping("user")
public class UserController {
    /**
     * 服务对象
     */
    @Resource
    private UserService userService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public User selectOne(String id) {
        return this.userService.queryById(id);
    }

    @ResponseBody
    @RequestMapping("queryAllByPage")
    public Map<String,Object> queryAllByPage(Integer rows, Integer page){
        Map<String, Object> stringObjectMap = userService.queryAllByPage(rows, page);

        return stringObjectMap;
    }

    @ResponseBody
    @RequestMapping("dml")
    public String dml(User user,String oper,String[] id){
        String uuid=null;
        if ("add".equals(oper)){
            uuid= userService.insert(user);
        }else if("del".equals(oper)){
            userService.deleteById(user.getId());
        }else if("edit".equals(oper)){
            userService.updateStatus(user);
        }
        return uuid;
    }

    @ResponseBody
    @RequestMapping("fileUpload")
    public void fileUpload(String id, MultipartFile headImg, HttpServletRequest request){
        userService.fileUpload(id,headImg,request);
    }

    @RequestMapping("phoneCode")
    public String phoneCode(String phone){
        String message = userService.phoneCode(phone);
        return message;
    }

    @ResponseBody
    @RequestMapping("exportAllUser")
    public CommonResult exportAllUser(){
        try {
            List<User> users = userService.queryAllUser();
            File file = new File("E:\\excle\\");
            if (!file.exists()){
                file.mkdirs();
            }
            Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams("应学用户表", "应学用户表"), User.class, users);

            workbook.write(new FileOutputStream("E:\\excle\\应学用户表.xls"));
            workbook.close();

            return new CommonResult().success("导出成功");
        } catch (IOException e) {
            e.printStackTrace();
            return new CommonResult().success("导出失败");
        }
    }


}