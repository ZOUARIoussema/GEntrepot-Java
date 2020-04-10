/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gentrepot.controllers;

import com.gentrepot.models.CommandeVente;
import com.gentrepot.models.LigneCommande;
import com.gentrepot.models.ProduitAchat;
import com.gentrepot.services.ServiceCommandeVente;
import com.gentrepot.services.ServiceProduitAchat;
import com.gentrepot.utils.DataSource;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
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
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class AjouterCommandeController implements Initializable {

    Connection cnx = DataSource.getInstance().getCnx();

    //instance commande global
   private  CommandeVente commandeVenteG = new CommandeVente();
    
    
    ServiceCommandeVente serviceCommandeVente = new ServiceCommandeVente();
    
    

    private ProduitAchat produitG = null;
    
    private LigneCommande ligneG=null;

        @FXML
    private TableColumn<ProduitAchat, String> col_ref;
    @FXML
    private TableColumn<ProduitAchat, String> col_lib;

    ObservableList<ProduitAchat> oblist = FXCollections.observableArrayList();
    ObservableList<LigneCommande> listeLigneCommande = FXCollections.observableArrayList();
    @FXML
    private TableView<ProduitAchat> tableViewProduit;
    @FXML
    private Button btnAjouterLigne;
    @FXML
    private TableView<LigneCommande> tableViewLigne;
    @FXML
    private TableColumn<LigneCommande, String> tableViewLigneProduit;
    @FXML
    private TableColumn<LigneCommande, Double> tableViewLignePrix;
    @FXML
    private TableColumn<LigneCommande, Integer> tableViewLigneQte;
    @FXML
    private TableColumn<LigneCommande, Double> tableViewLigneTVA;
    @FXML
    private TableColumn<LigneCommande, Double> tableViewLigneTotal;
    @FXML
    private TextField textfTotalCommande;
    @FXML
    private Button btnModifierLigne;
    @FXML
    private Button btnSupprimerLigne;
    @FXML
    private TextField textqteC;
    @FXML
    private TableColumn<ProduitAchat, Double> col_prix;
    @FXML
    private Button btnAC;
    @FXML
    private TableColumn<?, ?> col_prix1;
    private JFXTextField textFLibelle;
    @FXML
    private TextField filterField;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    commandeVenteG.setId(serviceCommandeVente.recupere()+1);

      /*  try {
            String requete
                    = "select * from produit_achat";
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet res = pst.executeQuery();

            while (res.next()) {
                
 
                oblist.add(new ProduitAchat(res.getString("reference"), res.getString("libelle"), res.getInt("quantiteEnStock"),res.getDouble("prixVente")));

            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }*/
      
      
      oblist.setAll(serviceCommandeVente.afficherProduit());

        col_ref.setCellValueFactory(new PropertyValueFactory<>("reference"));
        col_lib.setCellValueFactory(new PropertyValueFactory<>("libelle"));
        col_prix.setCellValueFactory(new PropertyValueFactory<>("prixVente"));

        
        

        tableViewLigneTotal.setCellValueFactory(new PropertyValueFactory<>("total"));

        tableViewLigneTVA.setCellValueFactory(new PropertyValueFactory<>("tva"));

        tableViewLigneQte.setCellValueFactory(new PropertyValueFactory<>("quantite"));

        tableViewLignePrix.setCellValueFactory(new PropertyValueFactory<>("prix"));

        tableViewLigneProduit.setCellValueFactory(new PropertyValueFactory<>("refp"));
        
        
         FilteredList<ProduitAchat> filteredData = new FilteredList<>(oblist, b -> true);
		
		// 2. Set the filter Predicate whenever the filter changes.
		filterField.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(produit -> {
				// If filter text is empty, display all persons.
								
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				
				// Compare first name and last name of every person with filter text.
				String lowerCaseFilter = newValue.toLowerCase();
				
				if (produit.getLibelle().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
					return true; // Filter matches first name.
				} 
				else if (String.valueOf(produit.getPrixVente()).indexOf(lowerCaseFilter)!=-1)
				     return true;
                                
                                
                                else if (String.valueOf(produit.getReference()).indexOf(lowerCaseFilter)!=-1)
				     return true;
				     else  
				    	 return false; // Does not match.
			});
		});
		
		// 3. Wrap the FilteredList in a SortedList. 
		SortedList<ProduitAchat> sortedData = new SortedList<>(filteredData);
		
		// 4. Bind the SortedList comparator to the TableView comparator.
		// 	  Otherwise, sorting the TableView would have no effect.
		sortedData.comparatorProperty().bind(tableViewProduit.comparatorProperty());
		
		// 5. Add sorted (and filtered) data to the table.
        
        
                tableViewProduit.setItems(sortedData);


        tableViewLigne.setItems(listeLigneCommande);
        
        
        
        

    }
    
    
    
    
    

    @FXML
    private void selectArticle(MouseEvent event) {

        produitG = tableViewProduit.getSelectionModel().getSelectedItem();
    }

    public void afficheLigne() {

        listeLigneCommande.setAll(commandeVenteG.getLigneCommande());
        
        
        textfTotalCommande.setText(String.valueOf(commandeVenteG.getTotalC()));

    }

    @FXML
    private void ajouterLigne(ActionEvent event) {

        if (produitG != null) {

            LigneCommande ligne = new LigneCommande(commandeVenteG, produitG, produitG.getPrixVente(), Integer.valueOf(textqteC.getText()), produitG.getPrixVente() * Integer.valueOf(textqteC.getText()), produitG.getTva());

             
            
           //verifier l'existance du produit dand le panier
            if(commandeVenteG.getLigneCommande().contains(ligne)){
            
            
            for(LigneCommande l :commandeVenteG.getLigneCommande()){
                
                
                if(l.getProduit().getReference().equals(ligne.getProduit().getReference())){
                    
                    
                    l.setQuantite(l.getQuantite()+ligne.getQuantite());
                    l.setTotal(l.getTotal()+ligne.getTotal());
                    
                }
                
                
            }
            }
            else
            {
                commandeVenteG.getLigneCommande().add(ligne);
            }
            
            
            
           
           
            
            
            commandeVenteG.setTotalC(commandeVenteG.getTotalC()+ligne.getTotal());
            
            afficheLigne();
        }

    }

    @FXML
    private void selectLigne(MouseEvent event) {
        
        
        ligneG=tableViewLigne.getSelectionModel().getSelectedItem();
        
        if(ligneG!=null){
            
            textqteC.setText(String.valueOf(ligneG.getQuantite()));
        
    }
        
    }

    private void validerCommande(ActionEvent event) {
        
     
        
        
        
        
        
    }

    @FXML
    private void modifierLigne(ActionEvent event) {
        
        
        
        
        int qte =Integer.valueOf(textqteC.getText());
        
        
        if(qte>0){
            
            
            
            
            for(LigneCommande l:commandeVenteG.getLigneCommande()){
                
                
                if(l.equals(ligneG)){
                    
                    l.setQuantite(qte);
                    l.setTotal(l.getProduit().getPrixVente()*l.getQuantite());
                    
                }
                
            }
            
            
            
        }
        
        
        updateTotal();
        
        
        afficheLigne();
        
        
        
    }
    
    
    
    public void updateTotal(){
        
        commandeVenteG.setTotalC(0);
        
        for(LigneCommande l:commandeVenteG.getLigneCommande()){
            
            commandeVenteG.setTotalC(commandeVenteG.getTotalC()+l.getTotal());
            
        }
        
        
    }
    

    @FXML
    private void supprimerLigne(ActionEvent event) {
        
        
        if(ligneG!=null){
            
            
            
            commandeVenteG.getLigneCommande().remove(ligneG);
            
            
            
            
            commandeVenteG.setTotalC(commandeVenteG.getTotalC()-ligneG.getTotal());
            
            ligneG=null;
            
            
            afficheLigne();
            
        }
        
    }

    @FXML
    private void validerAjouterCommande(ActionEvent event) {
        
        
        
        if(commandeVenteG!=null&&commandeVenteG.getLigneCommande().size()!=0){
        
        commandeVenteG.setDateC(new Date());
        commandeVenteG.setEtat("non livrer");
        
        
        //ajoyet user connecer
        
       // commandeVenteG.setUser(user);
        
        
          serviceCommandeVente.ajouterCommande(commandeVenteG);
          commandeVenteG=null;
          
        }
        
        
          Stage primaryStage = new Stage();
        
        try {

            AnchorPane root = (AnchorPane) FXMLLoader.load(getClass().getResource("/com/gentrepot/views/Merci.fxml"));
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);

            primaryStage.show();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
         
      
    }

    
    
    
   
    

}
