// src/main/java/com/example/app/mapper/FavoriteMapper.java
package com.example.app.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.example.app.domain.Favorite;

@Mapper
public interface FavoriteMapper {

    @Insert("INSERT IGNORE INTO favorites(user_id, performance_id) VALUES(#{userId}, #{performanceId})")
    void addFavorite(@Param("userId") int userId, @Param("performanceId") int performanceId);

    @Delete("DELETE FROM favorites WHERE user_id = #{userId} AND performance_id = #{performanceId}")
    void removeFavorite(@Param("userId") int userId, @Param("performanceId") int performanceId);

    @Select("SELECT performance_id FROM favorites WHERE user_id = #{userId}")
    List<Integer> getFavoriteIds(int userId);
    
    @Select("SELECT * FROM favorites WHERE user_id = #{userId}")
    List<Favorite> findByUserId(@Param("userId") int userId);

    @Select("SELECT COUNT(*) > 0 FROM favorites WHERE user_id = #{userId} AND performance_id = #{performanceId}")
    boolean isFavorited(@Param("userId") int userId, @Param("performanceId") int performanceId);
}
