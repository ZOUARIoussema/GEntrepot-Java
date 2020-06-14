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
public class BonE {
    private int id;
    String date;
    int  commandeDApprovisionnement;

    public BonE(int id, String date, int commandeDApprovisionnement) {
        this.id = id;
        this.date = date;
        this.commandeDApprovisionnement = commandeDApprovisionnement;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getCommandeDApprovisionnement() {
        return commandeDApprovisionnement;
    }

    public void setCommandeDApprovisionnement(int commandeDApprovisionnement) {
        this.commandeDApprovisionnement = commandeDApprovisionnement;
    }
    
}
