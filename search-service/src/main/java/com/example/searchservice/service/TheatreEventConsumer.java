package com.example.searchservice.service;

import java.io.IOException;
import java.util.Map;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.example.searchservice.entity.Theatre;
import com.fasterxml.jackson.databind.ObjectMapper;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.core.IndexResponse;

@Service
public class TheatreEventConsumer {

	private ObjectMapper objectMapper;

	private final ElasticsearchClient elasticsearchClient;

	public TheatreEventConsumer(ObjectMapper objectMapper, ElasticsearchClient elasticsearchClient) {
		this.objectMapper = objectMapper;
		this.elasticsearchClient = elasticsearchClient;
	}

	@KafkaListener(topics = "theatre_producer", groupId = "theatre-consumer-group")
	public void consume(Map<String, Object> message) {
		try {
			// Convert JSON string to TheatreEvent object
			Theatre event = objectMapper.convertValue(message, Theatre.class);
			System.out.println("Saved to Elasticsearch: " + event);
			String out = indexTheatre(event);
			System.out.println("After Indexing : " + out);
		} catch (Exception e) {
			System.err.println("Error processing message: " + e.getMessage());
		}
	}

	public String indexTheatre(Theatre event) throws IOException {
		Theatre eventstore = new Theatre(event.getId(), event.getName(), event.getLocation());
		IndexResponse response = elasticsearchClient.index(i -> i.index("theatres") // Index name in ES
				.id(event.getId().toString()).document(eventstore));
		return response.result().toString();
	}

}
