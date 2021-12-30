package com.github.ksgfk.windchimeweb.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.HashSet;

import static springfox.documentation.builders.PathSelectors.regex;

@ComponentScan(basePackages = {"com.github.ksgfk.windchimeweb.controller"})
@Configuration
@EnableWebMvc
@EnableSwagger2
public class WindChimeSwagger {
    @Bean
    public Docket allDocket() {
        var prod = new HashSet<String>();
        prod.add("application/json");
        var cons = new HashSet<String>();
        cons.add("application/json");
        var prot = new HashSet<String>();
        prot.add("http");
        return new Docket(DocumentationType.SWAGGER_2)
                .produces(prod)
                .consumes(cons)
                .protocols(prot)
                .forCodeGeneration(true)
                .select()
                .paths(regex(".*"))
                .build()
                .apiInfo(buildApiInfo());
    }

    private ApiInfo buildApiInfo() {
        return new ApiInfoBuilder()
                .title("WindChime接口文档")
                .termsOfServiceUrl("")
                .description("WindChime接口")
                .version("1.0.0")
                .build();
    }
}
