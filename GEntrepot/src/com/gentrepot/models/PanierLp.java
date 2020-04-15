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
public class PanierLp {
    private String produit;
    private int quantite;
    private String raisonPerte;

    public PanierLp(String produit, int quantite, String raisonPerte) {
        this.produit = produit;
        this.quantite = quantite;
        this.raisonPerte = raisonPerte;
    }

    public String getProduit() {
        return produit;
    }

    public void setProduit(String produit) {
        this.produit = produit;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public String getRaisonPerte() {
        return raisonPerte;
    }

    public void setRaisonPerte(String raisonPerte) {
        this.raisonPerte = raisonPerte;
    }
    
    
}
