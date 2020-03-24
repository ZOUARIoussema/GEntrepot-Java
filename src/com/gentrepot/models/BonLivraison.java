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
public class BonLivraison {
    
    
    
    private int id;
    private OrdreMission ordreMission;
    private String adresseLivraison;
    private String etat;
    private Date dateCreation;
    private Date dateSortie;
    private String nom;
    private String prenom;
    private CommandeVente commandeVente;

    public BonLivraison(int id, OrdreMission ordreMission, String adresseLivraison, String etat, Date dateCreation, Date dateSortie, String nom, String prenom, CommandeVente commandeVente) {
        this.id = id;
        this.ordreMission = ordreMission;
        this.adresseLivraison = adresseLivraison;
        this.etat = etat;
        this.dateCreation = dateCreation;
        this.dateSortie = dateSortie;
        this.nom = nom;
        this.prenom = prenom;
        this.commandeVente = commandeVente;
    }

    public int getId() {
        return id;
    }

    public BonLivraison(String adresseLivraison, String etat, Date dateCreation, String nom, String prenom, CommandeVente commandeVente) {
        this.adresseLivraison = adresseLivraison;
        this.etat = etat;
        this.dateCreation = dateCreation;
        this.nom = nom;
        this.prenom = prenom;
        this.commandeVente = commandeVente;
    }

    public void setId(int id) {
        this.id = id;
    }

    public OrdreMission getOrdreMission() {
        return ordreMission;
    }

    public void setOrdreMission(OrdreMission ordreMission) {
        this.ordreMission = ordreMission;
    }

    public String getAdresseLivraison() {
        return adresseLivraison;
    }

    public void setAdresseLivraison(String adresseLivraison) {
        this.adresseLivraison = adresseLivraison;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public Date getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }

    public Date getDateSortie() {
        return dateSortie;
    }

    public void setDateSortie(Date dateSortie) {
        this.dateSortie = dateSortie;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public CommandeVente getCommandeVente() {
        return commandeVente;
    }

    public void setCommandeVente(CommandeVente commandeVente) {
        this.commandeVente = commandeVente;
    }
    
   
    
    
    
}
