/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gentrepot.services;

import com.gentrepot.models.InventaireCaisse;
import com.gentrepot.services.Password;
import com.gentrepot.models.User;
import com.gentrepot.utils.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
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
public class ServiceUser implements IService<User> {

    Connection cnx = DataSource.getInstance().getCnx();

    @Override
    public void ajouter(User u) {

        if (this.verifValiditerMotPasse(u.getPassword())) {

            try {

                String requete = "INSERT INTO user (username,email,password,roles,username_canonical,email_canonical,enabled) VALUES (?,?,?,?,?,?,?)";
                PreparedStatement pst = cnx.prepareStatement(requete);

                pst.setString(1, u.getUsername());
                pst.setString(2, u.getEmail());
                pst.setString(3, Password.hashPassword(u.getPassword()));

                if (u.getRole().equals("Client")) {
                    pst.setString(4, "a:1:{i:0;s:10:\"ROLE_CLIEN\";}");

                }
                if (u.getRole().equals("Chef De Parc")) {
                    pst.setString(4, "a:1:{i:0;s:10:\"ROLE_CPARC\";}");

                }
                if (u.getRole().equals("Agent Caisse")) {
                    pst.setString(4, "a:1:{i:0;s:10:\"ROLE_ACAIS\";}");

                }
                if (u.getRole().equals("Responsable Vente")) {
                    pst.setString(4, "a:1:{i:0;s:10:\"ROLE_RVENT\";}");

                }
                if (u.getRole().equals("Responsable Achat")) {
                    pst.setString(4, "a:1:{i:0;s:10:\"ROLE_RACHA\";}");

                }
                if (u.getRole().equals("Admin")) {

                    pst.setString(4, "a:1:{i:0;s:10:\"ROLE_ADMIN\";}");

                }

                if (u.getRole().equals("Responsable Stockage")) {

                    pst.setString(4, "a:1:{i:0;s:10:\"ROLE_STOCK\";}");

                }

                pst.setString(5, u.getUsernamCanonical());
                pst.setString(6, u.getEmailCanonical());
                pst.setInt(7, 1);

                pst.executeUpdate();
                System.out.println("User ajoutée !");

            } catch (SQLException ex) {
                System.err.println(ex.getMessage());
            }

            String title = " Utilisateur ";
            String message = "compte utilisateur est ajouté avec succes ";

            TrayNotification tray = new TrayNotification();
            AnimationType type = AnimationType.POPUP;

            tray.setAnimationType(type);
            tray.setTitle(title);
            tray.setMessage(message);
            tray.setNotificationType(NotificationType.SUCCESS);
            tray.showAndDismiss(Duration.millis(3000));

        }

    }

    public boolean ajouterU(User u) {

        if (this.verifAdresseMail(u.getEmail()) && this.verifValiditerMotPasse(u.getPassword())) {

            try {

                String requete = "INSERT INTO user (username,email,password,roles,username_canonical,email_canonical,enabled) VALUES (?,?,?,?,?,?,?)";
                PreparedStatement pst = cnx.prepareStatement(requete);

                pst.setString(1, u.getUsername());
                pst.setString(2, u.getEmail());
                pst.setString(3, Password.hashPassword(u.getPassword()));

                if (u.getRole().equals("Client")) {
                    pst.setString(4, "a:1:{i:0;s:10:\"ROLE_CLIEN\";}");

                }
                if (u.getRole().equals("Chef De Parc")) {
                    pst.setString(4, "a:1:{i:0;s:10:\"ROLE_CPARC\";}");

                }
                if (u.getRole().equals("Agent Caisse")) {
                    pst.setString(4, "a:1:{i:0;s:10:\"ROLE_ACAIS\";}");

                }
                if (u.getRole().equals("Responsable Vente")) {
                    pst.setString(4, "a:1:{i:0;s:10:\"ROLE_RVENT\";}");

                }
                if (u.getRole().equals("Responsable Achat")) {
                    pst.setString(4, "a:1:{i:0;s:10:\"ROLE_RACHA\";}");

                }
                if (u.getRole().equals("Admin")) {

                    pst.setString(4, "a:1:{i:0;s:10:\"ROLE_ADMIN\";}");

                }

                if (u.getRole().equals("Responsable Stockage")) {

                    pst.setString(4, "a:1:{i:0;s:10:\"ROLE_STOCK\";}");

                }

                pst.setString(5, u.getUsernamCanonical());
                pst.setString(6, u.getEmailCanonical());
                pst.setInt(7, 1);

                pst.executeUpdate();
                System.out.println("User ajoutée !");

            } catch (SQLException ex) {
                System.err.println(ex.getMessage());

                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Ajouter un utilisateur");
                alert.setHeaderText(ex.getMessage());

                alert.showAndWait();

                return false;

            }

            String title = " Utilisateur ";
            String message = "compte utilisateur est ajouté avec succes ";

            TrayNotification tray = new TrayNotification();
            AnimationType type = AnimationType.POPUP;

            tray.setAnimationType(type);
            tray.setTitle(title);
            tray.setMessage(message);
            tray.setNotificationType(NotificationType.SUCCESS);
            tray.showAndDismiss(Duration.millis(3000));

            return true;

        }

        return false;

    }

