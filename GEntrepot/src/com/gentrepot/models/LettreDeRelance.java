/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gentrepot.models;

import java.util.Date;

/**
 *
 * @author oussema
 */
public class LettreDeRelance {
    
    
    private int id;
    private Date date;
    private FactureVente factureVente;

    public LettreDeRelance(int id, Date date, FactureVente factureVente) {
        this.id = id;
        this.date = date;
        this.factureVente = factureVente;
    }

    public LettreDeRelance(Date date, FactureVente factureVente) {
        this.date = date;
        this.factureVente = factureVente;
    }
    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public FactureVente getFactureVente() {
        return factureVente;
    }

    public void setFactureVente(FactureVente factureVente) {
        this.factureVente = factureVente;
    }

    @Override
    public String toString() {
        return "LettreDeRelance{" + "id=" + id + ", date=" + date + ", factureVente=" + factureVente.getNumeroF() + '}';
    }
    
    
    
    
    
    
    
    
    
    
}
