/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gentrepot.services;

import com.gentrepot.models.FactureAchat;
import com.gentrepot.models.FactureVente;
import com.gentrepot.models.RecouvrementClientCheque;
import com.gentrepot.models.ReglementFournisseurCheque;
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
public class ServiceRecouvrementClientCheque implements IService<RecouvrementClientCheque> {

    Connection cnx = DataSource.getInstance().getCnx();
    ServiceRecouvrementClientEspece serviceRecouvrementClientEspece = new ServiceRecouvrementClientEspece();
    ServiceFactureVente serviceFactureVente = new ServiceFactureVente();

    @Override
    public void ajouter(RecouvrementClientCheque r) {

        try {
            String requete = "INSERT INTO recouvrement_client_cheque (montant,date_creation,date_cheque,numero_cheque,idF_factureVente) VALUES (?,?,?,?,?)";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setDouble(1, r.getMontant());
            pst.setDate(2, new java.sql.Date(r.getDateCreation().getTime()));
            pst.setDate(3, new java.sql.Date(r.getDateCheque().getTime()));
            pst.setInt(4, r.getNumeroCheque());
            pst.setInt(5, r.getFactureVente().getNumeroF());
            pst.executeUpdate();
            System.out.println("Recouvrement Client cheque ajoutée !");

            //modifier facture vente 
            r.getFactureVente().setTotalPaye(r.getFactureVente().getTotalPaye() + r.getMontant());

            r.getFactureVente().setRestePaye(r.getFactureVente().getTotalTTC() - r.getFactureVente().getTotalPaye());

            if (r.getFactureVente().getRestePaye() == 0) {

                r.getFactureVente().setEtat("payer");
            }

            serviceFactureVente.modifierParRecouvrement(r.getFactureVente());

            String title = " Recouvrement client ";
            String message = "Recouvrement client cheque est ajouté avec succes ";

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
    public void supprimer(RecouvrementClientCheque r) {
        
        
        
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Supprimer recouvrement cheque");
        alert.setHeaderText("Voulez vous supprimer cette regouvrement ?");
        
        
        Optional<ButtonType> option = alert.showAndWait();
        
         if (option.get() == ButtonType.OK) {

        try {
            String requete = "DELETE FROM recouvrement_client_cheque WHERE id=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1, r.getId());
            pst.executeUpdate();
            System.out.println("Recouvrement Client  cheque supprimée !");

            //modifier facture vente 
            r.getFactureVente().setTotalPaye(r.getFactureVente().getTotalPaye() - r.getMontant());

            r.getFactureVente().setRestePaye(r.getFactureVente().getTotalTTC() - r.getFactureVente().getTotalPaye());

            if (r.getFactureVente().getRestePaye() == 0) {

                r.getFactureVente().setEtat("payer");
            } else {
                r.getFactureVente().setEtat("non_payer");

            }

            serviceFactureVente.modifierParRecouvrement(r.getFactureVente());

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        
         }

    }

    @Override
    public void modifier(RecouvrementClientCheque r) {

        try {
            String requete = "UPDATE recouvrement_client_cheque SET montant=?,date_creation=?,date_cheque=?,numero_cheque=?,idF_factureVente=? WHERE id=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setDouble(1, r.getMontant());
            pst.setDate(2, new java.sql.Date(r.getDateCreation().getTime()));
            pst.setDate(3, new java.sql.Date(r.getDateCheque().getTime()));
            pst.setInt(4, r.getNumeroCheque());
            pst.setInt(5, r.getFactureVente().getNumeroF());
            pst.setInt(6, r.getId());
            pst.executeUpdate();
            System.out.println("Recouvrement Client cheque modifiée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

    }

    public void modifier(RecouvrementClientCheque r, double aM) {

        try {
            String requete = "UPDATE recouvrement_client_cheque SET montant=?,date_creation=?,date_cheque=?,numero_cheque=?,idF_factureVente=? WHERE id=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setDouble(1, r.getMontant());
            pst.setDate(2, new java.sql.Date(r.getDateCreation().getTime()));
            pst.setDate(3, new java.sql.Date(r.getDateCheque().getTime()));
            pst.setInt(4, r.getNumeroCheque());
            pst.setInt(5, r.getFactureVente().getNumeroF());
            pst.setInt(6, r.getId());
            pst.executeUpdate();
            System.out.println("Recouvrement Client cheque modifiée !");

            FactureVente factureVente = r.getFactureVente();

            //modifier facture
            factureVente.setTotalPaye(factureVente.getTotalPaye() - aM);

            factureVente.setTotalPaye(factureVente.getTotalPaye() + r.getMontant());

            factureVente.setRestePaye(factureVente.getTotalTTC() - factureVente.getTotalPaye());

            if (factureVente.getRestePaye() == 0) {

                factureVente.setEtat("payer");
            } else {
                factureVente.setEtat("non_payer");
            }

            serviceFactureVente.modifierParRecouvrement(factureVente);

            String title = " Recouvrement client ";
            String message = "Recouvrement client cheque est modifié avec succes ";

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
    public List<RecouvrementClientCheque> afficher() {

        List<RecouvrementClientCheque> list = new ArrayList<>();

        try {
            String requete = "SELECT * FROM recouvrement_client_cheque";
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                list.add(new RecouvrementClientCheque(rs.getInt(1), rs.getDate(4), rs.getInt(5), serviceRecouvrementClientEspece.findFactureById(rs.getInt(6)), rs.getDouble(2), rs.getDate(3)));
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return list;

    }

    public double totalCheque() {

        double total = 0;

        try {
            String requete = "SELECT round( sum(montant),3) FROM recouvrement_client_cheque where date_creation=?";
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
