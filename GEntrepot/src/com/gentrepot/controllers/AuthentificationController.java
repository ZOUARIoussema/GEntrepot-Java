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
import javafx.fxml.Initializable;
import javafx.scene.layout.Pane;

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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void validerConnecter(ActionEvent event) {
        
        
        
        
        
        
        
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
        
        
        User user = new User(0, paneAjouterUsertextNomUser.getText(), paneAjouterUsertextAdresseMail.getText(), paneAjouterUsertextNomUser.getText(), paneAjouterUsertextAdresseMail.getText(), paneAjouterUsertextMotPasse.getText(),"ROLE_CLIEN");

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

}
