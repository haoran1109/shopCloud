package com.shop.dao.roleadmin;

import com.base.dao.MyBatisBaseDao;

import java.util.List;
import java.util.Map;

/**
 * 【】 数据访问对象 接口
 * 
 * @author AutoCode 940279663@qq.com
 * @date 2019-08-09 11:36:00
 * 
 */
public interface RoleadminDao extends MyBatisBaseDao {
    List<String> getRolesByUserId(Map<String,Object> map);
}
