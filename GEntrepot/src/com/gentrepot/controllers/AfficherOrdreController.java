/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gentrepot.controllers;


import com.gentrepot.models.AideChauffeur;
import com.gentrepot.models.Chauffeur;
import com.gentrepot.models.OrdreMission;
import com.gentrepot.models.Vehicule;
import com.gentrepot.services.ServiceBonLivraison;
import com.gentrepot.services.ServiceOrdreMission;
import com.gentrepot.utils.PDF;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.DocumentException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Date;
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
    private TableColumn<OrdreMission, Integer> vehicule;
    @FXML
    private TableColumn<OrdreMission, String> chauffeur;
    @FXML
    private TableColumn<OrdreMission, String> aidechauffeur;
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
    
    PDF pdf = new PDF();
    

    /**
     * Initializes the controller class.
     */
    
    
    ObservableList<OrdreMission> dataList = FXCollections.observableArrayList();
    @FXML
    private TableColumn<OrdreMission, Integer> id;
    private TableColumn<OrdreMission, String> cin;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        vehicule.setCellValueFactory(new PropertyValueFactory<OrdreMission,Integer>("id_vehicule"));
        id.setCellValueFactory(new PropertyValueFactory<OrdreMission,Integer>("id"));
            chauffeur.setCellValueFactory(new PropertyValueFactory<OrdreMission,String>("id_chauffeur"));
             //cin.setCellValueFactory(new PropertyValueFactory<OrdreMission,String>("cin"));
            aidechauffeur.setCellValueFactory(new PropertyValueFactory<OrdreMission,String>("id_aidechauff"));
             //cin.setCellValueFactory(new PropertyValueFactory<OrdreMission,String>("cin"));
           //  OrdreMission.(new PropertyValueFactory<OrdreMission,Date>("datecreation"));
             
        table.setItems(sb.afficher());
         dataList.addAll(sb.afficher());
         
         /* FilteredList<OrdreMission> filteredData = new FilteredList<>(dataList, b -> true);
		
		// 2. Set the filter Predicate whenever the filter changes.
		recherche.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(F -> {
				// If filter text is empty, display all persons.
								
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				
				// Compare first name and last name of every person with filter text.
				String lowerCaseFilter = newValue.toLowerCase();
				
				if (F.get.toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
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
		SortedList<OrdreMission> sortedData = new SortedList<>(filteredData);
		
		// 4. Bind the SortedList comparator to the TableView comparator.
		// 	  Otherwise, sorting the TableView would have no effect.
		sortedData.comparatorProperty().bind(table.comparatorProperty());
		
		// 5. Add sorted (and filtered) data to the table.
		table.setItems(sortedData);*/
      
       

    }    

    @FXML
    private void ModifierOrdred(MouseEvent event) throws IOException {
      //  ModifierOrdreController.chsel=table.getSelectionModel().getSelectedItem();
         Parent root = FXMLLoader.load(getClass().getResource(".fxml"));
                Scene scene = new Scene(root);
                Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
                window.setScene(scene);
                window.show();
    }

    @FXML
    private void impOrdre(MouseEvent event) throws DocumentException, BadElementException, IOException, FileNotFoundException, InterruptedException, SQLException {
        pdf.GeneratePdf("ordreMission", table.getSelectionModel().getSelectedItem());
        
    }
    
}
