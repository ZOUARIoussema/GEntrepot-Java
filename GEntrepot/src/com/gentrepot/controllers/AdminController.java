/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gentrepot.controllers;

import animatefx.animation.ZoomIn;
import com.gentrepot.models.BonLivraison;
import com.gentrepot.models.CommandeVente;
import com.gentrepot.models.FactureVente;
import com.gentrepot.services.ServiceBonLivraison;
import com.gentrepot.services.ServiceCommandeVente;
import com.gentrepot.services.ServiceFactureVente;
import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class AdminController implements Initializable {

    @FXML
    private JFXButton btnBoutique;
    @FXML
    private JFXButton btnCommande;
    @FXML
    private JFXButton btnBon;
    @FXML
    private JFXButton btnFacture;
    @FXML
    private Pane paneCommande;
    @FXML
    private Pane paneBoutique;
    @FXML
    private Pane paneFacture;
    @FXML
    private Pane paneBL;
    @FXML
    private Pane paneAceuille;
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
    //@FXML
    //private TableColumn<CommandeVente, String> col_user;

    /**
     * Initializes the controller class.
     */
    public static BonLivraison bon = null;

    ObservableList<CommandeVente> oblist = FXCollections.observableArrayList();

    ServiceCommandeVente serviceCommandeVente = new ServiceCommandeVente();

    ServiceBonLivraison serviceBonLivraison = new ServiceBonLivraison();
    ServiceFactureVente serviceFactureVente = new ServiceFactureVente();
    ObservableList<BonLivraison> bonliste = FXCollections.observableArrayList();
    ObservableList<FactureVente> facture = FXCollections.observableArrayList();

    //  @FXML
    // private TableColumn<?, ?> col_user;
    @FXML
    private TableColumn<BonLivraison, String> col_adress;
    @FXML
    private TableColumn<BonLivraison, String> col_etatBL;
    @FXML
    private TableColumn<BonLivraison, Date> col_dateBL;
    @FXML
    private TableColumn<BonLivraison, String> col_nom;
    @FXML
    private TableColumn<BonLivraison, String> col_prenom;
    @FXML
    private TableColumn<?, ?> col_user;
    @FXML
    private TableView<BonLivraison> tableviewBon;
    @FXML
    private TableView<FactureVente> tableViewFactureVente;
    @FXML
    private TableColumn<FactureVente, Date> col_dateF;
    @FXML
    private TableColumn<FactureVente, Date> col_dateE;
    @FXML
    private TableColumn<FactureVente, Double> col_totalF;
    @FXML
    private TableColumn<FactureVente, String> col_etatF;
    @FXML
    private TableColumn<FactureVente, Double> col_totalPa;
    @FXML
    private TableColumn<FactureVente, Double> col_rest;
    @FXML
    private TableColumn<FactureVente, Double> col_timbre;
    @FXML
    private TableColumn<FactureVente, Double> col_frais;

    public static BonLivraison blG = null;
    
    
   @FXML
    private Pane paneStatistique;
    @FXML
    private JFXButton btnStat;
    @FXML
    private BarChart<?, ?> ProduitChart;
    @FXML
    private NumberAxis y;
    @FXML
    private CategoryAxis x;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        /**
         * *
         *
         * ajouter button
         */
         TableColumn<BonLivraison, Void> colBtn = new TableColumn("Action");

        Callback<TableColumn<BonLivraison, Void>, TableCell<BonLivraison, Void>> cellFactory = new Callback<TableColumn<BonLivraison, Void>, TableCell<BonLivraison, Void>>() {
            @Override
            public TableCell<BonLivraison, Void> call(TableColumn<BonLivraison, Void> param) {

                final TableCell<BonLivraison, Void> cell = new TableCell<BonLivraison, Void>() {

                    private Button btn = new Button("Facturer");

                    {
                        btn.setOnAction((ActionEvent event) -> {

                            blG = tableviewBon.getSelectionModel().getSelectedItem();

                            Stage primaryStage = new Stage();

                            try {

                                AnchorPane root = (AnchorPane) FXMLLoader.load(getClass().getResource("/com/gentrepot/views/FactureVente.fxml"));
                                Scene scene = new Scene(root);
                                primaryStage.setScene(scene);

                                primaryStage.show();
                            } catch (Exception e) {
                                System.out.println(e.getMessage());
                            }

                        });
                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(btn);
                        }
                    }

                };

                return cell;
            }
        };

        colBtn.setCellFactory(cellFactory);

        tableviewBon.getColumns().add(colBtn);
 
    }

    @FXML
    private void BoutiqueAction(ActionEvent event) {

        Stage primaryStage = new Stage();

        try {

            AnchorPane root = (AnchorPane) FXMLLoader.load(getClass().getResource("/com/gentrepot/views/AjouterCommande.fxml"));
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);

            primaryStage.show();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    @FXML
    private void CommandesAction(ActionEvent event) {

        /*    Stage primaryStage = new Stage();
        
        try {

            AnchorPane root = (AnchorPane) FXMLLoader.load(getClass().getResource("/com/gentrepot/views/AjouterBL.fxml"));
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);

            primaryStage.show();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }*/
        oblist.addAll(serviceCommandeVente.afficherCommande());

        col_total.setCellValueFactory(new PropertyValueFactory<>("totalC"));
        col_date.setCellValueFactory(new PropertyValueFactory<>("dateC"));
        col_etat.setCellValueFactory(new PropertyValueFactory<>("etat"));
        col_taux.setCellValueFactory(new PropertyValueFactory<>("tauxRemise"));

        tableViewCommande.setItems(oblist);

        paneCommande.setVisible(true);

        new ZoomIn(paneCommande).play();

        paneCommande.toFront();

    }

    @FXML
    private void BonAction(ActionEvent event) {

        bonliste.setAll(serviceBonLivraison.afficherBon());

        col_adress.setCellValueFactory(new PropertyValueFactory<>("adresseLivraison"));
        col_etatBL.setCellValueFactory(new PropertyValueFactory<>("etat"));
        col_dateBL.setCellValueFactory(new PropertyValueFactory<>("dateCreation"));
        col_nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        col_prenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));

        tableviewBon.setItems(bonliste);

        paneBL.setVisible(true);
        paneBL.toFront();

    }

    @FXML
    private void FactureAction(ActionEvent event) {

        facture.setAll(serviceFactureVente.afficherFacture());

        col_dateF.setCellValueFactory(new PropertyValueFactory<>("dateCreation"));
        col_dateE.setCellValueFactory(new PropertyValueFactory<>("dateEchaillancePaiement"));
        col_totalF.setCellValueFactory(new PropertyValueFactory<>("totalTTC"));
        col_etatF.setCellValueFactory(new PropertyValueFactory<>("etat"));
        col_totalPa.setCellValueFactory(new PropertyValueFactory<>("totalPaye"));
        col_rest.setCellValueFactory(new PropertyValueFactory<>("restePaye"));
        col_timbre.setCellValueFactory(new PropertyValueFactory<>("timbreFiscale"));
        col_frais.setCellValueFactory(new PropertyValueFactory<>("fraisTransport"));

        tableViewFactureVente.setItems(facture);

        paneFacture.setVisible(true);
        paneFacture.toFront();

    }


    /*@FXML
    private void selectCommande(MouseEvent event) {
    }*/
    @FXML
    private void selectBon(MouseEvent event) {

        blG = tableviewBon.getSelectionModel().getSelectedItem();

    }

    @FXML
    private void StatAction(ActionEvent event) {
        
         XYChart.Series series1 = new XYChart.Series();
        
        
        for(CommandeVente c :serviceCommandeVente.stat()){
            
            
            
            
           
        series1.getData().add(new XYChart.Data(String.valueOf(c.getDateC()),c.getTotalC()));
        
          
            
        }
        
        
          ProduitChart.getData().setAll(series1);
        
        
        
        
      

        
        
       

        
        
           

        
         paneStatistique.setVisible(true);

        //new ZoomIn(paneStatistique).play();

        paneStatistique.toFront();
    }

}
