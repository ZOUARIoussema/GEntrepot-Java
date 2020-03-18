/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gentrepot.services;

import com.gentrepot.models.FactureAchat;
import com.gentrepot.models.FactureVente;
import com.gentrepot.models.RecouvrementClientEspece;
import com.gentrepot.models.ReglementFournisseurEspece;
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
public class ServiceRecouvrementClientEspece implements IService<RecouvrementClientEspece> {

    
      Connection cnx = DataSource.getInstance().getCnx();
    
    
    
    @Override
    public void ajouter(RecouvrementClientEspece r) {
        
        
         try {
            String requete = "INSERT INTO recouvrement_client_espece (montant,date_creation,idF_factureVente) VALUES (?,?,?)";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setDouble(1, r.getMontant());
            pst.setDate(2, new java.sql.Date(r.getDateCreation().getTime()));
            pst.setInt(3, r.getFactureVente().getNumeroF());
            pst.executeUpdate();
            System.out.println("Recouvrement client espece ajoutée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        
    }

    @Override
    public void supprimer(RecouvrementClientEspece r) {
        
        try {
            String requete = "DELETE FROM recouvrement_client_espece WHERE id=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1, r.getId());
            pst.executeUpdate();
            System.out.println("Recouvrement Client espece supprimée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        
       
    }

    @Override
    public void modifier(RecouvrementClientEspece r) {
        
        
          try {
            String requete = "UPDATE recouvrement_client_espece SET montant=?,date_creation=?,idF_factureVente=? WHERE id=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setDouble(1, r.getMontant());
            pst.setDate(2, new java.sql.Date(r.getDateCreation().getTime()));
            pst.setInt(3,r.getFactureVente().getNumeroF());
            pst.setInt(4,r.getId());
            pst.executeUpdate();
            System.out.println("Recouvrement Client espece modifiée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        
        
 
        
        
    }

    @Override
    public List<RecouvrementClientEspece> afficher() {
        
        
         List<RecouvrementClientEspece> list = new ArrayList<>();

        try {
            String requete = "SELECT * FROM recouvrement_client_espece";
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                list.add(new RecouvrementClientEspece(rs.getInt(1), new FactureVente(rs.getInt(4)), rs.getDouble(2),rs.getDate(3)));
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return list;
    }
       
    
    
     public double totalEspece(){
        
        double total =0;
        
        try {
            String requete = "SELECT sum(montant) FROM recouvrement_client_espece where date_creation=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setDate(1, new java.sql.Date(new java.util.Date().getTime()));
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                
                total=rs.getDouble(1);
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
       
        
        return total;
    }
        
    
    
}
