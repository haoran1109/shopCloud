
package com.shop.security.authentication;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.shop.RedisKeyUtil;
import com.shop.ThreadLocalMap;
import com.shop.entity.SocialUserExt;
import com.shop.entity.UacUserToken;
import com.shop.security.core.properties.SecurityProperties;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.codec.Base64;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.exceptions.UnapprovedClientAuthenticationException;
import org.springframework.security.oauth2.provider.*;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * APP环境下认证成功处理器
 * 
 *
 *
 */
@Component("imoocAuthenticationSuccessHandler")
public class ImoocAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private ClientDetailsService clientDetailsService;
	
	@Autowired
	private AuthorizationServerTokenServices authorizationServerTokenServices;

	@Autowired
	private RedisTemplate redisTemplate;
	@Autowired
	private SecurityProperties securityProperties;


	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.security.web.authentication.
	 * AuthenticationSuccessHandler#onAuthenticationSuccess(javax.servlet.http.
	 * HttpServletRequest, javax.servlet.http.HttpServletResponse,
	 * org.springframework.security.core.Authentication)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {

		logger.info("登录成功");

		String header = request.getHeader("Authorization");

		if (header == null || !header.startsWith("Basic ")) {
			throw new UnapprovedClientAuthenticationException("请求头中无client信息");
		}

		String[] tokens = extractAndDecodeHeader(header, request);
		assert tokens.length == 2;

		String clientId = tokens[0];
		String clientSecret = tokens[1];

		ClientDetails clientDetails = clientDetailsService.loadClientByClientId(clientId);

		if (clientDetails == null) {
			throw new UnapprovedClientAuthenticationException("clientId对应的配置信息不存在:" + clientId);
		} else if (!StringUtils.equals(clientDetails.getClientSecret(), clientSecret)) {
			throw new UnapprovedClientAuthenticationException("clientSecret不匹配:" + clientId);
		}
		
		TokenRequest tokenRequest = new TokenRequest(MapUtils.EMPTY_MAP, clientId, clientDetails.getScope(), "custom");
		
		OAuth2Request oAuth2Request = tokenRequest.createOAuth2Request(clientDetails);
		
		OAuth2Authentication oAuth2Authentication = new OAuth2Authentication(oAuth2Request, authentication);
		
		OAuth2AccessToken token = authorizationServerTokenServices.createAccessToken(oAuth2Authentication);

		// 记录token日志
		String accessToken = token.getValue();
		String refreshToken = token.getRefreshToken().getValue();

		SocialUserExt userTokenDto = (SocialUserExt) authentication.getPrincipal();

		// 存入mysql数据库
		UacUserToken uacUserToken = new UacUserToken();
		uacUserToken.setOs("test");
		uacUserToken.setBrowser("test");
		uacUserToken.setAccessToken(accessToken);
		uacUserToken.setAccessTokenValidity(securityProperties.getOauth2().getClients()[0].getAccessTokenValidateSeconds());
		uacUserToken.setLoginIp("192.168.0.0.1");
//		uacUserToken.setLoginLocation(remoteLocation);
//		uacUserToken.setLoginTime(uacUser.getLastLoginTime());
//		uacUserToken.setLoginName(loginAuthDto.getLoginName());
		uacUserToken.setRefreshToken(refreshToken);
		uacUserToken.setRefreshTokenValidity(securityProperties.getOauth2().getClients()[0].getRefreshTokenValiditySeconds());
//		uacUserToken.setStatus(UacUserTokenStatusEnum.ON_LINE.getStatus());
		uacUserToken.setUserId(userTokenDto.getId());
		uacUserToken.setUserName(userTokenDto.getUsername());
//		uacUserToken.setUpdateInfo(loginAuthDto);
//		uacUserToken.setGroupId(loginAuthDto.getGroupId());
//		uacUserToken.setGroupName(loginAuthDto.getGroupName());
//		uacUserToken.setId(userTokenDto.getId());

		Set<String> path = new HashSet<>();
		Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
		for (final GrantedAuthority authority : authorities) {
			String url = authority.getAuthority();
			if (StringUtils.isNotEmpty(url)) {
				path.add(url);
			}
		}
		path.add("/user/regist");
		uacUserToken.setGrantedAuthorities(path);
		String userKey=RedisKeyUtil.getAccessTokenKey(accessToken);

		redisTemplate.opsForValue().set(userKey, JSONObject.toJSONString(uacUserToken), securityProperties.getOauth2().getClients()[0].getAccessTokenValidateSeconds(), TimeUnit.SECONDS);

		logger.info("用户【 {} 】记录登录日志", userTokenDto.getUsername());

		response.setContentType("application/json;charset=UTF-8");
		response.getWriter().write(objectMapper.writeValueAsString(token));

	}

	private String[] extractAndDecodeHeader(String header, HttpServletRequest request) throws IOException {

		byte[] base64Token = header.substring(6).getBytes("UTF-8");
		byte[] decoded;
		try {
			decoded = Base64.decode(base64Token);
		} catch (IllegalArgumentException e) {
			throw new BadCredentialsException("Failed to decode basic authentication token");
		}

		String token = new String(decoded, "UTF-8");

		int delim = token.indexOf(":");

		if (delim == -1) {
			throw new BadCredentialsException("Invalid basic authentication token");
		}
		return new String[] { token.substring(0, delim), token.substring(delim + 1) };
	}

}
