
package com.shop;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

/**
 * The class O auth 2 feign request interceptor.
 *
 *
 */
@Slf4j
@Configuration
public class OAuth2FeignRequestInterceptor implements RequestInterceptor {

	/**
	 * Apply.
	 *
	 * @param template the template
	 */
	@Override
	public void apply(RequestTemplate template) {

		RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();
		Enumeration<String> headerNames = request.getHeaderNames();
		if (headerNames != null) {
			while (headerNames.hasMoreElements()) {
				String name = headerNames.nextElement();
				Enumeration<String> values = request.getHeaders(name);
				while (values.hasMoreElements()) {
					String value = values.nextElement();
					System.out.println(name + "---" + value);
					template.header(name, value);
				}
			}
		}
	}
}
