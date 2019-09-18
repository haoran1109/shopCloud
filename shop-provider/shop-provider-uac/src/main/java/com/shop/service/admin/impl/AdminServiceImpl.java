package com.shop.service.admin.impl;

import com.shop.entity.admin.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.base.dao.MyBatisBaseDao;
import com.base.service.impl.MybatisBaseServiceImpl;
import com.shop.dao.admin.AdminDao;
import com.shop.service.admin.AdminService;

import java.util.Map;

/**
 * 【】 服务类 实现类
 * 
 * @author AutoCode 309444359@qq.com
 * @date 2019-08-09 11:35:57
 * 
 */
@Service(value = "adminService")
public class AdminServiceImpl extends MybatisBaseServiceImpl implements AdminService {

    @Autowired
    private AdminDao adminDao;

    @Override
    public MyBatisBaseDao getDao() {
        return adminDao;
    }

    @Override
    public Admin selectByUserName(Map<String, Object> map) {
        return adminDao.selectByUserName(map);
    }
}
