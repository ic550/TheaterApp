// src/main/java/com/example/app/service/PerformanceService.java
package com.example.app.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.app.domain.Performance;
import com.example.app.mapper.PerformanceMapper;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class PerformanceService {

    private final PerformanceMapper performanceMapper;

    public List<Performance> getByDate(LocalDate date) {
        return performanceMapper.findByDate(date);
    }

    public Performance getById(int id) {
        return performanceMapper.findById(id);
    }

    public void save(Performance performance) {
        if (performance.getId() == 0) {
            performanceMapper.insert(performance);
        } else {
            performanceMapper.update(performance);
        }
    }

    public void deleteById(int id) {
        performanceMapper.deleteById(id);
    }

    public List<Performance> getAll() {
        return performanceMapper.findAll();
    }
}