    @Override
    public void supprimer(User u) {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Supprimer utilisateur");
        alert.setHeaderText("Voulez vous supprimer cette utilisateur " + u.getUsername() + " ?");

        Optional<ButtonType> option = alert.showAndWait();

        if (option.get() == ButtonType.OK) {

            try {
                String requete = "DELETE FROM user WHERE id=?";
                PreparedStatement pst = cnx.prepareStatement(requete);
                pst.setInt(1, u.getId());
                pst.executeUpdate();
                System.out.println("user  supprimée !");

            } catch (SQLException ex) {
                System.err.println(ex.getMessage());
            }

        }

    }

    @Override
    public void modifier(User u) {

        if (this.verifValiditerMotPasse(u.getPassword())) {

            try {
                String requete = "update user  set username=?,email=?,password=?,roles=?,username_canonical=?,email_canonical=? where id=?  ";

                PreparedStatement pst = cnx.prepareStatement(requete);

                pst.setString(1, u.getUsername());
                pst.setString(2, u.getEmail());
                pst.setString(3, Password.hashPassword(u.getPassword()));
                if (u.getRole().equals("Client")) {
                    pst.setString(4, "a:1:{i:0;s:10:\"ROLE_CLIEN\";}");

                }
                if (u.getRole().equals("Chef De Parc")) {
                    pst.setString(4, "a:1:{i:0;s:10:\"ROLE_CPARC\";}");

                }
                if (u.getRole().equals("Agent Caisse")) {
                    pst.setString(4, "a:1:{i:0;s:10:\"ROLE_ACAIS\";}");

                }
                if (u.getRole().equals("Responsable Vente")) {
                    pst.setString(4, "a:1:{i:0;s:10:\"ROLE_RVENT\";}");

                }
                if (u.getRole().equals("Responsable Achat")) {
                    pst.setString(4, "a:1:{i:0;s:10:\"ROLE_RACHA\";}");

                }
                if (u.getRole().equals("Admin")) {

                    pst.setString(4, "a:1:{i:0;s:10:\"ROLE_ADMIN\";}");

                }
                if (u.getRole().equals("Responsable Stockage")) {

                    pst.setString(4, "a:1:{i:0;s:10:\"ROLE_STOCK\";}");

                }
                pst.setString(5, u.getUsernamCanonical());
                pst.setString(6, u.getEmailCanonical());
                pst.setInt(7, u.getId());

                pst.executeUpdate();
                System.out.println("user modifiée !");

            } catch (SQLException ex) {
                System.err.println(ex.getMessage());
            }

            String title = " Utilisateur ";
            String message = "compte utilisateur est modifié avec succes ";

            TrayNotification tray = new TrayNotification();
            AnimationType type = AnimationType.POPUP;

            tray.setAnimationType(type);
            tray.setTitle(title);
            tray.setMessage(message);
            tray.setNotificationType(NotificationType.SUCCESS);
            tray.showAndDismiss(Duration.millis(3000));

        }
        
        
    }

