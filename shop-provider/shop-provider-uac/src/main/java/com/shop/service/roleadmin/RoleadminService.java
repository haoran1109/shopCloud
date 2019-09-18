package com.shop.service.roleadmin;

import com.base.service.MybatisBaseService;

import java.util.List;
import java.util.Map;

/**
 * 【】 服务类 接口
 * 
 * @author AutoCode 940279663@qq.com
 * @date 2019-08-09 11:36:00
 * 
 */
public interface RoleadminService extends MybatisBaseService {
    List<String> getUrlsByUserId(Map<String,Object> map);
}
