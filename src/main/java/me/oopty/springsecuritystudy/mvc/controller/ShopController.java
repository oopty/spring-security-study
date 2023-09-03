package me.oopty.springsecuritystudy.mvc.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/shop")
public class ShopController {

    @GetMapping("/login")
    public String login() {
        return "/shop/login";
    }

    @GetMapping("/users/any")
    public String usersAny() {
        return "/shop/users/**";
    }

    @GetMapping("/mypage")
    public String mypage() {
        return "/shop/mypage";
    }

    @GetMapping("/admin/pay")
    public String pay() {
        return "/shop/admin/pay";
    }

    @GetMapping("/admin/any")
    public String adminAny() {
        return "/shop/admin/**";
    }

}
