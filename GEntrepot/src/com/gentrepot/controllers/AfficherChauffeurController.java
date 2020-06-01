/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gentrepot.controllers;


import com.gentrepot.models.Chauffeur;
import com.gentrepot.services.ServiceChauffeur;
import com.gentrepot.utils.DataSource;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
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
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Rym
 */
public class AfficherChauffeurController implements Initializable {
   ServiceChauffeur sc = new ServiceChauffeur();


    @FXML private TableView<Chauffeur> table ;
    @FXML private TableColumn<Chauffeur,String> cin ;
     @FXML private TableColumn<Chauffeur,String> prenom ;
      @FXML private TableColumn<Chauffeur,String> nom ;
       @FXML private TableColumn<Chauffeur,String> adresse ;
        @FXML private TableColumn<Chauffeur,String> etat ;
        @FXML private TableColumn<Chauffeur,Integer> voyage ;
    @FXML
    private TextField recherche;
        
       
          public void AfficherChauffeur() {
      

        /*try {
            String requete = "SELECT * FROM chauffeur";
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                data.add(new Chauffeur(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), 0, rs.getString(5)));
            }
            cnx.close();
        } catch (SQLException ex) {
         
        }*/
       
    }
          
          ObservableList<Chauffeur> dataList = FXCollections.observableArrayList();
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         cin.setCellValueFactory(new PropertyValueFactory<Chauffeur,String>("cin"));
        prenom.setCellValueFactory(new PropertyValueFactory<Chauffeur,String>("prenom"));
        nom.setCellValueFactory(new PropertyValueFactory<Chauffeur,String>("nom"));
        adresse.setCellValueFactory(new PropertyValueFactory<Chauffeur,String>("adresse"));
        etat.setCellValueFactory(new PropertyValueFactory<Chauffeur,String>("etat")); 
        voyage.setCellValueFactory(new PropertyValueFactory<Chauffeur,Integer>("voyage")); 
        table.setItems(sc.afficher());
            dataList.addAll(sc.afficher());
                 FilteredList<Chauffeur> filteredData = new FilteredList<>(dataList, b -> true);
		
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
		SortedList<Chauffeur> sortedData = new SortedList<>(filteredData);
		
		// 4. Bind the SortedList comparator to the TableView comparator.
		// 	  Otherwise, sorting the TableView would have no effect.
		sortedData.comparatorProperty().bind(table.comparatorProperty());
		
		// 5. Add sorted (and filtered) data to the table.
		table.setItems(sortedData);
      
       

    }    

    @FXML
    private void modifier(MouseEvent event) throws IOException {
        UpdateChauffeurController.chsel=table.getSelectionModel().getSelectedItem();
       // System.err.println(UpdateChauffeurController.chsel.getCin());    
        
                Parent root = FXMLLoader.load(getClass().getResource("UpdateChauffeur.fxml"));
                Scene scene = new Scene(root);
                Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
                window.setScene(scene);
                window.show();
              
        
    }
}
