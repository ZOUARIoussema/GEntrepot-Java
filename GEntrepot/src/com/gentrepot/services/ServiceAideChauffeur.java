/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gentrepot.services;

import com.gentrepot.models.AideChauffeur;
import com.gentrepot.utils.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author oussema
 */
public class ServiceAideChauffeur implements IService<AideChauffeur>{
        
        
    
    Connection cnx = DataSource.getInstance().getCnx();

    @Override
    public void ajouter(AideChauffeur A) {
        
        try {
            String requete = "INSERT INTO aide_chauffeur (cin,nom,prenom,adresse) VALUES (?,?,?,?)";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setString(1, A.getCin());
            pst.setString(2, A.getNom());
             pst.setString(3, A.getPrenom());
              pst.setString(4, A.getAdresse());
            pst.executeUpdate();
            System.out.println("Aidechauffeur ajouté !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        
    }

    @Override
    public void supprimer(AideChauffeur A) {
        
         try {
            String requete = "DELETE FROM aide_chauffeur WHERE cin=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setString(1, A.getCin());
            pst.executeUpdate();
            System.out.println("Aidechauffeur supprimée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        
    }

    
    public void modifier(AideChauffeur A,String n,String p,String a) {
        
         try {
            String requete = "UPDATE aide_chauffeur SET nom=?,prenom=?,adresse=? WHERE cin=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setString(4, A.getCin());
            pst.setString(1,n);
            pst.setString(2, p);
             pst.setString(3, a);
            pst.executeUpdate();
            System.out.println("aidechauffeur modifiée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        
        
    }

    @Override
    public ObservableList<AideChauffeur> afficher() {
              ObservableList<AideChauffeur> c = FXCollections.observableArrayList();
        
          List<AideChauffeur> list = new ArrayList<>();

        try {
            String requete = "SELECT * FROM aide_chauffeur";
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                c.add(new AideChauffeur(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4)));
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return c;
        
         
    }

    @Override
    public void modifier(AideChauffeur t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
