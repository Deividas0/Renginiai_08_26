package org.example.Models;

public class Klientas {
    private String vardas;
    private String pavarde;
    private String elPastas;
    private long telNumeris;
    private String slaptazodis;

    public Klientas(String vardas, String pavarde, String elPastas, long telNumeris, String slaptazodis) {
        this.vardas = vardas;
        this.pavarde = pavarde;
        this.elPastas = elPastas;
        this.telNumeris = telNumeris;
        this.slaptazodis = slaptazodis;
    }

    public Klientas(String vardas, String pavarde, String elPastas, String slaptazodis) {
        this.vardas = vardas;
        this.pavarde = pavarde;
        this.elPastas = elPastas;
        this.slaptazodis = slaptazodis;
    }

    public Klientas() {
    }

    public String getVardas() {return vardas;}
    public void setVardas(String vardas) {this.vardas = vardas;}

    public String getPavarde() {return pavarde;}
    public void setPavarde(String pavarde) {this.pavarde = pavarde;}

    public String getElPastas() {return elPastas;}
    public void setElPastas(String elPastas) {this.elPastas = elPastas;}

    public long getTelNumeris() {return telNumeris;}
    public void setTelNumeris(long telNumeris) {this.telNumeris = telNumeris;}

    public String getSlaptazodis() {return slaptazodis;}
    public void setSlaptazodis(String slaptazodis) {this.slaptazodis = slaptazodis;}
}

