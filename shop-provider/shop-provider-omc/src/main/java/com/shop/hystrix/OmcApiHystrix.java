
package com.shop.hystrix;


import com.shop.UserPermission;
import org.springframework.stereotype.Component;



@Component
public class OmcApiHystrix implements UserPermission {


	@Override
	public String testFeign(String username) {
		return "fallback 999999999999999";
	}
}
