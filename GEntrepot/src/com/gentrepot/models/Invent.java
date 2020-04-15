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
public class Invent {
    private int id;
    private String produitAchat;
    private String emplacement;
    private String dateCreation;
    private int qunatiteInventiare;
    private int ecart;
    private int quantiteTheorique;

    public Invent(int id, String produitAchat, String emplacement, String dateCreation, int qunatiteInventiare, int ecart, int quantiteTheorique) {
        this.id = id;
        this.produitAchat = produitAchat;
        this.emplacement = emplacement;
        this.dateCreation = dateCreation;
        this.qunatiteInventiare = qunatiteInventiare;
        this.ecart = ecart;
        this.quantiteTheorique = quantiteTheorique;
    }
    public Invent(String produitAchat, String emplacement, String dateCreation, int qunatiteInventiare, int ecart, int quantiteTheorique) {
        
        this.produitAchat = produitAchat;
        this.emplacement = emplacement;
        this.dateCreation = dateCreation;
        this.qunatiteInventiare = qunatiteInventiare;
        this.ecart = ecart;
        this.quantiteTheorique = quantiteTheorique;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProduitAchat() {
        return produitAchat;
    }

    public void setProduitAchat(String produitAchat) {
        this.produitAchat = produitAchat;
    }

    public String getEmplacement() {
        return emplacement;
    }

    public void setEmplacement(String emplacement) {
        this.emplacement = emplacement;
    }

    public String getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(String dateCreation) {
        this.dateCreation = dateCreation;
    }

    public int getQunatiteInventiare() {
        return qunatiteInventiare;
    }

    public void setQunatiteInventiare(int qunatiteInventiare) {
        this.qunatiteInventiare = qunatiteInventiare;
    }

    public int getEcart() {
        return ecart;
    }

    public void setEcart(int ecart) {
        this.ecart = ecart;
    }

    public int getQuantiteTheorique() {
        return quantiteTheorique;
    }

    public void setQuantiteTheorique(int quantiteTheorique) {
        this.quantiteTheorique = quantiteTheorique;
    }
    
    
    
}
