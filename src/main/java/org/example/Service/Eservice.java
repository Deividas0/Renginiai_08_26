package org.example.Service;

import org.example.Repository.Erepository;
import org.example.Repository.Krepository;

public class Eservice {
    Erepository erepository = new Erepository();

    public String registracijosLaiskas(String vardas, String elPastas){
        return erepository.registracijosLaiskas(vardas, elPastas);
    }
    public String uzsisakePrenumerataLaiskas(String vardas, String elPastas){
        return erepository.uzsisakePrenumerataLaiskas(vardas, elPastas);
    }
}
