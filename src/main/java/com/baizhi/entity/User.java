package com.baizhi.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * (User)实体类
 *
 * @author makejava
 * @since 2020-03-28 15:02:45
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {
    private static final long serialVersionUID = 965614428534799286L;
    @Excel(name = "id")
    private String id;
    @Excel(name="用户名")
    private String username;
    @Excel(name = "手机号")
    private String phone;
    @Excel(name="头像",type = 2,width = 40)
    private String headImg;
    @Excel(name="个性签名")
    private String sign;
    @Excel(name = "微信")
    private String wechat;
    @Excel(name = "状态")
    private String status;

    @Excel(name = "注册日期",databaseFormat = "yyyy-MM-dd HH:mm:ss",format = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date createDate;



}