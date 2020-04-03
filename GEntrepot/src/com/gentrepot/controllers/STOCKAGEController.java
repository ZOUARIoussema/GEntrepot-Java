/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gentrepot.controllers;

import java.net.URL;
import java.util.Collections;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.StackPane;
import javafx.collections.ObservableList;
import javafx.scene.Node;

/**
 * FXML Controller class
 *
 * @author guiforodrigue
 */
public class STOCKAGEController implements Initializable {

    @FXML
    private MenuItem effectuerCommande;
    @FXML
    private MenuItem chercherCommande;
    @FXML
    private StackPane com;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void effectuerCommande(ActionEvent event) {
        
        /*ObservableList<Node> workingCollection = FXCollections.observableArrayList(com.getChildren());
        Collections.swap(workingCollection, 0, 1);
        com.getChildren().setAll(workingCollection);*/
        ObservableList<Node> childs = com.getChildren();
        Node topNode = childs.get(0);
        Node backNode = childs.get(1);
                  
           backNode.setVisible(false);
           backNode.toBack();
          
           topNode.setVisible(true);
           topNode.toFront();
       
        
    }

    @FXML
    private void chercherCommande(ActionEvent event) {
        
        /*ObservableList<Node> workingCollection = FXCollections.observableArrayList(com.getChildren());
        Collections.swap(workingCollection, 0, 1);
        com.getChildren().setAll(workingCollection);
        StackPane com = new StackPane(); */
        
        ObservableList<Node> childs = com.getChildren();
        Node topNode = childs.get(0);
        Node backNode = childs.get(1);
                  
           backNode.setVisible(true);
           backNode.toFront();
          
           topNode.setVisible(false);
           topNode.toBack();
    }
    
}
