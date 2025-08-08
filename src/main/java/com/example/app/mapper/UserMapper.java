package com.example.app.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.example.app.login.User;

@Mapper
public interface UserMapper {
    void insertUser(User user);
    User findByUsername(String username);
	void insert(User user);
}
