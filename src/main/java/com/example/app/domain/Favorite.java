package com.example.app.domain;

import lombok.Data;

@Data
public class Favorite {
    private int id;
    private int userId;
    private int performanceId;
    
    public Favorite(int userId, int performanceId) {
        this.userId = userId;
        this.performanceId = performanceId;
    }
}
