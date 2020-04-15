/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gentrepot.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class TestInterfaceController implements Initializable {

    private TextField textFNom;
    private TextField textFPrenom;
    @FXML
    private TableColumn<?, ?> col_produit;
    @FXML
    private TableColumn<?, ?> col_prix;
    @FXML
    private TableColumn<?, ?> col_quantite;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
        
        // TODO
    }    

    private void Ajouter(ActionEvent event) {
        
        System.out.println(textFNom.getText());
         System.out.println(textFPrenom.getText());
       
        
    }

    
}
