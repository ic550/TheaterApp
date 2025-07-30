// src/main/java/com/example/app/login/UserService.java
package com.example.app.login;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        com.example.app.login.User user = userMapper.findByUsername(username);
        if (user == null) throw new UsernameNotFoundException("ユーザーが見つかりません");

        return User.withUsername(user.getUsername())
                   .password(user.getPassword())
                   .roles(user.getRole())
                   .build();
    }
}
