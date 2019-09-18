package com.shop.service.resource.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.base.dao.MyBatisBaseDao;
import com.base.service.impl.MybatisBaseServiceImpl;
import com.shop.dao.resource.ResourceUrlsDao;
import com.shop.service.resource.ResourceUrlsService;

import java.util.List;
import java.util.Map;

/**
 * 【】 服务类 实现类
 * 
 * @author AutoCode 940279663@qq.com
 * @date 2019-08-09 11:35:59
 * 
 */
@Service(value = "resourceUrlsService")
public class ResourceUrlsServiceImpl extends MybatisBaseServiceImpl implements ResourceUrlsService {

    @Autowired
    private ResourceUrlsDao resourceUrlsDao;

    @Override
    public MyBatisBaseDao getDao() {
        return resourceUrlsDao;
    }

    @Override
    public List<String> getUrlsByUserId(Map<String,Object> map) {
        return resourceUrlsDao.getUrlsByUserId(map);
    }
}
