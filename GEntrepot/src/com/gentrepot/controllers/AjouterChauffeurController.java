/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gentrepot.controllers;


import com.gentrepot.models.Chauffeur;
import com.gentrepot.services.ServiceChauffeur;
import java.io.IOException;
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
 * @author Rym
 */
public class AjouterChauffeurController implements Initializable {

  
    @FXML
    private TextField txtcin;
    @FXML
    private TextField txtN;
    @FXML
    private TextField txtP;
    
    @FXML
     private TextField txtA;
    @FXML
    private Button btn;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }   
    @FXML
    private void AjouterChauffeur(ActionEvent event) throws IOException {
        ServiceChauffeur sp = new ServiceChauffeur();
        sp.ajouter(new Chauffeur(txtcin.getText(), txtN.getText(), txtP.getText(), txtA.getText()));
        
        JOptionPane.showMessageDialog(null, "chauffeur ajoutée !");
    }
    
    
   }
