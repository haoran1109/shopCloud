

package com.shop.filter;

import com.base.enums.ErrorCodeEnum;
import com.base.exception.BusinessException;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.Map;


@Component
public class AuthHeaderFilter extends ZuulFilter {

	private Logger log = LoggerFactory.getLogger(getClass());

	private static final String BEARER_TOKEN_TYPE = "Bearer ";
	private static final String OPTIONS = "OPTIONS";
	private static final String AUTH_PATH = "/auth";
	private static final String LOGOUT_URI = "/oauth/token";
	private static final String ALIPAY_CALL_URI = "/pay/alipayCallback";
	public static final String HEADER_LABEL = "x-label";


	/**
	 * Filter type string.
	 *
	 * @return the string
	 */
	@Override
	public String filterType() {
		return "pre";
	}

	/**
	 * Filter order int.
	 *
	 * @return the int
	 */
	@Override
	public int filterOrder() {
		return 0;
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
		log.info("AuthHeaderFilter - 开始鉴权...");
		RequestContext requestContext = RequestContext.getCurrentContext();
		try {
			doSomething(requestContext);
		} catch (Exception e) {
			log.error("AuthHeaderFilter - [FAIL] EXCEPTION={}", e.getMessage(), e);
			throw new BusinessException(ErrorCodeEnum.UAC10011041);
		}
		return null;
	}

	private void doSomething(RequestContext requestContext) throws ZuulException {
		HttpServletRequest request = requestContext.getRequest();
		String requestURI = request.getRequestURI();

		/*if (OPTIONS.equalsIgnoreCase(request.getMethod()) || !requestURI.contains(AUTH_PATH) || !requestURI.contains(LOGOUT_URI) || !requestURI.contains(ALIPAY_CALL_URI)) {
			return;
		}*/
		String authHeader = getAuthHeader(request);

//		if (isEmpty(authHeader)) {
//			throw new ZuulException("刷新页面重试", 403, "check token fail");
//		}



		if (authHeader.startsWith(BEARER_TOKEN_TYPE)) {
			requestContext.addZuulRequestHeader(HttpHeaders.AUTHORIZATION, authHeader);

			log.info("authHeader={} ", authHeader);
			// 传递给后续微服务
			requestContext.addZuulRequestHeader(HEADER_LABEL, authHeader);
		}
	}


	public static String getAuthHeader(HttpServletRequest request) {

		String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
//		if (org.apache.commons.lang.StringUtils.isEmpty(authHeader)) {
//			throw new BusinessException(ErrorCodeEnum.UAC10011040);
//		}
		return authHeader;
	}


	/**
	 * 判断对象是否Empty(null或元素为0)
	 * 实用于对如下对象做判断:String Collection及其子类 Map及其子类
	 *
	 * @param pObj 待检查对象
	 *
	 * @return boolean 返回的布尔值
	 */
	public static boolean isEmpty(Object pObj) {
		if (pObj == null) {
			return true;
		}
		if (pObj == "") {
			return true;
		}
		if (pObj instanceof String) {
			return ((String) pObj).length() == 0;
		} else if (pObj instanceof Collection) {
			return ((Collection) pObj).isEmpty();
		} else if (pObj instanceof Map) {
			return ((Map) pObj).size() == 0;
		}
		return false;
	}

}
