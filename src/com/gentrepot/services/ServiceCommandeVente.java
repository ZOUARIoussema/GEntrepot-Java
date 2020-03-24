/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gentrepot.services;

import com.gentrepot.models.CommandeVente;
import com.gentrepot.models.LigneCommande;
import com.gentrepot.utils.DataSource;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author oussema
 */
public class ServiceCommandeVente {
    
    
    
    //methode recupere id 
    Connection cnx = DataSource.getInstance().getCnx();

    
    public int recupere() {
        
        
        int id=0;
        
        try {
            String requete = "SELECT max(`id`) from commande_vente";
            PreparedStatement pst = cnx.prepareStatement(requete);
           
             ResultSet rs= pst.executeQuery();
             
             while(rs.next()){
                 
                 id=rs.getInt(1);
                 
                 
             }
           

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        
        return id;
        
    }
    
    
    //ajouter
     public void ajouterCommande(CommandeVente c) {
        try {
            String requete = "INSERT INTO commande_vente (id,totalC,dateC) VALUES (?,?,?)";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1, c.getId());
            pst.setDouble(2, c.getTotalC());
            pst.setDate(3,new java.sql.Date(c.getDateC().getTime()));

            pst.executeUpdate();
            System.out.println("Commande  ajoutée !");
            
            
            
            
            
            

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        
       
        
        //ajouter ligne commande
       
       ServiceLigneCommande serviceLigneCommande = new ServiceLigneCommande();
       
       
       
       for(LigneCommande ligne :c.getLigneCommande() ){
           
           
           
           
           serviceLigneCommande.ajouter(ligne);
           
           
           
        
        
        
    }
    
        
    }
    
    
  
    
    
 public void supprimerCommande(CommandeVente c) {
try {
              String req =
                      "DELETE FROM commande WHERE id_commande=?";    
             
              PreparedStatement ps = 
                      cnx.prepareStatement(req);
              ps.setInt(1, c.getId());
              ps.executeUpdate();
           
          } catch (SQLException ex) {
            System.err.println(ex.getMessage());
          }  }
        
    public List<CommandeVente> afficherCommande(){
    List<CommandeVente> commande =new ArrayList<>();
    
    ServiceLigneCommande ligneService = new ServiceLigneCommande();
    
    try {
            String requete = "SELECT * FROM commande_vente";
     PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
    

     while (rs.next()) {                
               int id=rs.getInt("id");
                int totalC=rs.getInt("totalC");
               java.util.Date dateC=rs.getDate("dateC");
                String etat=rs.getString("etat");

               int tauxRemise=rs.getInt("tauxRemise");

               CommandeVente co =new CommandeVente(id, totalC, dateC, etat, tauxRemise);
               co.getLigneCommande().addAll(ligneService.RecupereParCommande(co));
     commande.add(co);
         }

     }catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    return commande;
    }}