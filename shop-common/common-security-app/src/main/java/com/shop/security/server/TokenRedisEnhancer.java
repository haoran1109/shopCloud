
package com.shop.security.server;

import org.apache.commons.beanutils.BeanMap;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 *
 *token 添加附加字段
 */
@Component
public class TokenRedisEnhancer implements TokenEnhancer {

	/* (non-Javadoc)
	 * @see org.springframework.security.oauth2.provider.token.TokenEnhancer#enhance(org.springframework.security.oauth2.common.OAuth2AccessToken, org.springframework.security.oauth2.provider.OAuth2Authentication)
	 */
	@Override
	public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
		Map<String, Object> info = new HashMap<>();
		info.put("company", "shop");
		BeanMap user=new BeanMap(authentication.getUserAuthentication().getPrincipal());
		info.put("loginName", user.get("username"));
		((DefaultOAuth2AccessToken)accessToken).setAdditionalInformation(info);

		return accessToken;
	}

}
