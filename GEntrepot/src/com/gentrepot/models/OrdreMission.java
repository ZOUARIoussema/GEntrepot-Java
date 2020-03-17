/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gentrepot.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author oussema
 */
public class OrdreMission {
    
    
    private int id;
    private Vehicule vehicule;
    private Chauffeur chauffeur;
    private AideChauffeur aideChauffeur;
    private Date dateCeation;
    private Date dateSortie;
    private Date dateRetour;
    
    private List<BonLivraison> bonLivraisons;

    public OrdreMission(int id, Vehicule vehicule, Chauffeur chauffeur, AideChauffeur aideChauffeur, Date dateCeation, Date dateSortie, Date dateRetour) {
        this.id = id;
        this.vehicule = vehicule;
        this.chauffeur = chauffeur;
        this.aideChauffeur = aideChauffeur;
        this.dateCeation = dateCeation;
        this.dateSortie = dateSortie;
        this.dateRetour = dateRetour;
        this.bonLivraisons=new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Vehicule getVehicule() {
        return vehicule;
    }

    public void setVehicule(Vehicule vehicule) {
        this.vehicule = vehicule;
    }

    public Chauffeur getChauffeur() {
        return chauffeur;
    }

    public void setChauffeur(Chauffeur chauffeur) {
        this.chauffeur = chauffeur;
    }

    public AideChauffeur getAideChauffeur() {
        return aideChauffeur;
    }

    public void setAideChauffeur(AideChauffeur aideChauffeur) {
        this.aideChauffeur = aideChauffeur;
    }

    public Date getDateCeation() {
        return dateCeation;
    }

    public void setDateCeation(Date dateCeation) {
        this.dateCeation = dateCeation;
    }

    public Date getDateSortie() {
        return dateSortie;
    }

    public void setDateSortie(Date dateSortie) {
        this.dateSortie = dateSortie;
    }

    public Date getDateRetour() {
        return dateRetour;
    }

    public void setDateRetour(Date dateRetour) {
        this.dateRetour = dateRetour;
    }

    public List<BonLivraison> getBonLivraisons() {
        return bonLivraisons;
    }

    public void setBonLivraisons(List<BonLivraison> bonLivraisons) {
        this.bonLivraisons = bonLivraisons;
    }
    
    
    
    
    
    
    
    
    
    
    
}
