/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gentrepot.views;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author Rym
 */
public class AfficherOrdreController implements Initializable {

    @FXML
    private TableColumn<?, ?> vehicule;
    @FXML
    private TableColumn<?, ?> chauffeur;
    @FXML
    private TableColumn<?, ?> aidechauffeur;
    @FXML
    private TableColumn<?, ?> dateCreation;
    @FXML
    private TableColumn<?, ?> dateSortie;
    @FXML
    private TableColumn<?, ?> dateRetour;
    @FXML
    private TableColumn<?, ?> bonLivraison;
    @FXML
    private Button btn;
    @FXML
    private TableView<?> table;
    @FXML
    private TextField recherche;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void ModifierOrdred(MouseEvent event) {
    }
    
}