    public boolean modifierU(User u) {

        if (verifValiditerMotPasse(u.getPassword())) {

            try {
                String requete = "update user  set username=?,email=?,password=?,roles=?,username_canonical=?,email_canonical=? where id=?  ";

                PreparedStatement pst = cnx.prepareStatement(requete);

                pst.setString(1, u.getUsername());
                pst.setString(2, u.getEmail());
                pst.setString(3, Password.hashPassword(u.getPassword()));
                if (u.getRole().equals("Client")) {
                    pst.setString(4, "a:1:{i:0;s:10:\"ROLE_CLIEN\";}");

                }
                if (u.getRole().equals("Chef De Parc")) {
                    pst.setString(4, "a:1:{i:0;s:10:\"ROLE_CPARC\";}");

                }
                if (u.getRole().equals("Agent Caisse")) {
                    pst.setString(4, "a:1:{i:0;s:10:\"ROLE_ACAIS\";}");

                }
                if (u.getRole().equals("Responsable Vente")) {
                    pst.setString(4, "a:1:{i:0;s:10:\"ROLE_RVENT\";}");

                }
                if (u.getRole().equals("Responsable Achat")) {
                    pst.setString(4, "a:1:{i:0;s:10:\"ROLE_RACHA\";}");

                }
                if (u.getRole().equals("Admin")) {

                    pst.setString(4, "a:1:{i:0;s:10:\"ROLE_ADMIN\";}");

                }
                if (u.getRole().equals("Responsable Stockage")) {

                    pst.setString(4, "a:1:{i:0;s:10:\"ROLE_STOCK\";}");

                }
                pst.setString(5, u.getUsernamCanonical());
                pst.setString(6, u.getEmailCanonical());
                pst.setInt(7, u.getId());

                pst.executeUpdate();
                System.out.println("user modifiée !");

            } catch (SQLException ex) {
                System.err.println(ex.getMessage());

                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Modifier un utilisateur");
                alert.setHeaderText(ex.getMessage());

                alert.showAndWait();

                return false;
            }

            String title = " Utilisateur ";
            String message = "compte utilisateur est modifié avec succes ";

            TrayNotification tray = new TrayNotification();
            AnimationType type = AnimationType.POPUP;

            tray.setAnimationType(type);
            tray.setTitle(title);
            tray.setMessage(message);
            tray.setNotificationType(NotificationType.SUCCESS);
            tray.showAndDismiss(Duration.millis(3000));

            return true;
        }

        return false;

    }

