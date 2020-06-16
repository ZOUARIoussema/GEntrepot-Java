/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gentrepot.controllers;

import com.gentrepot.models.AideChauffeur;
import com.gentrepot.models.Chauffeur;
import com.gentrepot.services.ServiceAideChauffeur;
import com.gentrepot.utils.DataSource;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
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
 * @author Rym
 */
public class AfficherAideChauffeurController implements Initializable {

  ServiceAideChauffeur sc = new ServiceAideChauffeur();
   

    @FXML private TableView<AideChauffeur> table ;
    @FXML private TableColumn<AideChauffeur,String> cin ;
     @FXML private TableColumn<AideChauffeur,String> prenom ;
      @FXML private TableColumn<AideChauffeur,String> nom ;
       @FXML private TableColumn<AideChauffeur,String> adresse ;
    @FXML
    private TextField recherche;
     
          public void AfficherAideChauffeur() {
      

         /*try {
         String requete = "SELECT * FROM aidechauffeur";
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                data.add(new AideChauffeur(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4)));
            }
            cnx.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }*/
       
    }
         ObservableList<AideChauffeur> dataList = FXCollections.observableArrayList();
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         cin.setCellValueFactory(new PropertyValueFactory<AideChauffeur,String>("cin"));
        prenom.setCellValueFactory(new PropertyValueFactory<AideChauffeur,String>("prenom"));
        nom.setCellValueFactory(new PropertyValueFactory<AideChauffeur,String>("nom"));
        adresse.setCellValueFactory(new PropertyValueFactory<AideChauffeur,String>("adresse"));
         table.setItems(sc.afficher());
         
         dataList.addAll(sc.afficher());
        
                // Wrap the ObservableList in a FilteredList (initially display all data).
        FilteredList<AideChauffeur> filteredData = new FilteredList<>(dataList, b -> true);
		
		// 2. Set the filter Predicate whenever the filter changes.
		recherche.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(F -> {
				// If filter text is empty, display all persons.
								
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				
				// Compare first name and last name of every person with filter text.
				String lowerCaseFilter = newValue.toLowerCase();
				
				if (F.getNom().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
					return true; // Filter matches first name.
				} 
                                else if (String.valueOf(F.getCin()).indexOf(lowerCaseFilter)!=-1)
                                {
                                    return true;
                                }
				else if (F.getPrenom().toLowerCase().indexOf(lowerCaseFilter) != -1 )
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
		SortedList<AideChauffeur> sortedData = new SortedList<>(filteredData);
		
		// 4. Bind the SortedList comparator to the TableView comparator.
		// 	  Otherwise, sorting the TableView would have no effect.
		sortedData.comparatorProperty().bind(table.comparatorProperty());
		
		// 5. Add sorted (and filtered) data to the table.
		table.setItems(sortedData);
      
    }    

    @FXML
    private void MdfAideChauffeur(MouseEvent event) throws IOException {
        ModifierAideChauffeurController.chsel=table.getSelectionModel().getSelectedItem();
          Parent root = FXMLLoader.load(getClass().getResource("/com/gentrepot/views/ModifierAideChauffeur.fxml"));
                Scene scene = new Scene(root);
                Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
                window.setScene(scene);
                window.show();
    }
    
   

    
}
