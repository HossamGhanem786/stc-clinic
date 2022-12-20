package com.stc.clinic.util;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.annotation.PostConstruct;
import java.util.Collections;
import java.util.TimeZone;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("com.stc.clinic.web"))
				.build().apiInfo(apiInfo());
	}
	private ApiInfo apiInfo() {
		return new ApiInfo("Clinic  Application Api Services",
				"This service is to check the added APIs for OnlineBank backend services", "Version 1.0 - mw", "urn:tos",
				new Contact("Clinic ", "", "hossam.ghanem786@gmail.com"), "Apache 2.0",
				"http://www.apache.org/licenses/LICENSE-2.0", Collections.emptyList());

	}
	@PostConstruct
	public void init(){
		TimeZone.setDefault(TimeZone.getTimeZone("CAT"));
	}
	@Bean
	public ModelMapper modelMapper(){
		return new ModelMapper();
	}
}