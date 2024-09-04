package org.example.Models;

import java.time.LocalDateTime;

public class Renginys {
    private int id;
    private String pavadinimas;
    private String tipas;
    private LocalDateTime data;
    private double kaina;
    private byte[] image; // New field for storing image data

    public Renginys(int id, String pavadinimas, String tipas, LocalDateTime data, double kaina, byte[] image) {
        this.id = id;
        this.pavadinimas = pavadinimas;
        this.tipas = tipas;
        this.data = data;
        this.kaina = kaina;
        this.image = image;
    }

    public Renginys(String pavadinimas, String tipas, LocalDateTime data, double kaina, byte[] image) {
        this.pavadinimas = pavadinimas;
        this.tipas = tipas;
        this.data = data;
        this.kaina = kaina;
        this.image = image;
    }

    public Renginys() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPavadinimas() {
        return pavadinimas;
    }

    public void setPavadinimas(String pavadinimas) {
        this.pavadinimas = pavadinimas;
    }

    public String getTipas() {
        return tipas;
    }

    public void setTipas(String tipas) {
        this.tipas = tipas;
    }

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }

    public double getKaina() {
        return kaina;
    }

    public void setKaina(double kaina) {
        this.kaina = kaina;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
}
