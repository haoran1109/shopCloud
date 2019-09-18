
package com.shop.security;

import com.shop.security.core.authorize.AuthorizeConfigProvider;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.stereotype.Component;

/**
 *
 *
 */
@Component
@Order(Integer.MAX_VALUE)
public class DemoAuthorizeConifgProvider implements AuthorizeConfigProvider {

	/* (non-Javadoc)
	 * @see com.shop.security.core.authorize.AuthorizeConfigProvider#config(org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer.ExpressionInterceptUrlRegistry)
	 */
	@Override
	public boolean config(ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry config) {
		config
				.anyRequest()
				.access("@rbacPermissionService.hasPermission(request,authentication)");
		return true;
	}

}
