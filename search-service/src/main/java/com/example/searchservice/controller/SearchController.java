package com.example.searchservice.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.searchservice.entity.Theatre;
import com.example.searchservice.service.SearchService;

import jakarta.websocket.server.PathParam;

@RestController
@RequestMapping("/api/search")
public class SearchController {
	private final SearchService service;

	public SearchController(SearchService service) {
		this.service = service;
	}

	@GetMapping("/name/{name}")
	public ResponseEntity<List<Theatre>> searchByName(@RequestParam String name) {
		try {
			return ResponseEntity.ok(service.searchByName(name));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	@GetMapping("/location/{location}")
	public ResponseEntity<List<Theatre>> searchByLocation(@PathParam(value = "location") String location) {
		try {
			return ResponseEntity.ok(service.searchByLocation(location));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
