package com.example.theatre.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.theatre.entity.Theatre;

@Repository
public interface TheatreRepository extends JpaRepository<Theatre, Long> {
}
