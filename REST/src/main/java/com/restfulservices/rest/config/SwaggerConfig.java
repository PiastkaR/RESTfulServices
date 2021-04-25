package com.restfulservices.rest.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Configuration
public class SwaggerConfig {
    private static final Contact DEFAULT_CONTACT = new Contact(
            "Rafal P", "http://www.restfulservices.com", "someMail@gmail.com"
    );

    private static final ApiInfo DEFAULT_API_INFO = new ApiInfo(
            "Api Documentation", "Api description", "1.0",
            "urn:tos", DEFAULT_CONTACT.getName(),
            "not-licensed", "some link to license");

    private static final Set<String> DEFAULT_PRODUCERS_ADN_CONSUMERS = new HashSet<>(
            Arrays.asList("application/json", "application/xml")
    );


    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(DEFAULT_API_INFO)
                .produces(DEFAULT_PRODUCERS_ADN_CONSUMERS)
                .consumes(DEFAULT_PRODUCERS_ADN_CONSUMERS)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build();
    }
}
