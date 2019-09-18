package com.shop.security.core;

import org.apache.commons.lang.StringUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Principal;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;


@Component
public final class SecurityUtils {

	private static final String AUTH_LOGIN_AFTER_URL = "/user/loginAfter/*";
	private static final String AUTH_LOGOUT_URL = "/user/logout";

	/**
	 * Gets current login name.
	 *
	 * @return the current login name
	 */
	public static String getCurrentLoginName() {

		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		if (principal instanceof UserDetails) {

			return ((UserDetails) principal).getUsername();

		}

		if (principal instanceof Principal) {

			return ((Principal) principal).getName();

		}

		return String.valueOf(principal);

	}

	public static Set<String> getCurrentAuthorityUrl() {
		Set<String> path = new HashSet<>();
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
		for (final GrantedAuthority authority : authorities) {
			String url = authority.getAuthority();
			if (StringUtils.isNotEmpty(url)) {
				path.add(url);
			}
		}
		path.add("/user/regist");
		path.add(AUTH_LOGIN_AFTER_URL);
		path.add(AUTH_LOGOUT_URL);
		return path;
	}
}
