/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gentrepot.controllers;

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
    private Button ajoutF;
    @FXML
    private Button affFournisseur;
    @FXML
    private Button ajoutP;
    @FXML
    private Button affichP;
    @FXML
    private Button ajoutSC;
    @FXML
    private Button afficheSC;
    @FXML
    private Button ajoutC;
    @FXML
    private Button afficheC;
    @FXML
    private Button ajoutBE;
    @FXML
    private Button afficheBE;
    @FXML
    private Button ajoutBR;
    @FXML
    private Button afficheBR;
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
    private void AjouterCategorie(ActionEvent event) {
             chargerpage("AjouterCategorie");

    }

    @FXML
    private void AfficherCategorie(ActionEvent event) {
             chargerpage("AfficherCategorie");

    }

    @FXML
    private void AjouterBonEntre(ActionEvent event) {
             chargerpage("AjoutBonEntree");

    }

    @FXML
    private void AfficherBonEntre(ActionEvent event) {
             chargerpage("AffichageBonEntree");

    }

    @FXML
    private void AjouterBonRetour(ActionEvent event) {
             chargerpage("AjoutBonRetour");

    }

    @FXML
    private void AfficherBonretour(ActionEvent event) {
             chargerpage("AfficherBonRetour");

    }

    @FXML
    private void envoyerMail(MouseEvent event) {
        chargerpage("EnvoyerMAilForm");
    }

    @FXML
    private void AfficherFournisseur(ActionEvent event) {
        chargerpage("AffichageFournisseur");
    }
    
}
