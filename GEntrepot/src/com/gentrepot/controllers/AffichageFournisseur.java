/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gentrepot.controllers;

import com.gentrepot.models.Fournisseur;
import com.gentrepot.services.ServiceFournisseur;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLDataException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
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
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Mohamed
 */
public class AffichageFournisseur implements Initializable {

    private TableColumn<Fournisseur, Integer> id;
    @FXML
    private TableColumn<Fournisseur, String> reseauSoci;
    @FXML
    private TableColumn<Fournisseur, String> adresse;
    @FXML
    private TableColumn<Fournisseur, String> mail;
    @FXML
    private TableColumn<Fournisseur, String> matricule;
    @FXML
    private TableColumn<Fournisseur, Integer> codePoste;
    @FXML
    private TableView<Fournisseur> table;
    @FXML
    private TableColumn<Fournisseur, Integer> tel;
    
    private ObservableList<Fournisseur> FournisseurData = FXCollections.observableArrayList();
    
    ServiceFournisseur sf = new ServiceFournisseur();
    @FXML
    private TextField recherche;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        List<Fournisseur> listFournisseur = new ArrayList<Fournisseur>(); // TODO
        listFournisseur = sf.afficher();
        FournisseurData.clear();
        FournisseurData.addAll(listFournisseur);
    
        reseauSoci.setCellValueFactory
                (
                        new PropertyValueFactory<>("raisonSociale")
                );
        adresse.setCellValueFactory
                (
                        new PropertyValueFactory<>("adresse")
                );
        mail.setCellValueFactory
                (
                        new PropertyValueFactory<>("adresseMail")
                );
        matricule.setCellValueFactory
                (
                        new PropertyValueFactory<>("matriculeFiscale")
                );
        codePoste.setCellValueFactory
                (
                        new PropertyValueFactory<>("codePostale")
                );
        tel.setCellValueFactory
                (
                        new PropertyValueFactory<>("numeroTelephone")
                );
        table.setItems(FournisseurData);
        
        FilteredList<Fournisseur> filteredData = new FilteredList<>(FournisseurData, b -> true);
		
		// 2. Set the filter Predicate whenever the filter changes.
		recherche.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(F -> {
				// If filter text is empty, display all persons.
								
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				
				// Compare first name and last name of every person with filter text.
				String lowerCaseFilter = newValue.toLowerCase();
				
				if (F.getRaisonSociale().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
					return true; // Filter matches first name.
				} 
                                else if (String.valueOf(F.getNumeroTelephone()).indexOf(lowerCaseFilter)!=-1)
                                {
                                    return true;
                                }
				else if (F.getMatriculeFiscale().toLowerCase().indexOf(lowerCaseFilter) != -1 )
                                {
                                    return true;
                                }    
				else
                                {
                                    return false; // Does not match.
                                }
				    	 
			});
		});
		
		// 3. Wrap the FilteredList in a SortedList. 
		SortedList<Fournisseur> sortedData = new SortedList<>(filteredData);
		
		// 4. Bind the SortedList comparator to the TableView comparator.
		// 	  Otherwise, sorting the TableView would have no effect.
		sortedData.comparatorProperty().bind(table.comparatorProperty());
		
		// 5. Add sorted (and filtered) data to the table.
		table.setItems(sortedData);
      
       

    
        
    }    

    @FXML
    private void Modifier(ActionEvent event) throws IOException {
               ModifierFournisseurController.four=table.getSelectionModel().getSelectedItem();
        System.out.println("com.gentrepot.views.AffichageProduitController.ModifierProd()"+ModifierFournisseurController.four);
                Parent root = FXMLLoader.load(getClass().getResource("ModifierFournisseur.fxml"));
                Scene scene = new Scene(root);
                Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
                window.setScene(scene);
                window.show();
    }

    @FXML
    private void delete(ActionEvent event) throws SQLDataException {
                Fournisseur sponsorSelec = (Fournisseur) table.getSelectionModel().getSelectedItem();
                sf.supprimerFornisseur(sponsorSelec.getId());
                resetTableData();
    }
    public void resetTableData() throws SQLDataException
    {
        List<Fournisseur> list = new ArrayList<>();
        list = sf.afficher();
        ObservableList<Fournisseur> data = FXCollections.observableArrayList(list);
        table.setItems(data);
    }

    
}
