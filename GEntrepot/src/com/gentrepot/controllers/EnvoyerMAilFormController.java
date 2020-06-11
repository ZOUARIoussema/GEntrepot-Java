/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gentrepot.controllers;

import com.gentrepot.services.MailService;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author oussema
 */
public class EnvoyerMAilFormController implements Initializable {

    @FXML
    private TextArea txtSujet;
    @FXML
    private TextField txtObjet;
    @FXML
    private TextField txtAdressMail;
    @FXML
    private Button btnValider;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void validerEnvoyerMail(ActionEvent event) {
        
        
        MailService.EnvoyerMail(txtAdressMail.getText(), txtObjet.getText(), txtSujet.getText());
        
        
        
        
    }
    
}
