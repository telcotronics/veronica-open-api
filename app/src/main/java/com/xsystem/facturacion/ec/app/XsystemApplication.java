package com.xsystem.facturacion.ec.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan({"com.rolandopalermo.facturacion.ec"})
@EnableJpaRepositories(basePackages = {"com.rolandopalermo.facturacion.ec.persistence"})
@EntityScan(basePackages = {"com.rolandopalermo.facturacion.ec.domain"})
@PropertySource("classpath:data.properties")
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class XsystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(XsystemApplication.class, args);
    }

}