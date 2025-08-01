package com.example.app.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.app.domain.Favorite;
import com.example.app.domain.LoginUser;
import com.example.app.domain.Performance;
import com.example.app.service.FavoriteService;
import com.example.app.service.PerformanceService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class FavoriteController {

    private final FavoriteService favoriteService;
    private final PerformanceService performanceService;

    @GetMapping("/favorite/list")
    public String showFavorites(@AuthenticationPrincipal LoginUser loginUser, Model model) {
        int userId = loginUser.getUser().getId();

        // お気に入りPerformance ID取得
        List<Favorite> favorites = favoriteService.getFavoritesByUser(userId);
        List<Integer> performanceIds = favorites.stream()
                .map(Favorite::getPerformanceId)
                .collect(Collectors.toList());

        // Performance情報取得
        List<Performance> performanceList = performanceService.findByIds(performanceIds);

        model.addAttribute("favorites", performanceList);
        return "favorite/list";
    }
}
