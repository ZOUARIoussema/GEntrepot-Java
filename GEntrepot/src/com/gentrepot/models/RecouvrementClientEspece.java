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
public class RecouvrementClientEspece extends RecouvrementClient {

    private int id;
    private FactureVente factureVente;
    private int numeroF;
    private CheckBox checkBox = new CheckBox();

    public RecouvrementClientEspece(int id, FactureVente factureVente, double montant, Date dateCreation) {
        super(montant, dateCreation);
        this.id = id;
        this.factureVente = factureVente;
        this.numeroF = factureVente.getNumeroF();
    }

    public RecouvrementClientEspece(FactureVente factureVente, double montant, Date dateCreation) {
        super(montant, dateCreation);
        this.factureVente = factureVente;
        this.numeroF = factureVente.getNumeroF();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public FactureVente getFactureVente() {
        return factureVente;
    }

    public void setFactureVente(FactureVente factureVente) {
        this.factureVente = factureVente;
    }

    @Override
    public String toString() {
        return "RecouvrementClientEspece{" + "id=" + id + ", factureVente=" + factureVente.getNumeroF()
                + "  " + super.toString() + '}';
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
