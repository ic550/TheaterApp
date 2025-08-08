package com.example.app.service;

import java.util.List;

import com.example.app.domain.Favorite;
import com.example.app.domain.Performance;

public interface FavoriteService {
    void addFavorite(Favorite favorite);
    void removeFavorite(Favorite favorite);
    List<Performance> getFavoritesByUserId(int userId);
    boolean isFavorite(int userId, int performanceId);
    void toggleFavorite(int userId, int performanceId);
    void addFavorite(int userId, int performanceId);
    void removeFavorite(int userId, int performanceId);
    
}
