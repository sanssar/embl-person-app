package com.embl.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .httpBasic()
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.GET, "/persons","/person/**").hasRole("USER")
                .antMatchers(HttpMethod.POST, "/person/add","/persons/add").hasRole("ADMIN")
                .antMatchers(HttpMethod.PUT, "/person/update/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.PATCH, "/person/patch/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/person/delete/**").hasRole("ADMIN")
                .and()
                .csrf().disable()
                .formLogin().disable();

    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("user").password("{noop}user").roles("USER")
                .and()
                .withUser("admin").password("{noop}admin").roles("USER", "ADMIN");
    }
}
