package com.goruslan.eCommerce.config;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // Cross-origin-recourse-sharing: localhost:8080, localhost:4200
        http.cors().and()
                .authorizeRequests()
                // Public
                .antMatchers("/resources/**", "/error", "/api/user/**").permitAll()
                // Admin
                .antMatchers("/api/admin/**").hasRole("ADMIN")
                // Every other request should be authenticated
                .anyRequest().fullyAuthenticated()
                .and()
                .logout().permitAll()
                .logoutRequestMatcher(new AntPathRequestMatcher("/api/user/logout", "POST"))
                .and()
                .formLogin().loginPage("/api/user/login").and()
                // Enable basic authentication
                .httpBasic().and()
                // Cross side request forgery
                .csrf().disable();
    }


    


}
