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
public class BonRetour {
    
    
    private int id;
    private Date date;
    private String motifDeRetour;
    private int commandeDApprovisionnement;

    public BonRetour(int id, Date date, String motifDeRetour, int commandeDApprovisionnement) {
        this.id = id;
        this.date = date;
        this.motifDeRetour = motifDeRetour;
        this.commandeDApprovisionnement = commandeDApprovisionnement;
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

    public String getMotifDeRetour() {
        return motifDeRetour;
    }

    public void setMotifDeRetour(String motifDeRetour) {
        this.motifDeRetour = motifDeRetour;
    }

    public int getCommandeDApprovisionnement() {
        return commandeDApprovisionnement;
    }

    public void setCommandeDApprovisionnement(int commandeDApprovisionnement) {
        this.commandeDApprovisionnement = commandeDApprovisionnement;
    }

    public BonRetour(Date date, String motifDeRetour, int commandeDApprovisionnement) {
        this.date = date;
        this.motifDeRetour = motifDeRetour;
        this.commandeDApprovisionnement = commandeDApprovisionnement;
    }

    public BonRetour(int id, String motifDeRetour, int commandeDApprovisionnement) {
        this.id = id;
        this.motifDeRetour = motifDeRetour;
        this.commandeDApprovisionnement = commandeDApprovisionnement;
    }

    @Override
    public String toString() {
        return "BonRetour{" + "id=" + id + ", date=" + date + ", motifDeRetour=" + motifDeRetour + ", commandeDApprovisionnement=" + commandeDApprovisionnement + '}';
    }
    
    
    
    
    
    
    
    
    
    
}
