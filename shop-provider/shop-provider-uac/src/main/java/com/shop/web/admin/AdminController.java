
package com.shop.web.admin;

//import com.shop.dto.UserPermission;

import com.shop.security.app.social.AppSingUpUtils;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;

/**
 *
 *
 */
@RestController
@RequestMapping("/admin")
@Api("用户管理端")
public class AdminController {


	@ApiOperation("swagger ui 注释 方法级别")
	@GetMapping("/hello")
	public String hello() {
		return "hello spring security";
	}
}
