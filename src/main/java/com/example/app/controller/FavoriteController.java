// src/main/java/com/example/app/controller/FavoriteController.java
package com.example.app.controller;

import jakarta.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.app.login.LoginStatus;
import com.example.app.service.FavoriteService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/favorites")
public class FavoriteController {

    private final FavoriteService favoriteService;

    @PostMapping("/add")
    public String addFavorite(@RequestParam int performanceId, HttpSession session) {
        LoginStatus login = (LoginStatus) session.getAttribute("loginStatus");
        if (login != null && login.getRole().equals("USER")) {
            favoriteService.add(login.getId(), performanceId);
        }
        return "redirect:/performance/detail/" + performanceId;
    }

    @PostMapping("/remove")
    public String removeFavorite(@RequestParam int performanceId, HttpSession session) {
        LoginStatus login = (LoginStatus) session.getAttribute("loginStatus");
        if (login != null && login.getRole().equals("USER")) {
            favoriteService.remove(login.getId(), performanceId);
        }
        return "redirect:/performance/detail/" + performanceId;
    }
}
