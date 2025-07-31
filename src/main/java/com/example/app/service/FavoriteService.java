package com.example.app.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.app.domain.Favorite;
import com.example.app.mapper.FavoriteMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FavoriteService {

    private final FavoriteMapper favoriteMapper;

    public void addFavorite(int userId, int performanceId) {
        favoriteMapper.add(userId, performanceId); // 修正
    }

    public void removeFavorite(int userId, int performanceId) {
        favoriteMapper.remove(userId, performanceId); // 修正
    }

    public boolean isFavorited(int userId, int performanceId) {
        return favoriteMapper.isFavorited(userId, performanceId);
    }

    public List<Favorite> getFavoritesByUser(int userId) {
        return favoriteMapper.findByUserId(userId);
    }
    
}
