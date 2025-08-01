// src/main/java/com/example/app/controller/LoginController.java
package com.example.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class LoginController {


	
    @GetMapping("/login")
    public String loginForm() {
        return "login";
    }

    @GetMapping("/login_error")
    public String loginError() {
        return "login_error";
    }

    @GetMapping("/logout_success")
    public String logoutSuccess() {
        return "logout_success";
    }
    
   }
