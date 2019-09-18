package com.shop.dao.admin;


import com.base.dao.MyBatisBaseDao;
import com.shop.entity.admin.Admin;

import java.util.Map;

/**
 * 【】 数据访问对象 接口
 * 
 * @author AutoCode 940279663@qq.com
 * @date 2019-08-09 11:35:57
 * 
 */
public interface AdminDao extends MyBatisBaseDao {
    public Admin selectByUserName(Map<String,Object> map);
}
