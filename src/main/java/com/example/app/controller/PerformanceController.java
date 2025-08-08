// src/main/java/com/example/app/controller/PerformanceController.java
package com.example.app.controller;

import java.time.LocalDate;
import java.util.List;

import jakarta.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.app.domain.Performance;
import com.example.app.login.LoginStatus;
import com.example.app.service.FavoriteService;
import com.example.app.service.PerformanceService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/performance")
public class PerformanceController {

    private final PerformanceService performanceService;
    private final FavoriteService favoriteService;
    private final LoginStatus loginStatus;

    // 指定日の一覧を返す（ユーザー画面）
    @GetMapping("/list")
    public String listByDate(@RequestParam("date") String dateStr, Model model) {
        LocalDate date = LocalDate.parse(dateStr);
        List<Performance> list = performanceService.getByDate(date);
        model.addAttribute("performances", list);
        model.addAttribute("date", date);
        return "list";
    }

    // 詳細ページ（動画あり・なし対応）


    // 管理者画面（全件表示）
    @GetMapping("/admin")
    public String admin(Model model) {
        List<Performance> list = performanceService.getAll();
        model.addAttribute("performances", list);
        return "admin";
    }

    // 新規登録画面（管理者）
    @GetMapping("/admin/new")
    public String newForm(Model model) {
        model.addAttribute("performance", new Performance());
        return "admin_form";
    }

    // 編集画面（管理者）
    @GetMapping("/admin/edit/{id}")
    public String editForm(@PathVariable int id, Model model) {
        Performance perf = performanceService.getById(id);
        model.addAttribute("performance", perf);
        return "admin_form";
    }

    // 登録・更新（管理者）
    @PostMapping("/admin/save")
    public String save(@Valid @ModelAttribute Performance performance, BindingResult result) {
        if (result.hasErrors()) {
            return "admin_form";
        }
        performanceService.save(performance);
        return "redirect:/performance/admin";
    }

    // 削除（管理者）
    @PostMapping("/admin/delete/{id}")
    public String delete(@PathVariable int id) {
        performanceService.deleteById(id);
        return "redirect:/performance/admin";
    }
    
 // カレンダー表示
//    @GetMapping("/")
//    public String calendar() {
//        return "calendar";
//    }


    @GetMapping("/detail/{id}")
    public String showDetail(@PathVariable("id") int id, Model model) {
        Performance performance = performanceService.findByIds(id);
        model.addAttribute("performance", performance);
        
       
        // ★ LoginStatusがログイン済みかを確認
        

        // ★ loginStatus をモデルに渡す（Thymeleaf用）
        model.addAttribute("loginStatus", loginStatus);
        // お気に入り済かどうかも渡す（ログイン中のみ）
        boolean isFavorited = false;
        if (loginStatus.isLoggedIn()) {
            isFavorited = favoriteService.isFavorite(loginStatus.getId(), id);
        }
        model.addAttribute("isFavorited", isFavorited);
        return "detail"; // templates/detail.html を返す
    }
    
    @GetMapping("/login_error")
    public String loginError() {
        return "login_error";
    }

    @GetMapping("/logout_success")
    public String logoutSuccess() {
        return "logout_success";
    }

    @GetMapping("/")
    public String calendar(Model model) {
        // ログインしていればお気に入り取得
        if (loginStatus.isLoggedIn()) {
            int userId = loginStatus.getId();
            List<Performance> favoritePerformances = favoriteService.getFavoritesByUserId(userId);
            model.addAttribute("favoritePerformances", favoritePerformances);
        }
        return "calendar"; // templates/calendar.html
    }
    
    @GetMapping("/performance/detail/{id}")
    public String showPerformanceDetail(@PathVariable int id, Model model, LoginStatus loginStatus) {
        Performance performance = performanceService.getPerformanceById(id);
        model.addAttribute("performance", performance);

        boolean isFavorited = favoriteService.isFavorite(loginStatus.getId(), id);
        model.addAttribute("isFavorited", isFavorited);

        return "detail";  // templates/detail.html
    }

}
