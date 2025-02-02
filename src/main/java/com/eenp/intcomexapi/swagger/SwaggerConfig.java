package com.eenp.intcomexapi.swagger;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import springfox.documentation.service.Contact;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
	
	@Bean
	public Docket apiDocket() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.eenp.intcomexapi.controller"))
				.paths(PathSelectors.any())
				.build()
				.apiInfo(getApiInfo())
			;
	}
	
	private ApiInfo getApiInfo() {
		return new ApiInfo(
				"incomexAPI",
				"API demo para Informacion de Clientes",
				"1.0",
				"http://example.com/terms",
				new Contact("eliezer.navarro","https://www.linkedin.com/in/eliezer-navarro-perez/","eneperezv@gmail.com"),
				"GNU GPL",
				"https://www.gnu.org/licenses/#GPL",
				Collections.emptyList()
			);
	}

}
