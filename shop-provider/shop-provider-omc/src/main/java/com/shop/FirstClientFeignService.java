package com.shop;

import com.shop.hystrix.OmcApiHystrix;
import org.springframework.cloud.netflix.feign.FeignClient;

@FeignClient(name="shop-user",configuration = OAuth2FeignRequestInterceptor.class)
public interface FirstClientFeignService  extends UserPermission{
}
