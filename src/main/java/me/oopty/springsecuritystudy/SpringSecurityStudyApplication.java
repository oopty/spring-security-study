package me.oopty.springsecuritystudy;

import me.oopty.springsecuritystudy.config.formLogin.FormLoginSecurityConfig;
import me.oopty.springsecuritystudy.config.httpBasic.HttpBasicSecurityConfig;
import me.oopty.springsecuritystudy.config.logout.LogoutSecurityConfig;
import me.oopty.springsecuritystudy.config.rememberMe.RememberMeSecurityConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

//@Import(HttpBasicSecurityConfig.class)
//@Import(FormLoginSecurityConfig.class)
//@Import(LogoutSecurityConfig.class)
@Import(RememberMeSecurityConfig.class)
@SpringBootApplication(scanBasePackages = "me.oopty.springsecuritystudy.mvc")
public class SpringSecurityStudyApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringSecurityStudyApplication.class, args);
    }

}
