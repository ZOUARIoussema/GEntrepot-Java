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
public class BonEntree {
    
    
    
    private int id;
    private Date date;
    private Date dateProduction;
    private Date dateExpiration;
    private int commandeDApprovisionnement;

    public BonEntree(int id, Date date, Date dateProduction, Date dateExpiration, int commandeDApprovisionnement) {
        this.id = id;
        this.date = date;
        this.dateProduction = dateProduction;
        this.dateExpiration = dateExpiration;
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

    public Date getDateProduction() {
        return dateProduction;
    }

    @Override
    public String toString() {
        return "BonEntree{" + "id=" + id + ", date=" + date + ", dateProduction=" + dateProduction + ", dateExpiration=" + dateExpiration + ", commandeDApprovisionnement=" + commandeDApprovisionnement + '}';
    }

    public void setDateProduction(Date dateProduction) {
        this.dateProduction = dateProduction;
    }

    public Date getDateExpiration() {
        return dateExpiration;
    }

    public void setDateExpiration(Date dateExpiration) {
        this.dateExpiration = dateExpiration;
    }

    public int getCommandeDApprovisionnement() {
        return commandeDApprovisionnement;
    }

    public void setCommandeDApprovisionnement(int commandeDApprovisionnement) {
        this.commandeDApprovisionnement = commandeDApprovisionnement;
    }

    public BonEntree(Date date, Date dateProduction, Date dateExpiration, int commandeDApprovisionnement) {
        this.date = date;
        this.dateProduction = dateProduction;
        this.dateExpiration = dateExpiration;
        this.commandeDApprovisionnement = commandeDApprovisionnement;
    }
    
    
    
    
    
    
    
    
            
    
    
    
}
