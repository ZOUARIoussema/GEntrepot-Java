/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gentrepot.services;

import com.gentrepot.models.BonLivraison;
import com.gentrepot.models.FactureVente;
import com.gentrepot.utils.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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
}
