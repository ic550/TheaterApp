// src/main/java/com/example/app/mapper/PerformanceMapper.java
package com.example.app.mapper;

import java.time.LocalDate;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.app.domain.Performance;

@Mapper
public interface PerformanceMapper {
    List<Performance> findByDate(LocalDate date);
    Performance findById(int id);
    void insert(Performance performance);
    void update(Performance performance);
    void deleteById(int id);
    List<Performance> findAll();
    
    Performance getPerformanceById(int id);
    List<Performance> findByIds(@Param("ids") List<Integer> ids);

}
