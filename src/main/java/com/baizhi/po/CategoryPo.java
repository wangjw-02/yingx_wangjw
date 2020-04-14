package com.baizhi.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @Author: wangjiangwei
 * @Date 2020/4/7 9:23
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryPo implements Serializable {
    private String id;
    private String cateName;
    private String levels;
    private String parentId;
    private List<CategoryPo> categoryList;
}
