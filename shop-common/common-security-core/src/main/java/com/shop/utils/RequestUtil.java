

package com.shop.utils;

import com.base.constant.GlobalConstant;
import com.base.enums.ErrorCodeEnum;
import com.base.exception.BusinessException;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.codec.Base64;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * The class Request util.
 *
 *
 */
@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RequestUtil {

	/**
	 * Gets request.
	 *
	 * @return the request
	 */
	public static HttpServletRequest getRequest() {
		return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
	}

	/**
	 * Gets login user.
	 *
	 * @return the login user
	 */
	/*public static LoginAuthDto getLoginUser() {
		LoginAuthDto loginAuthDto = (LoginAuthDto) ThreadLocalMap.get(GlobalConstant.Sys.TOKEN_AUTH_DTO);
		if (PublicUtil.isEmpty(loginAuthDto)) {
			throw new BusinessException(ErrorCodeEnum.UAC10011039);
		}
		return loginAuthDto;

	}*/

	/**
	 * Gets auth header.
	 *
	 * @param request the request
	 *
	 * @return the auth header
	 */
	public static String getAuthHeader(HttpServletRequest request) {

		String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
		if (StringUtils.isEmpty(authHeader)) {
			throw new BusinessException(ErrorCodeEnum.UAC10011040);
		}
		return authHeader;
	}

	public static String[] extractAndDecodeHeader(String header) throws IOException {

		byte[] base64Token = header.substring(6).getBytes("UTF-8");
		byte[] decoded;
		try {
			decoded = Base64.decode(base64Token);
		} catch (IllegalArgumentException e) {
			throw new BadCredentialsException("Failed to decode basic authentication token");
		}

		String token = new String(decoded, "UTF-8");

		int delim = token.indexOf(GlobalConstant.Symbol.MH);

		if (delim == -1) {
			throw new BadCredentialsException("Invalid basic authentication token");
		}
		return new String[]{token.substring(0, delim), token.substring(delim + 1)};
	}
}
