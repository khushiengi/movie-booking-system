package com.example.searchservice.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.searchservice.entity.TheatreSearch;
import com.example.searchservice.repository.TheatreSearchRepository;

@Service
public class SearchService {
    private final TheatreSearchRepository repository;

    public SearchService(TheatreSearchRepository repository) {
        this.repository = repository;
    }

    public List<TheatreSearch> searchByName(String name) {
        return repository.findByName(name);
    }
}
