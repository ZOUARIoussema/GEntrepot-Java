/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gentrepot.controllers;

import animatefx.animation.ZoomIn;
import com.gentrepot.models.FactureVente;
import com.gentrepot.models.InventaireCaisse;
import com.gentrepot.models.LettreDeRelance;
import com.gentrepot.services.ServiceInventaireCaisse;
import com.gentrepot.services.ServiceLettreDeRelance;
import com.gentrepot.services.ServiceRecouvrementClientCheque;
import com.gentrepot.services.ServiceRecouvrementClientEspece;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author oussema
 */
public class MenueAgentCaisseController implements Initializable {

    ObservableList<InventaireCaisse> listInventaire = FXCollections.observableArrayList();
    ObservableList<LettreDeRelance> listeLettreDeRelance = FXCollections.observableArrayList();
    ServiceInventaireCaisse serviceInventaireCaisse = new ServiceInventaireCaisse();
    ServiceRecouvrementClientCheque serviceRecouvrementClientCheque = new ServiceRecouvrementClientCheque();
    ServiceRecouvrementClientEspece serviceRecouvrementClientEspece = new ServiceRecouvrementClientEspece();
    ServiceLettreDeRelance serviceLettreDeRelance = new ServiceLettreDeRelance();

    double totalT = (serviceRecouvrementClientCheque.totalCheque() + serviceRecouvrementClientEspece.totalEspece());

    private InventaireCaisse inventaireCaisseG = null;
    private LettreDeRelance lettreDeRelanceG = null;

    @FXML
    private Button btnFactureF;
    @FXML
    private Button btnReglemntF;
    @FXML
    private Button btnRecouvrementC;
    @FXML
    private Button btnLettreRelance;
    private Button btnInventaire;
    @FXML
    private Pane paneReglemntF;
    @FXML
    private Pane paneRecouvrementClient;
    private Pane paneInvetaireC;
    @FXML
    private Pane paneFactureF;
    @FXML
    private JFXButton btnAjouterInventaire;
    @FXML
    private TableView<InventaireCaisse> tableViewInventaire;
    @FXML
    private TableColumn<Date, Date> cDateCreation;
    @FXML
    private TableColumn<Double, Double> cSoldeCalculer;
    @FXML
    private TableColumn<Double, Double> cSoldeTheorique;
    @FXML
    private TableColumn<Double, Double> cSoldeCheque;
    @FXML
    private TableColumn<Double, Double> cSoldeEspece;
    @FXML
    private TableColumn<Double, Double> cEcart;
    @FXML
    private JFXButton btnModifierInventaire;
    @FXML
    private JFXButton btnSupprimerInventaire;
    @FXML
    private Pane paneModiferInventaire;
    @FXML
    private Pane paneAccueille;
    @FXML
    private Pane paneAjouterInventaire;
    @FXML
    private Label soldeTC;
    @FXML
    private Label soldeTE;
    @FXML
    private Label soldeCalculer;
    @FXML
    private Label ecart;
    @FXML
    private JFXTextField totalTheorique;
    @FXML
    private JFXTextField totalCheque;
    @FXML
    private JFXTextField totalEspece;
    @FXML
    private JFXTextField soldeTCalculer;
    @FXML
    private JFXTextField ecartC;
    @FXML
    private Button btnGInventaire;
    @FXML
    private Pane paneGInvetaireC;
    @FXML
    private JFXButton btnValiderAjouterInventaire;
    @FXML
    private JFXButton btnAnnulerAjouterInventairecaisse;
    @FXML
    private Label soldeTC1;
    @FXML
    private Label soldeTE1;
    @FXML
    private Label soldeCalculer1;
    @FXML
    private Label ecart1;
    @FXML
    private JFXTextField totalTheoriqueM;
    @FXML
    private JFXTextField totalChequeM;
    @FXML
    private JFXTextField totalEspeceM;
    @FXML
    private JFXTextField soldeTCalculerM;
    @FXML
    private JFXTextField ecartCM;
    @FXML
    private JFXButton btnEnregistrerModiferInventaire;
    @FXML
    private JFXButton btnAnnulerModiferInventaire;
    @FXML
    private Pane paneGLettreR;
    @FXML
    private TableView<LettreDeRelance> tableViewLettreDeRelance;
    @FXML
    private TableColumn<LettreDeRelance, Date> cLettreDateCreation;
    @FXML
    private TableColumn<LettreDeRelance, FactureVente> cLettreFactureClient;
    @FXML
    private JFXButton btnAjouterLettre;
    @FXML
    private JFXButton btnSupprimerLettre;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        /**
         * **
         *
         *
         * Inventaire caisse
         */
        chargerInventaire();

        tableViewInventaire.setItems(listInventaire);
        cDateCreation.setCellValueFactory(new PropertyValueFactory<>("dateCreation"));
        cSoldeCalculer.setCellValueFactory(new PropertyValueFactory<>("soldeCalculer"));
        cSoldeTheorique.setCellValueFactory(new PropertyValueFactory<>("soldeTheorique"));
        cSoldeCheque.setCellValueFactory(new PropertyValueFactory<>("soldeCheque"));
        cSoldeEspece.setCellValueFactory(new PropertyValueFactory<>("soldeEspece"));
        cEcart.setCellValueFactory(new PropertyValueFactory<>("ecart"));

