package com.example.theatre.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.theatre.entity.Seat;

@Repository
public interface SeatRepository extends JpaRepository<Seat, Long> {
}