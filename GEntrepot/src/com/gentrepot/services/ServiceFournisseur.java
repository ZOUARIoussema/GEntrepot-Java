/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gentrepot.services;

import com.gentrepot.models.Fournisseur;
import com.gentrepot.utils.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.control.Alert;

/**
 *
 * @author oussema
 */
public class ServiceFournisseur implements IService<Fournisseur> {

    Connection cnx = DataSource.getInstance().getCnx();

    @Override
    public void ajouter(Fournisseur t) {

        if (!this.verifParMatricule(t.getMatriculeFiscale())) {

            try {
                String requete = "INSERT INTO fournisseur (raisonSociale,numeroTelephone,adresse,adresseMail,matriculeFiscale,codePostale) VALUES (?,?,?,?,?,?)";
                PreparedStatement pst = cnx.prepareStatement(requete);
                pst.setString(1, t.getRaisonSociale());
                pst.setInt(2, t.getNumeroTelephone());
                pst.setString(3, t.getAdresse());
                pst.setString(4, t.getAdresseMail());
                pst.setString(5, t.getMatriculeFiscale());
                pst.setInt(6, t.getCodePostale());
                pst.executeUpdate();
                System.out.println("Fournisseur ajouté !");

            } catch (SQLException ex) {
                System.err.println(ex.getMessage());
            }
        } else {

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Ajouter Fournisseur");
            alert.setHeaderText("matricule fiscale deja existe !");

            alert.showAndWait();

        }
    }

    @Override
    public void supprimer(Fournisseur t) {
        try {
            String requete = "DELETE FROM personne WHERE id=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1, t.getId());
            pst.executeUpdate();
            System.out.println("Fournisseur supprimé !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public void modifier(Fournisseur t) {
        try {
            String requete = "UPDATE fournisseur SET raisonSociale=?,numeroTelephone=?,adresse=?,adresseMail=?,matriculeFiscale=?,codePostale=? WHERE id=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(7, t.getId());
            pst.setString(1, t.getRaisonSociale());
            pst.setInt(2, t.getNumeroTelephone());
            pst.setString(3, t.getAdresse());
            pst.setString(4, t.getAdresseMail());
            pst.setString(5, t.getMatriculeFiscale());
            pst.setInt(6, t.getCodePostale());
            pst.executeUpdate();
            System.out.println("Fournisseur modifié !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public List<Fournisseur> afficher() {
        List<Fournisseur> list = new ArrayList<>();

        try {
            String requete = "SELECT * FROM fournisseur";
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                list.add(new Fournisseur(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getInt(7)));
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return list;
    }

    public boolean verifParMatricule(String mt) {

        for (Fournisseur f : this.afficher()) {

            if (f.getMatriculeFiscale().equals(mt)) {
                return true;
            }

        }

        return false;
    }

    public Fournisseur rechercher(List<Fournisseur> et, int in) {
        int a = 0;
        for (int i = 0; i < et.size(); i++) {
            if (et.get(i).getId() == in) {
                a = i;
            }
        }
        return et.get(a);
    }
}
