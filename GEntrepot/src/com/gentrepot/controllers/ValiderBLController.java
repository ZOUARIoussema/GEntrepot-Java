/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gentrepot.controllers;

import com.gentrepot.models.CommandeVente;
import com.gentrepot.models.LigneCommande;
import com.gentrepot.models.ProduitAchat;
import com.gentrepot.utils.DataSource;
import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class ValiderBLController implements Initializable {

    @FXML
    private TableView<LigneCommande> tableViewDetails;
    @FXML
    private TableColumn<LigneCommande, String> col_ref;
    @FXML
    private TableColumn<LigneCommande, Integer> col_qte;
    ObservableList<LigneCommande> oblist = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
       
        
        
        col_ref.setCellValueFactory(new PropertyValueFactory<>("refp"));
      col_qte.setCellValueFactory(new PropertyValueFactory<>("quantite"));
        
    
    
    
    tableViewDetails.setItems(oblist);
    
    
    oblist.addAll(AjouterBLController.Commande.getLigneCommande());
    
        
    
        
      

        
        // TODO
    }    
    
}
