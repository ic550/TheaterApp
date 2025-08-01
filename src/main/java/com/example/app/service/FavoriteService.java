package com.example.app.service;

import java.util.List;

import com.example.app.domain.Favorite;
import com.example.app.domain.Performance;

public interface FavoriteService {
    void addFavorite(int userId, int performanceId);
    void removeFavorite(int userId, int performanceId);
    boolean isFavorited(int userId, int performanceId);
    List<Favorite> getFavoritesByUser(int userId);
    List<Performance> findByIds(List<Integer> ids);
    void toggleFavorite(int userId, int performanceId);
}
