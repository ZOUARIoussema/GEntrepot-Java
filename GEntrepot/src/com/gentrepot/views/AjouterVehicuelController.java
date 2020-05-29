/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gentrepot.views;

import com.gentrepot.models.Vehicule;
import com.gentrepot.services.ServiceVehicule;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Rym
 */
public class AjouterVehicuelController implements Initializable {

    @FXML
    private TextField txtM;
    @FXML
    private TextField txtC;
    @FXML
    private Button btn;
    @FXML
    private ComboBox txtT;
   
    ObservableList<String> list = FXCollections.observableArrayList("Camion","Om","Partner","semi remorque","autres");
   
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        txtT.setItems(list);
      
        // TODO
    }    

    @FXML
    private void AjoutVehicuel(ActionEvent event) throws IOException {
       ServiceVehicule sp = new  ServiceVehicule();
       sp.ajouter(new Vehicule(Integer.parseInt(txtM.getText()), Integer.parseInt(txtC.getText()),(String) txtT.getSelectionModel().getSelectedItem()));
        
        JOptionPane.showMessageDialog(null, "Vehicule ajoutée !");
    }
    }
    

