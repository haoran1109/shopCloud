package com.shop.business;

import com.shop.UserPermission;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserPermissionimpl implements UserPermission {


    @Override
    public String testFeign(String username) {
        return "test feign "+username;
    }
}
