package org.example.Controller;

import io.jsonwebtoken.Claims;
import org.example.JwtDecoder;
import org.example.JwtGenerator;
import org.example.Models.Klientas;
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
public class Kcontroller {
    Kservice kService = new Kservice();

    @PostMapping("/klientas/registracija")
    public ResponseEntity<String> klientoRegistracija(@RequestBody Klientas k) throws SQLException {
        kService.klientoRegistracija(k.getVardas(), k.getPavarde(), k.getElPastas(), k.getTelNumeris(),k.getSlaptazodis());
        return new ResponseEntity<>("success", HttpStatus.OK);
    }
    @PostMapping("/klientas/login")
    public ResponseEntity<String> login(@RequestBody Klientas k) throws SQLException {
        int id = kService.login(k.getElPastas(), k.getSlaptazodis());
        return (id == 0) ?
                new ResponseEntity<>("Neteisingi duomenys", HttpStatus.BAD_REQUEST) :
                new ResponseEntity<>(JwtGenerator.generateJwt(k.getSlaptazodis(), k.getSlaptazodis(), id), HttpStatus.OK);
    }
    @PostMapping("klientas/prenumerata")
    public int prenumeratosPatikra(@RequestBody String jwtToken) throws SQLException {
        Claims claim = JwtDecoder.decodeJwt(jwtToken);
        int id = Integer.parseInt(claim.get("UserId").toString());
        return kService.prenumeratosPatikra(id);
    }
    @PostMapping("klientas/adminpatikra")
    public ResponseEntity<String> adminPatikra(@RequestBody String jwtToken) throws SQLException {
        Claims claim = JwtDecoder.decodeJwt(jwtToken);
//        int id = Integer.parseInt(claim.get("UserId").toString());
        String vardas = claim.get("Username").toString();
        return (vardas.isEmpty()) ?
                new ResponseEntity<>("Vartotojas nerastas", HttpStatus.BAD_REQUEST) :
                new ResponseEntity<>(vardas, HttpStatus.OK);
    }

    @PostMapping("klientas/emailpatikra")
    public String emailPatikra(@RequestBody Klientas k) throws SQLException {
        return kService.emailPatikra(k.getElPastas());
    }
    @PostMapping("klientas/prenumeratosatsisakymas")
    public void prenumeratosAtsisakymas(@RequestBody String jwtToken) throws SQLException {
        Claims claim = JwtDecoder.decodeJwt(jwtToken);
        int id = Integer.parseInt(claim.get("UserId").toString());
        String elPastas = kService.emailParsinesimas(id);
        kService.prenumeratosAtsisakymas(elPastas);
    }
    @PostMapping("klientas/prenumeratosuzsisakymas")
    public void prenumeratosUzsisakymas(@RequestBody Klientas k) throws SQLException {
        kService.prenumeratosUzsisakymas(k.getElPastas());
    }

}
