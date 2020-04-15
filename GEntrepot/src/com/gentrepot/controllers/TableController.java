/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gentrepot.controllers;

import com.gentrepot.models.LigneCommande;
import com.gentrepot.services.ServiceLigneCommande;
import com.gentrepot.utils.DataSource;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 *
 * @author LENOVO
 */
public class TableController implements Initializable {
    
    private TableView<LigneCommande> table;

    @FXML
    private TableColumn<LigneCommande, String> col_produit;
    @FXML
    private TableColumn<LigneCommande, Double> col_prix;
    @FXML
    private TableColumn<LigneCommande, Integer> col_quantite;
    @FXML
    private TableColumn<LigneCommande, Double> col_tva;
    @FXML
    private TableColumn<LigneCommande, Double> col_total;

    
    ObservableList<LigneCommande> oblist = FXCollections.observableArrayList();
    @FXML
    private TableView<LigneCommande> tableViewLigne;
    
    
    
    ServiceLigneCommande serviceLigneCommande = new ServiceLigneCommande();
    
    @Override
    public void initialize(URL location, ResourceBundle resources) { 
       //  oblist.addAll(serviceLigneCommande.RecupereParCommande());
 
        col_produit.setCellValueFactory(new PropertyValueFactory<>("produit"));        
        col_prix.setCellValueFactory(new PropertyValueFactory<>("prix"));
        col_quantite.setCellValueFactory(new PropertyValueFactory<>("quantite"));
        col_tva.setCellValueFactory(new PropertyValueFactory<>("tva"));
        col_total.setCellValueFactory(new PropertyValueFactory<>("total"));
        
        tableViewLigne.setItems(oblist);

    }
    
}
