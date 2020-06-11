/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gentrepot.controllers;

import com.gentrepot.models.CategorieAchat;
import com.gentrepot.models.SousCategorieAchat;
import com.gentrepot.services.ServiceSousCategorieAchat;
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
public class AffichageSousCategorieController implements Initializable {
    ServiceSousCategorieAchat ss =  new ServiceSousCategorieAchat();

    @FXML
    private TableView<SousCategorieAchat> tab;
    private TableColumn<SousCategorieAchat, Integer> id;
    @FXML
    private TableColumn<SousCategorieAchat, String> nom;
    @FXML
    private TableColumn<SousCategorieAchat, Integer> cat;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        nom.setCellValueFactory(new PropertyValueFactory<SousCategorieAchat,String>("nom"));
       // id.setCellValueFactory(new PropertyValueFactory<SousCategorieAchat,Integer>("id"));
        cat.setCellValueFactory(new PropertyValueFactory<SousCategorieAchat,Integer>("categorieAchat"));
        tab.setItems(ss.afficher());
        // TODO
    }    

    @FXML
    private void Supprimer(ActionEvent event) throws SQLDataException {
    
        SousCategorieAchat sponsorSelec = (SousCategorieAchat) tab.getSelectionModel().getSelectedItem();
                ss.supprimer(sponsorSelec);
                resetTableData();
    }
    public void resetTableData() throws SQLDataException
    {
        List<SousCategorieAchat> list = new ArrayList<>();
        list = ss.afficher();
        ObservableList<SousCategorieAchat> data = FXCollections.observableArrayList(list);
        tab.setItems(data);
    }
    }
    

