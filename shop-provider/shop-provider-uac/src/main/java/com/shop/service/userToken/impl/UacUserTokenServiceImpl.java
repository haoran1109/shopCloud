package com.shop.service.userToken.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.arronlong.httpclientutil.HttpClientUtil;
import com.arronlong.httpclientutil.common.HttpConfig;
import com.arronlong.httpclientutil.common.HttpHeader;
import com.base.dto.LoginAuthDto;
import com.base.dto.UserTokenDto;
import com.shop.RedisKeyUtil;
import com.shop.entity.UacUserToken;
import com.shop.entity.admin.Admin;
import com.shop.security.core.properties.OAuth2ClientProperties;
import com.shop.security.core.properties.SecurityProperties;
import com.shop.service.admin.AdminService;
import com.shop.service.userToken.UacUserTokenService;
import org.apache.http.Header;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;


/**
 * The class Uac user token service.
 *
 *
 */
@Service
public class UacUserTokenServiceImpl implements UacUserTokenService {

	@Autowired
	private SecurityProperties securityProperties;
	@Resource
	private RedisTemplate<String, Object> redisTemplate;

	@Autowired
	private AdminService adminService;

	@Value("${shop.auth.refresh-token-url}")
	private String refreshTokenUrl;

	@Override
	public UserTokenDto getByAccessToken(String accessToken) {
		UserTokenDto userTokenDto = (UserTokenDto) redisTemplate.opsForValue().get(RedisKeyUtil.getAccessTokenKey(accessToken));
		return userTokenDto;
	}


	@Override
	public void saveUserToken(String accessToken, String refreshToken, LoginAuthDto loginAuthDto, HttpServletRequest request) {
		// 获取登录时间
		Long userId = loginAuthDto.getUserId();
		UacUserToken uacUserToken = new UacUserToken();
		OAuth2ClientProperties[] clients = securityProperties.getOauth2().getClients();
		int accessTokenValidateSeconds = clients[0].getAccessTokenValidateSeconds();
		int refreshTokenValiditySeconds = clients[0].getRefreshTokenValiditySeconds();
		//构造 uacUserToken
		uacUserToken.setOs("test");
		uacUserToken.setBrowser("test");
		uacUserToken.setAccessToken(accessToken);
		uacUserToken.setAccessTokenValidity(accessTokenValidateSeconds);
		uacUserToken.setLoginIp("192.168.0.0.1");
//		uacUserToken.setLoginLocation(remoteLocation);
//		uacUserToken.setLoginTime(uacUser.getLastLoginTime());
//		uacUserToken.setLoginName(loginAuthDto.getLoginName());
		uacUserToken.setRefreshToken(refreshToken);
		uacUserToken.setRefreshTokenValidity(refreshTokenValiditySeconds);
//		uacUserToken.setStatus(UacUserTokenStatusEnum.ON_LINE.getStatus());
		uacUserToken.setUserId(loginAuthDto.getUserId());
		uacUserToken.setUserName(loginAuthDto.getUserName());
//		uacUserToken.setUpdateInfo(loginAuthDto);
//		uacUserToken.setGroupId(loginAuthDto.getGroupId());
//		uacUserToken.setGroupName(loginAuthDto.getGroupName());
//		uacUserToken.setId(userTokenDto.getId());
		// 存入redis数据库
		updateRedisUserToken(accessToken, accessTokenValidateSeconds, uacUserToken);
	}


	@Override
	public String refreshToken(String accessToken,String refreshToken, HttpServletRequest request) throws Exception {
		String token;
		Map<String, Object> map = new HashMap<>(2);
		map.put("grant_type", "refresh_token");
		map.put("refresh_token", refreshToken);

		//插件式配置请求参数（网址、请求参数、编码、client）
		Header[] headers = HttpHeader.custom().contentType(HttpHeader.Headers.APP_FORM_URLENCODED).authorization(request.getHeader(HttpHeaders.AUTHORIZATION)).build();
		HttpConfig config = HttpConfig.custom().headers(headers).url(refreshTokenUrl).map(map);
		token = HttpClientUtil.post(config);
		JSONObject jsonObj = JSON.parseObject(token);
		String accessTokenNew = (String) jsonObj.get("access_token");
		String refreshTokenNew = (String) jsonObj.get("refresh_token");
		String loginName = (String) jsonObj.get("loginName");
		// 根据用户名查找用户信息
		Map<String,Object> params=new HashMap<>();
		params.put("username",loginName);
		Admin admin = adminService.selectByUserName(params);
		LoginAuthDto loginAuthDto = new LoginAuthDto(admin.getId(), admin.getUsername(), admin.getUsername(),0L, null);
		// 更新的token 保存对应的用户信息 并且返回给前端
		this.saveUserToken(accessTokenNew, refreshTokenNew, loginAuthDto, request);
		return token;
	}

	/**
	 * 重新刷新token
	 * @param accessToken
	 * @param accessTokenValidateSeconds
	 * @param uacUserToken
	 */
	private void updateRedisUserToken(String accessToken, int accessTokenValidateSeconds, UacUserToken uacUserToken) {
		redisTemplate.opsForValue().set(RedisKeyUtil.getAccessTokenKey(accessToken), JSONObject.toJSONString(uacUserToken), accessTokenValidateSeconds, TimeUnit.SECONDS);
	}
}
