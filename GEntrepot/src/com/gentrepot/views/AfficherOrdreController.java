/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gentrepot.views;

import com.gentrepot.models.AideChauffeur;
import com.gentrepot.models.Chauffeur;
import com.gentrepot.models.OrdreMission;
import com.gentrepot.models.Vehicule;
import com.gentrepot.services.ServiceBonLivraison;
import com.gentrepot.services.ServiceOrdreMission;
import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Rym
 */
public class AfficherOrdreController implements Initializable {

    @FXML
    private TableColumn<Vehicule, Integer> vehicule;
    @FXML
    private TableColumn<Chauffeur, String> chauffeur;
    @FXML
    private TableColumn<AideChauffeur, String> aidechauffeur;
    @FXML
    private TableColumn<OrdreMission, Date> dateCreation;
    @FXML
    private TableColumn<OrdreMission, Date> dateSortie;
    @FXML
    private TableColumn<OrdreMission, Date> dateRetour;
    @FXML
    private TableColumn<?, ?> bonLivraison;
    @FXML
    private Button btn;
    @FXML
    private TableView<OrdreMission> table;
    @FXML
    private TextField recherche;
    
    ServiceOrdreMission sb = new ServiceOrdreMission();

    /**
     * Initializes the controller class.
     */
    
    
    ObservableList<AideChauffeur> dataList = FXCollections.observableArrayList();
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        vehicule.setCellValueFactory(new PropertyValueFactory<Vehicule,Integer>("matricule"));
        table.setItems(sb.afficher());
    }    

    @FXML
    private void ModifierOrdred(MouseEvent event) throws IOException {
        //UpdateChauffeurController.chsel=table.getSelectionModel().getSelectedItem();
         Parent root = FXMLLoader.load(getClass().getResource("UpdateChauffeur.fxml"));
                Scene scene = new Scene(root);
                Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
                window.setScene(scene);
                window.show();
    }
    
}
