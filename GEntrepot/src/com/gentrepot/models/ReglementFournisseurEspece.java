/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gentrepot.models;

import java.util.Date;
import javafx.scene.control.CheckBox;

/**
 *
 * @author oussema
 */
public class ReglementFournisseurEspece extends ReglementFournisseur {
    
    private int id;
    private FactureAchat factureAchat;
    
     private int numeroF;
    private CheckBox checkBox = new CheckBox();

    public ReglementFournisseurEspece(int id, FactureAchat factureAchat, double montant, Date dateCreation) {
        super(montant, dateCreation);
        this.id = id;
        this.factureAchat = factureAchat;
         this.numeroF=factureAchat.getNumeroF();
    }

    public ReglementFournisseurEspece(FactureAchat factureAchat, double montant, Date dateCreation) {
        super(montant, dateCreation);
        this.factureAchat = factureAchat;
         this.numeroF=factureAchat.getNumeroF();
    }

    
    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public FactureAchat getFactureAchat() {
        return factureAchat;
    }

    public void setFactureAchat(FactureAchat factureAchat) {
        this.factureAchat = factureAchat;
    }

    @Override
    public String toString() {
        return "ReglementFournisseurEspece{" + "id=" + id + ", factureAchat=" + factureAchat.getNumeroF()+" " + super.toString()+'}';
    }

    public int getNumeroF() {
        return numeroF;
    }

    public void setNumeroF(int numeroF) {
        this.numeroF = numeroF;
    }

    public CheckBox getCheckBox() {
        return checkBox;
    }

    public void setCheckBox(CheckBox checkBox) {
        this.checkBox = checkBox;
    }
    
    
    
    
    
    
}
