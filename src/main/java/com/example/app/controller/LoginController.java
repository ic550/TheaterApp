// src/main/java/com/example/app/controller/LoginController.java
package com.example.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.app.login.LoginStatus;
import com.example.app.login.User;
import com.example.app.login.UserMapper;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class LoginController {

	@Autowired
	private UserMapper userMapper;

	@Autowired
	private LoginStatus loginStatus;
	
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
    
    @PostMapping("/login")
    public String doLogin(@RequestParam String username,
                          @RequestParam String password,
                          Model model) {
        User user = userMapper.findByUsername(username);
        if (user != null && BCrypt.checkpw(password, user.getPassword())) {
            // ✅ 成功時：ログイン状態を保存
            loginStatus.setLoggedIn(true);
            loginStatus.setId(user.getId());
            loginStatus.setRole(user.getRole());

            System.out.println("ログインユーザー role = " + user.getRole());
            
            return "redirect:/"; // ←トップページなどへリダイレクト
        } else {
            model.addAttribute("error", "ユーザー名またはパスワードが違います");
            return "login"; // ログインページに戻す
        }
    }
}
