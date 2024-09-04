package org.example.Service;

import org.example.Repository.Rrepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;
import java.util.Map;

@Service
public class Rservice {

    private final Rrepository rrepository;

    // Constructor-based injection
    @Autowired
    public Rservice(Rrepository rrepository) {
        this.rrepository = rrepository;
    }

    public void saveEvent(String eventName, String eventType, Date eventDate, double eventPrice, byte[] eventImage) {
        rrepository.saveEvent(eventName, eventType, eventDate, eventPrice, eventImage);
    }

    public List<Map<String, Object>> getAllEvents() {
        return rrepository.getAllEvents();
    }

    public Map<String, Object> getEventById(Long id) {
        return rrepository.getEventById(id);
    }

    public void updateEvent(Long id, String eventName, String eventType, Date eventDate, double eventPrice, byte[] eventImage) {
        rrepository.updateEvent(id, eventName, eventType, eventDate, eventPrice, eventImage);
    }

    public void deleteEvent(Long id) {
        rrepository.deleteEvent(id);
    }
}
