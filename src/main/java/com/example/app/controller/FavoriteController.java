package com.example.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.app.login.LoginStatus;
import com.example.app.service.FavoriteService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/favorite")
public class FavoriteController {

    private final FavoriteService favoriteService;
    private final LoginStatus loginStatus;

    @PostMapping("/add/{performanceId}")
    public String addFavorite(@PathVariable int performanceId) {
        int userId = loginStatus.getId();
        favoriteService.addFavorite(userId, performanceId);
        return "redirect:/performance/detail/" + performanceId;
    }

    @PostMapping("/remove/{performanceId}")
    public String removeFavorite(@PathVariable int performanceId) {
        int userId = loginStatus.getId();
        favoriteService.removeFavorite(userId, performanceId);
        return "redirect:/performance/detail/" + performanceId;
    }
}
