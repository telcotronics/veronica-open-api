package com.rolandopalermo.facturacion.ec.app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@Configuration
public class SwaggerConfig {

    @Bean
    public Docket v1APIConfiguration() {
        return new Docket(
                DocumentationType.SWAGGER_2).groupName("api_v1.0").select()
                .apis(RequestHandlerSelectors.basePackage("com.rolandopalermo.facturacion.ec.app.api.v1"))
                .paths(PathSelectors.regex("/api/v1.*"))
                .build()
                .apiInfo(new ApiInfoBuilder().version("1.0")
                        .title("Veronica Open API")
                        .description("\uD83C\uDDEA\uD83C\uDDE8 Implementanción del proceso de facturación electrónica según la normativa vigente del SRI")
                        .build());
    }

    @Bean
    public Docket v1OperationConfiguration() {
        return new Docket(
                DocumentationType.SWAGGER_2).groupName("operaciones").select()
                .apis(RequestHandlerSelectors.basePackage("com.rolandopalermo.facturacion.ec.app.api"))
                .paths(PathSelectors.regex("/operaciones.*"))
                .build()
                .apiInfo(new ApiInfoBuilder().version("1.0")
                        .title("Operaciones")
                        .description("Documentation Veronica API")
                        .build());
    }

}