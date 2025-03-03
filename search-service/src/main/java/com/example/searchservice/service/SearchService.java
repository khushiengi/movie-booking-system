package com.example.searchservice.service;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.searchservice.entity.Theatre;
//import com.example.searchservice.repository.TheatreSearchRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch._types.FieldValue;
import co.elastic.clients.elasticsearch.core.GetResponse;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import co.elastic.clients.elasticsearch.core.search.Hit;

@Service
public class SearchService {
	private final ElasticsearchClient elasticsearchClient;
	
	public SearchService(ElasticsearchClient elasticsearchClient) {
		this.elasticsearchClient = elasticsearchClient;
	}
    
    public List<Theatre> searchByName(String name) throws IOException {
        SearchResponse<Theatre> response = elasticsearchClient.search(s -> s
                .index("theatres")
                .query(q -> q
                        .match(m -> m
                                .field("name")
                                .query(FieldValue.of(name))
                        )
                ), Theatre.class);

        return response.hits().hits().stream()
                .map(Hit::source)
                .collect(Collectors.toList());
    }
    
    public List<Theatre> searchByLocation(String location) throws IOException {
        SearchResponse<Theatre> response = elasticsearchClient.search(s -> s
                .index("theatres")
                .query(q -> q
                        .match(m -> m
                                .field("location")
                                .query(FieldValue.of(location))
                        )
                ), Theatre.class);

        return response.hits().hits().stream()
                .map(Hit::source)
                .collect(Collectors.toList());
    }
}
