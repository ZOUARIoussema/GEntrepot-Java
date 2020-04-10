/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gentrepot.controllers;

import com.gentrepot.models.CommandeApp;
import com.gentrepot.models.CommandeDApprovisionnement;
import com.gentrepot.models.Fournisseur;
import com.gentrepot.models.LigneCommandeDApprovisionnement;
import com.gentrepot.models.Panier;
import com.gentrepot.models.ProduitAchat;
import com.gentrepot.services.ServiceCommandeDApprovisionnment;
import com.gentrepot.services.ServiceFournisseur;
import com.gentrepot.services.ServiceProduitAchat;
import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.StackPane;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author guiforodrigue
 */
public class STOCKAGEController implements Initializable {

    @FXML
    private MenuItem effectuerCommande;
    @FXML
    private MenuItem chercherCommande;
    @FXML
    private StackPane com;
    @FXML
    private DatePicker dateCreationCmd;
    @FXML
    private ChoiceBox<Integer> fournisseur;
    @FXML
    private TextField tauxRemise;
    @FXML
    private ChoiceBox<String> produit;
    
    @FXML
    private TextField qteCmd;
    @FXML
    private TextField tva;
    @FXML
    private Button ajouterP;
    @FXML
    private Button annulerC;
    @FXML
    private Button effectuerC;
    @FXML
    private TextField prixUnitaire;
    @FXML
    private TableView  panier;
    TableColumn produitcol = new TableColumn("Produit");
    TableColumn qtecol = new TableColumn("Qté");
    TableColumn prixcol = new TableColumn("Prix unitaire");
    TableColumn tvacol = new TableColumn("TVA");
    TableColumn action = new TableColumn("Supprimer");
    final ArrayList<Panier> panierDonne = new ArrayList<>();
    final ObservableList<Panier> PanierData = FXCollections.observableArrayList();   
    @FXML
    private TableView tabCommande;
    TableColumn numeroCmdcol = new TableColumn("Numéro");
    TableColumn dateCmdcol = new TableColumn("Date");
    TableColumn fournisseurCmdcol = new TableColumn("Fournisseur");
    TableColumn remiseCmdcol = new TableColumn("Taux Réduction");
    TableColumn pthtCmdcol = new TableColumn("Prix tht");
    TableColumn pttcCmdcol = new TableColumn("Prix ttc");
    TableColumn etatCmdcol = new TableColumn("Etat");
    TableColumn action1Cmd = new TableColumn("Modification");
    TableColumn action2Cmd = new TableColumn("Suppression");
    final ArrayList<CommandeApp> CmdDonne = new ArrayList<>();
    final ObservableList<CommandeApp> CmdData = FXCollections.observableArrayList();
    @FXML
    private MenuItem creerE;
    @FXML
    private MenuItem gererE;
    @FXML
    private MenuItem effectuerI;
    @FXML
    private MenuItem filtrerImprI;
    @FXML
    private MenuItem passerEnP;
    @FXML
    private MenuItem filImprP;
    @FXML
    private MenuItem entrepot;
    @FXML
    private MenuItem sauver;
    @FXML
    private DatePicker datePerte;
    @FXML
    private ChoiceBox<?> produitPerdu;
    @FXML
    private TextField qtePerdu;
    @FXML
    private TextField raisonPerte;
    @FXML
    private Button ajouterPerte;
    @FXML
    private TableView<?> tabPerte;
    @FXML
    private Button enregistrerP;
    @FXML
    private Button annulerP;
    @FXML
    private TextField matrifis;
    @FXML
    private TextField adresseEnp;
    @FXML
    private TextField raisonSocialEnp;
    @FXML
    private TextField adresseMail;
    @FXML
    private TextField telEnp;
    @FXML
    private Label mf;
    @FXML
    private Label adr;
    @FXML
    private Label rs;
    @FXML
    private Label mail;
    @FXML
    private Label tel;
    @FXML
    private Button enregEnp;
    @FXML
    private Button annulerEnp;
    @FXML
    private Button modilEnp;
    @FXML
    private Button suppriEnp;
    @FXML
    private Button enregisEmp;
    @FXML
    private Button annulerEmp;
    @FXML
    private Button modifEmp;
    @FXML
    private Button EnregisIv;
    @FXML
    private Button annulerIv;
    @FXML
    private Button modifierIv;
    @FXML
    private Button home;
    
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
    }    

    public void actualiserTableCmd(){
        ServiceCommandeDApprovisionnment sca = new ServiceCommandeDApprovisionnment();
        List<CommandeApp> lc = new ArrayList<>();
        for(int i = 0 ; i < sca.afficher().size(); i++){
            CommandeApp c = new CommandeApp(sca.afficher().get(i).getNumeroC(),sca.afficher().get(i).getDateCreation(),sca.afficher().get(i).getFournisseur().getId(),sca.afficher().get(i).getTauxRemise(),sca.afficher().get(i).getTotalC(),sca.afficher().get(i).getTotalTva(),sca.afficher().get(i).getEtat());
            lc.add(c);
        }
        for(int i = 0 ; i < lc.size(); i++){
            CmdData.add(lc.get(i));
        }
                       
        numeroCmdcol.setCellValueFactory(new PropertyValueFactory<CommandeApp, Integer>("numero"));
        dateCmdcol.setCellValueFactory(new PropertyValueFactory<CommandeApp, String>("date"));
        fournisseurCmdcol.setCellValueFactory(new PropertyValueFactory<CommandeApp, Integer>("fournisseur"));
        remiseCmdcol.setCellValueFactory(new PropertyValueFactory<CommandeApp, Double>("remise"));
        pthtCmdcol.setCellValueFactory(new PropertyValueFactory<CommandeApp, Double>("ptht"));
        pttcCmdcol.setCellValueFactory(new PropertyValueFactory<CommandeApp, Double>("pttc"));
        etatCmdcol.setCellValueFactory(new PropertyValueFactory<CommandeApp, String>("etat"));
        tabCommande.setItems(CmdData);
        addButtonUpdateToTableCommande();
        addButtonDeleteToTableCommande();   
          
    }
    @FXML
    private void effectuerCommande(ActionEvent event) {          
               
        ObservableList<Node> childs = com.getChildren();
        Node anchor0 = childs.get(0);
        Node anchor1 = childs.get(1);
        Node anchor2 = childs.get(2);
        Node anchor3 = childs.get(3);
        Node anchor4 = childs.get(4);
        Node anchor5 = childs.get(5);
            
           anchor0.setVisible(true);
           anchor1.setVisible(false);
           anchor2.setVisible(false);
           anchor3.setVisible(false);
           anchor4.setVisible(false);
           anchor5.setVisible(false);
           //topNode.toFront();
           //backNode.setVisible(false);
           //backNode.toBack();
        panier.getColumns().addAll(produitcol,qtecol,prixcol,tvacol,action);
        tabCommande.getColumns().addAll(numeroCmdcol,dateCmdcol,fournisseurCmdcol,remiseCmdcol,pthtCmdcol,pttcCmdcol,etatCmdcol,action1Cmd,action2Cmd);
        //ServiceProduitAchat spa = new ServiceProduitAchat();
        //for(int i=0;i<spa.afficher().size();i++){
            //produit.getItems().add(spa.afficher().get(i).getReference());
            produit.getItems().add("aa44");
            produit.getItems().add("bb17");
        //}
        /*ServiceFournisseur sf = new ServiceFournisseur();
        for(int i=0;i<sf.afficher().size();i++){
            fournisseur.getItems().add(sf.afficher().get(i).getAdresseMail());    
        }*/
        fournisseur.getItems().addAll(1,2);
        
        actualiserTableCmd();
        /*ServiceCommandeDApprovisionnment sca = new ServiceCommandeDApprovisionnment();
        List<CommandeApp> lc = new ArrayList<>();
        for(int i = 0 ; i < sca.afficher().size(); i++){
            CommandeApp c = new CommandeApp(sca.afficher().get(i).getNumeroC(),sca.afficher().get(i).getDateCreation(),sca.afficher().get(i).getFournisseur().getId(),sca.afficher().get(i).getTauxRemise(),sca.afficher().get(i).getTotalC(),sca.afficher().get(i).getTotalTva(),sca.afficher().get(i).getEtat());
            lc.add(c);
        }
        for(int i = 0 ; i < lc.size(); i++){
            CmdData.add(lc.get(i));
        }
                       
        numeroCmdcol.setCellValueFactory(new PropertyValueFactory<CommandeApp, Integer>("numero"));
        dateCmdcol.setCellValueFactory(new PropertyValueFactory<CommandeApp, String>("date"));
        fournisseurCmdcol.setCellValueFactory(new PropertyValueFactory<CommandeApp, Integer>("fournisseur"));
        remiseCmdcol.setCellValueFactory(new PropertyValueFactory<CommandeApp, Double>("remise"));
        pthtCmdcol.setCellValueFactory(new PropertyValueFactory<CommandeApp, Double>("ptht"));
        pttcCmdcol.setCellValueFactory(new PropertyValueFactory<CommandeApp, Double>("pttc"));
        etatCmdcol.setCellValueFactory(new PropertyValueFactory<CommandeApp, String>("etat"));
        tabCommande.setItems(CmdData);
        addButtonUpdateToTableCommande();
        addButtonDeleteToTableCommande();  */ 
          
              
    }
    
    @FXML
    private void chercherCommande(ActionEvent event) {
        
                                       
    }

    @FXML
    private void ajouterPanier(ActionEvent event) {
        Panier p = new Panier(produit.getValue(),Integer.valueOf(qteCmd.getText()),Double.valueOf(prixUnitaire.getText()),Double.valueOf(tva.getText()));      
        PanierData.add(p); 
        panierDonne.add(p);
        produitcol.setCellValueFactory(new PropertyValueFactory<Panier, String>("produit"));
        qtecol.setCellValueFactory(new PropertyValueFactory<Panier, Integer>("quantite"));
        prixcol.setCellValueFactory(new PropertyValueFactory<Panier, Double>("prix"));
        tvacol.setCellValueFactory(new PropertyValueFactory<Panier, Double>("tva"));
        panier.setItems(PanierData);
        addButtonDeleteToTablePanier();
        
    }

    @FXML
    private void annulerCommande(ActionEvent event) {
    }
    
    private void addButtonDeleteToTablePanier() {
        
        Callback<TableColumn<Panier, Void>, TableCell<Panier, Void>> cellFactory = new Callback<TableColumn<Panier, Void>, TableCell<Panier, Void>>() {
            @Override
            public TableCell<Panier, Void> call(final TableColumn<Panier, Void> param) {
                final TableCell<Panier, Void> cell = new TableCell<Panier, Void>() {

                    private final Button btn = new Button("Supprimer");

                    {
                        btn.setOnAction((ActionEvent event) -> {
                            getTableView().getItems().remove(getTableView().getSelectionModel().getSelectedItem());
                            panierDonne.remove(getTableView().getSelectionModel().getSelectedItem());
                            
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

        action.setCellFactory(cellFactory);

    }
    private void addButtonDeleteToTableCommande() {
        ServiceCommandeDApprovisionnment sca = new ServiceCommandeDApprovisionnment();
        Callback<TableColumn<CommandeApp, Void>, TableCell<CommandeApp, Void>> cellFactory = new Callback<TableColumn<CommandeApp, Void>, TableCell<CommandeApp, Void>>() {
            @Override
            public TableCell<CommandeApp, Void> call(final TableColumn<CommandeApp, Void> param) {
                final TableCell<CommandeApp, Void> cell = new TableCell<CommandeApp, Void>() {

                    private final Button btn = new Button("Supprimer");

                    {
                        btn.setOnAction((ActionEvent event) -> {
                            getTableView().getItems().remove(getTableView().getSelectionModel().getSelectedItem());
                            sca.supprimer(sca.rechercher(sca.afficher(), getTableView().getSelectionModel().getSelectedItem().getNumero()));
                            
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

        action2Cmd.setCellFactory(cellFactory);

    }
    private void addButtonUpdateToTableCommande() {
        ServiceCommandeDApprovisionnment sca = new ServiceCommandeDApprovisionnment();
        Callback<TableColumn<CommandeApp, Void>, TableCell<CommandeApp, Void>> cellFactory = new Callback<TableColumn<CommandeApp, Void>, TableCell<CommandeApp, Void>>() {
            @Override
            public TableCell<CommandeApp, Void> call(final TableColumn<CommandeApp, Void> param) {
                final TableCell<CommandeApp, Void> cell = new TableCell<CommandeApp, Void>() {

                    private final Button btn = new Button("Modifier");

                    {
                        btn.setOnAction((ActionEvent event) -> {
                            getTableView().getItems().remove(getTableView().getSelectionModel().getSelectedItem());
                            sca.modifier(sca.rechercher(sca.afficher(), getTableView().getSelectionModel().getSelectedItem().getNumero()));                          
                            
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

        action1Cmd.setCellFactory(cellFactory);

    }

    @FXML
    private void validerCommande(ActionEvent event) {
        ServiceCommandeDApprovisionnment sca = new ServiceCommandeDApprovisionnment();
        ServiceFournisseur sf = new ServiceFournisseur();
        Double ptht = 0d;
        Double pttc = 0d;
        for(int i=0;i<panierDonne.size();i++){
            ptht = ptht + (panierDonne.get(i).getPrix()*panierDonne.get(i).getQuantite());
            pttc = pttc +((panierDonne.get(i).getPrix()*panierDonne.get(i).getQuantite()) + (panierDonne.get(i).getPrix()*panierDonne.get(i).getQuantite()) * (panierDonne.get(i).getTva() / 100));
        }
        CommandeDApprovisionnement ca = new CommandeDApprovisionnement(ptht,dateCreationCmd.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")),"non_facturée",Double.valueOf(tauxRemise.getText()),pttc,new Fournisseur(fournisseur.getValue(),"SA",56562222,"Alger","Apple@gmail.com","AG441",65));
        sca.ajouter(ca);
        //sf.rechercher(sf.afficher(),fournisseur.getValue())
        CmdData.clear();
        actualiserTableCmd();
        
    }

    @FXML
    private void creerEmplacement(ActionEvent event) {
        ObservableList<Node> childs = com.getChildren();
        Node anchor0 = childs.get(0);
        Node anchor1 = childs.get(1);
        Node anchor2 = childs.get(2);
        Node anchor3 = childs.get(3);
        Node anchor4 = childs.get(4);
        Node anchor5 = childs.get(5);
            
           anchor0.setVisible(false);
           anchor1.setVisible(false);
           anchor2.setVisible(false);
           anchor3.setVisible(true);
           anchor4.setVisible(false);
           anchor5.setVisible(false);
    }

    @FXML
    private void gererEspace(ActionEvent event) {
    }

    @FXML
    private void effectuerInventaire(ActionEvent event) {
        ObservableList<Node> childs = com.getChildren();
        Node anchor0 = childs.get(0);
        Node anchor1 = childs.get(1);
        Node anchor2 = childs.get(2);
        Node anchor3 = childs.get(3);
        Node anchor4 = childs.get(4);
        Node anchor5 = childs.get(5);
            
           anchor0.setVisible(false);
           anchor1.setVisible(false);
           anchor2.setVisible(false);
           anchor3.setVisible(false);
           anchor4.setVisible(true);
           anchor5.setVisible(false);
    }

    @FXML
    private void FiltrerImprimerInventaire(ActionEvent event) {
    }

    @FXML
    private void passerEnPerte(ActionEvent event) {
        ObservableList<Node> childs = com.getChildren();
        Node anchor0 = childs.get(0);
        Node anchor1 = childs.get(1);
        Node anchor2 = childs.get(2);
        Node anchor3 = childs.get(3);
        Node anchor4 = childs.get(4);
        Node anchor5 = childs.get(5);
            
           anchor0.setVisible(false);
           anchor1.setVisible(true);
           anchor2.setVisible(false);
           anchor3.setVisible(false);
           anchor4.setVisible(false);
           anchor5.setVisible(false);
    }

    @FXML
    private void filtrerEtImprimerPertes(ActionEvent event) {
    }

    @FXML
    private void informationEntrepot(ActionEvent event) {
        ObservableList<Node> childs = com.getChildren();
        Node anchor0 = childs.get(0);
        Node anchor1 = childs.get(1);
        Node anchor2 = childs.get(2);
        Node anchor3 = childs.get(3);
        Node anchor4 = childs.get(4);
        Node anchor5 = childs.get(5);
            
           anchor0.setVisible(false);
           anchor1.setVisible(false);
           anchor2.setVisible(true);
           anchor3.setVisible(false);
           anchor4.setVisible(false);
           anchor5.setVisible(false);
    }

    @FXML
    private void sauvegarder(ActionEvent event) {
    }

    @FXML
    private void ajouterCorbeille(ActionEvent event) {
    }

    @FXML
    private void enregitrerPerte(ActionEvent event) {
    }

    @FXML
    private void annulerPerte(ActionEvent event) {
    }

    @FXML
    private void enregistrerEntrepot(ActionEvent event) {
    }

    @FXML
    private void annulerEntrepot(ActionEvent event) {
    }

    @FXML
    private void modifierEntrepot(ActionEvent event) {
    }

    @FXML
    private void supprimerEntrepot(ActionEvent event) {
    }

    @FXML
    private void enregistrerEmplacement(ActionEvent event) {
    }

    @FXML
    private void annulerEmplacement(ActionEvent event) {
    }

    @FXML
    private void modifierEmplacement(ActionEvent event) {
    }

    @FXML
    private void enregistrerInventaire(ActionEvent event) {
    }

    @FXML
    private void AnnulerInventaire(ActionEvent event) {
    }

    @FXML
    private void modifierInventaire(ActionEvent event) {
    }

    @FXML
    private void Acceuil(ActionEvent event) {
        ObservableList<Node> childs = com.getChildren();
        Node anchor0 = childs.get(0);
        Node anchor1 = childs.get(1);
        Node anchor2 = childs.get(2);
        Node anchor3 = childs.get(3);
        Node anchor4 = childs.get(4);
        Node anchor5 = childs.get(5);
            
           anchor0.setVisible(false);
           anchor1.setVisible(false);
           anchor2.setVisible(false);
           anchor3.setVisible(false);
           anchor4.setVisible(false);
           anchor5.setVisible(true);
    }
    
}
