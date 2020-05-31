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
import javafx.scene.control.Alert;
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

    public boolean verifNumerotel() {

        if (txtN.getText().length() != 8) {

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Ajouter Fournisseur");
            alert.setHeaderText("  nummero telephone doit comporter 8 chiffres !");

            alert.showAndWait();

            return false;
        }

        try {

            Integer numeroTel = Integer.parseInt(txtN.getText());

        } catch (Exception ex) {

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Ajouter Fournisseur");
            alert.setHeaderText(" champ nummero telephone invalide !");

            alert.showAndWait();
            return false;

        }

        return true;
    }

    public boolean verifCode() {
        
         if (txtC.getText().length() != 4) {

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Ajouter Fournisseur");
            alert.setHeaderText("  nummero code postale doit comporter 4 chiffres !");

            alert.showAndWait();

            return false;
        }
        

        try {

            Integer codePostale = Integer.parseInt(txtC.getText());

        } catch (Exception ex) {

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Ajouter Fournisseur");
            alert.setHeaderText(" champ code postale invalide !");

            alert.showAndWait();

            return false;

        }

        return true;
    }

    @FXML
    private void AjoutFournisseur(ActionEvent event) {

        ServiceFournisseur sp = new ServiceFournisseur();

        if (verifCode() && verifNumerotel()) {

            sp.ajouter(new Fournisseur(txtR.getText(), Integer.parseInt(txtN.getText()), txtA.getText(), txtAdresse.getText(), txtM.getText(), Integer.parseInt(txtC.getText())));

        }
    }

}
