

package com.shop.service.userToken;
import com.base.dto.LoginAuthDto;
import com.base.dto.UserTokenDto;

import javax.servlet.http.HttpServletRequest;

/**
 * 登录jwt token 管理.
 *
 *
 */
public interface UacUserTokenService {

	/**
	 * 获取token.
	 *
	 * @param accessToken the access token
	 *
	 * @return the by access token
	 */
	UserTokenDto getByAccessToken(String accessToken);

	/**
	 * 保存token.
	 *
	 * @param accessToken  the access token
	 * @param refreshToken the refresh token
	 * @param loginAuthDto the login auth dto
	 * @param request      the request
	 */
	void saveUserToken(String accessToken, String refreshToken, LoginAuthDto loginAuthDto, HttpServletRequest request);


	/**
	 * 刷新token.
	 *
	 * @param accessToken  the access token
	 * @param refreshToken the refresh token
	 * @param request      the request
	 *
	 * @return the string
	 *
	 */
	String refreshToken(String accessToken,String refreshTokenValue, HttpServletRequest request) throws Exception;
}
