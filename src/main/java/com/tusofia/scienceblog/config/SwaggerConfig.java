package com.tusofia.scienceblog.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Collections;
import java.util.List;

@Configuration
public class SwaggerConfig {

  public static final String AUTHORIZATION_HEADER = "Authorization";

  @Bean
  public Docket api() {
    return new Docket(DocumentationType.SWAGGER_2)
        .apiInfo(apiInfo())
        .securityContexts(List.of(securityContext()))
        .securitySchemes(List.of(apiKey()))
        .select()
        .apis(RequestHandlerSelectors.any())
        .paths(PathSelectors.any())
        .build();
  }

  private ApiKey apiKey() {
    return new ApiKey("JWT", AUTHORIZATION_HEADER, "header");
  }

  private SecurityContext securityContext() {
    return SecurityContext.builder().securityReferences(defaultAuth()).build();
  }

  List<SecurityReference> defaultAuth() {
    AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
    AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
    authorizationScopes[0] = authorizationScope;
    return List.of(new SecurityReference("JWT", authorizationScopes));
  }

  private ApiInfo apiInfo() {
    return new ApiInfo(
        "Science Blog API",
        "Science Blog API.",
        "1.0",
        "Terms of service",
        new Contact(
            "Nikola Randjelov", "https://github.com/nikolar21", "nikolarandjelov11@gmail.com"),
        "License of API",
        "API license URL",
        Collections.emptyList());
  }
}
