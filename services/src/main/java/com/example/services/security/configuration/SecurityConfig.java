package com.example.services.security.configuration;

import com.example.services.filter.AuthorizationFilter;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
@AllArgsConstructor
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final AuthorizationFilter authorizationFilter;


    private static final String[] PUBLIC_URLS = {
            "/v2/api-docs",
            "/swagger-resources/**",
            "/swagger-ui/**",
            "/webjars/**",
    };

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().cors().and()
                .sessionManagement().sessionCreationPolicy(STATELESS)
                .and()
                .authorizeRequests().antMatchers(PUBLIC_URLS).permitAll()
                .anyRequest().authenticated()
                .and()
                .addFilterBefore(authorizationFilter, UsernamePasswordAuthenticationFilter.class);
    }


}
