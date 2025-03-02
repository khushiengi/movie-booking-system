package com.example.theatre.event;

import com.example.theatre.entity.Theatre;

public class TheatreEvent {
    private String eventType;
    private Theatre theatre;

    public TheatreEvent() {}

    public TheatreEvent(String eventType, Theatre theatre) {
        this.eventType = eventType;
        this.theatre = theatre;
    }

    public String getEventType() { return eventType; }
    public Theatre getTheatre() { return theatre; }
}
