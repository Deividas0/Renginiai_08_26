package org.example.Controller;

import org.example.Models.Klientas;
import org.example.Service.Eservice;
import org.example.Service.Kservice;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;

@CrossOrigin(origins = "http://localhost:5500", allowCredentials = "true")
@RestController
//@RequestMapping("/api")
public class Econtroller {

    Eservice eservice = new Eservice();
    Kservice kservice = new Kservice();


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
}
