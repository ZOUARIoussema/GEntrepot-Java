/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gentrepot.controllers;

import com.gentrepot.models.InventaireCaisse;
import com.gentrepot.services.ServiceInventaireCaisse;
import com.gentrepot.services.ServiceRecouvrementClientCheque;
import com.gentrepot.services.ServiceRecouvrementClientEspece;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.KeyEvent;

/**
 * FXML Controller class
 *
 * @author oussema
 */
public class AjouterInventaireController implements Initializable {

    ServiceRecouvrementClientCheque serviceRecouvrementClientCheque = new ServiceRecouvrementClientCheque();
    ServiceRecouvrementClientEspece serviceRecouvrementClientEspece = new ServiceRecouvrementClientEspece();
    ServiceInventaireCaisse serviceInventaireCaisse = new ServiceInventaireCaisse();
    
    double totalT=(serviceRecouvrementClientCheque.totalCheque() + serviceRecouvrementClientEspece.totalEspece());

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
    private JFXButton btntAjouter;
    @FXML
    private JFXButton btnAnnuler;

    void Ajouter(ActionEvent event) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        totalTheorique.setText(String.valueOf(totalT));
        totalCheque.setText(String.valueOf(serviceRecouvrementClientCheque.totalCheque()));
        totalEspece.setText(String.valueOf(serviceRecouvrementClientEspece.totalEspece()));

    }

    @FXML
    private void ajouterInventaire(ActionEvent event) {

        InventaireCaisse inventaireCaisse = new InventaireCaisse(new Date(), Double.valueOf(soldeTCalculer.getText()), Double.valueOf(totalTheorique.getText()), Double.valueOf(totalCheque.getText()), Double.valueOf(totalEspece.getText()), Double.valueOf(ecartC.getText()));

        serviceInventaireCaisse.ajouter(inventaireCaisse);
        
        clear();
        
        
    }

    @FXML
    private void anuuler(ActionEvent event) {

       
        clear();

    }
    
    
    public void clear(){
        
         totalTheorique.setText(String.valueOf((serviceRecouvrementClientCheque.totalCheque() + serviceRecouvrementClientEspece.totalEspece())));
        totalCheque.setText(String.valueOf(serviceRecouvrementClientCheque.totalCheque()));
        totalEspece.setText(String.valueOf(serviceRecouvrementClientEspece.totalEspece()));
        
        soldeTCalculer.setText("");
        
        ecartC.setText("");
        
    }

    private void calculerEcart(InputMethodEvent event) {
        
         
        
    }

    @FXML
    private void calculerEcart(KeyEvent event) {
        
        
        ecartC.setText(String.valueOf(Double.valueOf(soldeTCalculer.getText())-totalT));
    }

}
