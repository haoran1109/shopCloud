package com.shop.service.resource.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.base.dao.MyBatisBaseDao;
import com.base.service.impl.MybatisBaseServiceImpl;
import com.shop.dao.resource.ResourceDao;
import com.shop.service.resource.ResourceService;

/**
 * 【】 服务类 实现类
 * 
 * @author AutoCode 940279663@qq.com
 * @date 2019-08-09 11:35:58
 * 
 */
@Service(value = "resourceService")
public class ResourceServiceImpl extends MybatisBaseServiceImpl implements ResourceService {

    @Autowired
    private ResourceDao resourceDao;

    @Override
    public MyBatisBaseDao getDao() {
        return resourceDao;
    }

}
