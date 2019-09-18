
package com.shop.security.rbac.authorize;

import com.shop.security.core.authorize.AuthorizeConfigProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.stereotype.Component;

/**
 *
 */
@Component
@Order(Integer.MIN_VALUE)
public class RbacAuthorizeConfigProvider implements AuthorizeConfigProvider {

	private Logger logger = LoggerFactory.getLogger(getClass());

	/* (non-Javadoc)
	 * @see com.shop.security.core.authorize.AuthorizeConfigProvider#config(org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer.ExpressionInterceptUrlRegistry)
	 */
	@Override
	public boolean config(ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry config) {
		config
			.antMatchers(HttpMethod.GET, "/fonts/**").permitAll()
			.antMatchers(HttpMethod.GET, "/**/*.html","/admin/me","/resource").permitAll()
			.antMatchers(HttpMethod.GET, "/v2/**","/swagger-resources/configuration/ui","/swagger-resources/**").permitAll();
		logger.info(" RbacAuthorizeConfigProvider init success ");
		return false;
	}

}
