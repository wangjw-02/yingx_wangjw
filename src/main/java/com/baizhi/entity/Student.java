package com.baizhi.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author: wangjiangwei
 * @Date 2020/4/7 21:08
 * @Version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student implements Serializable {
    @Excel(name = "id")
    private String id;
    @Excel(name = "姓名")
    private String name;
    @Excel(name = "性别",replace = {"男_1","女_2"})
    private int sex;
    @Excel(name = "出生日期",databaseFormat = "yyyy年MM月dd日HHmmss",format = "yyyy-MM-dd")
    private Date bir;
    @Excel(name = "进校日期",databaseFormat = "yyyy年MM月dd日HHmmss",format = "yyyy-MM-dd")
    private Date regDate;
    @Excel(name = "头像",type = 2)
    private String hi;
}
