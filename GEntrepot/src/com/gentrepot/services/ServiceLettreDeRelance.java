/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gentrepot.services;

import com.gentrepot.models.FactureVente;

import com.gentrepot.models.LettreDeRelance;
import com.gentrepot.utils.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javafx.util.Duration;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 *
 * @author oussema
 */
public class ServiceLettreDeRelance implements IService<LettreDeRelance> {

    Connection cnx = DataSource.getInstance().getCnx();

    @Override
    public void ajouter(LettreDeRelance l) {
        try {
            String requete = "INSERT INTO lettre_de_relance (date_creation,idF_factureVente) VALUES (?,?)";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setDate(1, new java.sql.Date(l.getDate().getTime()));
            pst.setInt(2, l.getFactureVente().getNumeroF());

            pst.executeUpdate();
            System.out.println("Lettre de relance ajoutée !");

            String title = " Lettre de relance ";
            String message = "Lettre de relane  est ajouté avec succes ";

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
    public void supprimer(LettreDeRelance l) {

        try {
            String requete = "DELETE FROM lettre_de_relance WHERE id=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1, l.getId());
            pst.executeUpdate();
            System.out.println("Lettre de relance supprimée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

    }

    @Override
    public void modifier(LettreDeRelance t) {

    }

    @Override
    public List<LettreDeRelance> afficher() {

        List<LettreDeRelance> list = new ArrayList<>();

        try {
            String requete = "SELECT * FROM lettre_de_relance";
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {

                list.add(new LettreDeRelance(rs.getInt(1), rs.getDate(2), new FactureVente(rs.getInt(3))));
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return list;

    }

    public List<FactureVente> chargerFacture() {

        List<FactureVente> list = new ArrayList<>();

        try {
            String requete = "SELECT * FROM facture_vente";
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {

                list.add(new FactureVente(rs.getInt(1), rs.getDate(2), rs.getDate(3), rs.getDouble(4), rs.getString(5)));
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return list;

    }

}
