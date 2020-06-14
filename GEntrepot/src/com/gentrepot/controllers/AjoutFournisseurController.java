/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gentrepot.controllers;

import com.gentrepot.models.Fournisseur;
import com.gentrepot.services.ServiceFournisseur;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.UnaryOperator;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
//import javafx.scene.control.TextFormatter;
import javafx.util.converter.IntegerStringConverter;
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
    ServiceFournisseur sp = new ServiceFournisseur();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
       /* UnaryOperator<TextFormatter.Change> integerFilter = change -> {
            String newText = change.getControlNewText();
            if (newText.matches("-?([1-9][0-9]*)?")) {
                return change;
            }
            return null;
        };
        txtN.setTextFormatter(new TextFormatter<Integer>(new IntegerStringConverter(), 0, integerFilter));*/

    }
    
    public boolean verifNumerotel() {

        if (txtN.getText().length() != 8) {

            JOptionPane.showMessageDialog(null, "nummero telephone doit comporter 8 chiffres !");
            

            return false;
        }

        try {

            Integer numeroTel = Integer.parseInt(txtN.getText());

        } catch (Exception ex) {

            JOptionPane.showMessageDialog(null,"champ nummero telephone invalide !");
            
            return false;

        }

        return true;
    }

    public boolean verifCode() {
        
         if (txtC.getText().length() != 4) {

            JOptionPane.showMessageDialog(null, "numéro code postale doit comporter 4 chiffres !");

            return false;
        }
        

        try {

            Integer codePostale = Integer.parseInt(txtC.getText());

        } catch (Exception ex) {

            JOptionPane.showMessageDialog(null, "champ code postale invalide !");
            

            return false;

        }

        return true;
    }

    @FXML
    private void AjoutFournisseur(ActionEvent event) {

        if (txtR.getText().equals("") || txtA.getText().equals("") || txtAdresse.getText().equals("") || txtM.getText().equals("") || txtC.getText().equals(0) || txtN.getText().equals(0)) {

            JOptionPane.showMessageDialog(null, "Vérifier votre champs");
        } else if (sp.validerEmail(txtAdresse.getText())) {
           
            JOptionPane.showMessageDialog(null, "Vérifier votre Mail");
        } else {
            if (verifCode() && verifNumerotel()) {
            sp.ajouter(new Fournisseur(txtR.getText(), Integer.parseInt(txtN.getText()), txtA.getText(), txtAdresse.getText(), txtM.getText(), Integer.parseInt(txtC.getText())));
            }
        }
    }
}
