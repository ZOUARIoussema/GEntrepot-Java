/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gentrepot.controllers;

import com.gentrepot.models.BonEntree;
import com.gentrepot.models.Fournisseur;
import com.gentrepot.services.ServiceBonEntree;
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
public class AffichageBonEntreeController implements Initializable {

    @FXML
    private TableView<BonEntree> table;
    @FXML
    private TableColumn<BonEntree, Date> date;
    @FXML
    private TableColumn<BonEntree, Date> datep;
    @FXML
    private TableColumn<BonEntree, Date> datee;
    @FXML
    private TableColumn<BonEntree, Integer> commande;

    private ObservableList<BonEntree> BonEntreeData = FXCollections.observableArrayList();

    ServiceBonEntree sb = new ServiceBonEntree();
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        List<BonEntree> listBonEntre = new ArrayList<BonEntree>(); // TODO        
        listBonEntre = sb.afficher();
        BonEntreeData.clear();
        BonEntreeData.addAll(listBonEntre);
        table.setItems(BonEntreeData);
        
       /* id.setCellValueFactory
                (
                        new PropertyValueFactory<>("id")
                );*/
        date.setCellValueFactory
                (
                        new PropertyValueFactory<>("date")
                );
        datep.setCellValueFactory
                (
                        new PropertyValueFactory<>("dateProduction")
                );
        datee.setCellValueFactory
                (
                        new PropertyValueFactory<>("dateExpiration")
                );
        commande.setCellValueFactory
                (
                        new PropertyValueFactory<>("commandeDApprovisionnement")
                );


        // TODO
    }    

    @FXML
    private void supprimer(ActionEvent event) throws SQLDataException {
    
              BonEntree sponsorSelec = (BonEntree) table.getSelectionModel().getSelectedItem();
                sb.supprimer(sponsorSelec);
                resetTableData();
    }
    public void resetTableData() throws SQLDataException
    {
        List<BonEntree> list = new ArrayList<>();
        list = sb.afficher();
        ObservableList<BonEntree> data = FXCollections.observableArrayList(list);
        table.setItems(data);
    }

    @FXML
    private void Modifier(ActionEvent event) throws IOException {
        
                ModifierBonEntreeController.entre=table.getSelectionModel().getSelectedItem();
                System.out.println("com.gentrepot.views.AffichageProduitController.ModifierProd()"+ModifierBonEntreeController.entre);
                Parent root = FXMLLoader.load(getClass().getResource("ModifierBonEntree.fxml"));
                Scene scene = new Scene(root);
                Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
                window.setScene(scene);
                window.show();
    }
    
}
