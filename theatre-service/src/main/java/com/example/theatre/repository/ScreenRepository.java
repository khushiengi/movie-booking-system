package com.example.theatre.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.theatre.entity.Screen;

@Repository
public interface ScreenRepository extends JpaRepository<Screen, Long> {
}
