package com.geese.server;

/**
 * Created by Ni on 2015-11-16.
 */

import com.geese.server.service.GooseService;
import com.geese.server.service.LoginService;
import com.geese.server.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
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

import javax.annotation.Resource;

@Configuration
@EnableWebSecurity
@Order(2)
@ComponentScan(basePackages = {"com.geese.server"})
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    @Resource(name="gooseServiceImpl")
    private GooseService gooseService;

    @Autowired
    private TokenService tokenService;

    public SpringSecurityConfig() {
        super(true); // Fixes CSRF error
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
    .antMatchers("/login/**").permitAll()

    // All other request need to be authenticated
            .anyRequest().authenticated().and()

    // Create / pass Token upon login
//    .addFilterBefore(new StatelessLoginFilter(tokenService),
//            UsernamePasswordAuthenticationFilter.class)

    // Custom Token based authentication based on the header previously given to the client
            .addFilterBefore(new StatelessAuthenticationFilter(tokenService),
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
    public TokenService tokenService() {
        return tokenService;
        }
    }