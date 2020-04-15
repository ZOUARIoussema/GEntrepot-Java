/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gentrepot.services;

import com.gentrepot.models.Chauffeur;
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
public class ServiceChauffeur   implements IService<Chauffeur> {
        
      
 
    Connection cnx = DataSource.getInstance().getCnx();

    @Override
    public void ajouter(Chauffeur A) {
         try {
            String requete = "INSERT INTO chauffeur (cin,nom,prenom,adresse,voyage,etat) VALUES (?,?,?,?,?,?)";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setString(1, A.getCin());
            pst.setString(2, A.getNom());
             pst.setString(3, A.getPrenom());
              pst.setString(4, A.getAdresse());
               pst.setInt(5, A.getVoyage());
                pst.setString(6, A.getEtat());
            pst.executeUpdate();
            System.out.println("chauffeur ajouté !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public void supprimer(Chauffeur A) {
        try {
            String requete = "DELETE FROM chauffeur WHERE cin=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setString(1, A.getCin());
            pst.executeUpdate();
            System.out.println("chauffeur supprimée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        
    }

   
    public void modifier(Chauffeur A,String n,String p,String a,String e) {
        try {
            String requete = "UPDATE chauffeur SET nom=?,prenom=?,adresse=?,etat=? WHERE cin=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setString(5, A.getCin());
            pst.setString(1, n);
            pst.setString(2, p);
             pst.setString(3, a);
          pst.setString(4, e);
            pst.executeUpdate();
            System.out.println("chauffeur modifiée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        
    }

    @Override
    public ObservableList<Chauffeur> afficher() {
         ObservableList<Chauffeur> c = FXCollections.observableArrayList();
         
       List<Chauffeur> list = new ArrayList<>();

        try {
            String requete = "SELECT * FROM chauffeur";
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                c.add(new Chauffeur(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), 0, rs.getString(5)));
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return c;
        
         
    }

    @Override
    public void modifier(Chauffeur t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
