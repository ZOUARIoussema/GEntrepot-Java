/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gentrepot.services;

import com.gentrepot.models.FactureAchat;
import com.gentrepot.models.FactureVente;
import com.gentrepot.utils.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

/**
 *
 * @author oussema
 */
public class ServiceFactureVente {
    
    
    /***ousema***/
    Connection cnx = DataSource.getInstance().getCnx();
    
    
     public void modifierParRecouvrement(FactureVente f) {

        try {
            String requete = "update facture_vente  set etat=?,total_paye=?,reste_apaye=? where id=?  ";

            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setString(1, f.getEtat());
            pst.setDouble(2, f.getTotalPaye());
            pst.setDouble(3, f.getRestePaye());

            pst.setInt(4, f.getNumeroF());

            pst.executeUpdate();
            System.out.println("Facture vente  modifiée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

    }
     
     
      public double totalVenteParAnneSysteme() {

        double total = 0;

        try {
            String requete = "SELECT sum(total_ttc) FROM `facture_vente` WHERE YEAR(`date_creation`)=year(sysdate())";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setDate(1, new java.sql.Date(new Date().getTime()));
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {

                total = rs.getDouble(1);
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return total;

    }
      
      public double totalPayerParAnneSysteme() {

        double total = 0;

        try {
            String requete = "SELECT sum(total_paye) FROM `facture_vente` WHERE YEAR(`date_creation`)=year(sysdate())";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setDate(1, new java.sql.Date(new Date().getTime()));
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {

                total = rs.getDouble(1);
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return total;

    }
     /***    *************  ****/
    
    
}
