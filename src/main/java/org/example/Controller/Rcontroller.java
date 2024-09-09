package org.example.Controller;

import org.example.Models.Klientas;
import org.example.Service.Eservice;
import org.example.Service.Kservice;
import org.example.Service.Rservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.SQLException;
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
    private final Kservice kservice;
    private final Eservice eservice;

    @Autowired
    public Rcontroller(Rservice rservice, Kservice kservice, Eservice eservice) {
        this.rservice = rservice;
        this.kservice = kservice;
        this.eservice = eservice;
    }

    @PostMapping("/create")
    public String createEvent(@RequestParam String eventName, @RequestParam String eventType, @RequestParam String eventDate,
                              @RequestParam String eventPrice, @RequestParam MultipartFile eventImage) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
            java.util.Date parsedDate = dateFormat.parse(eventDate);
            java.sql.Date sqlParsedDate = new java.sql.Date(parsedDate.getTime());
            double price = Double.parseDouble(eventPrice);
            byte[] imageBytes = eventImage.getBytes();
            rservice.saveEvent(eventName, eventType, sqlParsedDate, price, imageBytes);
            eservice.sendNewEventEmail(kservice.getSubscribedUsers(), eventName, eventType, parsedDate, price, eventImage.getOriginalFilename(), imageBytes);
            return "Event created and emails sent successfully!";
        } catch (ParseException | IOException | SQLException e) {
            e.printStackTrace();
            return "Failed to create event and send emails.";
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
    public String updateEvent(@PathVariable Long id, @RequestParam String eventName, @RequestParam String eventType,
                              @RequestParam String eventDate, @RequestParam String eventPrice, @RequestParam MultipartFile eventImage) {
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
