package com.shop.dao.resource;

import com.base.dao.MyBatisBaseDao;

import java.util.List;
import java.util.Map;

/**
 * 【】 数据访问对象 接口
 * 
 * @author AutoCode 940279663@qq.com
 * @date 2019-08-09 11:35:59
 * 
 */
public interface ResourceUrlsDao extends MyBatisBaseDao {
    List<String> getUrlsByUserId(Map<String,Object> map);
}
