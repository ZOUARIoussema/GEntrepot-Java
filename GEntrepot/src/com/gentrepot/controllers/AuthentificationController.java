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
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author oussema
 */
public class AuthentificationController implements Initializable {

    ServiceUser serviceUser = new ServiceUser();

    @FXML
    private Pane paneAjouterUser;
    @FXML
    private JFXTextField paneAjouterUsertextNomUser;
    @FXML
    private JFXPasswordField paneAjouterUsertextMotPasse;
    @FXML
    private JFXPasswordField paneAjouterUsertextAdresseMail;
    @FXML
    private JFXButton btnValider;
    @FXML
    private JFXButton btnAnnuler;
    @FXML
    private Pane paneConnection;
    @FXML
    private JFXTextField paneConnectionNUser;
    @FXML
    private JFXPasswordField paneConnectionMotPasse;
    @FXML
    private JFXButton btnCreerCompte;
    @FXML
    private Pane paneVerifierCode;
    @FXML
    private Pane paneMotPasseOublie;
    @FXML
    private Pane paneValiderNouveauMotDePasse;
    @FXML
    private JFXTextField textFUserC;
    @FXML
    private JFXTextField textFMailC;
    @FXML
    private JFXButton btnEnvoyerCode;
    @FXML
    private JFXTextField textFCode;
    @FXML
    private JFXButton btnValiderCode;
    @FXML
    private JFXPasswordField textFNewPassword;
    @FXML
    private JFXPasswordField textFVerifPasseword;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    Alert creerAlerte(String text, Alert.AlertType type) {
        Alert alert = new Alert(type);
        alert.setHeaderText(null);
        alert.setContentText(text);

        return alert;
    }

    @FXML
    private void validerConnecter(ActionEvent event) {

        Stage primaryStage = new Stage();

        User user = serviceUser.find(paneConnectionNUser.getText(), paneConnectionMotPasse.getText());

        if (user != null) {

            System.out.println("correcte");

            if (user.getRole().equals("Admin")) {

                try {

                    BorderPane root = (BorderPane) FXMLLoader.load(getClass().getResource("/com/gentrepot/views/MenueAdmin.fxml"));
                    Scene scene = new Scene(root);
                    primaryStage.setScene(scene);
                    primaryStage.setTitle("Menue Agent Caisse : " + user.getUsername());
                    primaryStage.show();
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }

            }

            if (user.getRole().equals("Responsable Achat")) {

            }

            if (user.getRole().equals("Responsable Vente")) {

            }

            if (user.getRole().equals("Agent Caisse")) {

                try {

                    BorderPane root = (BorderPane) FXMLLoader.load(getClass().getResource("/com/gentrepot/views/MenueAgentCaisse.fxml"));
                    Scene scene = new Scene(root);
                    primaryStage.setScene(scene);
                    primaryStage.setTitle("Menue Agent Caisse : " + user.getUsername());
                    primaryStage.show();
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }

            }

            if (user.getRole().equals("Chef De Parc")) {

            }

            if (user.getRole().equals("Client")) {

            }

        } else {
            System.out.println("incorrecte");
            creerAlerte("Non d'utlisateur ou mot de passe incorrecte ! ", Alert.AlertType.WARNING).showAndWait();

        }

    }

    @FXML
    private void annulerConnecter(ActionEvent event) {
    }

    @FXML
    private void creerCompte(ActionEvent event) {

        paneAjouterUser.setVisible(true);
        new ZoomIn(paneAjouterUser).play();
        paneAjouterUser.toFront();

    }

    @FXML
    private void validerajouterUser(ActionEvent event) {

        User user = new User(0, paneAjouterUsertextNomUser.getText(), paneAjouterUsertextAdresseMail.getText(), paneAjouterUsertextNomUser.getText(), paneAjouterUsertextAdresseMail.getText(), paneAjouterUsertextMotPasse.getText(), "Client");

        serviceUser.ajouter(user);

        paneConnection.setVisible(true);
        new ZoomIn(paneConnection).play();
        paneConnection.toFront();

    }

    @FXML
    private void annulerAjouterUser(ActionEvent event) {

        paneConnection.setVisible(true);
        new ZoomIn(paneConnection).play();
        paneConnection.toFront();
    }

    @FXML
    private void afficherPaneMotPasseOublie(MouseEvent event) {

        paneMotPasseOublie.setVisible(true);
        new ZoomIn(paneMotPasseOublie).play();
        paneMotPasseOublie.toFront();
    }

    @FXML
    private void validerEnvoyerCode(ActionEvent event) {
    }

    @FXML
    private void validerCode(ActionEvent event) {
    }

    @FXML
    private void validerCreationPasseWord(ActionEvent event) {
    }

}
