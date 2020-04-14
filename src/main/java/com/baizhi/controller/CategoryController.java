package com.baizhi.controller;


import com.baizhi.common.CommonResult;
import com.baizhi.entity.Category;
import com.baizhi.po.CategoryPo;
import com.baizhi.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * @Author: wangjiangwei
 * @Date 2020/3/30 16:50
 * @Version 1.0
 */
@Controller
@RequestMapping("category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @RequestMapping("queryFirst")
    @ResponseBody
    public Map<String,Object> queryAllFirst(Integer page, Integer rows){
        Map<String, Object> stringObjectMap = categoryService.queryAllFirst(page, rows);
        return stringObjectMap;
    }
    @RequestMapping("querySecond")
    @ResponseBody
    public Map<String,Object> querySecond(String parId,Integer page,Integer rows){
        Map<String, Object> stringObjectMap = categoryService.queryAllSecond(parId, page, rows);
        return stringObjectMap;
    }

    @ResponseBody
    @RequestMapping("dmlCategory")
    public String dmlCategory(Category category, String oper){
        String message=null;
        if ("add".equals(oper)){
            categoryService.addCategory(category);
        }else if ("del".equals(oper)){
            message = categoryService.delCategory(category);
        }else if ("edit".equals(oper)){
            categoryService.updateCategory(category);
        }

        return message;

    }
    /**
     * 查询所有的二级类别
     */
    @ResponseBody
    @RequestMapping("queryAllTwo")
    public List<Category> queryAllTwo(){
        List<Category> categories = categoryService.queryAllTwo();
        return categories;
    }



}
