/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gentrepot.controllers;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author oussema
 */
public class MenueAdminController implements Initializable {

    @FXML
    private JFXButton btnAchat;
    @FXML
    private JFXButton btnV;
    @FXML
    private JFXButton btnT;
    @FXML
    private JFXButton btnLo;
    @FXML
    private JFXButton btngererCompte;
    @FXML
    private JFXButton btnS;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void gererAchat(ActionEvent event) {
        Stage primaryStage = new Stage();

        try {

            AnchorPane root = (AnchorPane) FXMLLoader.load(getClass().getResource("/com/gentrepot/views/Acceuil.fxml"));
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Menue Responsable Achat");
            primaryStage.show();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    @FXML
    private void gererVente(ActionEvent event) {

        Stage primaryStage = new Stage();

        try {

            BorderPane root = (BorderPane) FXMLLoader.load(getClass().getResource("/com/gentrepot/views/admin.fxml"));
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Menue Vente");
            primaryStage.show();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    @FXML
    private void gererTresorerie(ActionEvent event) {

        Stage primaryStage = new Stage();

        try {

            BorderPane root = (BorderPane) FXMLLoader.load(getClass().getResource("/com/gentrepot/views/MenueAgentCaisse.fxml"));
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);

            primaryStage.show();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    @FXML
    private void gererLogistique(ActionEvent event) {

        Stage primaryStage = new Stage();

        try {

            AnchorPane root = (AnchorPane) FXMLLoader.load(getClass().getResource("/com/gentrepot/views/ChefParc.fxml"));
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Menue Chef Parc");
            primaryStage.show();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    @FXML
    private void gererCompte(ActionEvent event) {

        Stage primaryStage = new Stage();

        try {

            BorderPane root = (BorderPane) FXMLLoader.load(getClass().getResource("/com/gentrepot/views/GestionUtilisateur.fxml"));
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Gestion des utilisateurs");

            primaryStage.show();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    @FXML
    private void deconnecter(MouseEvent event) {

        Stage primaryStage = new Stage();

        try {

            HBox root = (HBox) FXMLLoader.load(getClass().getResource("/com/gentrepot/views/Authentification.fxml"));
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);

            primaryStage.show();

            ((Stage) this.btnAchat.getScene().getWindow()).close();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    @FXML
    private void gererS(ActionEvent event) {
        
        Stage primaryStage = new Stage();

        try {

            AnchorPane root = (AnchorPane) FXMLLoader.load(getClass().getResource("/com/gentrepot/views/STOCKAGE.fxml"));
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Gestion des utilisateurs");

            primaryStage.show();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
        
        
        
    }

}
