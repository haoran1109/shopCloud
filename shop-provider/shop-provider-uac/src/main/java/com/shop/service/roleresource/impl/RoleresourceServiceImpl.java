package com.shop.service.roleresource.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.base.dao.MyBatisBaseDao;
import com.base.service.impl.MybatisBaseServiceImpl;
import com.shop.dao.roleresource.RoleresourceDao;
import com.shop.service.roleresource.RoleresourceService;

/**
 * 【】 服务类 实现类
 * 
 * @author AutoCode 940279663@qq.com
 * @date 2019-08-09 11:36:00
 * 
 */
@Service(value = "roleresourceService")
public class RoleresourceServiceImpl extends MybatisBaseServiceImpl implements RoleresourceService {

    @Autowired
    private RoleresourceDao roleresourceDao;

    @Override
    public MyBatisBaseDao getDao() {
        return roleresourceDao;
    }

}
