/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gentrepot.views;

import com.gentrepot.models.Vehicule;
import com.gentrepot.services.ServiceVehicule;
import java.io.IOException;
import java.net.URL;
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
public class AfficherVehicuelController implements Initializable {
    ServiceVehicule sc = new ServiceVehicule();
   // UpVehiculeController uv  = new UpVehiculeController();

    @FXML private TableView<Vehicule> table ;
    @FXML private TableColumn<Vehicule,String> matricule;
     @FXML private TableColumn<Vehicule,String> capacite ;
      @FXML private TableColumn<Vehicule,String> etat ;
       @FXML private TableColumn<Vehicule,String> type ;
    @FXML
    private TextField rech;
        
        

    /**
     * Initializes the controller class.
     */
    
    ObservableList<Vehicule> dataList = FXCollections.observableArrayList();
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        matricule.setCellValueFactory(new PropertyValueFactory<Vehicule,String>("matricule"));
        capacite.setCellValueFactory(new PropertyValueFactory<Vehicule,String>("capacite"));
        etat.setCellValueFactory(new PropertyValueFactory<Vehicule,String>("etat"));
        type.setCellValueFactory(new PropertyValueFactory<Vehicule,String>("type"));
         table.setItems(sc.afficher());
         dataList.addAll(sc.afficher());
          FilteredList<Vehicule> filteredData = new FilteredList<>(dataList, b -> true);
		
		// 2. Set the filter Predicate whenever the filter changes.
		rech.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(F -> {
				// If filter text is empty, display all persons.
								
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				
				// Compare first name and last name of every person with filter text.
				String lowerCaseFilter = newValue.toLowerCase();
				
				if (F.getType().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
					return true; // Filter matches first name.
				} 
                                else if (String.valueOf(F.getMatricule()).indexOf(lowerCaseFilter)!=-1)
                                {
                                    return true;
                                }
				 else if (String.valueOf(F.getCapacite()).indexOf(lowerCaseFilter)!=-1)
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
		SortedList<Vehicule> sortedData = new SortedList<>(filteredData);
		
		// 4. Bind the SortedList comparator to the TableView comparator.
		// 	  Otherwise, sorting the TableView would have no effect.
		sortedData.comparatorProperty().bind(table.comparatorProperty());
		
		// 5. Add sorted (and filtered) data to the table.
		table.setItems(sortedData);
      
    }    

    @FXML
    private void ModifierVehiculee(MouseEvent event) throws IOException {
        UpVehiculeController.chsel= table.getSelectionModel().getSelectedItem();
         Parent root = FXMLLoader.load(getClass().getResource("UpVehicule.fxml"));
                Scene scene = new Scene(root);
                Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
                window.setScene(scene);
                window.show();
    }
    
}
