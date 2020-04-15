/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gentrepot.views;

import com.gentrepot.models.ProduitAchat;
import com.gentrepot.services.ServiceProduitAchat;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Mohamed
 */
public class AffichageProduitController implements Initializable {
    ServiceProduitAchat ss = new ServiceProduitAchat();
    @FXML
    private TableView<ProduitAchat> table;
    @FXML
    private TableColumn<ProduitAchat, String> ref;
    @FXML
    private TableColumn<ProduitAchat, String> lib;
    @FXML
    private TableColumn<ProduitAchat, Integer> qte;
    @FXML
    private TableColumn<ProduitAchat, String> c;
    @FXML
    private TableColumn<ProduitAchat, Double> pa;
    @FXML
    private TableColumn<ProduitAchat, Double> pv;
    @FXML
    private TableColumn<ProduitAchat, ?> sc;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ref.setCellValueFactory(new PropertyValueFactory<ProduitAchat,String>("reference"));
        lib.setCellValueFactory(new PropertyValueFactory<ProduitAchat,String>("libelle"));
        //qte.setCellValueFactory(new PropertyValueFactory<ProduitAchat,Integer>("quantiteEnStock"));
        qte.setCellValueFactory(new PropertyValueFactory<ProduitAchat,Integer>("quantiteEnStock"));
        c.setCellValueFactory(new PropertyValueFactory<ProduitAchat,String>("classe"));
        pa.setCellValueFactory(new PropertyValueFactory<ProduitAchat,Double>("dernierPrixAchat"));
        pv.setCellValueFactory(new PropertyValueFactory<ProduitAchat,Double>("prixVente"));
        table.setItems(ss.afficher());
        // TODO
    }    

    @FXML
    private void ModifProduit(MouseEvent event) throws IOException {
        ModifierProduitController.chsel=table.getSelectionModel().getSelectedItem();
         Parent root = FXMLLoader.load(getClass().getResource("ModifierProduit.fxml"));
                Scene scene = new Scene(root);
                Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
                window.setScene(scene);
                window.show();
    }
    
}
