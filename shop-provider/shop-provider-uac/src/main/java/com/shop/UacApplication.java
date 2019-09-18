
package com.shop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 *
 *
 */
@SpringBootApplication
@RestController
@EnableSwagger2
@EnableEurekaClient
public class UacApplication {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(UacApplication.class, args);
	}

	@Bean
	public Docket createRestApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(apiInfo())
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.shop.web"))
				.paths(PathSelectors.any())
				.build();
	}

	/**
	 * 创建该API的基本信息（这些基本信息会展现在文档页面中）
	 * 访问地址：http://项目实际地址/swagger-ui.html
	 *
	 * @return
	 */
	private ApiInfo apiInfo() {
		return new ApiInfoBuilder()
				.title("测试 APIs")
				.description("测试api接口文档")
				.termsOfServiceUrl("http://www.baidu.com")
				.version("1.0")
				.build();
	}



}
