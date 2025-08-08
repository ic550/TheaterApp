package com.example.app.config;

import java.io.IOException;
import java.util.Collection;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.example.app.login.LoginStatus;
import com.example.app.login.User;

import lombok.RequiredArgsConstructor;

@Component("roleBasedAuthenticationSuccessHandler")
@RequiredArgsConstructor
public class RoleBasedAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private final LoginStatus loginStatus;

    @Override
    public void onAuthenticationSuccess(
            HttpServletRequest request,
            HttpServletResponse response,
            Authentication authentication) throws IOException, ServletException {

        // ユーザー情報を取得（UserDetails のサブクラスならキャスト）
        User user = (User) authentication.getPrincipal(); // ← ここ重要

        // loginStatus にユーザー情報をセット
        loginStatus.setId(user.getId());
        loginStatus.setName(user.getUsername());
        loginStatus.setAdmin(user.getRole().equals("ADMIN"));
        loginStatus.setRole(user.getRole());

        // 権限に基づいてリダイレクト
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

        for (GrantedAuthority authority : authorities) {
            String role = authority.getAuthority();

            if (role.equals("ROLE_ADMIN")) {
                response.sendRedirect("/admin");
                return;
            } else if (role.equals("ROLE_USER")) {
                response.sendRedirect("/calendar");
                return;
            }
        }

        response.sendRedirect("/");
    }
}
