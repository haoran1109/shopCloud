
package com.shop.security.rbac.service;

import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;


public interface RbacPermissionService {
	
	boolean hasPermission(HttpServletRequest request, Authentication authentication);

}
