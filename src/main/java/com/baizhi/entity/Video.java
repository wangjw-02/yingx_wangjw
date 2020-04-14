package com.baizhi.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.io.Serializable;

/**
 * (Video)实体类
 *
 * @author wangjiangwei
 * @since 2020-04-01 14:46:12
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Video implements Serializable {
    private static final long serialVersionUID = -87550757329874236L;
    
    private String id;
    
    private String title;
    
    private String brief;
    
    private String path;
    
    private String cover;
    
    private Date publishDate;
    
    private String categoryId;
    
    private String groupId;
    
    private String userId;


}