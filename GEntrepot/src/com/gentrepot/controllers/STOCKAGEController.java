/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gentrepot.controllers;

import com.gentrepot.models.CategorieAchat;
import com.gentrepot.models.CommandeApp;
import com.gentrepot.models.CommandeDApprovisionnement;
import com.gentrepot.models.Emplace;
import com.gentrepot.models.Emplacement;
import com.gentrepot.models.Entrepot;
import com.gentrepot.models.Fournisseur;
import com.gentrepot.models.Invent;
import com.gentrepot.models.InventaireStock;
import com.gentrepot.models.LigneCommandeDApprovisionnement;
import com.gentrepot.models.LignePerte;
import com.gentrepot.models.Panier;
import com.gentrepot.models.PanierLp;
import com.gentrepot.models.Pert;
import com.gentrepot.models.Perte;
import com.gentrepot.models.ProduitAchat;
import com.gentrepot.models.SousCategorieAchat;
import com.gentrepot.services.ServiceCommandeDApprovisionnment;
import com.gentrepot.services.ServiceEmplacement;
import com.gentrepot.services.ServiceEntrepot;
import com.gentrepot.services.ServiceFournisseur;
import com.gentrepot.services.ServiceInventaireStock;
import com.gentrepot.services.ServiceLigneCommandeDApprovisionnement;
import com.gentrepot.services.ServiceLignePerte;
import com.gentrepot.services.ServicePerte;
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
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;
import javafx.util.converter.IntegerStringConverter;

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
    TableColumn<CommandeApp, String> dateCmdcol = new TableColumn<CommandeApp, String>("Date");
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
    private ChoiceBox<String> produitPerdu;
    @FXML
    private TextField qtePerdu;    
    @FXML
    private ChoiceBox<String> raisonPerte;
    @FXML
    private Button ajouterPerte;
    @FXML
    private TableView tabPert;
    TableColumn produitcolLp = new TableColumn("Produit");
    TableColumn qtecolLp = new TableColumn("Qté");
    TableColumn raisoncolLp = new TableColumn("Raison de la perte");
    TableColumn actionLp = new TableColumn("Supprimer");
    final ArrayList<PanierLp> lperteDonne = new ArrayList<>();
    final ObservableList<PanierLp> lperteData = FXCollections.observableArrayList();  
    @FXML
    private TableView tabPerte;
    TableColumn numeroPertecol = new TableColumn("Numéro");
    TableColumn<Pert, String> datePertecol = new TableColumn<Pert, String>("Date");
    TableColumn action1Pert = new TableColumn("Modification");
    TableColumn action2Pert = new TableColumn("Suppression");
    final ArrayList<Pert> perteDonne = new ArrayList<>();
    final ObservableList<Pert> perteData = FXCollections.observableArrayList();
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
    private Button EnregisIv;
    @FXML
    private Button annulerIv;
    @FXML
    private Button home;
    @FXML
    private TextField adrEmpl;
    @FXML
    private TextField clasEmpl;
    @FXML
    private TextField capStoEmpl;
    @FXML
    private TextField qteEmpl;
    @FXML
    private TableView tabEmp;
    TableColumn numeroEmp = new TableColumn("Numéro");
    TableColumn<Emplace, String> adresseEmp = new TableColumn<Emplace, String>("Adresse");
    TableColumn<Emplace, Integer> capaciteStockageEmp = new TableColumn<Emplace, Integer>("Capacité de stockage");
    TableColumn<Emplace, Integer> qteStockerEmp = new TableColumn<Emplace, Integer>("Qté Stockée");
    TableColumn<Emplace, String> classe = new TableColumn<Emplace, String>("Classe");
    TableColumn action1Emp = new TableColumn("Modifier");
    TableColumn action2Emp = new TableColumn("Supprimer");
    final ArrayList<Emplace> emplaceDonne = new ArrayList<>();
    final ObservableList<Emplace> emplaceData = FXCollections.observableArrayList();
    @FXML
    private DatePicker dateIv;
    @FXML
    private ChoiceBox<String> produitIv;
    @FXML
    private ChoiceBox<String> emplaceIv;
    @FXML
    private TextField qteIv;
    @FXML
    private TableView tabIv;
    TableColumn numerocolIv = new TableColumn("Numéro");
    TableColumn<Invent, String> produitcolIv = new TableColumn<Invent, String>("Produit");
    TableColumn<Invent, String> emplacecolIv = new TableColumn<Invent, String>("Emplacement");
    TableColumn<Invent, String> datecolIv = new TableColumn<Invent, String>("Date");
    TableColumn<Invent, Integer> qtecolIv = new TableColumn<Invent, Integer>("Qté Inventée");
    TableColumn<Invent, Integer> ecartcolIv = new TableColumn<Invent, Integer>("Ecart");
    TableColumn<Invent, Integer> qtethcolIv = new TableColumn<Invent, Integer>("Qté théorique");    
    TableColumn action1Iv = new TableColumn("Modifier");
    TableColumn action2Iv = new TableColumn("Supprimer");
    final ArrayList<Invent> inventDonne = new ArrayList<>();
    final ObservableList<Invent> inventData = FXCollections.observableArrayList(); 
    
    

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
        tabCommande.setEditable(true);               
        numeroCmdcol.setCellValueFactory(new PropertyValueFactory<CommandeApp, Integer>("numero"));
        dateCmdcol.setCellValueFactory(new PropertyValueFactory("date"));        
        fournisseurCmdcol.setCellValueFactory(new PropertyValueFactory<CommandeApp, Integer>("fournisseur"));
        remiseCmdcol.setCellValueFactory(new PropertyValueFactory<CommandeApp, Double>("remise"));
        pthtCmdcol.setCellValueFactory(new PropertyValueFactory<CommandeApp, Double>("ptht"));
        pttcCmdcol.setCellValueFactory(new PropertyValueFactory<CommandeApp, Double>("pttc"));
        etatCmdcol.setCellValueFactory(new PropertyValueFactory<CommandeApp, String>("etat"));
        tabCommande.setItems(CmdData);
        addButtonUpdateToTableCommande();
        addButtonDeleteToTableCommande();
        //remiseCmdcol.setCellFactory(TextFieldTableCell.<CommandeApp> forTableColumn());
        //remiseCmdcol.setMinWidth(200);
        
        dateCmdcol.setCellFactory(TextFieldTableCell.<CommandeApp> forTableColumn());
        dateCmdcol.setMinWidth(50);
        dateCmdcol.setOnEditCommit((CellEditEvent<CommandeApp, String> event) -> {
            TablePosition<CommandeApp, String> pos = event.getTablePosition();
 
            String newdateCmdcol = event.getNewValue();
 
            int row = pos.getRow();
            CommandeApp ca = event.getTableView().getItems().get(row);           
            ca.setDate(newdateCmdcol);
            CmdData.set(row, ca);
        });
          
    }
    public void actualiserTablePerte(){
        ServicePerte sca = new ServicePerte();
        List<Pert> lc = new ArrayList<>();
        for(int i = 0 ; i < sca.afficher().size(); i++){
            Pert c = new Pert(sca.afficher().get(i).getId(),sca.afficher().get(i).getDate());
            lc.add(c);
        }
        for(int i = 0 ; i < lc.size(); i++){
            perteData.add(lc.get(i));
        }
        tabPerte.setEditable(true);               
        numeroPertecol.setCellValueFactory(new PropertyValueFactory<Pert, Integer>("id"));
        datePertecol.setCellValueFactory(new PropertyValueFactory<Pert, String>("date"));        
        
        tabPerte.setItems(perteData);
        addButtonUpdateToTablePerte();
        addButtonDeleteToTablePerte();
     
        datePertecol.setCellFactory(TextFieldTableCell.<Pert> forTableColumn());
        datePertecol.setMinWidth(50);
        datePertecol.setOnEditCommit((CellEditEvent<Pert, String> event) -> {
            TablePosition<Pert, String> pos = event.getTablePosition();
 
            String newdatePertcol = event.getNewValue();
 
            int row = pos.getRow();
            Pert ca = event.getTableView().getItems().get(row);           
            ca.setDate(newdatePertcol);
            perteData.set(row, ca);
        });
          
    }
    public void actualiserTableEmp(){
        ServiceEmplacement sca = new ServiceEmplacement();
        List<Emplace> lc = new ArrayList<>();
        for(int i = 0 ; i < sca.afficher().size(); i++){
            Emplace c = new Emplace(sca.afficher().get(i).getId(),sca.afficher().get(i).getAdresse(),sca.afficher().get(i).getCapaciteStockage(),sca.afficher().get(i).getQuantiteStocker(),sca.afficher().get(i).getClasse());
            System.out.print(c.getId());
            System.out.print(c.getAdresse());
            System.out.print(c.getCapaciteStockage());
            System.out.print(c.getQuantiteStocker());
            System.out.print(c.getClasse());
            lc.add(c);
        }
        for(int i = 0 ; i < lc.size(); i++){
            emplaceData.add(lc.get(i));
        }
        tabEmp.setEditable(true);               
        numeroEmp.setCellValueFactory(new PropertyValueFactory<Emplace, Integer>("id"));       
        adresseEmp.setCellValueFactory(new PropertyValueFactory<Emplace, String>("adresse"));
        capaciteStockageEmp.setCellValueFactory(new PropertyValueFactory<Emplace, Integer>("capaciteStockage"));
        qteStockerEmp.setCellValueFactory(new PropertyValueFactory<Emplace, Integer>("quantiteStocker"));
        classe.setCellValueFactory(new PropertyValueFactory<Emplace, String>("classe"));
        tabEmp.setItems(emplaceData);
        addButtonUpdateToTableEmp();
        addButtonDeleteToTableEmp();
        qteStockerEmp.setCellFactory(TextFieldTableCell.<Emplace, Integer>forTableColumn(new IntegerStringConverter()));
        qteStockerEmp.setMinWidth(50);
        qteStockerEmp.setOnEditCommit((CellEditEvent<Emplace, Integer> event) -> {
            TablePosition<Emplace, Integer> pos = event.getTablePosition();
 
            Integer newadr = event.getNewValue();
 
            int row = pos.getRow();
            Emplace ca = event.getTableView().getItems().get(row);           
            ca.setQuantiteStocker(newadr);
            emplaceData.set(row, ca);
        });
        capaciteStockageEmp.setCellFactory(TextFieldTableCell.<Emplace, Integer>forTableColumn(new IntegerStringConverter()));
        capaciteStockageEmp.setMinWidth(50);
        capaciteStockageEmp.setOnEditCommit((CellEditEvent<Emplace, Integer> event) -> {
            TablePosition<Emplace, Integer> pos = event.getTablePosition();
 
            Integer newadr = event.getNewValue();
 
            int row = pos.getRow();
            Emplace ca = event.getTableView().getItems().get(row);           
            ca.setCapaciteStockage(newadr);
            emplaceData.set(row, ca);
        });
        adresseEmp.setCellFactory(TextFieldTableCell.<Emplace> forTableColumn());
        adresseEmp.setMinWidth(50);
        adresseEmp.setOnEditCommit((CellEditEvent<Emplace, String> event) -> {
            TablePosition<Emplace, String> pos = event.getTablePosition();
 
            String newadr = event.getNewValue();
 
            int row = pos.getRow();
            Emplace ca = event.getTableView().getItems().get(row);           
            ca.setAdresse(newadr);
            emplaceData.set(row, ca);
        });
        classe.setCellFactory(TextFieldTableCell.<Emplace> forTableColumn());
        classe.setMinWidth(50);
        classe.setOnEditCommit((CellEditEvent<Emplace, String> event) -> {
            TablePosition<Emplace, String> pos = event.getTablePosition();
 
            String newadr = event.getNewValue();
 
            int row = pos.getRow();
            Emplace ca = event.getTableView().getItems().get(row);           
            ca.setClasse(newadr);
            emplaceData.set(row, ca);
        });
          
    }
    public void actualiserTableIv(){
        ServiceInventaireStock sca = new ServiceInventaireStock();
        List<Invent> lc = new ArrayList<>();
        for(int i = 0 ; i < sca.afficher().size(); i++){
            Invent c = new Invent(sca.afficher().get(i).getId(),sca.afficher().get(i).getProduitAchat().getLibelle(),sca.afficher().get(i).getEmplacement().getAdresse(),sca.afficher().get(i).getDateCreation(),sca.afficher().get(i).getQunatiteInventiare(),sca.afficher().get(i).getEcart(),sca.afficher().get(i).getQuantiteTheorique());
            System.out.print(c.getId());            
            lc.add(c);
        }
        for(int i = 0 ; i < lc.size(); i++){
            inventData.add(lc.get(i));
        }
       
        tabIv.setEditable(true);               
        numerocolIv.setCellValueFactory(new PropertyValueFactory<Invent, Integer>("id"));       
        produitcolIv.setCellValueFactory(new PropertyValueFactory<Invent, String>("produitAchat"));
        emplacecolIv.setCellValueFactory(new PropertyValueFactory("emplacement"));
        datecolIv.setCellValueFactory(new PropertyValueFactory<Invent, String>("dateCreation"));
        qtecolIv.setCellValueFactory(new PropertyValueFactory<Invent, Integer>("qunatiteInventiare"));
        ecartcolIv.setCellValueFactory(new PropertyValueFactory<Invent, Integer>("ecart"));
        qtethcolIv.setCellValueFactory(new PropertyValueFactory<Invent, Integer>("quantiteTheorique"));
        tabIv.setItems(inventData);
        addButtonUpdateToTableIv();
        addButtonDeleteToTableIv();
        
        qtecolIv.setCellFactory(TextFieldTableCell.<Invent, Integer>forTableColumn(new IntegerStringConverter()));
        qtecolIv.setMinWidth(50);
        qtecolIv.setOnEditCommit((CellEditEvent<Invent, Integer> event) -> {
            TablePosition<Invent, Integer> pos = event.getTablePosition();
 
            Integer newadr = event.getNewValue();
 
            int row = pos.getRow();
            Invent ca = event.getTableView().getItems().get(row);           
            ca.setQunatiteInventiare(newadr);
            inventData.set(row, ca);
        });
        
        datecolIv.setCellFactory(TextFieldTableCell.<Invent> forTableColumn());
        datecolIv.setMinWidth(50);
        datecolIv.setOnEditCommit((CellEditEvent<Invent, String> event) -> {
            TablePosition<Invent, String> pos = event.getTablePosition();
 
            String newadr = event.getNewValue();
 
            int row = pos.getRow();
            Invent ca = event.getTableView().getItems().get(row);           
            ca.setDateCreation(newadr);
            inventData.set(row, ca);
        });
          
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
        dateCreationCmd.setValue(null);
        //fournisseur.getSelectionModel().clearSelection();
        //produit.getSelectionModel().clearSelection();
        tauxRemise.setText("");
        qteCmd.setText("");
        prixUnitaire.setText("");
        tva.setText("");
        panier.getItems().clear();
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
    private void addButtonDeleteToTableEmp() {
        ServiceEmplacement sca = new ServiceEmplacement();
        Callback<TableColumn<Emplace, Void>, TableCell<Emplace, Void>> cellFactory = new Callback<TableColumn<Emplace, Void>, TableCell<Emplace, Void>>() {
            @Override
            public TableCell<Emplace, Void> call(final TableColumn<Emplace, Void> param) {
                final TableCell<Emplace, Void> cell = new TableCell<Emplace, Void>() {

                    private final Button btn = new Button("Supprimer");

                    {
                        btn.setOnAction((ActionEvent event) -> {
                            getTableView().getItems().remove(getTableView().getSelectionModel().getSelectedItem());
                            sca.supprimer(sca.rechercher(sca.afficher(), getTableView().getSelectionModel().getSelectedItem().getId()));
                            
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

        action2Emp.setCellFactory(cellFactory);

    }
    private void addButtonUpdateToTableEmp() {
        ServiceEmplacement sca = new ServiceEmplacement();
        
        Callback<TableColumn<Emplace, Void>, TableCell<Emplace, Void>> cellFactory = new Callback<TableColumn<Emplace, Void>, TableCell<Emplace, Void>>() {
            @Override
            public TableCell<Emplace, Void> call(final TableColumn<Emplace, Void> param) {
                final TableCell<Emplace, Void> cell = new TableCell<Emplace, Void>() {

                    private final Button btn = new Button("Modifier");

                    {
                        btn.setOnAction(new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent event) {
                                Emplacement c = sca.rechercher(sca.afficher(), getTableView().getSelectionModel().getSelectedItem().getId());
                                c.setAdresse(getTableView().getSelectionModel().getSelectedItem().getAdresse());
                                c.setCapaciteStockage(getTableView().getSelectionModel().getSelectedItem().getCapaciteStockage());
                                c.setQuantiteStocker(getTableView().getSelectionModel().getSelectedItem().getQuantiteStocker());
                                c.setClasse(getTableView().getSelectionModel().getSelectedItem().getAdresse());
                                sca.modifier(c);
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

        action1Emp.setCellFactory(cellFactory);

    }
    private void addButtonDeleteToTableIv() {
        ServiceInventaireStock sca = new ServiceInventaireStock();
        Callback<TableColumn<Invent, Void>, TableCell<Invent, Void>> cellFactory = new Callback<TableColumn<Invent, Void>, TableCell<Invent, Void>>() {
            @Override
            public TableCell<Invent, Void> call(final TableColumn<Invent, Void> param) {
                final TableCell<Invent, Void> cell = new TableCell<Invent, Void>() {

                    private final Button btn = new Button("Supprimer");

                    {
                        btn.setOnAction((ActionEvent event) -> {
                            getTableView().getItems().remove(getTableView().getSelectionModel().getSelectedItem());
                            sca.supprimer(sca.rechercher(sca.afficher(), getTableView().getSelectionModel().getSelectedItem().getId()));
                            
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

        action2Iv.setCellFactory(cellFactory);

    }
    private void addButtonUpdateToTableIv() {
        ServiceInventaireStock sca = new ServiceInventaireStock();
        
        Callback<TableColumn<Invent, Void>, TableCell<Invent, Void>> cellFactory = new Callback<TableColumn<Invent, Void>, TableCell<Invent, Void>>() {
            @Override
            public TableCell<Invent, Void> call(final TableColumn<Invent, Void> param) {
                final TableCell<Invent, Void> cell = new TableCell<Invent, Void>() {

                    private final Button btn = new Button("Modifier");

                    {
                        btn.setOnAction(new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent event) {
                                InventaireStock c = sca.rechercher(sca.afficher(), getTableView().getSelectionModel().getSelectedItem().getId());
                                c.setDateCreation(getTableView().getSelectionModel().getSelectedItem().getDateCreation());
                                c.setQunatiteInventiare(getTableView().getSelectionModel().getSelectedItem().getQunatiteInventiare());
                    
                                sca.modifier(c);
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

        action1Iv.setCellFactory(cellFactory);

    }
    private void addButtonUpdateToTableCommande() {
        ServiceCommandeDApprovisionnment sca = new ServiceCommandeDApprovisionnment();
        
        Callback<TableColumn<CommandeApp, Void>, TableCell<CommandeApp, Void>> cellFactory = new Callback<TableColumn<CommandeApp, Void>, TableCell<CommandeApp, Void>>() {
            @Override
            public TableCell<CommandeApp, Void> call(final TableColumn<CommandeApp, Void> param) {
                final TableCell<CommandeApp, Void> cell = new TableCell<CommandeApp, Void>() {

                    private final Button btn = new Button("Modifier");

                    {
                        btn.setOnAction(new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent event) {
                                CommandeDApprovisionnement c = sca.rechercher(sca.afficher(), getTableView().getSelectionModel().getSelectedItem().getNumero());
                                c.setDateCreation(getTableView().getSelectionModel().getSelectedItem().getDate());         
                                sca.modifier(c);
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

        action1Cmd.setCellFactory(cellFactory);

    }
    private void addButtonDeleteToTablePanierLp() {
        
        Callback<TableColumn<PanierLp, Void>, TableCell<PanierLp, Void>> cellFactory = new Callback<TableColumn<PanierLp, Void>, TableCell<PanierLp, Void>>() {
            @Override
            public TableCell<PanierLp, Void> call(final TableColumn<PanierLp, Void> param) {
                final TableCell<PanierLp, Void> cell = new TableCell<PanierLp, Void>() {

                    private final Button btn = new Button("Supprimer");

                    {
                        btn.setOnAction((ActionEvent event) -> {
                            getTableView().getItems().remove(getTableView().getSelectionModel().getSelectedItem());
                            lperteDonne.remove(getTableView().getSelectionModel().getSelectedItem());
                            
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

        actionLp.setCellFactory(cellFactory);

    }

    private void addButtonUpdateToTablePerte() {
        ServicePerte sca = new ServicePerte();
        
        Callback<TableColumn<Pert, Void>, TableCell<Pert, Void>> cellFactory = new Callback<TableColumn<Pert, Void>, TableCell<Pert, Void>>() {
            @Override
            public TableCell<Pert, Void> call(final TableColumn<Pert, Void> param) {
                final TableCell<Pert, Void> cell = new TableCell<Pert, Void>() {

                    private final Button btn = new Button("Modifier");

                    {
                        btn.setOnAction(new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent event) {
                                Perte c = sca.rechercher(sca.afficher(), getTableView().getSelectionModel().getSelectedItem().getId());
                                c.setDate(getTableView().getSelectionModel().getSelectedItem().getDate());         
                                sca.modifier(c);
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

        action1Pert.setCellFactory(cellFactory);

    }
     private void addButtonDeleteToTablePerte() {
        ServicePerte sca = new ServicePerte();
        Callback<TableColumn<Pert, Void>, TableCell<Pert, Void>> cellFactory = new Callback<TableColumn<Pert, Void>, TableCell<Pert, Void>>() {
            @Override
            public TableCell<Pert, Void> call(final TableColumn<Pert, Void> param) {
                final TableCell<Pert, Void> cell = new TableCell<Pert, Void>() {

                    private final Button btn = new Button("Supprimer");

                    {
                        btn.setOnAction((ActionEvent event) -> {
                            getTableView().getItems().remove(getTableView().getSelectionModel().getSelectedItem());
                            sca.supprimer(sca.rechercher(sca.afficher(), getTableView().getSelectionModel().getSelectedItem().getId()));
                            
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

        action2Pert.setCellFactory(cellFactory);

    }
    @FXML
    private void validerCommande(ActionEvent event) {
        ServiceProduitAchat sp = new ServiceProduitAchat();
        ServiceLigneCommandeDApprovisionnement slc = new ServiceLigneCommandeDApprovisionnement();
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
        ca.setNumeroC(sca.lastCmd().getNumeroC());
        for(int i=0;i<panierDonne.size();i++){
            Double t = panierDonne.get(i).getPrix()*panierDonne.get(i).getQuantite(); //sp.rechercher(sp.afficher(), panierDonne.get(i).getProduit())
            LigneCommandeDApprovisionnement lc = new LigneCommandeDApprovisionnement(ca,new ProduitAchat("aa44","laptop",8,"A",5,5000,1.2,42,"fff","j",50,"h","i","ii","ih","f",new SousCategorieAchat(1,"jdj",new CategorieAchat(1,"jfh"))),panierDonne.get(i).getPrix(),panierDonne.get(i).getQuantite(),t,panierDonne.get(i).getTva());
            slc.ajouter(lc);
        }
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
        
        tabEmp.getColumns().addAll(numeroEmp,adresseEmp,capaciteStockageEmp,qteStockerEmp,classe,action1Emp,action2Emp);

        
        actualiserTableEmp();

    }

    @FXML
    private void gererEspace(ActionEvent event) {
    }

    @FXML
    private void effectuerInventaire(ActionEvent event) {
        ServiceEmplacement se = new ServiceEmplacement();
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
        tabIv.getColumns().addAll(numerocolIv,produitcolIv,emplacecolIv,datecolIv,qtecolIv,ecartcolIv,qtethcolIv,action1Iv,action2Iv);

        produitIv.getItems().add("aa44");
        produitIv.getItems().add("bb17");
        for(int i = 0; i<se.afficher().size();i++){
            emplaceIv.getItems().add(se.afficher().get(i).getAdresse());
        }
        
        actualiserTableIv();   
    
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
        tabPert.getColumns().addAll(produitcolLp,qtecolLp,raisoncolLp,actionLp);
        tabPerte.getColumns().addAll(numeroPertecol,datePertecol,action1Pert,action2Pert);
        //ServiceProduitAchat spa = new ServiceProduitAchat();
        //for(int i=0;i<spa.afficher().size();i++){
            //produit.getItems().add(spa.afficher().get(i).getReference());
            produitPerdu.getItems().add("aa44");
            produitPerdu.getItems().add("bb17");
        //}
            raisonPerte.getItems().add("Vol");
            raisonPerte.getItems().add("Détruit");
            raisonPerte.getItems().add("Inconnue");
       
        actualiserTablePerte();
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
        ServiceEntrepot se = new ServiceEntrepot();
        
        mf.setText(se.afficher().get(0).getMatriculeFiscale());
        adr.setText(se.afficher().get(0).getAdresse());
        rs.setText(se.afficher().get(0).getRaisonSociale());
        mail.setText(se.afficher().get(0).getAdresseMail());
        tel.setText(se.afficher().get(0).getNumeroTel());
           
    }

    @FXML
    private void sauvegarder(ActionEvent event) {
    }

    @FXML
    private void ajouterCorbeille(ActionEvent event) {
        PanierLp p = new PanierLp(produitPerdu.getValue(),Integer.valueOf(qtePerdu.getText()),raisonPerte.getValue());      
        lperteData.add(p); 
        lperteDonne.add(p);
        produitcolLp.setCellValueFactory(new PropertyValueFactory<PanierLp, String>("produit"));
        qtecolLp.setCellValueFactory(new PropertyValueFactory<PanierLp, Integer>("quantite"));
        raisoncolLp.setCellValueFactory(new PropertyValueFactory<PanierLp, String>("raisonPerte"));
        tabPert.setItems(lperteData);
        addButtonDeleteToTablePanierLp();
        
    }

    @FXML
    private void enregitrerPerte(ActionEvent event) {
        ServiceProduitAchat sp = new ServiceProduitAchat();
        ServiceLignePerte slc = new ServiceLignePerte();
        ServicePerte sca = new ServicePerte();

        Perte ca = new Perte(datePerte.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        sca.ajouter(ca);
        ca.setId(sca.lastPert().getId());
        for(int i=0;i<lperteDonne.size();i++){
            //sp.rechercher(sp.afficher(), panierDonne.get(i).getProduit())
            LignePerte lc = new LignePerte(ca,new ProduitAchat("aa44","laptop",8,"A",5,5000,1.2,42,"fff","j",50,"h","i","ii","ih","f",new SousCategorieAchat(1,"jdj",new CategorieAchat(1,"jfh"))),lperteDonne.get(i).getQuantite(),lperteDonne.get(i).getRaisonPerte());
            slc.ajouter(lc);
        }
        //sf.rechercher(sf.afficher(),fournisseur.getValue())
        perteData.clear();
        actualiserTablePerte();
       
    }

    @FXML
    private void annulerPerte(ActionEvent event) {
        datePerte.setValue(null);
        //produitPerdu.getSelectionModel().clearSelection();
        qtePerdu.setText("");
        //raisonPerte.getSelectionModel().clearSelection();
        tabPert.getItems().clear();
    }

    @FXML
    private void enregistrerEntrepot(ActionEvent event) {
        ServiceEntrepot se = new ServiceEntrepot();
        if(se.afficher().size() == 0){
            se.ajouter(new Entrepot(matrifis.getText(),adresseEnp.getText(),raisonSocialEnp.getText(),adresseMail.getText(),telEnp.getText()));         
        }
        if(se.afficher().size() == 1){
            se.modifier(new Entrepot(matrifis.getText(),adresseEnp.getText(),raisonSocialEnp.getText(),adresseMail.getText(),telEnp.getText()));           
            mf.setText(se.afficher().get(0).getMatriculeFiscale());
            adr.setText(se.afficher().get(0).getAdresse());
            rs.setText(se.afficher().get(0).getRaisonSociale());
            mail.setText(se.afficher().get(0).getAdresseMail());
            tel.setText(se.afficher().get(0).getNumeroTel());
        }
    }

    @FXML
    private void annulerEntrepot(ActionEvent event) {
        matrifis.setText("");
        adresseEnp.setText("");
        raisonSocialEnp.setText("");
        adresseMail.setText("");
        telEnp.setText("");
   
    }

    @FXML
    private void modifierEntrepot(ActionEvent event) {
        ServiceEntrepot se = new ServiceEntrepot();
        
        matrifis.setText(se.afficher().get(0).getMatriculeFiscale());
        adresseEnp.setText(se.afficher().get(0).getAdresse());
        raisonSocialEnp.setText(se.afficher().get(0).getRaisonSociale());
        adresseMail.setText(se.afficher().get(0).getAdresseMail());
        telEnp.setText(se.afficher().get(0).getNumeroTel());
        
    }

    @FXML
    private void supprimerEntrepot(ActionEvent event) {
        ServiceEntrepot se = new ServiceEntrepot();
        se.supprimer(new Entrepot(se.afficher().get(0).getMatriculeFiscale(),se.afficher().get(0).getAdresse(),se.afficher().get(0).getRaisonSociale(),se.afficher().get(0).getAdresseMail(),se.afficher().get(0).getNumeroTel()));
        mf.setText("");
        adr.setText("");
        rs.setText("");
        mail.setText("");
        tel.setText("");
    }

    @FXML
    private void enregistrerEmplacement(ActionEvent event) {
        ServiceEntrepot se = new ServiceEntrepot();
        ServiceEmplacement sp = new ServiceEmplacement();
        Emplacement ca = new Emplacement(adrEmpl.getText(),Integer.valueOf(capStoEmpl.getText()),Integer.valueOf(qteEmpl.getText()),clasEmpl.getText(),new Entrepot("A2D708","tot","tit","@","55454544"));        
        //ca.setEntrepot(se.afficher().get(0));
        sp.ajouter(ca);
        tabEmp.getItems().clear();
        actualiserTableEmp();
        
    }

    @FXML
    private void annulerEmplacement(ActionEvent event) {
        adrEmpl.clear();
        capStoEmpl.clear();
        qteEmpl.clear();
        clasEmpl.clear();
       
        
    }


    @FXML
    private void enregistrerInventaire(ActionEvent event) {
        ServiceInventaireStock se = new ServiceInventaireStock();
        ServiceEmplacement sp = new ServiceEmplacement();
        InventaireStock ca = new InventaireStock(new ProduitAchat(produitIv.getValue(),"laptop",8,"A",5,5000,1.2,42,"fff","j",50,"h","i","ii","ih","f",new SousCategorieAchat(1,"jdj",new CategorieAchat(1,"jfh"))),new Emplacement(emplaceIv.getValue(),9,3,"tt",new Entrepot("A2D708","tot","tit","@","55454544")),dateIv.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")),Integer.valueOf(qteIv.getText()),5,3);        
        se.ajouter(ca);
        tabEmp.getItems().clear();
        actualiserTableEmp();
    }

    @FXML
    private void AnnulerInventaire(ActionEvent event) {
        
        qteIv.clear();
        
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
