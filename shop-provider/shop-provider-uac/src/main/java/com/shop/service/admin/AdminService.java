package com.shop.service.admin;

import com.base.service.MybatisBaseService;
import com.shop.entity.admin.Admin;

import java.util.Map;

/**
 * 【】 服务类 接口
 * 
 * @author AutoCode 940279663@qq.com
 * @date 2019-08-09 11:35:57
 * 
 */
public interface AdminService extends MybatisBaseService {
    public Admin selectByUserName(Map<String,Object> map);
}
