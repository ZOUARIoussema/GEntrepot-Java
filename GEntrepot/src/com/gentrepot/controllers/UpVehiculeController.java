/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gentrepot.controllers;

import com.gentrepot.models.Vehicule;
import com.gentrepot.services.ServiceVehicule;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Rym
 */
public class UpVehiculeController implements Initializable {
    ObservableList<Vehicule> cls = FXCollections.observableArrayList();
     public static Vehicule chsel ;


    @FXML
    private Button btn;
    @FXML
    private TextField txtM;
    @FXML
    private TextField txtC;
  
    @FXML
    private ComboBox<String> T;

    /**
     * Initializes the controller class.
     */
     ObservableList<String> list = FXCollections.observableArrayList("Camion","Om","Partner","semi remorque","autres");
 


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Integer m = chsel.getMatricule();
         txtM.setText(m.toString());
         Integer c = chsel.getCapacite();
        txtC.setText(c.toString());
         T.setItems(list);
    
       
    }    
 
    ServiceVehicule sp = new ServiceVehicule();

    @FXML
    private void ModifierVehicule(MouseEvent event) throws IOException {
        sp.modifier(chsel,Integer.parseInt(txtM.getText()), Integer.parseInt(txtC.getText()), T.getSelectionModel().getSelectedItem() );
        
        Parent root = FXMLLoader.load(getClass().getResource("ChefParc.fxml"));
                Scene scene = new Scene(root);
                Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
                window.setScene(scene);
                window.show();
    }

    @FXML
    private void SupV(MouseEvent event) throws IOException {
        sp.supprimer(chsel);
        
        Parent root = FXMLLoader.load(getClass().getResource("ChefParc.fxml"));
                Scene scene = new Scene(root);
                Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
                window.setScene(scene);
                window.show();
    }
}
