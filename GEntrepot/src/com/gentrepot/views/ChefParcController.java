/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gentrepot.views;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;

/**
 * FXML Controller class
 *
 * @author Rym
 */
public class ChefParcController implements Initializable {

    @FXML
    private BorderPane bp2;
        public void chargerpage (String page) 
    {
        Parent root = null ;
        try {
            root = FXMLLoader.load(getClass().getResource(page+".fxml"));
        } catch (IOException ex) {
            Logger.getLogger(ChefParcController.class.getName()).log(Level.SEVERE, null, ex);
        }
        bp2.setCenter(root);
        
               
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void ajouterchauffeur(MouseEvent event) {
        chargerpage("AjouterChauffeur");
    }

    private void modifierchauf(MouseEvent event) {
        chargerpage("UpdateChauffeur");
    }

    @FXML
    private void AfficherChauffeur(MouseEvent event) {
         chargerpage("AfficherChauffeur");
    }

   

    @FXML
    private void AfficherAideChauffeur(MouseEvent event) {
         chargerpage("AfficherAideChauffeur");

    }

    @FXML
    private void AjoutAideChauffeur(MouseEvent event) {
         chargerpage("AjoutAideChauffeur");
    }

    @FXML
    private void AjouterVehicule(MouseEvent event) {
        chargerpage("AjouterVehicuel");
    }

    @FXML
    private void AfficherVehicule(MouseEvent event) {
         chargerpage("AfficherVehicuel");
    }

    @FXML
    private void AjouOrdere(MouseEvent event) {
         chargerpage("AjoutOrdre");
    }

    @FXML
    private void AffOrdre(MouseEvent event) {
         chargerpage("AfficherOrdre");
    }
    
}
