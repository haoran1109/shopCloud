
package com.shop.security.server;

import com.shop.security.app.authentication.openid.OpenIdAuthenticationSecurityConfig;
import com.shop.security.core.authentication.FormAuthenticationConfig;
import com.shop.security.core.authentication.mobile.SmsCodeAuthenticationSecurityConfig;
import com.shop.security.core.authorize.AuthorizeConfigManager;
import com.shop.security.core.validate.code.ValidateCodeSecurityConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.expression.OAuth2WebSecurityExpressionHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.social.security.SpringSocialConfigurer;

/**
 * 资源服务器配置
 * 
 *
 *
 */
@Configuration
@EnableResourceServer
public class ImoocResourceServerConfig extends ResourceServerConfigurerAdapter {
	
	@Autowired
	protected AuthenticationSuccessHandler imoocAuthenticationSuccessHandler;
	
	@Autowired
	protected AuthenticationFailureHandler imoocAuthenticationFailureHandler;
	
	@Autowired
	private SmsCodeAuthenticationSecurityConfig smsCodeAuthenticationSecurityConfig;
	
	@Autowired
	private OpenIdAuthenticationSecurityConfig openIdAuthenticationSecurityConfig;
	
	@Autowired
	private ValidateCodeSecurityConfig validateCodeSecurityConfig;
	
	@Autowired
	private SpringSocialConfigurer imoocSocialSecurityConfig;
	
	@Autowired
	private AuthorizeConfigManager authorizeConfigManager;
	
	@Autowired
	private FormAuthenticationConfig formAuthenticationConfig;


	@Autowired
	private OAuth2WebSecurityExpressionHandler expressionHandler;
	
	@Override
	public void configure(HttpSecurity http) throws Exception {
		
		formAuthenticationConfig.configure(http);
		
		http.apply(validateCodeSecurityConfig)
				.and()
			.apply(smsCodeAuthenticationSecurityConfig)
				.and()
			.apply(imoocSocialSecurityConfig)
				.and()
			.apply(openIdAuthenticationSecurityConfig)
				.and()
			.csrf().disable();
		
		authorizeConfigManager.config(http.authorizeRequests());
	}


	/**
	 * @description:  这里不加会报错
	 *
	 * {
	 *     "timestamp": 1564924365894,
	 *     "status": 500,
	 *     "error": "Internal Server Error",
	 *     "exception": "java.lang.IllegalArgumentException",
	 *     "message": "Failed to evaluate expression '#oauth2.throwOnError(@rbacService.hasPermission(request, authentication))'",
	 *     "path": "/user/me"
	 * }
	 *
	 * 详细解决办法 参见:
	 * https://coding.shop.com/learn/questiondetail/41222.html
	 * https://github.com/spring-projects/spring-security-oauth/issues/730#issuecomment-219480394
	 *
	 * @author haoran.zhang
	 * @date 2019/08/04 21:36
	 * @param [applicationContext]
	 * @return org.springframework.security.oauth2.provider.expression.OAuth2WebSecurityExpressionHandler
	 */
	@Bean
	public OAuth2WebSecurityExpressionHandler oAuth2WebSecurityExpressionHandler(ApplicationContext applicationContext) {

		OAuth2WebSecurityExpressionHandler expressionHandler = new OAuth2WebSecurityExpressionHandler();

		expressionHandler.setApplicationContext(applicationContext);

		return expressionHandler;

	}

	@Override
	public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
		resources.expressionHandler(expressionHandler);
	}

}