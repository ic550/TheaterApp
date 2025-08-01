package com.example.app.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.app.domain.Favorite;
import com.example.app.domain.Performance;
import com.example.app.mapper.FavoriteMapper;
import com.example.app.mapper.PerformanceMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FavoriteServiceImpl implements FavoriteService {

    private final FavoriteMapper favoriteMapper;
    private final PerformanceMapper performanceMapper;

    @Override
    public void addFavorite(int userId, int performanceId) {
        favoriteMapper.addFavorite(userId, performanceId);
    }

    @Override
    public void removeFavorite(int userId, int performanceId) {
        favoriteMapper.removeFavorite(userId, performanceId);
    }

    @Override
    public boolean isFavorited(int userId, int performanceId) {
        return favoriteMapper.isFavorited(userId, performanceId);
    }

    @Override
    public List<Favorite> getFavoritesByUser(int userId) {
        return favoriteMapper.findByUserId(userId);
    }
    
    @Override
    public List<Performance> findByIds(List<Integer> ids) {
        return performanceMapper.findByIds(ids);
    }

}
