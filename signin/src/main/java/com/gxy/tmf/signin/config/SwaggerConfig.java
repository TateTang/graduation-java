package com.gxy.tmf.signin.config;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


/**
 * @Author: tangmf
 * @Description: swaage2的配置文件
 * @Data: 2019年3月20日 下午4:02:03
 */
@Component
@EnableSwagger2//增加swagger2的扫描注解就可以了
public class SwaggerConfig {
	/**
	 * @Bean 注解用于告诉方法，产生一个Bean对象，然后这个Bean对象交给Spring管理,默认的bean的名称就是方法名
	 * @return Docket
	 */
	@Bean
	public Docket createRestApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(apiInfo())
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.gxy.tmf.signin.controller"))
				.paths(PathSelectors.any())
				.build();
				
	}
	private ApiInfo apiInfo() {
		return new ApiInfoBuilder()
				.title("Swagger 接口文档")
				.description("Spring Boot SignIn")
				.build();
	}
}
