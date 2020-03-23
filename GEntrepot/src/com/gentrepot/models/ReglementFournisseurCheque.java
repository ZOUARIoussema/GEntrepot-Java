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
public class ReglementFournisseurCheque extends ReglementFournisseur {
    
    private int id;
    private Date dateCheque;
    private int numeroCheque;
    private FactureAchat factureAchat;
    private int numeroF;
    private CheckBox checkBox = new CheckBox();

    public ReglementFournisseurCheque(int id, Date dateCheque, int numeroCheque, FactureAchat factureAchat, double montant, Date dateCreation) {
        super(montant, dateCreation);
        this.id = id;
        this.dateCheque = dateCheque;
        this.numeroCheque = numeroCheque;
        this.factureAchat = factureAchat;
        this.numeroF=factureAchat.getNumeroF();
    }

    public ReglementFournisseurCheque(Date dateCheque, int numeroCheque, FactureAchat factureAchat, double montant, Date dateCreation) {
        super(montant, dateCreation);
        this.dateCheque = dateCheque;
        this.numeroCheque = numeroCheque;
        this.factureAchat = factureAchat;
        this.numeroF=factureAchat.getNumeroF();
    }
    
    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDateCheque() {
        return dateCheque;
    }

    public void setDateCheque(Date dateCheque) {
        this.dateCheque = dateCheque;
    }

    public int getNumeroCheque() {
        return numeroCheque;
    }

    public void setNumeroCheque(int numeroCheque) {
        this.numeroCheque = numeroCheque;
    }

    public FactureAchat getFactureAchat() {
        return factureAchat;
    }

    public void setFactureAchat(FactureAchat factureAchat) {
        this.factureAchat = factureAchat;
    }

    @Override
    public String toString() {
        return "ReglementFournisseurCheque{" + "id=" + id + ", dateCheque=" + dateCheque + ", numeroCheque=" + numeroCheque + ", factureAchat=" + factureAchat.getNumeroF()+" "+super.toString() + '}';
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
