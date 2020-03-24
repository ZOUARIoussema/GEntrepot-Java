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
    private Produit produit;
    private User user;
    private double prix;
    private int quantite;
    private double total;
    private double tva;

    public LigneCommande( CommandeVente commandeVente, Produit produit, User user, double prix, int quantite, double total, double tva) {
      
        this.commandeVente = commandeVente;
        this.produit = produit;
        this.user = user;
        this.prix = prix;
        this.quantite = quantite;
        this.total = total;
        this.tva = tva;
    }

    public LigneCommande(CommandeVente commandeVente, Produit produit, double prix, int quantite, double total, double tva) {
        this.commandeVente = commandeVente;
        this.produit = produit;
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

    public Produit getProduit() {
        return produit;
    }

    public void setProduit(Produit produit) {
        this.produit = produit;
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

    @Override
    public String toString() {
        return "LigneCommande{" + "id=" + id + ", produit=" + produit + ", user=" + user + ", prix=" + prix + ", quantite=" + quantite + ", total=" + total + ", tva=" + tva + '}';
    }
    
    
    
    
    
    
    
    
    
}
