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
public class RecouvrementClient {
    
    private double montant;
    private Date dateCreation;

    public RecouvrementClient(double montant, Date dateCreation) {
        this.montant = montant;
        this.dateCreation = dateCreation;
    }

    public double getMontant() {
        return montant;
    }

    public void setMontant(double montant) {
        this.montant = montant;
    }

    public Date getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }
    
    
    
    
    
    
    
    
}
