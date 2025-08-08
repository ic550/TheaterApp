// src/main/java/com/example/app/service/UserService.java
package com.example.app.service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import com.example.app.login.User;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserRegistrationService {

	@Qualifier("userMapper")
	private final com.example.app.mapper.UserMapper userMapper;

    public void register(User user) {
        String hashed = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
        user.setPassword(hashed);
        user.setRole("USER");
        userMapper.insert(user);
    }

    public User findByUsername(String username) {
        return userMapper.findByUsername(username);
    }
}
