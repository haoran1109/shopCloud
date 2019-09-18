package com.shop.service.roleadmin.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.base.dao.MyBatisBaseDao;
import com.base.service.impl.MybatisBaseServiceImpl;
import com.shop.dao.roleadmin.RoleadminDao;
import com.shop.service.roleadmin.RoleadminService;

import java.util.List;
import java.util.Map;

/**
 * 【】 服务类 实现类
 * 
 * @author AutoCode 940279663@qq.com
 * @date 2019-08-09 11:36:00
 * 
 */
@Service(value = "roleadminService")
public class RoleadminServiceImpl extends MybatisBaseServiceImpl implements RoleadminService {

    @Autowired
    private RoleadminDao roleadminDao;

    @Override
    public MyBatisBaseDao getDao() {
        return roleadminDao;
    }

    @Override
    public List<String> getUrlsByUserId(Map<String, Object> map) {
        return roleadminDao.getRolesByUserId(map);
    }
}
