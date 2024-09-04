package org.example.Controller;

import org.example.Service.Rservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.sql.Date;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:5500", allowCredentials = "true")
@RestController
@RequestMapping("/renginiai")
public class Rcontroller {

    private final Rservice rservice;

    // Constructor-based injection
    @Autowired
    public Rcontroller(Rservice rservice) {
        this.rservice = rservice;
    }

    @PostMapping("/create")
    public String createEvent(@RequestParam String eventName,
                              @RequestParam String eventType,
                              @RequestParam String eventDate,
                              @RequestParam String eventPrice,
                              @RequestParam MultipartFile eventImage) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
            Date parsedDate = new Date(dateFormat.parse(eventDate).getTime());
            double price = Double.parseDouble(eventPrice);
            byte[] imageBytes = eventImage.getBytes();
            rservice.saveEvent(eventName, eventType, parsedDate, price, imageBytes);
            return "Event created successfully!";
        } catch (ParseException | IOException | NumberFormatException e) {
            return "Failed to create event.";
        }
    }

    @GetMapping("/all")
    public List<Map<String, Object>> getAllEvents() {
        return rservice.getAllEvents();
    }

    @GetMapping("/event/{id}")
    public Map<String, Object> getEventById(@PathVariable Long id) {
        return rservice.getEventById(id);
    }

    @PutMapping("/update/{id}")
    public String updateEvent(@PathVariable Long id,
                              @RequestParam String eventName,
                              @RequestParam String eventType,
                              @RequestParam String eventDate,
                              @RequestParam String eventPrice,
                              @RequestParam MultipartFile eventImage) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
            Date parsedDate = new Date(dateFormat.parse(eventDate).getTime());
            double price = Double.parseDouble(eventPrice);
            byte[] imageBytes = eventImage.getBytes();
            rservice.updateEvent(id, eventName, eventType, parsedDate, price, imageBytes);
            return "Event updated successfully!";
        } catch (ParseException | IOException | NumberFormatException e) {
            return "Failed to update event.";
        }
    }

    @DeleteMapping("/delete/{id}")
    public String deleteEvent(@PathVariable Long id) {
        rservice.deleteEvent(id);
        return "Event deleted successfully!";
    }
}
