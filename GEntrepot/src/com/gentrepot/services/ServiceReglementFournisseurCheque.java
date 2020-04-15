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
import java.util.Date;
import java.util.List;
import java.util.Optional;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.util.Duration;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 *
 * @author oussema
 */
public class ServiceReglementFournisseurCheque implements IService<ReglementFournisseurCheque> {

    Connection cnx = DataSource.getInstance().getCnx();

    ServiceFactureAchat serviceFactureAchat = new ServiceFactureAchat();

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

            //modifier facture
            r.getFactureAchat().setTotalPaye(r.getFactureAchat().getTotalPaye() + r.getMontant());

            r.getFactureAchat().setRestePaye(r.getFactureAchat().getTotalTTC() - r.getFactureAchat().getTotalPaye());

            if (r.getFactureAchat().getRestePaye() == 0) {

                r.getFactureAchat().setEtat("payer");
            }

            serviceFactureAchat.modifier(r.getFactureAchat());
            
            
             String title = " Reglement fournisseur  ";
            String message = "Reglement fournisseur cheque est ajouté avec succes ";

            TrayNotification tray = new TrayNotification();
            AnimationType type = AnimationType.POPUP;

            tray.setAnimationType(type);
            tray.setTitle(title);
            tray.setMessage(message);
            tray.setNotificationType(NotificationType.SUCCESS);
            tray.showAndDismiss(Duration.millis(3000));

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

    }

    @Override
    public void supprimer(ReglementFournisseurCheque r) {
        
        
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Supprimer reglement cheque");
        alert.setHeaderText("Voulez vous supprimer cette reglement ?");
        
        
        Optional<ButtonType> option = alert.showAndWait();
        
         if (option.get() == ButtonType.OK) {
        
        
        try {
            String requete = "DELETE FROM reglement_fournisseur_cheque WHERE id=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1, r.getId());
            pst.executeUpdate();
            System.out.println("Reglement Fournisseur cheque supprimée !");

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

    public void modifier(ReglementFournisseurCheque r, double ancienrM) {

        FactureAchat factureAchat = r.getFactureAchat();

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

            //modifier facture
            factureAchat.setTotalPaye(factureAchat.getTotalPaye() - ancienrM);

            factureAchat.setTotalPaye(factureAchat.getTotalPaye() + r.getMontant());

            factureAchat.setRestePaye(factureAchat.getTotalTTC() - factureAchat.getTotalPaye());

            if (factureAchat.getRestePaye() == 0) {

                factureAchat.setEtat("payer");
            } else {
                factureAchat.setEtat("non_payer");
            }

            serviceFactureAchat.modifier(factureAchat);
            
             String title = " Reglement fournisseur  ";
            String message = "Reglement fournisseur cheque est modifié avec succes ";

            TrayNotification tray = new TrayNotification();
            AnimationType type = AnimationType.POPUP;

            tray.setAnimationType(type);
            tray.setTitle(title);
            tray.setMessage(message);
            tray.setNotificationType(NotificationType.SUCCESS);
            tray.showAndDismiss(Duration.millis(3000));

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
                list.add(new ReglementFournisseurCheque(rs.getInt(1), rs.getDate(4), rs.getInt(5), serviceFactureAchat.findFactureById(rs.getInt(6)), rs.getDouble(2), rs.getDate(3)));
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return list;
    }

    public double totalCheque() {

        double total = 0;

        try {
            String requete = "SELECT round( sum(montant),3) FROM reglement_fournisseur_cheque where date_creation=?";
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

}
