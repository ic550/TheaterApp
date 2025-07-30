// src/main/java/com/example/app/security/UserDetailsServiceImpl.java
package com.example.app.security;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.app.login.UserMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userMapper.findByUsername(username);
        if (user == null) throw new UsernameNotFoundException("ユーザーが見つかりません");

        return User.withUsername(user.getUsername())
                .password(user.getPassword())
                .roles(user.getRole()) // ROLE_USER または ROLE_ADMIN
                .build();
    }
}
