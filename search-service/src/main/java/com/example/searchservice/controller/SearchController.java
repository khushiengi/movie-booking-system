package com.example.searchservice.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.searchservice.entity.TheatreSearch;
import com.example.searchservice.service.SearchService;

@RestController
@RequestMapping("/api/search")
public class SearchController {
	private final SearchService service;

	public SearchController(SearchService service) {
		this.service = service;
	}

	@GetMapping
	public ResponseEntity<List<TheatreSearch>> searchByName(@RequestParam String name) {
		return ResponseEntity.ok(service.searchByName(name));
	}
}
