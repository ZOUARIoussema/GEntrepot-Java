/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gentrepot.services;

import com.gentrepot.models.Vehicule;
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
public class ServiceVehicule  implements IService<Vehicule> {
        
       
 Connection cnx = DataSource.getInstance().getCnx();

    @Override
    public void ajouter(Vehicule A) {
        try {
            String requete = "INSERT INTO vehicule (id,etat,matricule,capacite,type) VALUES (?,?,?,?,?)";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1, A.getId());
            pst.setString(2, A.getEtat());
             pst.setInt(3, A.getMatricule());
              pst.setInt(4, A.getCapacite());
               pst.setString(5, A.getType());
            pst.executeUpdate();
            System.out.println("vehicule ajouté !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public void supprimer(Vehicule A) {
        try {
            String requete = "DELETE FROM vehicule WHERE id=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1, A.getId());
            pst.executeUpdate();
            System.out.println("vehicule supprimée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        
    }

  
    public void modifier(Vehicule A,String E , Integer M ,Integer C , String T) {
         try {
            String requete = "UPDATE vehicule SET etat=?,matricule=?,type=?,capacite=? WHERE id=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
             pst.setInt(5, A.getId());
            pst.setString(1,E);
             pst.setInt(2,M);
              pst.setInt(4,C);
               pst.setString(3,T);
            pst.executeUpdate();
            System.out.println("vehicule modifiée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public ObservableList<Vehicule> afficher() {
        ObservableList<Vehicule> c = FXCollections.observableArrayList();
       List<Vehicule> list = new ArrayList<>();

        try {
            String requete = "SELECT * FROM vehicule";
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                c.add(new Vehicule(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4), rs.getString(5)));
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return c;
        
    }

    @Override
    public void modifier(Vehicule t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
