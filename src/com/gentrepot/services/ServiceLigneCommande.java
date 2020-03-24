/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gentrepot.services;

import com.gentrepot.models.CommandeVente;
import com.gentrepot.models.LigneCommande;
import com.gentrepot.models.Produit;
import com.gentrepot.utils.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author oussema
 */
public class ServiceLigneCommande {

    Connection cnx = DataSource.getInstance().getCnx();

    public void ajouter(LigneCommande l) {
        try {
            String requete = "INSERT INTO ligne_commande (id_commande,prix,total,quantite,tva,idP_produit) VALUES (?,?,?,?,?,?)";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1, l.getCommandeVente().getId());

            pst.setDouble(2, l.getPrix());
            pst.setDouble(3, l.getTotal());
            pst.setInt(4, l.getQuantite());
            pst.setDouble(5, l.getTva());
            pst.setString(6, l.getProduit().getReference());

            pst.executeUpdate();
            System.out.println("Ligne  ajoutée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public void modifier(LigneCommande l) {
        try {
            String requete
                    = "UPDATE ligne_commande SET quantite=? WHERE idP_produit=?";
            PreparedStatement pst
                    = cnx.prepareStatement(requete);
            pst.setInt(1, l.getQuantite());
            pst.setString(2, l.getProduit().getReference());
            pst.executeUpdate();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public void supprimerProdCmd(LigneCommande l) {
        try {
            String req
                    = "DELETE FROM ligne_commande WHERE idP_produit=?";

            PreparedStatement ps
                    = cnx.prepareStatement(req);
            ps.setString(1, l.getProduit().getReference());
            ps.executeUpdate();

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public List<LigneCommande> afficher() {

        try {
            String requete
                    = "select * from ligne_commande";
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet res = pst.executeQuery();

            while (res.next()) {
                System.out.println("le produit_commande est "
                        + res.getInt("id") + " "
                        + res.getInt("idP_produit") + " "
                        + res.getInt("id_commande") + " "
                        + res.getInt("quantite"));

            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return null;
    }

    public List<LigneCommande> RecupereParCommande(CommandeVente c) {

        List<LigneCommande> liste = new ArrayList<>();

        try {
            String requete
                    = "select * from ligne_commande where id_commande=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1, c.getId());
            ResultSet res = pst.executeQuery();

            while (res.next()) {

                liste.add(new LigneCommande(c, new Produit(res.getString(8)), res.getDouble(3), res.getInt(5), res.getDouble(4), res.getDouble(6)));

            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return liste;
    }

}
