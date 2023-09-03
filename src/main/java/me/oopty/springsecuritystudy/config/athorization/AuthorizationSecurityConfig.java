package me.oopty.springsecuritystudy.config.athorization;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Configuration
@EnableWebSecurity
public class AuthorizationSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin()
                .successHandler((request, response, authentication) -> {
                    HttpSessionRequestCache requestCache = new HttpSessionRequestCache();

                    SavedRequest savedRequest = requestCache.getRequest(request, response);
                    String url = savedRequest.getRedirectUrl();
                    response.sendRedirect(url);
                });

        http
            .authorizeRequests()
            .antMatchers("/shop/login", "/shop/users/**").permitAll()
            .antMatchers("/shop/mypage").hasRole("USER")
            .antMatchers("/shop/admin/pay").access("hasRole('ADMIN')")
            .antMatchers("/shop/admin/**").access("hasRole('ADMIN') or hasRole('SYS')")
            .anyRequest().permitAll();

        http.exceptionHandling()
//                .authenticationEntryPoint((request, response, authException) -> {
//                    response.sendRedirect("/custom/login");
//                })
                .accessDeniedHandler((request, response, accessDeniedException) -> {
                    response.sendRedirect("/denied");
                });
    }
}
