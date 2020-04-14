package com.baizhi.service.impl;


import com.baizhi.annotation.AddCache;
import com.baizhi.annotation.DelCache;
import com.baizhi.dao.CategoryDAO;
import com.baizhi.entity.Category;
import com.baizhi.po.CategoryPo;
import com.baizhi.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * @Author wangjiangwei
 * @Date 2020/3/30 16:43
 * @Version 1.0
 */
@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryDAO categoryDAO;

    @AddCache
    @Override
    public Map<String,Object> queryAllFirst(Integer page, Integer rows) {

        Map<String,Object> map = new HashMap<>();

        // 一级类别总条数
        Integer records = categoryDAO.queryAllFirstCount();
        // 总页数
        Integer total = records%rows==0?records/rows:records/rows+1;
        Integer offest = (page-1)*rows;
        // 所有的一级类别
        List<Category> categories = categoryDAO.queryAllFirst(offest,rows);
        map.put("rows",categories);
        map.put("page",page);
        map.put("records",records);
        map.put("total",total);
        return map;
    }

    @AddCache
    @Override
    public Map<String,Object> queryAllSecond(String parId,Integer page,Integer rows) {
        Map<String,Object> map = new HashMap<>();
        // 分页起始条数
        Integer offest = (page-1)*rows;
       // 分页查询某一级类别下的所有二级类别
        List<Category> categories = categoryDAO.queryAllSecond(parId, offest, rows);

        // 查询某一级类别下二级类别的数量
        Integer records = categoryDAO.queryAllSecondCount(parId);

        // 总页数
        Integer total = records%rows==0?records/rows:records/rows+1;

        map.put("total",total);
        map.put("page",page);
        map.put("rows",categories);
        map.put("records",records);

        return map;
    }

    @DelCache
    @Override
    public void addCategory(Category category) {
        category.setId(UUID.randomUUID().toString().replace("-",""));
        categoryDAO.addCategory(category);
    }

    @DelCache
    @Override
    public String delCategory(Category category) {
        String message = null;
        // 判断是否为2级类别，是直接删除
        if ("2".equals(category.getLevels())){
            categoryDAO.delCategory(category);
            message="删除成功";
            return message;
        }else {
            // 如果是一级类别，判断该一级类别下是否有二级类别，
            Integer integer = categoryDAO.queryAllSecondCount(category.getId());
            // 没有二级类别，直接删除
            if (integer==0){
                categoryDAO.delCategory(category);
                message="删除成功";
                return message;
            }else{
                // 有二级类别，不能删除
                 message ="该一级类别下还有二级类别,不能删除";
                return message;
            }
        }
    }

    @DelCache
    @Override
    public void updateCategory(Category category) {
        categoryDAO.updateCategory(category);
    }

    @AddCache
    @Override
    public List<Category> queryAllTwo() {
        List<Category> categories = categoryDAO.queryAllTwo();

        return categories;
    }

    @AddCache
    @Override
    public List<CategoryPo> queryAllCategory() {
        List<CategoryPo> categoryPos = categoryDAO.queryAllCategory();
        return categoryPos;
    }


}
