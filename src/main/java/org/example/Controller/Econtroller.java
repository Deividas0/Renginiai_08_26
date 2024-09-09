package org.example.Controller;

import org.example.Models.Klientas;
import org.example.Service.Eservice;
import org.example.Service.Kservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

@CrossOrigin(origins = "http://localhost:5500", allowCredentials = "true")
@RestController
public class Econtroller {

    @Autowired
    private Eservice eservice;

    @Autowired
    private Kservice kservice;

    @PostMapping("/email/prisiregistravo")
    public ResponseEntity<String> sendEmail(@RequestBody Klientas k) {
        eservice.registracijosLaiskas(k.getVardas(), k.getElPastas());
        return new ResponseEntity<>("success", HttpStatus.OK);
    }

    @PostMapping("/email/uzsisakeprenumerata")
    public ResponseEntity<String> uzsisakePrenumerata(@RequestBody Klientas k) throws SQLException {
        kservice.prenumeratosUzsisakymas(k.getElPastas());
        eservice.uzsisakePrenumerataLaiskas(k.getVardas(), k.getElPastas());
        return new ResponseEntity<>("success", HttpStatus.OK);
    }

    @PostMapping("/send-event-emails")
    public String sendEmailsForNewEvent(String eventName, String eventType, Date eventDate, double eventPrice, String fileName, byte[] eventImage) {
        try {
            List<Klientas> subscribedUsers = kservice.getSubscribedUsers();
            eservice.sendNewEventEmail(subscribedUsers, eventName, eventType, eventDate, eventPrice, fileName, eventImage);
            return "Emails sent successfully!";
        } catch (SQLException e) {
            e.printStackTrace();
            return "Failed to send emails.";
        }
    }
}
