/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gentrepot.controllers;

import com.gentrepot.models.CategorieAchat;
import com.gentrepot.models.Fournisseur;
import com.gentrepot.services.ServiceCategorieAchat;
import java.net.URL;
import java.sql.SQLDataException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author Mohamed
 */
public class AfficherCategorieController implements Initializable {

    @FXML
    private TableView<CategorieAchat> table;
    private TableColumn<CategorieAchat, Integer> Id;
    @FXML
    private TableColumn<CategorieAchat, Integer> nom;

    private ObservableList<CategorieAchat> CategorieData = FXCollections.observableArrayList();

    ServiceCategorieAchat sc = new ServiceCategorieAchat();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        List<CategorieAchat> listCategorie = new ArrayList<CategorieAchat>(); // TODO
        listCategorie = sc.afficher();
        CategorieData.clear();
        CategorieData.addAll(listCategorie);
        table.setItems(CategorieData);
      /*  Id.setCellValueFactory
                (
                        new PropertyValueFactory<>("id")
                );*/
        nom.setCellValueFactory
                (
                        new PropertyValueFactory<>("nom")
                );
        // TODO
    }    

    @FXML
    private void Supprimer(ActionEvent event) throws SQLDataException {
    
            CategorieAchat sponsorSelec = (CategorieAchat) table.getSelectionModel().getSelectedItem();
                sc.supprimer(sponsorSelec);
                resetTableData();
    }
    public void resetTableData() throws SQLDataException
    {
        List<CategorieAchat> list = new ArrayList<>();
        list = sc.afficher();
        ObservableList<CategorieAchat> data = FXCollections.observableArrayList(list);
        table.setItems(data);
    }
    
}
