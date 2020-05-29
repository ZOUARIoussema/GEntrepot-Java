/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gentrepot.services;

import com.gentrepot.models.BonLivraison;
import com.gentrepot.models.CommandeVente;
import com.gentrepot.utils.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author oussema
 */
public class ServiceBonLivraison {
    
    
    ServiceCommandeVente serviceCommandeVente = new ServiceCommandeVente();
    
     Connection cnx = DataSource.getInstance().getCnx();

    public void ajouterBon(BonLivraison b) {
        try {
            String requete = "INSERT INTO  bon_livraison (adresseLivraison,etat,dateCreation,nom,prenom,idC_Commande) VALUES (?,?,?,?,?,?)";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setString(1, b.getAdresseLivraison());

            pst.setString(2, b.getEtat());
            pst.setDate(3,new java.sql.Date(b.getDateCreation().getTime()));
            pst.setString(4, b.getNom());
            pst.setString(5, b.getPrenom());
            pst.setInt(6, b.getCommandeVente().getId());

            pst.executeUpdate();
            System.out.println("Bon Livraison ajouté !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    
      public List<BonLivraison> afficherBon() {
        List<BonLivraison> bon = new ArrayList<>();


        try {
            String requete = "SELECT * FROM bon_livraison";
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                
                Integer id =rs.getInt("id");
                String adresseLivraison = rs.getString("adresseLivraison");
                String etat = rs.getString("etat");
                java.util.Date dateC = rs.getDate("dateCreation");
                java.util.Date dates = rs.getDate("datesortie");
                String nom = rs.getString("nom");
                String prenom = rs.getString("prenom");
                

                

                BonLivraison bl = new BonLivraison(id,adresseLivraison, etat, dateC,dates, nom, prenom);
                
                bl.setCommandeVente(serviceCommandeVente.findById(rs.getInt(9)));
                
                bon.add(bl);
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return bon;

    }
      
      
        public void modifierBonLiv(BonLivraison b) {
        try {
            String requete
                    = "UPDATE bon_livraison SET etat=? WHERE id=?";
            PreparedStatement pst
                    = cnx.prepareStatement(requete);
            pst.setString(1, b.getEtat());
             pst.setInt(2, b.getId());

            pst.executeUpdate();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

    }
        public BonLivraison findById(int id){
        
        BonLivraison bo =null;
        
        
        
        for(BonLivraison b : this.afficherBon()){
            
            
            if(b.getId()==id){
                
                bo=b;
                return bo;
            }
        }
        
        return bo;
    }
    
    
}
