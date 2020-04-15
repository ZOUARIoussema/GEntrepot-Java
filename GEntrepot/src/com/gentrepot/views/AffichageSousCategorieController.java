/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gentrepot.views;

import com.gentrepot.models.SousCategorieAchat;
import com.gentrepot.services.ServiceSousCategorieAchat;
import java.net.URL;
import java.util.ResourceBundle;
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
    @FXML
    private TableColumn<SousCategorieAchat, Integer> id;
    @FXML
    private TableColumn<SousCategorieAchat, String> nom;
    @FXML
    private TableColumn<SousCategorieAchat, ?> cat;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        nom.setCellValueFactory(new PropertyValueFactory<SousCategorieAchat,String>("nom"));
        id.setCellValueFactory(new PropertyValueFactory<SousCategorieAchat,Integer>("id"));
       tab.setItems(ss.afficher());
        // TODO
    }    
    
}
