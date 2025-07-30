// src/main/java/com/example/app/service/FavoriteService.java
package com.example.app.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.app.mapper.FavoriteMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FavoriteService {

    private final FavoriteMapper favoriteMapper;

    public void add(int userId, int performanceId) {
        favoriteMapper.add(userId, performanceId);
    }

    public void remove(int userId, int performanceId) {
        favoriteMapper.remove(userId, performanceId);
    }

    public List<Integer> getFavoriteIds(int userId) {
        return favoriteMapper.getFavoriteIds(userId);
    }
}
