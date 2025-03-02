package com.example.searchservice.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Document(indexName = "theatres")
public class TheatreSearch {

	@Id
	private String id;
	private String name;
	private String location;

}
