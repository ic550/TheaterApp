// src/main/java/com/example/app/domain/Performance.java
package com.example.app.domain;

import java.time.LocalDate;

import lombok.Data;

@Data
public class Performance {
    private int id;
    private String title;
    private String theater;
    private LocalDate date;
    private String time;         // 昼 or 夜
    private String cast;
    private String youtubeUrl;
    
}
