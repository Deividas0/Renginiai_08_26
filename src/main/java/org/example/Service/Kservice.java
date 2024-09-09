package org.example.Service;

import org.example.Controller.Kcontroller;
import org.example.Models.Klientas;
import org.example.Repository.Krepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service // Add this annotation
public class Kservice {

    @Autowired
    private Krepository krepository;

    public void klientoRegistracija(String vardas, String pavarde, String elPastas, long telNumeris, String slaptazodis) throws SQLException {
        krepository.klientoRegistracija(vardas,pavarde,elPastas,telNumeris, slaptazodis);
    }
    public int login(String elPastas, String slaptazodis) throws SQLException {
        return krepository.login(elPastas,slaptazodis);
    }
    public int prenumeratosPatikra(int id) throws SQLException {
        return krepository.prenumeratosPatikra(id);
    }
    public String emailPatikra(String elPastas) throws SQLException {
       return krepository.emailPatikra(elPastas);
    }
    public void prenumeratosAtsisakymas(String elPastas) throws SQLException {
        krepository.prenumeratosAtsisakymas(elPastas);
    }
    public void prenumeratosUzsisakymas(String elPastas) throws SQLException {
        krepository.prenumeratosUzsisakymas(elPastas);
    }
    public String emailParsinesimas(int id) throws SQLException {
        return krepository.emailParsinesimas(id);
    }

    // CHAT GPT ---------------------------------------------

    // Fetch the subscribed clients
    public List<Klientas> getSubscribedUsers() throws SQLException {
        return krepository.getSubscribedUsers();
    }
}
