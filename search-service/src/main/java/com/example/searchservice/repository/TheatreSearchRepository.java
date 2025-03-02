package com.example.searchservice.repository;

import java.util.List;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import com.example.searchservice.entity.TheatreSearch;

@Repository
public interface TheatreSearchRepository extends ElasticsearchRepository<TheatreSearch, String> {
    List<TheatreSearch> findByName(String name);
}
