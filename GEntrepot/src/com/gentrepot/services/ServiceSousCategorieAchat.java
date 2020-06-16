/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gentrepot.services;

import com.gentrepot.models.CategorieAchat;
import com.gentrepot.models.SousCategorieAchat;
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
public class ServiceSousCategorieAchat  implements IService<SousCategorieAchat> {
        
       
    Connection cnx = DataSource.getInstance().getCnx();

    @Override
    public void ajouter(SousCategorieAchat t) {
        try {
            String requete = "INSERT INTO sous_categorie_achat (name,Categorie_id ) VALUES (?,?)";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setString(1, t.getNom());
            pst.setInt(2, t.getCategorieAchat());
            pst.executeUpdate();
            System.out.println("Sous categorie ajoutée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public void supprimer(SousCategorieAchat t) {
        try {
            String requete = "DELETE FROM sous_categorie_achat WHERE id=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1, t.getId());
            pst.executeUpdate();
            System.out.println("Sous categorie supprimée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public void modifier(SousCategorieAchat t) {
        try {
            String requete = "UPDATE sous_categorie_achat SET name=?,categorieAchat=? WHERE id=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(3, t.getId());
            pst.setString(1, t.getNom());
            pst.setInt(2, t.getCategorieAchat());
            pst.executeUpdate();
            System.out.println("Sous categorie modifiée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public ObservableList<SousCategorieAchat> afficher() {
        List<SousCategorieAchat> list = new ArrayList<>();
      ObservableList<SousCategorieAchat> c = FXCollections.observableArrayList();

        try {
            String requete = "SELECT * FROM sous_categorie_achat";
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                c.add(new SousCategorieAchat(rs.getInt(1), rs.getString(2), new CategorieAchat(rs.getInt(3)).getId()));
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return c;
    }
    
}
