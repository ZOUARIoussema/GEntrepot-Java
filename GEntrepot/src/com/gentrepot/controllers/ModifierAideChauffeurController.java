/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gentrepot.controllers;

import com.gentrepot.models.AideChauffeur;
import com.gentrepot.services.ServiceAideChauffeur;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Rym
 */
public class ModifierAideChauffeurController implements Initializable {
public static AideChauffeur chsel ;
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

    /**
     * Initializes the controller class.
     */
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       txtN.setText(chsel.getNom());
        txtP.setText(chsel.getPrenom());
       txtA.setText(chsel.getAdresse());
        txtcin.setText(chsel.getCin());
    }    
 ServiceAideChauffeur sp = new ServiceAideChauffeur();
    @FXML
    private void ModifeAideChauffeur(MouseEvent event) throws IOException {
         
        sp.modifier(chsel,txtN.getText(), txtP.getText(), txtA.getText());
        
        System.err.println(chsel.getCin());
                Parent root = FXMLLoader.load(getClass().getResource("ChefParc.fxml"));
                Scene scene = new Scene(root);
                Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
                window.setScene(scene);
                window.show();
    }

    @FXML
    private void suppAide(MouseEvent event) throws IOException {
          sp.supprimer(chsel);
        Parent root = FXMLLoader.load(getClass().getResource("ChefParc.fxml"));
                Scene scene = new Scene(root);
                Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
                window.setScene(scene);
                window.show();
    }
    }
    

