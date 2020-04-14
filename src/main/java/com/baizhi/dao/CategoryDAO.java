package com.baizhi.dao;

import com.baizhi.entity.Category;
import com.baizhi.po.CategoryPo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CategoryDAO {


    /**
     * @param offset
     * @param limit
     * @return 所有的一级类别
     */
    List<Category> queryAllFirst(@Param("offset") Integer offset, @Param("limit") Integer limit);


    /**
     * 查询某一级类别下的所有二级类别
     */
    List<Category> queryAllSecond(@Param("parId") String parId,@Param("offset") Integer offset,@Param("limit") Integer limit);

    /**
     *
     * @return 查询一级类别的数量
     */
    Integer queryAllFirstCount();

    /**
     * 查询某一级类别下的二级类别数量
     */
    Integer queryAllSecondCount(String parId);

    /**
     * 添加类别
     * @param category
     */
    void addCategory(Category category);

    /**
     * 删除某个类别
     * @param category
     */
    void delCategory(Category category);

    /**
     * 根据id修改类别信息
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
