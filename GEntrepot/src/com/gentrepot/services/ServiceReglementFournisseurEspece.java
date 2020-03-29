/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gentrepot.services;

import com.gentrepot.models.FactureAchat;
import com.gentrepot.models.ReglementFournisseurEspece;
import com.gentrepot.utils.DataSource;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author oussema
 */
public class ServiceReglementFournisseurEspece implements IService<ReglementFournisseurEspece> {

    Connection cnx = DataSource.getInstance().getCnx();
    ServiceFactureAchat serviceFactureAchat = new ServiceFactureAchat();

    @Override
    public void ajouter(ReglementFournisseurEspece r) {

        try {
            String requete = "INSERT INTO reglement_fournisseur_espece (montant,date_creation,numeroF_factureAchat) VALUES (?,?,?)";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setDouble(1, r.getMontant());
            pst.setDate(2, new java.sql.Date(r.getDateCreation().getTime()));
            pst.setInt(3, r.getFactureAchat().getNumeroF());
            pst.executeUpdate();
            System.out.println("Reglement fournisseur espece ajoutée !");
            
            
            
            //modifier facture
            r.getFactureAchat().setTotalPaye(r.getFactureAchat().getTotalPaye() + r.getMontant());

            r.getFactureAchat().setRestePaye(r.getFactureAchat().getTotalTTC() - r.getFactureAchat().getTotalPaye());

            if (r.getFactureAchat().getRestePaye() == 0) {

                r.getFactureAchat().setEtat("payer");
            }

            serviceFactureAchat.modifier(r.getFactureAchat());
            

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

    }

    @Override
    public void supprimer(ReglementFournisseurEspece r) {

        try {
            String requete = "DELETE FROM reglement_fournisseur_espece WHERE id=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1, r.getId());
            pst.executeUpdate();
            System.out.println("Reglement Fournisseur espece supprimée !");
            
            
            
            //modifier facture
            r.getFactureAchat().setTotalPaye(r.getFactureAchat().getTotalPaye() - r.getMontant());

            r.getFactureAchat().setRestePaye(r.getFactureAchat().getTotalTTC() - r.getFactureAchat().getTotalPaye());

            if (r.getFactureAchat().getRestePaye() == 0) {

                r.getFactureAchat().setEtat("payer");
            }else
            {
                  r.getFactureAchat().setEtat("non_payer");
                
            }

            serviceFactureAchat.modifier(r.getFactureAchat());

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

    }

    @Override
    public void modifier(ReglementFournisseurEspece r) {

        try {
            String requete = "UPDATE reglement_fournisseur_espece SET montant=?,date_creation=?,numeroF_factureAchat=? WHERE id=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setDouble(1, r.getMontant());
            pst.setDate(2, new java.sql.Date(r.getDateCreation().getTime()));
            pst.setInt(3, r.getFactureAchat().getNumeroF());
            pst.setInt(4, r.getId());
            pst.executeUpdate();
            System.out.println("Reglement Fournisseur espece modifiée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

    }
    
    public void modifier(ReglementFournisseurEspece r,double ancientM) {
        
        
         FactureAchat factureAchat =r.getFactureAchat();

        try {
            String requete = "UPDATE reglement_fournisseur_espece SET montant=?,date_creation=?,numeroF_factureAchat=? WHERE id=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setDouble(1, r.getMontant());
            pst.setDate(2, new java.sql.Date(r.getDateCreation().getTime()));
            pst.setInt(3, r.getFactureAchat().getNumeroF());
            pst.setInt(4, r.getId());
            pst.executeUpdate();
            System.out.println("Reglement Fournisseur espece modifiée !");
            
            
             //modifier facture
            factureAchat.setTotalPaye(factureAchat.getTotalPaye() - ancientM);

            factureAchat.setTotalPaye(factureAchat.getTotalPaye() + r.getMontant());

            factureAchat.setRestePaye(factureAchat.getTotalTTC() - factureAchat.getTotalPaye());

            if (factureAchat.getRestePaye() == 0) {

                factureAchat.setEtat("payer");
            } else {
                factureAchat.setEtat("non_payer");
            }

            serviceFactureAchat.modifier(factureAchat);

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

    }

    @Override
    public List<ReglementFournisseurEspece> afficher() {

        List<ReglementFournisseurEspece> list = new ArrayList<>();

        try {
            String requete = "SELECT * FROM reglement_fournisseur_espece";
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                list.add(new ReglementFournisseurEspece(rs.getInt(1), serviceFactureAchat.findFactureById(rs.getInt(4)), rs.getDouble(2), rs.getDate(3)));
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return list;
    }

    public double totalEspece() {

        double total = 0;

        try {
            String requete = "SELECT sum(montant) FROM reglement_fournisseur_espece where date_creation=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setDate(1, new java.sql.Date(new java.util.Date().getTime()));
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {

                total = rs.getDouble(1);
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return total;
    }

}
