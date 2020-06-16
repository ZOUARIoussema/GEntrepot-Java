/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gentrepot.controllers;

import com.gentrepot.models.BonRetour;
import com.gentrepot.models.CommandeDApprovisionnement;
import com.gentrepot.services.ServiceBonEntree;
import com.gentrepot.services.ServiceBonRetour;
import com.gentrepot.services.ServiceCommandeDApprovisionnment;
import com.gentrepot.utils.DataSource;
import java.net.URL;
import java.sql.Connection;
import java.time.LocalDate;
import java.util.Date;
import java.util.Iterator;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Mohamed
 */
public class AjouterBonRetourController implements Initializable {

    @FXML
    private TextField motif;
    @FXML
    private ChoiceBox<Integer> commandeAp;

    ServiceBonRetour sr = new ServiceBonRetour();
    /**
     * Initializes the controller class.
     */
  public ObservableList catlist (){
    
      ServiceCommandeDApprovisionnment sc = new ServiceCommandeDApprovisionnment();
    ObservableList<Integer> l = FXCollections.observableArrayList();
    ObservableList<CommandeDApprovisionnement> k = sc.afficherCommande();
    Iterator<CommandeDApprovisionnement> i = k.iterator();
    while(i.hasNext())
    {
        l.add(i.next().getNumeroC());
    }
              return l;
    }
                 
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
            Connection cnx = DataSource.getInstance().getCnx();
            ServiceBonEntree sb = new ServiceBonEntree();
            
            commandeAp.setItems(catlist());

            
   
        // TODO
    }    

    @FXML
    private void Ajouter(ActionEvent event) {
        
          LocalDate d = LocalDate.now();
          Date date = java.sql.Date.valueOf(d);
               
          BonRetour br = new BonRetour( date, motif.getText(), commandeAp.getValue());
          sr.ajouter(br);
    }
    
}