    @Override
    public List<User> afficher() {

        List<User> list = new ArrayList<>();
        User u;

        try {
            String requete = "SELECT * FROM user";
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {

                u = new User(rs.getInt(1), rs.getString(2), rs.getString(4), rs.getString(3), rs.getString(5), rs.getString(8), rs.getString(12));

                if (u.getRole().equals("a:1:{i:0;s:10:\"ROLE_CLIEN\";}")) {
                    u.setRole("Client");

                }
                if (u.getRole().equals("a:1:{i:0;s:10:\"ROLE_CPARC\";}")) {

                    u.setRole("Chef De Parc");

                }
                if (u.getRole().equals("a:1:{i:0;s:10:\"ROLE_ACAIS\";}")) {

                    u.setRole("Agent Caisse");

                }
                if (u.getRole().equals("a:1:{i:0;s:10:\"ROLE_RVENT\";}")) {

                    u.setRole("Responsable Vente");

                }
                if (u.getRole().equals("a:1:{i:0;s:10:\"ROLE_RACHA\";}")) {

                    u.setRole("Responsable Achat");

                }
                if (u.getRole().equals("a:1:{i:0;s:10:\"ROLE_ADMIN\";}")) {

                    u.setRole("Admin");

                }
                if (u.getRole().equals("a:1:{i:0;s:10:\"ROLE_STOCK\";}")) {

                    u.setRole("Responsable Stockage");
                }

                list.add(u);
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return list;
    }

    public User find(String nonU, String motPasse) {

        User u = null;

        try {
            String requete = "SELECT * FROM user where username=? and password=? ";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setString(1, nonU);
            pst.setString(2, motPasse);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                u = new User(rs.getInt(1), rs.getString(2), rs.getString(4), rs.getString(3), rs.getString(5), rs.getString(8), rs.getString(12));

                if (u.getRole().equals("a:1:{i:0;s:10:\"ROLE_CLIEN\";}")) {
                    u.setRole("Client");

                }
                if (u.getRole().equals("a:1:{i:0;s:10:\"ROLE_CPARC\";}")) {

                    u.setRole("Chef De Parc");

                }
                if (u.getRole().equals("a:1:{i:0;s:10:\"ROLE_ACAIS\";}")) {

                    u.setRole("Agent Caisse");

                }
                if (u.getRole().equals("a:1:{i:0;s:10:\"ROLE_RVENT\";}")) {

                    u.setRole("Responsable Vente");

                }
                if (u.getRole().equals("a:1:{i:0;s:10:\"ROLE_RACHA\";}")) {

                    u.setRole("Responsable Achat");

                }
                if (u.getRole().equals("a:1:{i:0;s:10:\"ROLE_ADMIN\";}")) {

                    u.setRole("Admin");

                }
                if (u.getRole().equals("a:1:{i:0;s:10:\"ROLE_STOCK\";}")) {

                    u.setRole("Responsable Stockage");
                }

            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return u;
    }

    public User verifUser(String nonU, String motPasse) {

        User user = null;

        for (User u : this.afficher()) {

            if (nonU.equals(u.getUsername()) && Password.checkPassword(motPasse, u.getPassword())) {

                user = u;
                return user;

            }

        }

        return user;

    }

    public User verifLoginMail(String login, String adresseMail) {

        User u = null;

        System.out.println(login);
        System.out.println(adresseMail);

        try {
            String requete = "SELECT * FROM user where username=? and email=? ";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setString(1, login);
            pst.setString(2, adresseMail);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {

                u = new User(rs.getInt(1), rs.getString(2), rs.getString(4), rs.getString(3), rs.getString(5), rs.getString(8), rs.getString(12));

                if (u.getRole().equals("a:1:{i:0;s:10:\"ROLE_CLIEN\";}")) {
                    u.setRole("Client");

                }
                if (u.getRole().equals("a:1:{i:0;s:10:\"ROLE_CPARC\";}")) {

                    u.setRole("Chef De Parc");

                }
                if (u.getRole().equals("a:1:{i:0;s:10:\"ROLE_ACAIS\";}")) {

                    u.setRole("Agent Caisse");

                }
                if (u.getRole().equals("a:1:{i:0;s:10:\"ROLE_RVENT\";}")) {

                    u.setRole("Responsable Vente");

                }
                if (u.getRole().equals("a:1:{i:0;s:10:\"ROLE_RACHA\";}")) {

                    u.setRole("Responsable Achat");

                }
                if (u.getRole().equals("a:1:{i:0;s:10:\"ROLE_ADMIN\";}")) {

                    u.setRole("Admin");

                }
                if (u.getRole().equals("a:1:{i:0;s:10:\"ROLE_STOCK\";}")) {

                    u.setRole("Responsable Stockage");
                }

            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return u;
    }

    public boolean verifValiditerMotPasse(String mot) {

        boolean aAuMoinsUneLettre = false;
        boolean aAuMoinsUnChiffre = false;
        boolean lesLettresSontToutesDesMajuscules = true;
        boolean aUnCaractereSpecial = false;
        boolean verifNbC = false;

        if (mot.length() != 6) {

            verifNbC = true;

        }

        for (int i = 0; i < mot.length(); i++) {
            char caractereATraiter = mot.charAt(i);

            if (Character.isDigit(caractereATraiter)) {
                aAuMoinsUnChiffre = true;
            } else if (Character.isLetter(caractereATraiter)) {
                aAuMoinsUneLettre = true;
                if (Character.isLowerCase(caractereATraiter)) {
                    lesLettresSontToutesDesMajuscules = false;
                }
            } else {
                aUnCaractereSpecial = true;
            }
        }

        StringBuilder erreurs = new StringBuilder("Merci d'entrer");

        boolean aUneErreur = false;

        if (!aAuMoinsUneLettre) {
            aUneErreur = true;
            erreurs.append(" au moins une lettre\n");
        }

        if (!aAuMoinsUnChiffre) {
            if (aUneErreur) {
                erreurs.append(",");
            }
            aUneErreur = true;
            erreurs.append(" au moins un chiffre\n");
        }

        if (!lesLettresSontToutesDesMajuscules) {
            if (aUneErreur) {
                erreurs.append(",");
            }
            aUneErreur = true;
            erreurs.append(" les lettres en majuscules\n");
        }

        if (aUnCaractereSpecial) {
            if (aUneErreur) {
                erreurs.append(",");
            }
            aUneErreur = true;
            erreurs.append(" uniquement des lettres ou chiffres \n");
        }

        if (verifNbC) {
            if (aUneErreur) {
                erreurs.append(",");
            }
            aUneErreur = true;
            erreurs.append("un mot de passe de longeur est egale a 6 caractere \n");
        }

        if (aUneErreur == true) {

            System.out.println((erreurs.toString()));

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Mot de passe invalide");
            alert.setHeaderText((erreurs.toString()));

            alert.showAndWait();

            return false;
        }

        return true;

    }

    public boolean verifAdresseMail(String email) {

        Pattern p = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,4}$");
        Matcher m = p.matcher(email.toUpperCase());

        if (!m.matches()) {

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Adresse mail invalide");
            alert.setHeaderText("format d'adresse est invalide");

            alert.showAndWait();

            return false;
        }

        return true;

    }

}
