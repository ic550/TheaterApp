package com.example.app.domain;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.app.login.User;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class LoginUser implements UserDetails {

    private final User user;

    public User getUser() {
        return user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // 権限を1つだけ持つと仮定（例: "ROLE_ADMIN", "ROLE_USER"）
        return Collections.singleton(() -> "ROLE_" + user.getRole().toUpperCase());
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true; // アカウント期限なし
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; // アカウントロックなし
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // 資格情報期限なし
    }

    @Override
    public boolean isEnabled() {
        return true; // 有効状態
    }
}
