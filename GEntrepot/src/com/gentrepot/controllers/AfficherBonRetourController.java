
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gentrepot.controllers;

import com.gentrepot.models.BonEntree;
import com.gentrepot.models.BonRetour;
import com.gentrepot.services.ServiceBonEntree;
import com.gentrepot.services.ServiceBonRetour;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLDataException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Mohamed
 */
public class AfficherBonRetourController implements Initializable {

    @FXML
    private TableView<BonRetour> Table;
    private TableColumn<BonRetour, Integer> id;
    @FXML
    private TableColumn<BonRetour, String> motif;
    @FXML
    private TableColumn<BonRetour, Date> date;
    @FXML
    private TableColumn<BonRetour, Integer> NumCommande;

    private ObservableList<BonRetour> BonRetourData = FXCollections.observableArrayList();

    ServiceBonRetour sb = new ServiceBonRetour();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        List<BonRetour> listRetours = new ArrayList<BonRetour>(); // TODO        
        listRetours = sb.afficher();
        BonRetourData.clear();
        BonRetourData.addAll(listRetours);
        Table.setItems(BonRetourData);
        
       /* id.setCellValueFactory
                (
                        new PropertyValueFactory<>("id")
                );*/
        date.setCellValueFactory
                (
                        new PropertyValueFactory<>("date")
                );
        motif.setCellValueFactory
                (
                        new PropertyValueFactory<>("motifDeRetour")
                );
        NumCommande.setCellValueFactory
                (
                        new PropertyValueFactory<>("commandeDApprovisionnement")
                );
    }    

    @FXML
    private void Supprimer(ActionEvent event) throws SQLDataException {
    
              BonRetour sponsorSelec = (BonRetour) Table.getSelectionModel().getSelectedItem();
                sb.supprimer(sponsorSelec);
                resetTableData();
    }
    public void resetTableData() throws SQLDataException
    {
        List<BonRetour> list = new ArrayList<>();
        list = sb.afficher();
        ObservableList<BonRetour> data = FXCollections.observableArrayList(list);
        Table.setItems(data);
    }

    @FXML
    private void Modifier(ActionEvent event) throws IOException {
              
                ModifierBonRetourController.Brouteur=Table.getSelectionModel().getSelectedItem();
                System.out.println("com.gentrepot.views.AffichageProduitController.ModifierProd()"+ModifierBonRetourController.Brouteur);
                Parent root = FXMLLoader.load(getClass().getResource("ModifierBonRetour.fxml"));
                Scene scene = new Scene(root);
                Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
                window.setScene(scene);
                window.show();
    }
    
}
