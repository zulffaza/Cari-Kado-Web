package com.example.carikado.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @GetMapping("/")
    public String index() {
        return "admin/index";
    }

    @GetMapping("/login")
    public String login() {
        return "index/login";
    }
}
