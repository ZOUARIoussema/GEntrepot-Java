/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gentrepot.services;

import com.gentrepot.models.CommandeDApprovisionnement;
import com.gentrepot.models.FactureAchat;
import com.gentrepot.models.Fournisseur;
import com.gentrepot.models.LigneCommandeDApprovisionnement;
import com.gentrepot.models.ProduitAchat;
import com.gentrepot.utils.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javafx.util.Duration;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 *
 * @author oussema
 */
public class ServiceFactureAchat implements IService<FactureAchat> {

    Connection cnx = DataSource.getInstance().getCnx();
    ServiceCommandeDApprovisionnment serviceCommandeDApprovisionnment = new ServiceCommandeDApprovisionnment();

    @Override
    public void ajouter(FactureAchat f) {

        try {
            String requete = "INSERT INTO facture_achat VALUES (?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1, f.getNumeroF());
            pst.setDate(2, new java.sql.Date(f.getDateCreation().getTime()));
            pst.setDate(3, new java.sql.Date(f.getDateEchaillancePaiement().getTime()));
            pst.setDouble(4, f.getTotalTTC());
            pst.setString(5, f.getEtat());
            pst.setDouble(6, 0);
            pst.setDouble(7, f.getTotalTTC());
            pst.setDouble(8, f.getTimbreFiscale());
            pst.setDouble(9, f.getFraisTransport());
            pst.setInt(10, f.getCommandeDApprovisionnement().getNumeroC());

            pst.executeUpdate();
            System.out.println("Facture achat ajoutée !");

            f.getCommandeDApprovisionnement().setEtat("facturer");

            serviceCommandeDApprovisionnment.modifierEtatCommande(f.getCommandeDApprovisionnement());

            String title = " Facture achat ";
            String message = " Facture achat ajouté avec succes ";

            TrayNotification tray = new TrayNotification();
            AnimationType type = AnimationType.POPUP;

            tray.setAnimationType(type);
            tray.setTitle(title);
            tray.setMessage(message);
            tray.setNotificationType(NotificationType.INFORMATION);
            tray.showAndDismiss(Duration.millis(3000));

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

    }

    @Override
    public void supprimer(FactureAchat t) {
    }

    @Override
    public void modifier(FactureAchat f) {

        try {
            String requete = "update facture_achat  set etat=?,total_paye=?,reste_apaye=? where numeroF=?  ";

            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setString(1, f.getEtat());
            pst.setDouble(2, f.getTotalPaye());
            pst.setDouble(3, f.getRestePaye());

            pst.setInt(4, f.getNumeroF());

            pst.executeUpdate();
            System.out.println("Facture achat  modifiée !");
            
            
            
            String title = " Modification Facture achat ";
            String message = "Total payé  modier avec succes ";

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

    public List<FactureAchat> chargerParDateSysteme() {

        List<FactureAchat> list = new ArrayList<>();

        try {
            String requete = "SELECT * FROM facture_achat where date_echaillance_paiement=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setDate(1, new java.sql.Date(new Date().getTime()));
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                list.add(new FactureAchat(rs.getInt(1), rs.getDate(2), rs.getDate(3), rs.getDouble(4), rs.getString(5), rs.getDouble(6), rs.getDouble(7), rs.getDouble(8), rs.getDouble(9), getCommandeById(rs.getInt(10))));
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return list;
    }

    @Override
    public List<FactureAchat> afficher() {

        List<FactureAchat> list = new ArrayList<>();

        try {
            String requete = "SELECT * FROM facture_achat";
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                list.add(new FactureAchat(rs.getInt(1), rs.getDate(2), rs.getDate(3), rs.getDouble(4), rs.getString(5), rs.getDouble(6), rs.getDouble(7), rs.getDouble(8), rs.getDouble(9), getCommandeById(rs.getInt(10))));
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return list;
    }

    public CommandeDApprovisionnement getCommandeById(int id) {

        CommandeDApprovisionnement commande = null;

        for (CommandeDApprovisionnement c : chargerCommande()) {

            if (c.getNumeroC() == id) {

                commande = c;
                return commande;

            }
        }

        return commande;

    }

    public List<CommandeDApprovisionnement> chargerCommande() {

        List<CommandeDApprovisionnement> list = new ArrayList<>();

        try {
            String requete = "SELECT * FROM commande_d_aprovisionnement";
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {

                CommandeDApprovisionnement c = new CommandeDApprovisionnement(rs.getInt(2), rs.getDouble(3), rs.getDate(4), rs.getString(5), rs.getDouble(6), rs.getDouble(7), new Fournisseur(rs.getInt(1)));

                list.add(c);

                c.getLigneCommandeDApprovisionnements().addAll(this.chargerLigne(c));
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return list;
    }

    public List<LigneCommandeDApprovisionnement> chargerLigne(CommandeDApprovisionnement c) {

        List<LigneCommandeDApprovisionnement> list = new ArrayList<>();

        try {
            String requete = "SELECT * FROM ligne_commande_d_approvisionnement where numeroC_commandeAp =?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1, c.getNumeroC());
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                list.add(new LigneCommandeDApprovisionnement(rs.getInt(1), c, new ProduitAchat(rs.getString(2)), rs.getDouble(3), rs.getInt(4), rs.getDouble(5), rs.getDouble(6)));
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return list;
    }

    public List<CommandeDApprovisionnement> chargerCommandeNonFacture() {

        List<CommandeDApprovisionnement> liste = new ArrayList<>();

        for (CommandeDApprovisionnement c : this.chargerCommande()) {

            if (c.getEtat().equals("non_facturer")) {

                liste.add(c);
            }

        }

        return liste;
    }

    public FactureAchat findFactureById(int id) {

        FactureAchat factureAchat = null;

        for (FactureAchat f : this.afficher()) {

            if (f.getNumeroF() == id) {

                factureAchat = f;
                return factureAchat;
            }
        }

        return factureAchat;

    }

    public double totalAchatParAnneSysteme() {

        double total = 0;

        try {
            String requete = "SELECT sum(total_ttc) FROM `facture_achat` WHERE YEAR(`date_creation`)=year(sysdate())";
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
            String requete = "SELECT sum(total_paye) FROM `facture_achat` WHERE YEAR(`date_creation`)=year(sysdate())";
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

}
