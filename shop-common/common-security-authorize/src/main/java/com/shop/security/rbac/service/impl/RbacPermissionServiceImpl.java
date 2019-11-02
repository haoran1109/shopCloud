/**
 * 
 */
package com.shop.security.rbac.service.impl;

import com.shop.security.core.SecurityUtils;
import com.shop.security.rbac.service.RbacPermissionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import javax.servlet.http.HttpServletRequest;
import java.util.Set;

/**
 * @description: 对请求的URL与用户所拥有的权限比对
 * @author haoran.zhang
 * @date 2019/11/02 18:56
 * @param
 * @return
 */
@Component("rbacPermissionService")
public class RbacPermissionServiceImpl implements RbacPermissionService {

	private AntPathMatcher antPathMatcher = new AntPathMatcher();

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Override
	public boolean hasPermission(HttpServletRequest request, Authentication authentication) {
		Object principal = authentication.getPrincipal();
		Set<String> currentAuthorityUrl = SecurityUtils.getCurrentAuthorityUrl();
		boolean hasPermission = false;
		String requestURI = request.getRequestURI();

		if ("admin".equals(principal)) {
			hasPermission = true;
		}else{
			for (final String authority : currentAuthorityUrl) {
				/*// DEMO项目放过查询权限
				if (requestURI.contains("query") || requestURI.contains("get") || requestURI.contains("check") || requestURI.contains("select")) {
					return true;
				}*/
				if (antPathMatcher.match(authority, requestURI)) {
					logger.info("有权限");
					return true;
				}
			}
		}
		return hasPermission;
	}

}
