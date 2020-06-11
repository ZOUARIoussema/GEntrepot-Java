/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gentrepot.services;

import com.gentrepot.models.BonEntree;
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
public class ServiceBonEntree implements IService<BonEntree> {
    Connection cnx = DataSource.getInstance().getCnx();

    @Override
    public void ajouter(BonEntree t) {
        try {
            String requete = "INSERT INTO bon_entree (date,dateProduction,dateExpiration) VALUES (?,?,?)";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setDate(1, new java.sql.Date(t.getDate().getTime()));
            pst.setDate(2, new java.sql.Date(t.getDateProduction().getTime()));
            pst.setDate(3, new java.sql.Date(t.getDateExpiration().getTime()));
           // pst.setInt(4, t.getCommandeDApprovisionnement().getNumeroC());
            pst.executeUpdate();
            System.out.println("Bon d'entree ajouté !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public void supprimer(BonEntree t) {
        try {
            String requete = "DELETE FROM bon_entree WHERE id=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1, t.getId());
            pst.executeUpdate();
            System.out.println("Bon d'entree supprimé !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public void modifier(BonEntree t) {
        try {
            String requete = "UPDATE bon_entree SET date=?,dateProduction=?,dateExpiration=?,commandeDApprovisionnement=? WHERE id=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(5, t.getId());
            pst.setDate(1, new java.sql.Date(t.getDate().getTime()));
            pst.setDate(2, new java.sql.Date(t.getDateProduction().getTime()));
            pst.setDate(3, new java.sql.Date(t.getDateExpiration().getTime()));
            pst.setInt(4, t.getCommandeDApprovisionnement().getNumeroC());
            pst.executeUpdate();
            System.out.println("Bon d'entree modifié !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public List<BonEntree> afficher() {
         List<BonEntree> list = new ArrayList<>();

        try {
            String requete = "SELECT * FROM bon_entree";
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
               //list.add(new BonEntree(rs.getInt(1), rs.getDate(2), rs.getDate(3), rs.getDate(4)), (CommandeDApprovisionnement) rs.getObject(5));
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return list;
    }
    
    
}
