/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gentrepot.services;

import com.gentrepot.models.BonRetour;
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
public class ServiceBonRetour  implements IService<BonRetour> {
    Connection cnx = DataSource.getInstance().getCnx();

    @Override
    public void ajouter(BonRetour t) {
        try {
            String requete = "INSERT INTO bon_retour (date,motifDeRetour,numeroC_commandeAp) VALUES (?,?,?)";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setDate(1, new java.sql.Date(t.getDate().getTime()));
            pst.setString(2, t.getMotifDeRetour());
            pst.setInt(3, t.getCommandeDApprovisionnement());
            pst.executeUpdate();
            System.out.println("Bon de retour ajouté !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public void supprimer(BonRetour t) {
        try {
            String requete = "DELETE FROM bon_retour WHERE id=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1, t.getId());
            pst.executeUpdate();
            System.out.println("Bon de retour supprimé !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public void modifier(BonRetour t) {
        try {
            String requete = "UPDATE bon_retour SET motifDeRetour=?,numeroC_commandeAp=? WHERE id=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(3, t.getId());
            pst.setString(1, t.getMotifDeRetour());
            pst.setInt(2, t.getCommandeDApprovisionnement());
            pst.executeUpdate();
            System.out.println("Bon d'entree modifié !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public List<BonRetour> afficher() {
        List<BonRetour> list = new ArrayList<>();

        try {
            String requete = "SELECT * FROM bon_retour";
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
               list.add(new BonRetour(rs.getInt(1), rs.getDate(2), rs.getString(3), rs.getInt(4)));
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return list;
    }
    
}
