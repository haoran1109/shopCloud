

package com.shop.filter;

import com.base.enums.ErrorCodeEnum;
import com.base.exception.BusinessException;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @description: token续期
 * @author haoran.zhang
 * @date 2019/08/09 23:09
 * @param
 * @return
 */
@Component
public class RenewFilter extends ZuulFilter {

	private Logger log = LoggerFactory.getLogger(getClass());

	@Resource
	private JwtTokenStore jwtTokenStore;

	private static final int EXPIRES_IN = 60 * 20;

	public static ThreadLocal<String> th = new ThreadLocal<String>();

	/**
	 * Filter type string.
	 *
	 * @return the string
	 */
	@Override
	public String filterType() {
		return "post";
	}

	/**
	 * Filter order int.
	 *
	 * @return the int
	 */
	@Override
	public int filterOrder() {
		return 10;
	}

	/**
	 * Should filter boolean.
	 *
	 * @return the boolean
	 */
	@Override
	public boolean shouldFilter() {
		return true;
	}

	/**
	 * Run object.
	 *
	 * @return the object
	 */
	@Override
	public Object run() {
		log.info("RenewFilter - token续租...");
		RequestContext requestContext = RequestContext.getCurrentContext();
		try {
			doSomething(requestContext);
		} catch (Exception e) {
			log.error("RenewFilter - token续租. [FAIL] EXCEPTION={}", e.getMessage(), e);
			throw new BusinessException(ErrorCodeEnum.UAC10011041);
		}
		return null;
	}

	private void doSomething(RequestContext requestContext) {
		HttpServletRequest request = requestContext.getRequest();
		String token = StringUtils.substringAfter(request.getHeader(HttpHeaders.AUTHORIZATION), "Bearer ");
		if (StringUtils.isEmpty(token)) {
			return;
		}
		OAuth2AccessToken oAuth2AccessToken = jwtTokenStore.readAccessToken(token);
		int expiresIn = oAuth2AccessToken.getExpiresIn();

		if (expiresIn < EXPIRES_IN) {
			HttpServletResponse servletResponse = requestContext.getResponse();
			servletResponse.addHeader("Renew-Header", "true");
		}
	}

}