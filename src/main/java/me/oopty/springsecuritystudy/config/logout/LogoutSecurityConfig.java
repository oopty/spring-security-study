package me.oopty.springsecuritystudy.config.logout;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

@Configuration
@EnableWebSecurity
public class LogoutSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .anyRequest().authenticated()
            .and()
                .formLogin();


        http.logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login")
                .deleteCookies("JSESSIONID", "remember-me")
                .addLogoutHandler(logoutHandler());
//                .logoutSuccessHandler(logoutSuccessHandler());

    }

    private LogoutSuccessHandler logoutSuccessHandler() {
        return (request, response, authentication) -> {
            System.out.println("logoutSuccessHandler");
            response.sendRedirect("/login");
        };
    }

    private LogoutHandler logoutHandler() {
        return (request, response, authentication) -> {
            System.out.println("logoutHandler");
        };
    }
}
