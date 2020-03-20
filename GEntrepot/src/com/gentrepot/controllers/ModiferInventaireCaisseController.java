/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gentrepot.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;

/**
 * FXML Controller class
 *
 * @author oussema
 */
public class ModiferInventaireCaisseController implements Initializable {

    @FXML
    private Label soldeTC;
    @FXML
    private Label soldeTE;
    @FXML
    private Label soldeCalculer;
    @FXML
    private Label ecart;
    @FXML
    private JFXTextField totalTheorique;
    @FXML
    private JFXTextField totalCheque;
    @FXML
    private JFXTextField totalEspece;
    @FXML
    private JFXTextField soldeTCalculer;
    @FXML
    private JFXTextField ecartC;
    @FXML
    private JFXButton btnModifer;
    @FXML
    private JFXButton btnAnnuler;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

       

    }

    @FXML
    private void calculerEcart(KeyEvent event) {
    }

    @FXML
    private void modifer(ActionEvent event) {
    }

    @FXML
    private void annuler(ActionEvent event) {
    }

}
