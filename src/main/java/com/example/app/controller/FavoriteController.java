// src/main/java/com/example/app/controller/FavoriteController.java
package com.example.app.controller;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.app.login.User;
import com.example.app.service.FavoriteService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/favorites")
@RequiredArgsConstructor
public class FavoriteController {

    private final FavoriteService favoriteService;

    // "USER"ロールを持つユーザーのみ実行可能
    @PreAuthorize("hasRole('USER')")
    @PostMapping("/add")
    public String addFavorite(@RequestParam int performanceId, @AuthenticationPrincipal User user) {
        // @AuthenticationPrincipalから直接ユーザー情報を取得
        favoriteService.add(user.getId(), performanceId);
        return "redirect:/performance/detail/" + performanceId;
    }

    @PreAuthorize("hasRole('USER')")
    @PostMapping("/remove")
    public String removeFavorite(@RequestParam int performanceId, @AuthenticationPrincipal User user) {
        favoriteService.remove(user.getId(), performanceId);
        return "redirect:/performance/detail/" + performanceId;
    }
}
