/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gentrepot.services;

import com.gentrepot.models.FactureAchat;
import com.gentrepot.models.FactureVente;
import com.gentrepot.models.RecouvrementClientEspece;
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
public class ServiceRecouvrementClientEspece implements IService<RecouvrementClientEspece> {

    Connection cnx = DataSource.getInstance().getCnx();

    ServiceFactureVente serviceFactureVente = new ServiceFactureVente();

    @Override
    public void ajouter(RecouvrementClientEspece r) {

        try {
            String requete = "INSERT INTO recouvrement_client_espece (montant,date_creation,idF_factureVente) VALUES (?,?,?)";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setDouble(1, r.getMontant());
            pst.setDate(2, new java.sql.Date(r.getDateCreation().getTime()));
            pst.setInt(3, r.getFactureVente().getNumeroF());
            pst.executeUpdate();
            System.out.println("Recouvrement client espece ajoutée !");

            //modifier facture vente 
            r.getFactureVente().setTotalPaye(r.getFactureVente().getTotalPaye() + r.getMontant());

            r.getFactureVente().setRestePaye(r.getFactureVente().getTotalTTC() - r.getFactureVente().getTotalPaye());

            if (r.getFactureVente().getRestePaye() == 0) {

                r.getFactureVente().setEtat("payer");
            }

            serviceFactureVente.modifierParRecouvrement(r.getFactureVente());

            String title = " Recouvrement client ";
            String message = "Recouvrement client espece est ajouté avec succes ";

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
    public void supprimer(RecouvrementClientEspece r) {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Supprimer recouvrement espece");
        alert.setHeaderText("Voulez vous supprimer cette recouvrement ?");

        Optional<ButtonType> option = alert.showAndWait();

        if (option.get() == ButtonType.OK) {

            try {
                String requete = "DELETE FROM recouvrement_client_espece WHERE id=?";
                PreparedStatement pst = cnx.prepareStatement(requete);
                pst.setInt(1, r.getId());
                pst.executeUpdate();
                System.out.println("Recouvrement Client espece supprimée !");

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
    public void modifier(RecouvrementClientEspece r) {

        try {
            String requete = "UPDATE recouvrement_client_espece SET montant=?,date_creation=?,idF_factureVente=? WHERE id=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setDouble(1, r.getMontant());
            pst.setDate(2, new java.sql.Date(r.getDateCreation().getTime()));
            pst.setInt(3, r.getFactureVente().getNumeroF());
            pst.setInt(4, r.getId());
            pst.executeUpdate();
            System.out.println("Recouvrement Client espece modifiée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

    }

    public void modifier(RecouvrementClientEspece r, double ancientM) {

        try {
            String requete = "UPDATE recouvrement_client_espece SET montant=?,date_creation=?,idF_factureVente=? WHERE id=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setDouble(1, r.getMontant());
            pst.setDate(2, new java.sql.Date(r.getDateCreation().getTime()));
            pst.setInt(3, r.getFactureVente().getNumeroF());
            pst.setInt(4, r.getId());
            pst.executeUpdate();
            System.out.println("Recouvrement Client espece modifiée !");

            FactureVente factureVente = r.getFactureVente();

            //modifier facture
            factureVente.setTotalPaye(factureVente.getTotalPaye() - ancientM);

            factureVente.setTotalPaye(factureVente.getTotalPaye() + r.getMontant());

            factureVente.setRestePaye(factureVente.getTotalTTC() - factureVente.getTotalPaye());

            if (factureVente.getRestePaye() == 0) {

                factureVente.setEtat("payer");
            } else {
                factureVente.setEtat("non_payer");
            }

            serviceFactureVente.modifierParRecouvrement(factureVente);

            String title = " Recouvrement client ";
            String message = "Recouvrement client espece est modifié avec succes ";

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

    public List<RecouvrementClientEspece> afficher() {

        List<RecouvrementClientEspece> list = new ArrayList<>();

        try {
            String requete = "SELECT * FROM recouvrement_client_espece";
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                list.add(new RecouvrementClientEspece(rs.getInt(1), findFactureById(rs.getInt(4)), rs.getDouble(2), rs.getDate(3)));
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return list;
    }

    public List<FactureVente> chargerFactureVente() {

        List<FactureVente> list = new ArrayList<>();

        try {
            String requete = "SELECT * FROM facture_vente";
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                list.add(new FactureVente(rs.getInt(1), rs.getDate(2), rs.getDate(3), rs.getDouble(4), rs.getString(5), rs.getDouble(6), rs.getDouble(7)));
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return list;
    }

    public List<FactureVente> chargerFactureVenteParDateSysteme() {

        List<FactureVente> list = new ArrayList<>();

        try {
            String requete = "SELECT * FROM facture_vente where date_echaillance_paiement=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setDate(1, new java.sql.Date(new Date().getTime()));
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                list.add(new FactureVente(rs.getInt(1), rs.getDate(2), rs.getDate(3), rs.getDouble(4), rs.getString(5), rs.getDouble(6), rs.getDouble(7)));
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return list;
    }

    public double totalEspece() {

        double total = 0;

        try {
            String requete = "SELECT round( sum(montant),3) FROM recouvrement_client_espece where date_creation=?";
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

    public FactureVente findFactureById(int id) {

        FactureVente factureVente = null;

        for (FactureVente f : this.chargerFactureVente()) {
            if (f.getNumeroF() == id) {
                factureVente = f;
                return factureVente;
            }
        }

        return factureVente;
    }

}
