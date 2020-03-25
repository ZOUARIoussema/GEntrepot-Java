/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gentrepot.services;

import com.gentrepot.models.LignePerte;
import com.gentrepot.utils.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author oussema
 */
public class ServiceLignePerte implements IService<LignePerte>{
    Connection cnx = DataSource.getInstance().getCnx();

    @Override
    public void ajouter(LignePerte lp) {
        try {
            String requete = "INSERT INTO ligne_perte (id_perte,ref_produit,quantite,raisonPerte) VALUES ('" + lp.getPerte().getId() + "','" + lp.getProduitAchat().getReference() + "','" + lp.getQuantite() + "','" + lp.getRaisonPerte() + "')";
            Statement st = cnx.createStatement();
            st.executeUpdate(requete);
            System.out.println("Ligne de perte ajoutée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }  
    }

    @Override
    public void supprimer(LignePerte lp) {
        try {
            String requete = "DELETE FROM ligne_perte WHERE id=" + lp.getId();
            Statement st = cnx.createStatement();
            st.executeUpdate(requete);
            System.out.println("Ligne de Perte supprimée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public void modifier(LignePerte lp) {
        try {
            String requete = "UPDATE ligne_perte SET id_perte='" + lp.getPerte().getId() + "',ref_produit='" + lp.getProduitAchat().getReference() + "',quantite='" + lp.getQuantite() + "',raisonPerte='" + lp.getRaisonPerte() + "' WHERE id=" + lp.getId();
            Statement st = cnx.createStatement();
            st.executeUpdate(requete);
            System.out.println("Ligne Perte modifiée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public List<LignePerte> afficher() {
        List<LignePerte> list = new ArrayList<>();
        ServicePerte sp = new ServicePerte();
        ServiceProduitAchat sa = new ServiceProduitAchat();
        try {
            String requete = "SELECT * FROM ligne_perte";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                list.add(new LignePerte(rs.getInt(1),sp.rechercher(sp.afficher(),rs.getInt(2) ), sa.rechercher(sa.afficher(),rs.getString(3)),rs.getInt(4), rs.getString(5)));
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return list;
    
    }
    
}
