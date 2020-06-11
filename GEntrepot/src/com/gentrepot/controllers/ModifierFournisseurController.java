/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gentrepot.controllers;

import com.gentrepot.models.Fournisseur;
import com.gentrepot.services.ServiceFournisseur;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Mohamed
 */
public class ModifierFournisseurController implements Initializable {

    @FXML
    private TextField txtR;
    @FXML
    private TextField txtN;
    @FXML
    private TextField txtA;
    @FXML
    private TextField txtAdresse;
    @FXML
    private TextField txtM;
    @FXML
    private TextField txtC;
    @FXML
    private Button btn;
 
    public static Fournisseur four ;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        txtR.setText(four.getRaisonSociale());
          Integer i =four.getNumeroTelephone();

        txtN.setText(i.toString());
        txtA.setText(four.getAdresse());
        txtAdresse.setText(four.getAdresseMail());
        txtM.setText(four.getMatriculeFiscale());
        Integer cod =four.getCodePostale();
        txtC.setText(cod.toString());

        // TODO
    }    

    @FXML
    private void ModifierFournisseur(ActionEvent event) throws IOException {
            Fournisseur f = new Fournisseur(four.getId(), txtR.getText(), Integer.parseInt(txtN.getText()), txtA.getText(), txtAdresse.getText(), txtM.getText(), Integer.parseInt(txtC.getText()));
            ServiceFournisseur FS = new ServiceFournisseur();
            FS.modifier(f);
                
                Parent root = FXMLLoader.load(getClass().getResource("Acceuil.fxml"));
                Scene scene = new Scene(root);
                Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
                window.setScene(scene);
                window.show();

     
    }
    
}
