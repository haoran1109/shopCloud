
package com.shop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 *
 *
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@EnableZuulProxy
@EnableEurekaClient
public class ZuulApplication {

	/**
	 * @description: 网关层
	 * @author haoran.zhang
	 * @date 2019/08/09 22:19
	 * @param [args]
	 * @return void
	 */
	public static void main(String[] args) {
		SpringApplication.run(ZuulApplication.class, args);
	}

}
