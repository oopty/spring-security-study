package me.oopty.springsecuritystudy.config.sessionManagement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;

@Configuration
@EnableWebSecurity
public class SessionManagementSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/invalid").permitAll()
                .antMatchers("/expired").permitAll()
                .anyRequest().authenticated()
            .and()
                .formLogin();

        http.sessionManagement()
                .invalidSessionUrl("/invalid")
                .maximumSessions(1)
//                .maxSessionsPreventsLogin(true)
                .expiredUrl("/expired");

        http.sessionManagement()
                .sessionFixation().changeSessionId();

        http.sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.NEVER);

        http.csrf();
    }
}
