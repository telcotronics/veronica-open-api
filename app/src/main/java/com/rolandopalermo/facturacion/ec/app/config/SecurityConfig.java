package com.rolandopalermo.facturacion.ec.app.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

import static com.rolandopalermo.facturacion.ec.app.common.Constants.URI_API_V1;
import static com.rolandopalermo.facturacion.ec.app.common.Constants.URI_OPERATIONS;
import static com.rolandopalermo.facturacion.ec.app.common.Constants.URI_PUBLIC;

@Configuration
@EnableWebSecurity
@PropertySource("classpath:data.properties")
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    @Qualifier("datasourceBean")
    private DataSource dataSource;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().dataSource(dataSource)
                .usersByUsernameQuery(
                        "select username as username, password as password, is_active as enabled from public.user where username=?")
                .authoritiesByUsernameQuery(
                        "select username as username, role as authority from public.user where username=?")
                .passwordEncoder(passwordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .csrf()
                .disable()
                .authorizeRequests()
                .antMatchers("/swagger-resources/**", "/webjars/**", URI_PUBLIC + "**").permitAll()
                .antMatchers("/swagger-ui.html").authenticated()
                .antMatchers(URI_OPERATIONS + "**", URI_API_V1 + "**").authenticated()
                .and()
                .httpBasic()
                .realmName("Veronica");
    }

    @Override
    @Bean
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

}