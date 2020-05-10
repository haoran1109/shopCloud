
package com.shop.interceptor;

import com.base.constant.GlobalConstant;
import com.base.dto.LoginAuthDto;
import com.base.enums.ErrorCodeEnum;
import com.base.exception.BusinessException;
import com.google.gson.Gson;
import com.shop.RedisKeyUtil;
import com.shop.ThreadLocalMap;
import com.shop.annotation.NoNeedAccessAuthentication;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;


/**
 * The class Token interceptor.
 *
 *
 */
@Component
public class TokenInterceptor implements HandlerInterceptor {

	private Logger log = LoggerFactory.getLogger(getClass());

	@Resource
	private RedisTemplate<String, Object> redisTemplate;

	private static final String CODE_SMS = "/code/sms";
	private static final String AUTH_PATH1 = "/auth";
	private static final String AUTH_PATH2 = "/oauth";
	private static final String AUTH_PATH3 = "/error";
	private static final String AUTH_PATH4 = "/api";

	/**
	 * After completion.
	 *
	 * @param request  the request
	 * @param response the response
	 * @param arg2     the arg 2
	 * @param ex       the ex
	 *
	 * @throws Exception the exception
	 */
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object arg2, Exception ex) throws Exception {
		if (ex != null) {
			log.error("<== afterCompletion - 解析token失败. ex={}", ex.getMessage(), ex);
			this.handleException(response);
		}
	}

	/**
	 * Post handle.
	 *
	 * @param request  the request
	 * @param response the response
	 * @param arg2     the arg 2
	 * @param mv       the mv
	 */
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object arg2, ModelAndView mv) {
	}

	/**
	 * Pre handle boolean.
	 *
	 * @param request  the request
	 * @param response the response
	 * @param handler  the handler
	 *
	 * @return the boolean
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
		String uri = request.getRequestURI();
		log.info("<== preHandle - 权限拦截器.  url={}", uri);
		if (uri.contains(AUTH_PATH1) || uri.contains(AUTH_PATH2) || uri.contains(AUTH_PATH3)
				|| uri.contains(AUTH_PATH4)  || uri.contains(CODE_SMS) ) {
			log.info("<== preHandle - 配置URL不走认证.  url={}", uri);
			return true;
		}
		//API 包含注解无需登录
		if (isHaveAccess(handler)) {
			log.info("<== preHandle - API不需要认证");
			return true;
		}else{
			String token = StringUtils.substringAfter(request.getHeader(HttpHeaders.AUTHORIZATION), "Bearer ");
			Object obj=redisTemplate.opsForValue().get(RedisKeyUtil.getAccessTokenKey(token));
			if (obj == null) {
				//用户已经退出登录 或者是刷新了token后带着旧的token访问接口
				log.error("获取用户信息失败, 不允许操作");
				throw new BusinessException(ErrorCodeEnum.GL99990401);
			}
			Gson gson = new Gson();
			LoginAuthDto loginUser=gson.fromJson(obj.toString(), LoginAuthDto.class);
			ThreadLocalMap.put(GlobalConstant.Sys.TOKEN_AUTH_DTO, loginUser);
			return true;
		}
	}

	private boolean isHaveAccess(Object handler) {
		HandlerMethod handlerMethod = (HandlerMethod) handler;

		Method method = handlerMethod.getMethod();

		NoNeedAccessAuthentication responseBody = AnnotationUtils.findAnnotation(method, NoNeedAccessAuthentication.class);
		return responseBody != null;
	}

	private void handleException(HttpServletResponse res) throws IOException {
		res.resetBuffer();
		res.setHeader("Access-Control-Allow-Origin", "*");
		res.setHeader("Access-Control-Allow-Credentials", "true");
		res.setContentType("application/json");
		res.setCharacterEncoding("UTF-8");
		res.getWriter().write("{\"code\":100009 ,\"message\" :\"解析token失败\"}");
		res.flushBuffer();
	}
}
  