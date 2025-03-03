package com.example.theatre.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.example.theatre.entity.Theatre;

@Service
public class TheatreProducer {
    private final KafkaTemplate<String, Map<String, Object>> kafkaTemplate;

    @Value("${spring.kafka.topic.theatre}")
    private String theatreTopic;

  //  public TheatreProducer(KafkaTemplate<String, TheatreEvent> kafkaTemplate) {
   //     this.kafkaTemplate = kafkaTemplate;
  //  }

   // public void sendTheatreEvent(String eventType, Theatre theatre) {
   //     TheatreEvent event = new TheatreEvent(eventType, theatre);
   //     kafkaTemplate.send(theatreTopic, event);
   // }
    
    
    public TheatreProducer(KafkaTemplate<String, Map<String, Object>> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendTheatreEvent(String eventType, Theatre theatre) {
    	Map<String, Object> message = new HashMap<>();
        message.put("id", theatre.getTheatreId());
        message.put("name", theatre.getTheatreName());
        message.put("location", theatre.getLocation());
        kafkaTemplate.send(theatreTopic, message);
    }
}
