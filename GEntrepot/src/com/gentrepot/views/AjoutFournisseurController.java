/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gentrepot.views;

import com.gentrepot.models.Fournisseur;
import com.gentrepot.services.ServiceFournisseur;
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
public class AjoutFournisseurController implements Initializable {

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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    
    @FXML
    private void AjoutFournisseur(ActionEvent event) {
        ServiceFournisseur sp = new ServiceFournisseur();
        sp.ajouter(new Fournisseur(txtR.getText(),Integer.parseInt(txtN.getText()), txtA.getText(), txtAdresse.getText(), txtM.getText(),Integer.parseInt(txtC.getText())));
        
        JOptionPane.showMessageDialog(null, "Fournissseur ajoutée !");
    
    }
    
}
