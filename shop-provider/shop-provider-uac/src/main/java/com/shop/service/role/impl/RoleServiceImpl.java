package com.shop.service.role.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.base.dao.MyBatisBaseDao;
import com.base.service.impl.MybatisBaseServiceImpl;
import com.shop.dao.role.RoleDao;
import com.shop.service.role.RoleService;

/**
 * 【】 服务类 实现类
 * 
 * @author AutoCode 940279663@qq.com
 * @date 2019-08-09 11:36:00
 * 
 */
@Service(value = "roleService")
public class RoleServiceImpl extends MybatisBaseServiceImpl implements RoleService {

    @Autowired
    private RoleDao roleDao;

    @Override
    public MyBatisBaseDao getDao() {
        return roleDao;
    }

}
