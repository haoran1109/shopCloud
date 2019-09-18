

package com.shop.config;

import com.shop.interceptor.TokenInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.List;


/**
 * The class Mdc web mvc config.
 *
 *
 */
@Configuration
@EnableWebMvc
public class OmcWebMvcConfig extends WebMvcConfigurerAdapter {

	@Autowired
	private TokenInterceptor vueViewInterceptor;

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/**")
				.addResourceLocations("classpath:/META-INF/resources/", "classpath:/resources/", "classpath:/static/");
	}

	/**
	 * Add interceptors.
	 *
	 * @param registry the registry
	 */
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		super.addInterceptors(registry);
		registry.addInterceptor(vueViewInterceptor)
				.addPathPatterns("/**")
				.excludePathPatterns("/pay/alipayCallback", "/swagger-resources/**", "*.js", "/**/*.js", "*.css", "/**/*.css", "*.html", "/**/*.html");
	}

	/*@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		PcObjectMapper.buidMvcMessageConverter(converters);
	}*/

}
