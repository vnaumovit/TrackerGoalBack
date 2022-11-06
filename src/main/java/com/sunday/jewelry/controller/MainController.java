package com.sunday.jewelry.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class MainController {

    @GetMapping("/users")
    public String userPage() {
        return "user/userPage";
    }

    @GetMapping("/items")
    public String itemPage() {
        return "item/itemPage";
    }

    @GetMapping("/sales")
    public String salePage() {
        return "sale/salePage";
    }

    @GetMapping("/login")
    public String login() {
        return "user/login";
    }

    @GetMapping("/registration")
    public String registration() {
        return "user/registration";
    }
}
