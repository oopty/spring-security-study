package me.oopty.springsecuritystudy.config.httpBasic;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Base64;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Import(HttpBasicSecurityConfig.class)
public class HttpBasicTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private SecurityProperties securityProperties;

    @Test
    void notAuthenticated_return401() throws Exception {
        mvc.perform(get("/"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void Authenticated_return200() throws Exception {
        String name = securityProperties.getUser().getName();
        String password = securityProperties.getUser().getPassword();


        String auth = name + ":" + password;
        String encodedAuth = Base64.getEncoder().encodeToString(auth.getBytes());


        mvc.perform(get("/")
                        .header("Authorization", "Basic " + encodedAuth))
                .andExpect(status().isOk());
    }
}
