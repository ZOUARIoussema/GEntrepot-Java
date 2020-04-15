/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gentrepot.models;

/**
 *
 * @author guiforodrigue
 */
public class Emplace {
    private int id;
    private String adresse;
    private int capaciteStockage;
    private int quantiteStocker;
    private String classe;

    public Emplace(int id, String adresse, int capaciteStockage, int quantiteStocker, String classe) {
        this.id = id;
        this.adresse = adresse;
        this.capaciteStockage = capaciteStockage;
        this.quantiteStocker = quantiteStocker;
        this.classe = classe;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public int getCapaciteStockage() {
        return capaciteStockage;
    }

    public void setCapaciteStockage(int capaciteStockage) {
        this.capaciteStockage = capaciteStockage;
    }

    public int getQuantiteStocker() {
        return quantiteStocker;
    }

    public void setQuantiteStocker(int quantiteStocker) {
        this.quantiteStocker = quantiteStocker;
    }

    public String getClasse() {
        return classe;
    }

    public void setClasse(String classe) {
        this.classe = classe;
    }
    
}
