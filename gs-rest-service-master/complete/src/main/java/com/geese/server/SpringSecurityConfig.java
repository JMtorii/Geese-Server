package com.geese.server;

/**
 * Created by Ni on 2015-11-16.
 */

import com.geese.server.service.impl.GooseServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@Order(2)
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {
    private final GooseServiceImpl gooseService;
    private final TokenAuthenticationService tokenAuthenticationService;

    public SpringSecurityConfig() {
        super(true);
        this.gooseService = new GooseServiceImpl();
        tokenAuthenticationService = new TokenAuthenticationService("tooManySecrets", gooseService);
        }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
    http
    .exceptionHandling().and()
    .anonymous().and()
    .servletApi().and()
    .headers().cacheControl();

    http
    .authorizeRequests()

    // Allow anonymous resource requests
    .antMatchers("/").permitAll()
    .antMatchers("/favicon.ico").permitAll()
    .antMatchers("**/*.html").permitAll()
    .antMatchers("**/*.css").permitAll()
    .antMatchers("**/*.js").permitAll()

    // Allow anonymous logins
    .antMatchers("/auth/**").permitAll()

    // All other request need to be authenticated
    .anyRequest().authenticated().and()

    // Custom Token based authentication based on the header previously given to the client
    .addFilterBefore(new StatelessAuthenticationFilter(tokenAuthenticationService),
            UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(userDetailsService()).passwordEncoder(new BCryptPasswordEncoder());
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    @Override
    public UserDetailsService userDetailsService() {
        return gooseService;
    }

    @Bean
    public TokenAuthenticationService tokenAuthenticationService() {
        return tokenAuthenticationService;
        }
    }