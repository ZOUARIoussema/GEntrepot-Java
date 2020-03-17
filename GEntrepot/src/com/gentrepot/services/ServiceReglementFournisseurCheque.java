/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gentrepot.services;

import com.gentrepot.models.FactureAchat;
import com.gentrepot.models.ReglementFournisseurCheque;
import com.gentrepot.models.ReglementFournisseurEspece;
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
public class ServiceReglementFournisseurCheque implements IService<ReglementFournisseurCheque> {

    Connection cnx = DataSource.getInstance().getCnx();

    @Override
    public void ajouter(ReglementFournisseurCheque r) {

        try {
            String requete = "INSERT INTO reglement_fournisseur_cheque (montant,date_creation,date_cheque,numero_cheque,numeroF_factureAchat) VALUES (?,?,?,?,?)";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setDouble(1, r.getMontant());
            pst.setDate(2, new java.sql.Date(r.getDateCreation().getTime()));
            pst.setDate(3, new java.sql.Date(r.getDateCheque().getTime()));
            pst.setInt(4, r.getNumeroCheque());
            pst.setInt(5, r.getFactureAchat().getNumeroF());
            pst.executeUpdate();
            System.out.println("Reglement fournisseur cheque ajoutée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

    }

    @Override
    public void supprimer(ReglementFournisseurCheque r) {
        try {
            String requete = "DELETE FROM reglement_fournisseur_cheque WHERE id=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1, r.getId());
            pst.executeUpdate();
            System.out.println("Reglement Fournisseur cheque supprimée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public void modifier(ReglementFournisseurCheque r) {
        try {
            String requete = "UPDATE reglement_fournisseur_cheque SET montant=?,date_creation=?,date_cheque=?,numero_cheque=?,numeroF_factureAchat=? WHERE id=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setDouble(1, r.getMontant());
            pst.setDate(2, new java.sql.Date(r.getDateCreation().getTime()));
            pst.setDate(3, new java.sql.Date(r.getDateCheque().getTime()));
            pst.setInt(4, r.getNumeroCheque());
            pst.setInt(5, r.getFactureAchat().getNumeroF());
            pst.setInt(6, r.getId());
            pst.executeUpdate();
            System.out.println("Reglement Fournisseur cheque modifiée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

    }

    @Override
    public List<ReglementFournisseurCheque> afficher() {
        List<ReglementFournisseurCheque> list = new ArrayList<>();

        try {
            String requete = "SELECT * FROM reglement_fournisseur_cheque";
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                list.add(new ReglementFournisseurCheque(rs.getInt(rs.getInt(1)), rs.getDate(4), rs.getInt(5), new FactureAchat(rs.getInt(6)), rs.getDouble(2), rs.getDate(3)));
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return list;
    }

}
