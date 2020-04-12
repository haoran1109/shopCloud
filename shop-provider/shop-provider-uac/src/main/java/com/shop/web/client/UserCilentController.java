
package com.shop.web.client;


import com.shop.dto.AdminDto;
import com.shop.entity.admin.Admin;
import com.shop.service.admin.AdminService;
import com.shop.support.BaseController;
import com.shop.wrapper.WrapMapper;
import com.shop.wrapper.Wrapper;
import io.swagger.annotations.Api;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 *
 *
 */
@RestController
@RequestMapping("/client/user")
@Api("用户 client 测试类")
public class UserCilentController extends BaseController {


	@Autowired
	private AdminService adminService;

	@RequestMapping(value = "/testFeign")
	public Wrapper<AdminDto> feign(@RequestParam(name = "username") String username){
		Map<String,Object> map=new HashMap<>(10);
		map.put("username",username);
		Admin admin =adminService.selectByUserName(map);
		AdminDto  dto=new AdminDto();
		BeanUtils.copyProperties(admin,dto);
		return WrapMapper.ok(dto);
	}

	@RequestMapping(value = "/test")
	public  Wrapper<String> test(@RequestParam(name = "username") String username){
		return WrapMapper.ok(username);
	}


}
