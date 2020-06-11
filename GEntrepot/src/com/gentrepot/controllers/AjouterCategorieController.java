/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gentrepot.controllers;

import com.gentrepot.models.CategorieAchat;
import com.gentrepot.services.ServiceCategorieAchat;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Mohamed
 */
public class AjouterCategorieController implements Initializable {

    @FXML
    private TextField nom;
    @FXML
    private Button AjouteCategorie;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void AjouteCategorie(ActionEvent event) {
        
        ServiceCategorieAchat sc = new ServiceCategorieAchat();
        CategorieAchat c = new CategorieAchat(nom.getText());
        sc.ajouter(c);
        JOptionPane.showMessageDialog(null, "Categorie ajoutée !");

        
    }
    
}
