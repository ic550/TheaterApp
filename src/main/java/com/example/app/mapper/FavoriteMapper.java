package com.example.app.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.app.domain.Favorite;
import com.example.app.domain.Performance;

@Mapper
public interface FavoriteMapper {
    void insertFavorite(Favorite favorite);
    void deleteFavorite(Favorite favorite);
    List<Performance> selectFavoritesByUserId(int userId);
    boolean existsFavorite(Favorite favorite);
    boolean exists(@Param("userId") int userId, @Param("performanceId") int performanceId);
    void insert(int userId, int performanceId);
    void delete(int userId, int performanceId);
    Performance getPerformanceById(int id);


}
