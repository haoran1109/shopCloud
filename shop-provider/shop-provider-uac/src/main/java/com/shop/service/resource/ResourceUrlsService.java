package com.shop.service.resource;

import com.base.service.MybatisBaseService;

import java.util.List;
import java.util.Map;

/**
 * 【】 服务类 接口
 * 
 * @author AutoCode 940279663@qq.com
 * @date 2019-08-09 11:35:59
 * 
 */
public interface ResourceUrlsService extends MybatisBaseService {
    List<String> getUrlsByUserId(Map<String,Object> map);
}