        /**
         * **
         *
         * Lettre de relance
         *
         *
         */
        tableViewLettreDeRelance.setItems(listeLettreDeRelance);
        cLettreDateCreation.setCellValueFactory(new PropertyValueFactory<>("date"));
      cLettreFactureClient.setCellValueFactory(new PropertyValueFactory<>("factureVente"));

     
    }

    public void chargerInventaire() {

        listInventaire.setAll(serviceInventaireCaisse.afficher());

    }
    
    public void chargerLettreDeRelance(){
        
        listeLettreDeRelance.setAll(serviceLettreDeRelance.afficher());
        
    }

    @FXML
    private void afficheFacture(ActionEvent event) {

        if (event.getSource().equals(btnFactureF)) {

            paneFactureF.setVisible(true);
            new ZoomIn(paneFactureF).play();
            paneFactureF.toFront();

        }

    }

    @FXML
    private void afficheReglementF(ActionEvent event) {
    }

    @FXML
    private void afficheRecouvrementC(ActionEvent event) {
    }

    private void afficheInventaire(ActionEvent event) {

    }

    private void ajouterInventaire(ActionEvent event) {

        Stage primaryStage = new Stage();

        try {

            primaryStage.setTitle("Ajouter Inventaire Caisse");
            AnchorPane root = (AnchorPane) FXMLLoader.load(getClass().getResource("/com/gentrepot/views/AjouterInventaire.fxml"));
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);

            primaryStage.show();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        chargerInventaire();

    }

    @FXML
    private void supprimerInventaire(ActionEvent event) {

        serviceInventaireCaisse.supprimer(inventaireCaisseG);
        chargerInventaire();

    }

    @FXML
    private void selectionner(MouseEvent event) {

        inventaireCaisseG = tableViewInventaire.getSelectionModel().getSelectedItem();

    }

    @FXML
    private void calculerEcart(KeyEvent event) {

        ecartC.setText(String.valueOf(Double.valueOf(soldeTCalculer.getText()) - totalT));
    }

    @FXML
    private void afficherPaneAjouterInventaire(ActionEvent event) {

        totalTheorique.setText(String.valueOf(totalT));
        totalCheque.setText(String.valueOf(serviceRecouvrementClientCheque.totalCheque()));
        totalEspece.setText(String.valueOf(serviceRecouvrementClientEspece.totalEspece()));

        paneAjouterInventaire.setVisible(true);
        new ZoomIn(paneAjouterInventaire).play();
        paneAjouterInventaire.toFront();

    }

    public void chargerPaneGInventaire() {

        chargerInventaire();
        paneGInvetaireC.setVisible(true);
        new ZoomIn(paneGInvetaireC).play();
        paneGInvetaireC.toFront();

    }

    @FXML
    private void afficheGInventaire(ActionEvent event) {

        if (event.getSource().equals(btnGInventaire)) {

            chargerPaneGInventaire();

        }

    }

    public void clear() {

        totalTheorique.setText(String.valueOf((serviceRecouvrementClientCheque.totalCheque() + serviceRecouvrementClientEspece.totalEspece())));
        totalCheque.setText(String.valueOf(serviceRecouvrementClientCheque.totalCheque()));
        totalEspece.setText(String.valueOf(serviceRecouvrementClientEspece.totalEspece()));

        soldeTCalculer.setText("");

        ecartC.setText("");

    }

    @FXML
    private void ajouterInventaireCaisse(ActionEvent event) {

        InventaireCaisse inventaireCaisse = new InventaireCaisse(new Date(), Double.valueOf(soldeTCalculer.getText()), Double.valueOf(totalTheorique.getText()), Double.valueOf(totalCheque.getText()), Double.valueOf(totalEspece.getText()), Double.valueOf(ecartC.getText()));

        serviceInventaireCaisse.ajouter(inventaireCaisse);

        clear();

        chargerPaneGInventaire();
    }

    @FXML
    private void anuulerAjouterInventaireCaisse(ActionEvent event) {

        clear();
    }

    @FXML
    private void chargerPaneModifierInventaireCaisse(ActionEvent event) {

        if (inventaireCaisseG != null) {

            totalTheoriqueM.setText(String.valueOf(inventaireCaisseG.getSoldeTheorique()));
            totalChequeM.setText(String.valueOf(inventaireCaisseG.getSoldeCheque()));
            totalEspeceM.setText(String.valueOf(inventaireCaisseG.getSoldeEspece()));
            soldeTCalculerM.setText(String.valueOf(inventaireCaisseG.getSoldeCalculer()));
            ecartCM.setText(String.valueOf(inventaireCaisseG.getEcart()));

            paneModiferInventaire.setVisible(true);
            new ZoomIn(paneModiferInventaire).play();
            paneModiferInventaire.toFront();

        }
    }

    @FXML
    private void calculerEcartM(KeyEvent event) {

        ecartCM.setText(String.valueOf(Double.valueOf(soldeTCalculerM.getText()) - totalT));

    }

    @FXML
    private void EnregistrerModifierInventaire(ActionEvent event) {

        inventaireCaisseG.setEcart(Double.valueOf(ecartCM.getText()));
        inventaireCaisseG.setSoldeCalculer(Double.valueOf(soldeTCalculerM.getText()));
        serviceInventaireCaisse.modifier(inventaireCaisseG);
        chargerPaneGInventaire();
        inventaireCaisseG = null;

    }

    @FXML
    private void annulerModiferInventaire(ActionEvent event) {
    }

    @FXML
    private void afficherPaneAjouterLettre(ActionEvent event) {
    }

    @FXML
    private void supprimerLettre(ActionEvent event) {
    }

    @FXML
    private void afficherPanelGLettreDeRelance(ActionEvent event) {

        if (event.getSource().equals(btnLettreRelance)) {

            
            chargerLettreDeRelance();
            paneGLettreR.setVisible(true);
            new ZoomIn(paneGLettreR).play();
            paneGLettreR.toFront();

        }

    }

}
