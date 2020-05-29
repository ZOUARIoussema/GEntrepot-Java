/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gentrepot.controllers;

import static com.gentrepot.controllers.AuthentificationController.userG;
import com.gentrepot.models.CommandeVente;
import com.gentrepot.models.LigneCommande;
import com.gentrepot.models.ProduitAchat;
import com.gentrepot.services.ServiceCommandeVente;
import com.gentrepot.utils.DataSource;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class AjouterBLController implements Initializable {

    @FXML
    private Button btnLivrer;
    @FXML
    private TableView<CommandeVente> tableViewCommande;
    @FXML
    private TableColumn<CommandeVente, Double> col_total;
    @FXML
    private TableColumn<CommandeVente, Date> col_date;
    @FXML
    private TableColumn<CommandeVente, Double> col_taux;
    @FXML
    private TableColumn<CommandeVente, String> col_etat;
    
    @FXML
    private Button btnDetails;
    Connection cnx = DataSource.getInstance().getCnx();
    ObservableList<CommandeVente> oblist = FXCollections.observableArrayList();
    
    
    ServiceCommandeVente serviceCommandeVente =new ServiceCommandeVente();

    
    
    
    
       public static CommandeVente Commande = null;
       

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
     /*  try {
            String requete
                                  = "select * from commande_vente WHERE user="+Commande.getUser();


            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet res = pst.executeQuery();

            while (res.next()) {
                
 
                oblist.add(new CommandeVente( res.getDouble("totalC"), res.getDate("dateC"), res.getString("etat"), res.getDouble("tauxRemise")));

            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }*/
      
      
      oblist.addAll(serviceCommandeVente.afficherCommandeByUser(userG));
        
        col_total.setCellValueFactory(new PropertyValueFactory<>("totalC"));
        col_date.setCellValueFactory(new PropertyValueFactory<>("dateC"));
        col_etat.setCellValueFactory(new PropertyValueFactory<>("etat"));
        col_taux.setCellValueFactory(new PropertyValueFactory<>("tauxRemise"));

                tableViewCommande.setItems(oblist);

    }    

    @FXML
    private void afficherInterfaceDetails(ActionEvent event) {
        
        
        Stage primaryStage = new Stage();
        
        try {

            AnchorPane root = (AnchorPane) FXMLLoader.load(getClass().getResource("/com/gentrepot/views/ValiderBL.fxml"));
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);

            primaryStage.show();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
        
        
    }
    
     @FXML
    private void selectCommande(MouseEvent event) {
        
        Commande = tableViewCommande.getSelectionModel().getSelectedItem();

        
    }

    @FXML
    private void afficherInterfaceLivreer(ActionEvent event) {
        
        
        
        
          Stage primaryStage = new Stage();
        
        try {

            AnchorPane root = (AnchorPane) FXMLLoader.load(getClass().getResource("/com/gentrepot/views/ValidationB.fxml"));
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);

            primaryStage.show();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
        
                
    }

   
    
    
    
    
}
