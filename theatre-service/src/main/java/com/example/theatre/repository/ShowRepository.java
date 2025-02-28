package com.example.theatre.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.theatre.entity.Show;

@Repository
public interface ShowRepository extends JpaRepository<Show, Long> {
}
