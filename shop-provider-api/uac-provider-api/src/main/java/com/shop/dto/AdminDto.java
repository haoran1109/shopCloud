package com.shop.dto;

import lombok.Data;

import java.util.Date;

/**
 * 【】持久化对象 数据库表：admin
 * 用户表
 * @author AutoCode 940279663@qq.com
 * @date 2019-08-09 11:35:57
 * 
 */
@Data
public class AdminDto {

    public static final long serialVersionUID = 1L;

    // 
    private Long id;
    // 
    private Date createdTime;
    // 
    private String password;
    // 
    private String username;
}