package com.example.app.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.app.domain.Favorite;
import com.example.app.domain.Performance;
import com.example.app.login.LoginStatus;
import com.example.app.service.FavoriteService;
import com.example.app.service.PerformanceService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/favorite")
@RequiredArgsConstructor
public class FavoriteController {

    private final FavoriteService favoriteService;
    private final LoginStatus loginStatus;
    private final PerformanceService performanceService;

    @PostMapping("/add/{performanceId}")
    public String addFavorite(@PathVariable int performanceId) {
        int userId = loginStatus.getId();
        Favorite favorite = new Favorite(userId, performanceId);
        favorite.setUserId(userId);
        favorite.setPerformanceId(performanceId);
        favoriteService.addFavorite(favorite);
        return "redirect:/performance/detail/" + performanceId;
    }

    @PostMapping("/remove/{performanceId}")
    public String removeFavorite(@PathVariable int performanceId) {
        int userId = loginStatus.getId();
        Favorite favorite = new Favorite(userId, performanceId);
        favorite.setUserId(userId);
        favorite.setPerformanceId(performanceId);
        favoriteService.removeFavorite(favorite);
        return "redirect:/favorite/list";
    }

    @GetMapping("/list")
    public String listFavorites(Model model) {
        int userId = loginStatus.getId();
        List<Performance> favorites = favoriteService.getFavoritesByUserId(userId);
        model.addAttribute("favorites", favorites);
        return "favorite/list";
    }
    
 // ★ お気に入りのトグル（登録 / 解除）
    @PostMapping("/toggle")
    public String toggleFavorite(@RequestParam("performanceId") int performanceId) {
        if (loginStatus.isLoggedIn()) {
            favoriteService.toggleFavorite(loginStatus.getId(), performanceId);
        }
        // リダイレクトで公演詳細に戻る
        return "redirect:/performance/detail/" + performanceId;
    }
    
    @GetMapping("/performance/detail/{id}")
    public String detail(@PathVariable int id, Model model) {
        Performance performance = performanceService.getPerformanceById(id);
        model.addAttribute("performance", performance);

        if (loginStatus.isLoggedIn()) {
            boolean isFavorited = favoriteService.isFavorite(loginStatus.getId(), id);
            model.addAttribute("isFavorited", isFavorited);
        }

        model.addAttribute("loginStatus", loginStatus);
        return "detail";
    }
}
