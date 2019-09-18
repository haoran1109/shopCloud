package com.shop;

import org.springframework.web.bind.annotation.GetMapping;

public interface UserPermission {
    @GetMapping(value="/testFeign")
    public  String  testFeign(String username);
}
