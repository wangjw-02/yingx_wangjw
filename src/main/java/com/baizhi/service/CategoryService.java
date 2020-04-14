package com.baizhi.service;

import com.baizhi.entity.Category;
import com.baizhi.po.CategoryPo;

import java.util.List;
import java.util.Map;

public interface CategoryService {

    Map<String,Object> queryAllFirst(Integer page, Integer rows);


    Map<String,Object> queryAllSecond(String parId,Integer page,Integer rows);

    /**
     * 添加类别
     */
    void addCategory(Category category);

    /**
     * 删除某一类别
     * @param category
     * @return
     */
    String delCategory(Category category);

    /**
     * 根据id修改某一类别信息
     * @param category
     */
    void updateCategory(Category category);

    /**
     * 查询所有的二级类别
     * @return
     */
    List<Category> queryAllTwo();



    /**
     * 前台查询所有类别
     * @return
     */
    List<CategoryPo> queryAllCategory();
}
