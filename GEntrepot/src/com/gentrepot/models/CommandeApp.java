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
public class CommandeApp {
    private int numero;
    private String date;    
    private String fournisseur;
    private double remise;
    private double ptht;
    private double pttc;
    private String etat;

    public CommandeApp(int numero, String date, String fournisseur, double remise, double ptht, double pttc, String etat) {
        this.numero = numero;
        this.date = date;
        this.fournisseur = fournisseur;
        this.remise = remise;
        this.ptht = ptht;
        this.pttc = pttc;
        this.etat = etat;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getFournisseur() {
        return fournisseur;
    }

    public void setFournisseur(String fournisseur) {
        this.fournisseur = fournisseur;
    }

    public double getRemise() {
        return remise;
    }

    public void setRemise(double remise) {
        this.remise = remise;
    }

    public double getPtht() {
        return ptht;
    }

    public void setPtht(double ptht) {
        this.ptht = ptht;
    }

    public double getPttc() {
        return pttc;
    }

    public void setPttc(double pttc) {
        this.pttc = pttc;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }
    
    
    
    
}
