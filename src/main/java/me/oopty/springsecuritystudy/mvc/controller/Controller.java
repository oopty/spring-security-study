package me.oopty.springsecuritystudy.mvc.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    @GetMapping("/")
    public String get() {
        return "ok";
    }

    @GetMapping("/success")
    public String success() {
        return "success";
    }

    @GetMapping("/fail")
    public String fail() {
        return "fail";
    }

    @GetMapping("/expired")
    public String expired() {
        return "expired";
    }
    @GetMapping("/invalid")
    public String invalid() {
        return "invalid";
    }
    @GetMapping("/denied")
    public String denied() {
        return "/denied";
    }
    @GetMapping("/custom/login")
    public String customLogin() {
        return "/custom/login";
    }
}
