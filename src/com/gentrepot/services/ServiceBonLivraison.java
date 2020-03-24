/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gentrepot.services;

import com.gentrepot.models.BonLivraison;
import com.gentrepot.models.LigneCommande;
import com.gentrepot.utils.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author oussema
 */
public class ServiceBonLivraison {
    
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
    
    
}
