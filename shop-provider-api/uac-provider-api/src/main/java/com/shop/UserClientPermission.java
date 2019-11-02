package com.shop;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name="shop-user",path = "/client/user")
public interface UserClientPermission {
    @GetMapping(value="/testFeign")
    public  String  testFeign(@RequestParam(name = "username") String username);

    @GetMapping(value="/test")
    public  String  test(@RequestParam(name = "username") String username);
}
