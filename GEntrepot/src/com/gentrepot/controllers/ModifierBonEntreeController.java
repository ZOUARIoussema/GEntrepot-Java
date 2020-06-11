/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gentrepot.controllers;

import com.gentrepot.models.BonEntree;
import com.gentrepot.models.CommandeDApprovisionnement;
import com.gentrepot.models.Fournisseur;
import com.gentrepot.services.ServiceBonEntree;
import com.gentrepot.services.ServiceCommandeDApprovisionnment;
import com.gentrepot.utils.DataSource;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Iterator;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Mohamed
 */
public class ModifierBonEntreeController implements Initializable {

    @FXML
    private ChoiceBox<Integer> commande;
    @FXML
    private DatePicker datep;
    @FXML
    private DatePicker datee;

    public static BonEntree entre ;

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
            
           commande.setItems(catlist());
       
        // TODO
    }    

    @FXML
    private void ModifierBonEntre(ActionEvent event) throws IOException {
        
        ServiceBonEntree sb = new ServiceBonEntree();
                Date de = new java.sql.Date(  new Date(datee.getEditor().getText()).getTime());
                Date dp = new java.sql.Date(  new Date(datep.getEditor().getText()).getTime());

         BonEntree be = new BonEntree(entre.getId(), entre.getDate(),(java.sql.Date) dp,(java.sql.Date) de,  sb.findCommndeById(commande.getValue()).getNumeroC());
        sb.modifier(be);
        
                  Parent root = FXMLLoader.load(getClass().getResource("Acceuil.fxml"));
                Scene scene = new Scene(root);
                Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
                window.setScene(scene);
                window.show();

    }
    
}
