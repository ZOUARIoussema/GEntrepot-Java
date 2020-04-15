/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gentrepot.services;

import com.gentrepot.models.LigneCommande;
import com.gentrepot.models.ProduitAchat;
import com.gentrepot.utils.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author oussema
 */
public class ServiceProduitAchat {
        Connection cnx = DataSource.getInstance().getCnx();
      ObservableList<ProduitAchat> oblist = FXCollections.observableArrayList();

    public List<ProduitAchat> afficher() {

        try {
            String requete
                    = "select * from produit_achat";
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet res = pst.executeQuery();

            while (res.next()) {
                
               // oblist.add(new ProduitAchat(res.getString("reference"), res.getString("libelle"), res.getInt("quantiteEnStock")));
               
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return oblist;
    }
    
}
