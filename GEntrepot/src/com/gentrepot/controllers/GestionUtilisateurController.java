/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gentrepot.controllers;

import animatefx.animation.ZoomIn;
import com.gentrepot.models.User;
import com.gentrepot.services.ServiceUser;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author oussema
 */
public class GestionUtilisateurController implements Initializable {

    ObservableList<User> listeUsers = FXCollections.observableArrayList();
    ObservableList<String> listeRole = FXCollections.observableArrayList("Admin", "Responsable Achat", "Responsable Vente", "Agent Caisse", "Chef De Parc", "Client");

    ServiceUser serviceUser = new ServiceUser();

    User userG = null;

    @FXML
    private Pane paneGuser;
    @FXML
    private TableView<User> tableViewListeUser;
    @FXML
    private TableColumn<User, String> tableNonUser;
    @FXML
    private TableColumn<User, String> tableAdresseMailUser;
    @FXML
    private TableColumn<User, String> tableViewRole;
    @FXML
    private Pane paneAjouterUser;
    @FXML
    private JFXTextField textFNonUtilisateur;
    @FXML
    private JFXTextField textFAdresseMail;
    private JFXTextField textFRole;
    @FXML
    private JFXButton btnAjouterUser;
    @FXML
    private JFXButton btnAnnuler;
    @FXML
    private JFXTextField textMotPAsse;
    @FXML
    private JFXComboBox<String> AjouterUserCombobox;
    @FXML
    private Pane paneModiferUser;
    @FXML
    private JFXTextField textFNonUtilisateurM;
    @FXML
    private JFXTextField textFAdresseMailM;
    @FXML
    private JFXTextField textMotPAsseM;
    @FXML
    private JFXComboBox<String> AjouterUserComboboxM;
    @FXML
    private JFXButton btnAjouterUserM;
    @FXML
    private JFXButton btnAnnulerM;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        AjouterUserCombobox.setItems(listeRole);
        AjouterUserComboboxM.setItems(listeRole);

        tableViewListeUser.setItems(listeUsers);

        tableNonUser.setCellValueFactory(new PropertyValueFactory<>("username"));
        tableAdresseMailUser.setCellValueFactory(new PropertyValueFactory<>("email"));
        tableViewRole.setCellValueFactory(new PropertyValueFactory<>("role"));

        chargerListeUser();

    }

    Alert creerAlerte(String text, Alert.AlertType type) {
        Alert alert = new Alert(type);
        alert.setHeaderText(null);
        alert.setContentText(text);

        return alert;
    }

    @FXML
    private void modifierUser(ActionEvent event) {

        if (userG != null) {

            textFNonUtilisateurM.setText(userG.getUsername());
            textFAdresseMailM.setText(userG.getEmail());
            textMotPAsseM.setText(userG.getPassword());

            paneModiferUser.setVisible(true);
            new ZoomIn(paneModiferUser).play();
            paneModiferUser.toFront();

        } else {
            creerAlerte("Selectionner une utilisateur ! ", Alert.AlertType.WARNING).showAndWait();
        }

    }

    @FXML
    private void supprimerUser(ActionEvent event) {
    }

    @FXML
    private void AfficherPaneAjouterUser(ActionEvent event) {

        paneAjouterUser.setVisible(true);
        new ZoomIn(paneAjouterUser).play();
        paneAjouterUser.toFront();

    }

    @FXML
    private void annulerAjouterUser(ActionEvent event) {

        paneGuser.setVisible(true);
        new ZoomIn(paneGuser).play();
        paneGuser.toFront();

    }

    @FXML
    private void validerAjouterUser(MouseEvent event) {
    }

    public void chargerListeUser() {

        listeUsers.setAll(serviceUser.afficher());

    }

    @FXML
    private void validerAjouterUser(ActionEvent event) {

        User user = new User(0, textFNonUtilisateur.getText(), textFAdresseMail.getText(), textFNonUtilisateur.getText(), textFAdresseMail.getText(), textMotPAsse.getText(), AjouterUserCombobox.getValue());

        System.out.println(user);

        serviceUser.ajouter(user);

        chargerListeUser();

    }

    @FXML
    private void validerModifier(ActionEvent event) {

        userG.setEmail(textFAdresseMailM.getText());
        userG.setEmailCanonical(textFAdresseMailM.getText());
        userG.setPassword(textMotPAsseM.getText());
        userG.setUsername(textFNonUtilisateurM.getText());
        userG.setUsernamCanonical(textFNonUtilisateurM.getText());
        userG.setRole(AjouterUserComboboxM.getValue());

    }

    @FXML
    private void annulerModifierUser(ActionEvent event) {

        paneGuser.setVisible(true);
        new ZoomIn(paneGuser).play();
        paneGuser.toFront();
    }

    @FXML
    private void selectUser(MouseEvent event) {

        userG = tableViewListeUser.getSelectionModel().getSelectedItem();
    }

}
