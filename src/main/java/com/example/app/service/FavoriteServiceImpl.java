package com.example.app.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.app.domain.Favorite;
import com.example.app.domain.Performance;
import com.example.app.mapper.FavoriteMapper;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class FavoriteServiceImpl implements FavoriteService {

    private final FavoriteMapper favoriteMapper;

    @Override
    public void addFavorite(Favorite favorite) {
        if (!favoriteMapper.existsFavorite(favorite)) {
            favoriteMapper.insertFavorite(favorite);
        }
    }

    @Override
    public void removeFavorite(Favorite favorite) {
        favoriteMapper.deleteFavorite(favorite);
    }

    @Override
    public List<Performance> getFavoritesByUserId(int userId) {
        return favoriteMapper.selectFavoritesByUserId(userId);
    }

    @Override
    public boolean isFavorite(int userId, int performanceId) {
        return favoriteMapper.exists(userId, performanceId);
    }
    
 // 登録されていれば削除、なければ追加
    @Override
    public void toggleFavorite(int userId, int performanceId) {
    	Favorite favorite = new Favorite(userId, performanceId);
        favorite.setUserId(userId);
        favorite.setPerformanceId(performanceId);
    	if (isFavorite(userId, performanceId)) {
            removeFavorite(userId, performanceId);
        } else {
            addFavorite(userId, performanceId);
        }
    }
    
    @Override
    public void addFavorite(int userId, int performanceId) {
        Favorite favorite = new Favorite(userId, performanceId);

        favorite.setUserId(userId);
        favorite.setPerformanceId(performanceId);
        addFavorite(favorite);
    }

    @Override
    public void removeFavorite(int userId, int performanceId) {
        Favorite favorite = new Favorite(userId, performanceId);
        favorite.setUserId(userId);
        favorite.setPerformanceId(performanceId);
        removeFavorite(favorite);
    }
}
