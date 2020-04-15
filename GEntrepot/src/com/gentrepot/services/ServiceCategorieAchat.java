/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gentrepot.services;

import com.gentrepot.models.CategorieAchat;
import com.gentrepot.utils.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author oussema
 */
public class ServiceCategorieAchat  implements IService<CategorieAchat> {
    Connection cnx = DataSource.getInstance().getCnx();

    @Override
    public void ajouter(CategorieAchat t) {
        try {
            String requete = "INSERT INTO categorie_achat (nom) VALUES (?)";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setString(1, t.getNom());
            pst.executeUpdate();
            System.out.println("Categorie ajoutée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public void supprimer(CategorieAchat t) {
        try {
            String requete = "DELETE FROM categorie_achat WHERE id=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1, t.getId());
            pst.executeUpdate();
            System.out.println("Categorie supprimée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public void modifier(CategorieAchat t) {
        try {
            String requete = "UPDATE categorie_achat SET nom=? WHERE id=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(2, t.getId());
            pst.setString(1, t.getNom());           
            pst.executeUpdate();
            System.out.println("Categorie modifiée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public ObservableList<CategorieAchat> afficher() {
        ObservableList<CategorieAchat> c = FXCollections.observableArrayList();


        try {
            String requete = "SELECT * FROM categorie_achat";
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                c.add(new CategorieAchat(rs.getInt(1), rs.getString(2)));
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return c;
    }
    
}
