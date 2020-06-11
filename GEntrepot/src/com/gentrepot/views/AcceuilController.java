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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;

/**
 * FXML Controller class
 *
 * @author Mohamed
 */
public class AcceuilController implements Initializable {

    @FXML
    private BorderPane bp2;
    @FXML
    private ImageView imageMail;
    @FXML
    private Button abe;
    @FXML
    private Button ajbe;
        public void chargerpage (String page) 
    {
        Parent root = null ;
        try {
            root = FXMLLoader.load(getClass().getResource(page+".fxml"));
        } catch (IOException ex) {
            Logger.getLogger(AcceuilController.class.getName()).log(Level.SEVERE, null, ex);
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
    private void AjoutFournisseur(MouseEvent event) {
        chargerpage("AjoutFournisseur");
    }

    @FXML
    private void AjouterProduit(MouseEvent event) {
        chargerpage("AjoutProduit");
    }

    @FXML
    private void AjoutSousC(MouseEvent event) {
        chargerpage("AjoutSousCategorie");
    }

    @FXML
    private void affichersouscat(MouseEvent event) {
        chargerpage("AffichageSousCategorie");
    }

    @FXML
    private void afficherproduits(MouseEvent event) {
        chargerpage("AffichageProduit");
    }

    @FXML
    private void envoyerMail(MouseEvent event) {
        
         chargerpage("EnvoyerMAilForm");
    }

    @FXML
    private void afficherbe(ActionEvent event) {
        chargerpage("AffichageBonEntree");
    }

    @FXML
    private void ajoutBE(ActionEvent event) {
        chargerpage("AjoutBonEntree");
    }
    
}
