// src/main/java/com/example/app/controller/UserRegisterController.java
package com.example.app.controller;

import jakarta.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.app.login.User;
import com.example.app.service.UserRegistrationService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/register")
public class UserRegisterController {

    private final UserRegistrationService userService;

    @GetMapping
    public String showRegisterForm(Model model) {
        model.addAttribute("user", new User());
        return "register/form";
    }

//    @PostMapping
//    public String register(@ModelAttribute User user) {
//        userService.register(user);
//        return "redirect:/login?registerSuccess";
//    }
    
    @PostMapping("/register")
    public String register(@ModelAttribute("user") @Valid User user, BindingResult result, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "register";
        }
        userService.register(user); // ユーザー登録処理
        redirectAttributes.addFlashAttribute("successMessage", "登録が完了しました。ログインしてください。");
        return "redirect:/login";
    }
    
    @GetMapping("/form")
    public String showRegisterFormAlias(Model model) {
        model.addAttribute("user", new User());
        return "register/form";
    }
}
