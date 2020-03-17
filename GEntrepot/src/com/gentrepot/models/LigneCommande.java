/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gentrepot.models;

/**
 *
 * @author oussema
 */
public class LigneCommande {
    
    private int id;
    private CommandeVente commandeVente;
    private ProduitAchat produitAchat;
    private User user;
    private double prix;
    private int quantite;
    private double total;
    private double tva;

    public LigneCommande(int id, CommandeVente commandeVente, ProduitAchat produitAchat, User user, double prix, int quantite, double total, double tva) {
        this.id = id;
        this.commandeVente = commandeVente;
        this.produitAchat = produitAchat;
        this.user = user;
        this.prix = prix;
        this.quantite = quantite;
        this.total = total;
        this.tva = tva;
    }

    
    
    
    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public CommandeVente getCommandeVente() {
        return commandeVente;
    }

    public void setCommandeVente(CommandeVente commandeVente) {
        this.commandeVente = commandeVente;
    }

    public ProduitAchat getProduitAchat() {
        return produitAchat;
    }

    public void setProduitAchat(ProduitAchat produitAchat) {
        this.produitAchat = produitAchat;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public double getTva() {
        return tva;
    }

    public void setTva(double tva) {
        this.tva = tva;
    }
    
    
    
    
    
    
    
    
    
}
