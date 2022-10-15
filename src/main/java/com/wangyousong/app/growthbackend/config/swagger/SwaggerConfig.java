package com.wangyousong.app.growthbackend.config.swagger;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
@EnableOpenApi
@EnableWebMvc
public class SwaggerConfig {

    @Value("${spring.profiles.active:NA}")
    private String active;

    @Value("${server.servlet.context-path}")
    private String contextPath;

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.OAS_30)
                .enable(!"prod".equals(active))
                .apiInfo(apiInfo())
                .host("http://localhost:8080" + contextPath + "/swagger-ui")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.wangyousong.app.growthbackend.web.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("API文档")
                .description("API接口说明")
                .contact(new Contact("Bob Jackson", "https://wangyousong.com", "yousong.wang@gmail.com"))
                .version("1.0")
                .build();
    }

    @Bean
    public InternalResourceViewResolver defaultViewResolver() {
        return new InternalResourceViewResolver();
    }

}
