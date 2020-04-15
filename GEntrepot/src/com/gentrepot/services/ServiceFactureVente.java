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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author oussema
 */
public class ServiceFactureVente {
    
    
    
    
    
    
    Connection cnx = DataSource.getInstance().getCnx();

    public void ajouterFactureV(FactureVente fv) {
        try {
            String requete = "INSERT INTO  facture_vente (date_creation,date_echaillance_paiement,total_ttc,etat,total_paye,reste_apaye,timbre_fiscale,frais_transport,numeroBL_BonLivraison) VALUES (?,?,?,?,?,?,?,?,?)";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setDate(1,new java.sql.Date(fv.getDateCreation().getTime ()));

            pst.setDate(2,new java.sql.Date(fv.getDateEchaillancePaiement().getTime ()));
            pst.setDouble(3, fv.getTotalTTC());
            pst.setString(4, fv.getEtat());
            pst.setDouble(5, fv.getTotalPaye());
            pst.setDouble(6, fv.getRestePaye());
            pst.setDouble(7, fv.getTimbreFiscale());
            pst.setDouble(8, fv.getFraisTransport());
            pst.setInt(9, fv.getBonLivraison().getId());

            pst.executeUpdate();
            System.out.println("Facture vente  ajouté !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    
      public List<FactureVente> afficherFacture() {
        List<FactureVente> facture = new ArrayList<>();

  
        try {
            String requete = "SELECT * FROM facture_vente";
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                java.util.Date date_creation = rs.getDate("date_creation");
                java.util.Date date_echaillance_paiement = rs.getDate("date_echaillance_paiement");
                Double total_ttc = rs.getDouble("total_ttc");
                String etat = rs.getString("etat");
                Double total_paye = rs.getDouble("total_paye");
                Double reste_apaye = rs.getDouble("reste_apaye");
                Double timbre_fiscale = rs.getDouble("timbre_fiscale");
                Double frais_transport = rs.getDouble("frais_transport");

                

                FactureVente f1 = new FactureVente(date_creation, date_echaillance_paiement, total_ttc, etat, total_paye, reste_apaye, timbre_fiscale, frais_transport);
                
                
                facture.add(f1);
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return facture;

    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    /***ousema***/
   
    
    
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
            String requete = "SELECT round( sum(total_ttc),3) FROM `facture_vente` WHERE YEAR(`date_creation`)=year(sysdate())";
            PreparedStatement pst = cnx.prepareStatement(requete);
           
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
            String requete = "SELECT round( sum(total_paye),3) FROM `facture_vente` WHERE YEAR(`date_creation`)=year(sysdate())";
            PreparedStatement pst = cnx.prepareStatement(requete);
            
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
