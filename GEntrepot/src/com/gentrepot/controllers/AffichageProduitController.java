/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gentrepot.controllers;

import com.gentrepot.models.Fournisseur;
import com.gentrepot.models.ProduitAchat;
import com.gentrepot.services.ServiceProduitAchat;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLDataException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
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
    private TableColumn<ProduitAchat, Integer> sc;

    @FXML
    private TextField recherche;
    
         private ObservableList<ProduitAchat> prData = FXCollections.observableArrayList();


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
      
       /* prData.clear();
        prData.addAll(ss.afficher());
        table.setItems(prData);
        
        
        ref.setCellValueFactory(new PropertyValueFactory<ProduitAchat,String>("reference"));
        lib.setCellValueFactory(new PropertyValueFactory<ProduitAchat,String>("libelle"));
        qte.setCellValueFactory(new PropertyValueFactory<ProduitAchat,Integer>("quantiteStock"));
        c.setCellValueFactory(new PropertyValueFactory<ProduitAchat,String>("classe"));
        pa.setCellValueFactory(new PropertyValueFactory<ProduitAchat,Double>("dernierPrixAchat"));
        pv.setCellValueFactory(new PropertyValueFactory<ProduitAchat,Double>("prixVente"));
        sc.setCellValueFactory(new PropertyValueFactory<ProduitAchat,Integer>("sousCategorieAchat"));
        table.setItems(ss.afficher());*/
        // TODO
        
        ref.setCellValueFactory(new PropertyValueFactory<ProduitAchat,String>("reference"));
        lib.setCellValueFactory(new PropertyValueFactory<ProduitAchat,String>("libelle"));
        qte.setCellValueFactory(new PropertyValueFactory<ProduitAchat,Integer>("quantiteStock"));
        c.setCellValueFactory(new PropertyValueFactory<ProduitAchat,String>("classe"));
        pa.setCellValueFactory(new PropertyValueFactory<ProduitAchat,Double>("dernierPrixAchat"));
        pv.setCellValueFactory(new PropertyValueFactory<ProduitAchat,Double>("prixVente"));
        sc.setCellValueFactory(new PropertyValueFactory<ProduitAchat,Integer>("sousCategorieAchat"));
        table.setItems(ss.afficher());
        prData.addAll(ss.afficher());
        FilteredList<ProduitAchat> filtredData= new FilteredList<>(prData, b ->true);
       
        // 2. Set the filter Predicate whenever the filter changes.
		recherche.textProperty().addListener((observable, oldValue, newValue) -> {
			filtredData.setPredicate(F -> {
				// If filter text is empty, display all persons.
								
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				
				// Compare first name and last name of every person with filter text.
				String lowerCaseFilter = newValue.toLowerCase();
                                if(F.getReference().toLowerCase().indexOf(lowerCaseFilter) != -1 ){
                        return true;
                    }
                    else if (F.getLibelle().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                        return true; // Filter matches last name.
                    }
                    
                    else
                        return false;
                } );
            });
             // 3. Wrap the FilteredList in a SortedList. 
        SortedList<ProduitAchat> sortedData = new SortedList<>(filtredData);

        // 4. Bind the SortedList comparator to the TableView comparator.
        // 	  Otherwise, sorting the TableView would have no effect.
        sortedData.comparatorProperty().bind(table.comparatorProperty());

        // 5. Add sorted (and filtered) data to the table.
        table.setItems(sortedData);
    
      
  

    }
        
        
     



  /*  @FXML
    private void ModifProduit(ActionEvent event) throws IOException {
        
               ModifierProduitController.chsel=table.getSelectionModel().getSelectedItem();
          Parent root = FXMLLoader.load(getClass().getResource("/views/ModifierProduit.fxml"));
                Scene scene = new Scene(root);
                Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
                window.setScene(scene);
                window.show();
        
    }
*/
    @FXML
    private void SupprimerProd(ActionEvent event) throws SQLDataException {
        
         ProduitAchat sponsorSelec = (ProduitAchat) table.getSelectionModel().getSelectedItem();
                ss.supprimer(sponsorSelec);
                resetTableData();
    }
    public void resetTableData() throws SQLDataException
    {
        List<ProduitAchat> list = new ArrayList<>();
        list = ss.afficher();
        ObservableList<ProduitAchat> data = FXCollections.observableArrayList(list);
        table.setItems(data);
    }

    @FXML
    private void ModifierProd(ActionEvent event) throws IOException {
             ModifierProduitController.chsel=table.getSelectionModel().getSelectedItem();
        System.out.println("com.gentrepot.views.AffichageProduitController.ModifierProd()"+ModifierProduitController.chsel);
                Parent root = FXMLLoader.load(getClass().getResource("ModifierProduit.fxml"));
                Scene scene = new Scene(root);
                Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
                window.setScene(scene);
                window.show();
    }

    

   /* @FXML
    private void Rechercher(ActionEvent event) {
        
        ref.setCellValueFactory(new PropertyValueFactory<ProduitAchat,String>("reference"));
        lib.setCellValueFactory(new PropertyValueFactory<ProduitAchat,String>("libelle"));
        qte.setCellValueFactory(new PropertyValueFactory<ProduitAchat,Integer>("quantiteStock"));
        c.setCellValueFactory(new PropertyValueFactory<ProduitAchat,String>("classe"));
        pa.setCellValueFactory(new PropertyValueFactory<ProduitAchat,Double>("dernierPrixAchat"));
        pv.setCellValueFactory(new PropertyValueFactory<ProduitAchat,Double>("prixVente"));
        sc.setCellValueFactory(new PropertyValueFactory<ProduitAchat,Integer>("sousCategorieAchat"));
        
        List<ProduitAchat> list = ss.afficher();
            
            //tableview.setItems(observablelist);
            
            FilteredList<ProduitAchat> filtredData= new FilteredList<>(prData, b ->true);
            recherche.textProperty().addListener((observable,oldValue,newValue) -> {
                Predicate<? super ProduitAchat> ProduitAchat;
                filtredData.setPredicate((ProduitAchat evenement) -> {
                    if (newValue == null || newValue.isEmpty()){
                        return true;
                    }
                    
                    String lowerCaseFilter = newValue.toLowerCase();
                    if(evenement.getReference().toLowerCase().indexOf(lowerCaseFilter) != -1 ){
                        return true;
                    }
                    else if (evenement.getLibelle().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                        return true; // Filter matches last name.
                    }
                    
                    else
                        return false;
                } );
            });
             // 3. Wrap the FilteredList in a SortedList. 
        SortedList<ProduitAchat> sortedData = new SortedList<>(filtredData);

        // 4. Bind the SortedList comparator to the TableView comparator.
        // 	  Otherwise, sorting the TableView would have no effect.
        sortedData.comparatorProperty().bind(table.comparatorProperty());

        // 5. Add sorted (and filtered) data to the table.
        table.setItems(sortedData);
    
      
  

    }*/

    @FXML
    private void stat(ActionEvent event) throws IOException {
        
        
        System.out.println("com.gentrepot.views.AffichageProduitController.ModifierProd()"+ModifierProduitController.chsel);
                Parent root = FXMLLoader.load(getClass().getResource("Stat.fxml"));
                Scene scene = new Scene(root);
                Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
                window.setScene(scene);
                window.show();
    }
    
    
}
