package com.example.theatre.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.example.theatre.entity.Theatre;
import com.example.theatre.event.TheatreEvent;

@Service
public class TheatreProducer {
    private final KafkaTemplate<String, TheatreEvent> kafkaTemplate;

    @Value("${spring.kafka.topic.theatre}")
    private String theatreTopic;

    public TheatreProducer(KafkaTemplate<String, TheatreEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendTheatreEvent(String eventType, Theatre theatre) {
        TheatreEvent event = new TheatreEvent(eventType, theatre);
        kafkaTemplate.send(theatreTopic, event);
    }
}
