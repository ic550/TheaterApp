package com.example.app.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.app.domain.Favorite;
import com.example.app.domain.LoginUser;
import com.example.app.domain.Performance;
import com.example.app.service.FavoriteService;
import com.example.app.service.PerformanceService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/favorite")
public class FavoriteController {

    private final FavoriteService favoriteService;
    private final PerformanceService performanceService;

    // お気に入り一覧表示
    @GetMapping("/list")
    public String showFavorites(@AuthenticationPrincipal LoginUser loginUser, Model model) {
        int userId = loginUser.getUser().getId();

        List<Favorite> favorites = favoriteService.getFavoritesByUser(userId);
        List<Integer> performanceIds = favorites.stream()
                .map(Favorite::getPerformanceId)
                .collect(Collectors.toList());

        List<Performance> performanceList = performanceService.findByIds(performanceIds);

        model.addAttribute("favorites", performanceList);
        return "favorite/list";
    }

    // お気に入り登録・解除（トグル）
    @PostMapping("/toggle")
    @ResponseBody
    public String toggleFavorite(
    	    @RequestParam("performanceId") int performanceId,
    	    @AuthenticationPrincipal LoginUser loginUser,
    	    RedirectAttributes redirectAttributes
    	) {
    	    if (loginUser == null || loginUser.getUser() == null) {
    	        // 認証されていない（ログイン切れ等）
    	        return "redirect:/login";
    	    }

    	    int userId = loginUser.getUser().getId();

    	    try {
    	        favoriteService.toggleFavorite(userId, performanceId);
    	        redirectAttributes.addFlashAttribute("success", "お気に入りを更新しました。");
    	    } catch (Exception e) {
    	        redirectAttributes.addFlashAttribute("error", "お気に入りの更新に失敗しました。");
    	    }

    	    return "redirect:/performance/list"; // 元のページにリダイレクト（必要ならパラメータ付けて）
    	}
}
