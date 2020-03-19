/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gentrepot.controllers;

import animatefx.animation.ZoomIn;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author oussema
 */
public class MenueAgentCaisseController implements Initializable {

    @FXML
    private Button btnFactureF;
    @FXML
    private Button btnReglemntF;
    @FXML
    private Button btnRecouvrementC;
    @FXML
    private Button btnLettreRelance;
    @FXML
    private Button btnInventaire;
    @FXML
    private Pane paneReglemntF;
    @FXML
    private Pane paneRecouvrementClient;
    @FXML
    private Pane paneLettreR;
    @FXML
    private Pane paneInvetaireC;
    @FXML
    private Pane paneFactureF;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void afficheFacture(ActionEvent event) {
        
        
       if(event.getSource().equals(btnFactureF)) {
           
           
           paneFactureF.setVisible(true);
           new ZoomIn(paneFactureF).play();
           paneFactureF.toFront();
           
           
       }
        
    }

    @FXML
    private void afficheReglementF(ActionEvent event) {
    }

    @FXML
    private void afficheRecouvrementC(ActionEvent event) {
    }

    @FXML
    private void afficheLettreRelance(ActionEvent event) {
    }

    @FXML
    private void afficheInventaire(ActionEvent event) {
    }
    
}
