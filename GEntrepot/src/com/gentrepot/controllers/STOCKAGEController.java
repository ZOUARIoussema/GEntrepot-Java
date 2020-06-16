/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gentrepot.controllers;

import com.gentrepot.models.BonE;
import com.gentrepot.models.CommandeApp;
import com.gentrepot.models.CommandeDApprovisionnement;
import com.gentrepot.models.Email;
import com.gentrepot.models.Emplace;
import com.gentrepot.models.Emplacement;
import com.gentrepot.models.Entrepot;
import com.gentrepot.models.Invent;
import com.gentrepot.models.InventaireStock;
import com.gentrepot.models.LigneCommandeDApprovisionnement;
import com.gentrepot.models.LignePerte;
import com.gentrepot.models.Panier;
import com.gentrepot.models.PanierLp;
import com.gentrepot.models.Pert;
import com.gentrepot.models.Perte;
import com.gentrepot.services.MailCmd;
import com.gentrepot.services.ServiceBonEntree;
import com.gentrepot.services.ServiceCommandeDApprovisionnment;
import com.gentrepot.services.ServiceEmplacement;
import com.gentrepot.services.ServiceEntrepot;
import com.gentrepot.services.ServiceFournisseur;
import com.gentrepot.services.ServiceInventaireStock;
import com.gentrepot.services.ServiceLigneCommandeDApprovisionnement;
import com.gentrepot.services.ServiceLignePerte;
import com.gentrepot.services.ServicePerte;
import com.gentrepot.services.ServiceProduitAchat;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.opencsv.CSVWriter;
import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import javafx.scene.paint.Color;
//import java.awt.Color;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.StackPane;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Side;
import javafx.scene.Node;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.Duration;
import javafx.util.converter.DoubleStringConverter;
import javafx.util.converter.IntegerStringConverter;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.ERROR_MESSAGE;
import static javax.swing.JOptionPane.showMessageDialog;
import javax.swing.JTextField;
//import javax.swing.text.Document;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

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
    private ChoiceBox<String> fournisseur;
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
    TableColumn<CommandeApp, Email> fournisseurCmdcol = new TableColumn("Fournisseur");
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
    @FXML
    private PieChart EmplaPieChart;
    @FXML
    private Label ChoixClassProduitPerte;
    @FXML
    private ChoiceBox<String> choixClassPerte;
    @FXML
    private Button VoirBarChart;
    @FXML
    private BarChart<String, Number> BarchartPerte;
    @FXML
    private HBox PieBarAncho;
    @FXML
    private Label ChoixAdressEmpla;
    @FXML
    private ChoiceBox<String> choixAdrEplace;
    @FXML
    private Button VoirPieChart;
    
    @FXML
    private AnchorPane anchorCom;
    @FXML
    private AnchorPane myAnchor;
    @FXML
    private HBox myHbox;
    @FXML
    private AnchorPane filtrerLesCommand;
    @FXML
    private DatePicker dateDebutCmd;
    @FXML
    private DatePicker dateFinCmd;
    @FXML
    private ChoiceBox<String> triParFour;
    @FXML
    private Button btnTriCmd;
    @FXML
    private TableView tabTriCmd; 
    TableColumn numeroCmdcolf = new TableColumn("Numéro");
    TableColumn<CommandeApp, String> dateCmdcolf = new TableColumn<CommandeApp, String>("Date");
    TableColumn fournisseurCmdcolf = new TableColumn("Fournisseur");
    TableColumn remiseCmdcolf = new TableColumn("Taux Réduction");
    TableColumn pthtCmdcolf = new TableColumn("Prix tht");
    TableColumn pttcCmdcolf = new TableColumn("Prix ttc");
    TableColumn etatCmdcolf = new TableColumn("Etat");
    final ArrayList<CommandeApp> CmdDonnef = new ArrayList<>();
    final ObservableList<CommandeApp> CmdDataf = FXCollections.observableArrayList();
    TableColumn action1CmdMail = new TableColumn("Mail");
    TableColumn action2CmdBon = new TableColumn("Bon");
    @FXML
    private ChoiceBox<String> choixDatePerte;
    @FXML
    private TableView tabTriPerte;
    TableColumn produitcolLpf = new TableColumn("Produit");
    TableColumn qtecolLpf = new TableColumn("Qté");
    TableColumn raisoncolLpf = new TableColumn("Raison de la perte");
    final ArrayList<PanierLp> lperteDonnef = new ArrayList<>();
    final ObservableList<PanierLp> lperteDataf = FXCollections.observableArrayList();
    @FXML
    private Button bnChoixDatePerte;
    @FXML
    private DatePicker dateDebInv;
    @FXML
    private DatePicker dateFinInv;
    @FXML
    private Button voirInvTri;
    @FXML
    private TableView tabTriInvent;
    TableColumn numerocolIvf = new TableColumn("Numéro");
    TableColumn<Invent, String> produitcolIvf = new TableColumn<Invent, String>("Produit");
    TableColumn<Invent, String> emplacecolIvf = new TableColumn<Invent, String>("Emplacement");
    TableColumn<Invent, String> datecolIvf = new TableColumn<Invent, String>("Date");
    TableColumn<Invent, Integer> qtecolIvf = new TableColumn<Invent, Integer>("Qté Inventée");
    TableColumn<Invent, Integer> ecartcolIvf = new TableColumn<Invent, Integer>("Ecart");
    TableColumn<Invent, Integer> qtethcolIvf = new TableColumn<Invent, Integer>("Qté théorique");    
    
    final ArrayList<Invent> inventDonnef = new ArrayList<>();
    final ObservableList<Invent> inventDataf = FXCollections.observableArrayList();
    @FXML
    private Button imprimerInv;
    @FXML
    private ChoiceBox<String> choiEmplf;
    @FXML
    private Button cherEmpl;
    @FXML
    private Label classef;
    @FXML
    private Label titreInfoEmpl;
    @FXML
    private Label capacitef;
    @FXML
    private Label percentOccup;
    @FXML
    private TableView tabBonEntrer;
    TableColumn<BonE, Integer> numeroBon = new TableColumn<BonE, Integer>("Numéro");
    TableColumn<BonE, String> dateBon = new TableColumn<BonE, String>("Date");
    TableColumn<BonE, Integer> numCom = new TableColumn<BonE, Integer>("ID COMD");
    TableColumn action1Bon = new TableColumn("Valider");
    final ArrayList<BonE> bonDonne = new ArrayList<>();
    final ObservableList<BonE> bonData = FXCollections.observableArrayList();
    @FXML
    private VBox vboxBar;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
    }  
    
    Alert creerAlerte(String text, Alert.AlertType type) {
        Alert alert = new Alert(type);
        alert.setHeaderText(null);
        alert.setContentText(text);

        return alert;
    }
    private void EnvoyerMailCmd(CommandeDApprovisionnement c) {

        if (c.getFournisseur().getAdresseMail() != null) {
            String corp = "\n Bonjour ou bonsoir, ci-joint le bon de la nouvelle commande d'approvisionnement.\n Cordialement";
            
            
            MailCmd.EnvoyerMail(c.getFournisseur().getAdresseMail(),"BON DE COMMANDE D'APPROVISSIONNEMENT",corp);         
            tauxRemise.setText("");
            String title = "COMMANDE D'APPROVISSIONNEMENT";
            String message = " Mail envoyé avec succes ";
        }else{
            showMessageDialog(null, "L'adresse mail du fournisseur est introuvable !", "Title", ERROR_MESSAGE);
        }    

    }


    public void actualiserTableCmd(){
        ServiceCommandeDApprovisionnment sca = new ServiceCommandeDApprovisionnment();
        ServiceLigneCommandeDApprovisionnement slc = new ServiceLigneCommandeDApprovisionnement();
        List<CommandeApp> lc = new ArrayList<>();
        for(int j=0; j < sca.afficher().size();j++){
            for(int i=0; i < slc.afficher().size();i++){
                if(slc.afficher().get(i).getCommandeDApprovisionnement().getNumeroC() == sca.afficher().get(j).getNumeroC()){
                    sca.afficher().get(j).getLigneCommandeDApprovisionnements().add(slc.afficher().get(i));
                }
            }
        }
        for(int i = 0 ; i < sca.afficher().size(); i++){
            CommandeApp c = new CommandeApp(sca.afficher().get(i).getNumeroC(),sca.afficher().get(i).getDateCreation(),sca.afficher().get(i).getFournisseur().getAdresseMail(),sca.afficher().get(i).getTauxRemise(),sca.afficher().get(i).getTotalC(),sca.afficher().get(i).getTotalTva(),sca.afficher().get(i).getEtat());
            lc.add(c);
        }
        for(int i = 0 ; i < lc.size(); i++){
            CmdData.add(lc.get(i));
        }
        tabCommande.setEditable(true);               
        numeroCmdcol.setCellValueFactory(new PropertyValueFactory<CommandeApp, Integer>("numero"));
        dateCmdcol.setCellValueFactory(new PropertyValueFactory("date"));        
        fournisseurCmdcol.setCellValueFactory(new PropertyValueFactory<CommandeApp, Email>("fournisseur"));
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
            try{
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                String dateString = format.format( new Date()   );
                Date   date       = format.parse (newdateCmdcol); 
            
            int row = pos.getRow();
            CommandeApp ca = event.getTableView().getItems().get(row);           
            ca.setDate(newdateCmdcol);
            CmdData.set(row, ca);
            }catch(Exception e){
                showMessageDialog(null, "Respectez le format date en chiffre : yyyy-MM-dd", "Title", ERROR_MESSAGE);
            }
        });
        remiseCmdcol.setCellFactory(TextFieldTableCell.<CommandeApp, Double>forTableColumn(new DoubleStringConverter()));
        remiseCmdcol.setMinWidth(50);
        remiseCmdcol.setOnEditCommit(new EventHandler<CellEditEvent<CommandeApp, Double>>(){
            @Override
            public void handle(CellEditEvent<CommandeApp, Double> event) {
            TablePosition<CommandeApp, Double> pos = event.getTablePosition();
            try{
                Double newadr = event.getNewValue();

                int row = pos.getRow();
                CommandeApp ca = event.getTableView().getItems().get(row);           
                ca.setRemise(newadr);
                CmdData.set(row, ca);
            }catch(Exception e){
                showMessageDialog(null, "Ce champ est numérique", "Title", ERROR_MESSAGE);
            }    
            }
        });
        
        
        ObservableList<Email> emailList = FXCollections.observableArrayList(Email.values());
 
        fournisseurCmdcol.setCellValueFactory(new Callback<CellDataFeatures<CommandeApp, Email>, ObservableValue<Email>>() {
            @Override
            public ObservableValue<Email> call(CellDataFeatures<CommandeApp, Email> param) {
                CommandeApp com = param.getValue();
                // F,M
                String genderCode = com.getFournisseur();
                Email em = Email.getByCode(genderCode);
                return new SimpleObjectProperty<Email>(em);
            }
        });
        fournisseurCmdcol.setCellFactory(ComboBoxTableCell.forTableColumn(emailList));
        fournisseurCmdcol.setOnEditCommit(new EventHandler<CellEditEvent<CommandeApp, Email>>(){
            @Override
            public void handle(CellEditEvent<CommandeApp, Email> event) {        
            TablePosition<CommandeApp, Email> pos = event.getTablePosition();
 
            Email newGender = event.getNewValue();
 
            int row = pos.getRow();
            CommandeApp com = event.getTableView().getItems().get(row);
 
            com.setFournisseur(newGender.getCode());
            }
        });
 
        fournisseurCmdcol.setMinWidth(120);
          
    }
    
    public void actualiserTableCmdTri(Date d1,Date d2){
        ServiceCommandeDApprovisionnment sca = new ServiceCommandeDApprovisionnment();
        List<CommandeDApprovisionnement> lsca = new ArrayList<>();
        lsca = sca.afficher();
        ServiceLigneCommandeDApprovisionnement slc = new ServiceLigneCommandeDApprovisionnement();
        List<CommandeApp> lc = new ArrayList<>();
        for(int j=0; j < lsca.size();j++){
            for(int i=0; i < slc.afficher().size();i++){
                if(slc.afficher().get(i).getCommandeDApprovisionnement().getNumeroC() == lsca.get(j).getNumeroC()){
                    lsca.get(j).getLigneCommandeDApprovisionnements().add(slc.afficher().get(i));
                }
            }
        }
        DateFormat format = new SimpleDateFormat("yyyy-MM-DD",Locale.ENGLISH);
        for(int i = 0 ; i < lsca.size(); i++){
            try {
                Date date = format.parse(lsca.get(i).getDateCreation());
                if(compareDate(d1,date,d2) && triParFour.getValue().equals("All")){
                    CommandeApp c = new CommandeApp(lsca.get(i).getNumeroC(),lsca.get(i).getDateCreation(),lsca.get(i).getFournisseur().getAdresseMail(),lsca.get(i).getTauxRemise(),lsca.get(i).getTotalC(),lsca.get(i).getTotalTva(),lsca.get(i).getEtat());
                    lc.add(c);
                }
                if(compareDate(d1,date,d2) && lsca.get(i).getFournisseur().getAdresseMail().equals(triParFour.getValue())){
                    CommandeApp c = new CommandeApp(lsca.get(i).getNumeroC(),lsca.get(i).getDateCreation(),lsca.get(i).getFournisseur().getAdresseMail(),lsca.get(i).getTauxRemise(),lsca.get(i).getTotalC(),lsca.get(i).getTotalTva(),lsca.get(i).getEtat());
                    lc.add(c);
                }
            } catch (ParseException ex) {
                ex.printStackTrace();
            }
        }
        CmdDataf.clear();
        for(int i = 0 ; i < lc.size(); i++){
            CmdDataf.add(lc.get(i));
        }
        tabTriCmd.setEditable(true);               
        numeroCmdcolf.setCellValueFactory(new PropertyValueFactory<CommandeApp, Integer>("numero"));
        dateCmdcolf.setCellValueFactory(new PropertyValueFactory("date"));        
        fournisseurCmdcolf.setCellValueFactory(new PropertyValueFactory<CommandeApp, String>("fournisseur"));
        remiseCmdcolf.setCellValueFactory(new PropertyValueFactory<CommandeApp, Double>("remise"));
        pthtCmdcolf.setCellValueFactory(new PropertyValueFactory<CommandeApp, Double>("ptht"));
        pttcCmdcolf.setCellValueFactory(new PropertyValueFactory<CommandeApp, Double>("pttc"));
        etatCmdcolf.setCellValueFactory(new PropertyValueFactory<CommandeApp, String>("etat"));
        tabTriCmd.setItems(CmdDataf);
        addButtonSendMailCommande();
        addButtonPrintBonCommande();
        //remiseCmdcol.setCellFactory(TextFieldTableCell.<CommandeApp> forTableColumn());
        //remiseCmdcol.setMinWidth(200);
        
        dateCmdcolf.setCellFactory(TextFieldTableCell.<CommandeApp> forTableColumn());
        dateCmdcolf.setMinWidth(50);
        dateCmdcolf.setOnEditCommit((CellEditEvent<CommandeApp, String> event) -> {
            TablePosition<CommandeApp, String> pos = event.getTablePosition();
 
            String newdateCmdcol = event.getNewValue();
 
            int row = pos.getRow();
            CommandeApp ca = event.getTableView().getItems().get(row);           
            ca.setDate(newdateCmdcol);
            CmdDataf.set(row, ca);
        });
          
    }
    
    public void addButtonSendMailCommande(){
       
        Callback<TableColumn<CommandeApp, Void>, TableCell<CommandeApp, Void>> cellFactory = new Callback<TableColumn<CommandeApp, Void>, TableCell<CommandeApp, Void>>() {
            @Override
            public TableCell<CommandeApp, Void> call(final TableColumn<CommandeApp, Void> param) {
                final TableCell<CommandeApp, Void> cell = new TableCell<CommandeApp, Void>() {

                    private final Button btn = new Button("Mail");

                    {
                        btn.setOnAction((ActionEvent event) -> {
                         int input = JOptionPane.showConfirmDialog(null, "Voulez-vous envoyer un mail au fournisseur?");
                         if(input == 0){
                            try {
                                ServiceCommandeDApprovisionnment sca = new ServiceCommandeDApprovisionnment();
                                List<CommandeDApprovisionnement> lsca = new ArrayList<>();
                                lsca = sca.afficher();
                                ServiceLigneCommandeDApprovisionnement slc = new ServiceLigneCommandeDApprovisionnement();
                                List<CommandeApp> lc = new ArrayList<>();
                                for(int j=0; j < lsca.size();j++){
                                    for(int i=0; i < slc.afficher().size();i++){
                                        if(slc.afficher().get(i).getCommandeDApprovisionnement().getNumeroC() == lsca.get(j).getNumeroC()){
                                            lsca.get(j).getLigneCommandeDApprovisionnements().add(slc.afficher().get(i));
                                        }
                                    }
                                }
                                EnvoyerMailCmd(sca.rechercher(lsca, getTableView().getSelectionModel().getSelectedItem().getNumero()));
                                showMessageDialog(null, "Mail envoyé avec succes !", "Title", ERROR_MESSAGE);
                                Date d1 = Date.from(dateDebutCmd.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
                                Date d2 = Date.from(dateFinCmd.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
                                CmdDataf.clear();
                                actualiserTableCmdTri(d1,d2);
                            
                            } catch (Exception ex) {
                                ex.printStackTrace();
                            }
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

        action1CmdMail.setCellFactory(cellFactory);
            
    }
    //sauvegarder
    public void addButtonPrintBonCommande(){
        
        Callback<TableColumn<CommandeApp, Void>, TableCell<CommandeApp, Void>> cellFactory = new Callback<TableColumn<CommandeApp, Void>, TableCell<CommandeApp, Void>>() {
            @Override
            public TableCell<CommandeApp, Void> call(final TableColumn<CommandeApp, Void> param) {
                final TableCell<CommandeApp, Void> cell = new TableCell<CommandeApp, Void>() {

                    private final Button btn = new Button("Bon");

                    {
                        btn.setOnAction((ActionEvent event) -> {
                        int input = JOptionPane.showConfirmDialog(null, "Voulez-vous imprimer le bon de commande?");
                        if(input == 0){
                            try {
                                ServiceCommandeDApprovisionnment sca = new ServiceCommandeDApprovisionnment();
                                List<CommandeDApprovisionnement> lsca = new ArrayList<>();
                                lsca = sca.afficher();
                                ServiceLigneCommandeDApprovisionnement slc = new ServiceLigneCommandeDApprovisionnement();
                                List<CommandeApp> lc = new ArrayList<>();
                                for(int j=0; j < lsca.size();j++){
                                    for(int i=0; i < slc.afficher().size();i++){
                                        if(slc.afficher().get(i).getCommandeDApprovisionnement().getNumeroC() == lsca.get(j).getNumeroC()){
                                            lsca.get(j).getLigneCommandeDApprovisionnements().add(slc.afficher().get(i));
                                        }
                                    }
                                }
                                Document d = new Document();
                                PdfWriter.getInstance(d, new FileOutputStream("C:\\Users\\guiforodrigue\\Desktop\\PDF\\Bon_de_commande_approvisionnement.pdf"));
                                d.open();
                                Paragraph p = new Paragraph();
                                //getTableView().getItems().remove(getTableView().getSelectionModel().getSelectedItem());
                                CommandeDApprovisionnement co = sca.rechercher(lsca, getTableView().getSelectionModel().getSelectedItem().getNumero());
                                p.add("BON DE COMMANDE D'APPROVISIONNEMENT N° "+ co.getNumeroC() +"\n       Date "+ co.getDateCreation()
                                        + "\n\n" );
                                PdfPTable table = new PdfPTable(3);
                                PdfPCell cell;
      
                                cell = new PdfPCell(new Phrase("Liste"));
                                cell.setColspan(3);
                                table.addCell(cell);
                                for(int i=0;i < co.getLigneCommandeDApprovisionnements().size();i++){
                                    table.addCell(Integer.toString(co.getLigneCommandeDApprovisionnements().get(i).getId()));
                                    table.addCell(co.getLigneCommandeDApprovisionnements().get(i).getProduitAchat().getLibelle());
                                    table.addCell(Integer.toString(co.getLigneCommandeDApprovisionnements().get(i).getId()));
                                }
                                d.add(p);
                                d.add(table);
                                d.close();
                                
                                Date d1 = Date.from(dateDebutCmd.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
                                Date d2 = Date.from(dateFinCmd.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
                                CmdDataf.clear();
                                actualiserTableCmdTri(d1,d2);
                                Desktop.getDesktop().open(new File("C:\\Users\\guiforodrigue\\Desktop\\PDF\\Bon_de_commande_approvisionnement.pdf"));
                            } catch (Exception ex) {
                                ex.printStackTrace();
                            }
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

        action2CmdBon.setCellFactory(cellFactory);
            
        
    }
    
    public void actualiserTableChoixPerte(){
        lperteDataf.clear();
        ServicePerte sp = new ServicePerte();
        List<Perte> lp = new ArrayList<>();
        ServiceLignePerte slp = new ServiceLignePerte();
        List<LignePerte> llp = new ArrayList<>();
        llp = slp.afficher();
        lp = sp.afficher();
        for(int i=0; i < lp.size(); i++){
            for(int j=0; j < llp.size(); j++){
                if(lp.get(i).getId() == llp.get(j).getPerte().getId()){
                    lp.get(i).getLignePertes().add(llp.get(j));
                }
            }
        }
        for(int i=0; i < lp.size(); i++){
            if(lp.get(i).getDate().equals(choixDatePerte.getValue())){
                for(int j=0; j < lp.get(i).getLignePertes().size();j++){
                    PanierLp p = new PanierLp(lp.get(i).getLignePertes().get(j).getProduitAchat().getLibelle(),lp.get(i).getLignePertes().get(j).getQuantite(),lp.get(i).getLignePertes().get(j).getRaisonPerte());
                    lperteDataf.add(p);
                }
            }
        }
        if (choixDatePerte.getValue() != null){                      
            produitcolLpf.setCellValueFactory(new PropertyValueFactory<PanierLp, String>("produit"));
            qtecolLpf.setCellValueFactory(new PropertyValueFactory<PanierLp, Integer>("quantite"));
            raisoncolLpf.setCellValueFactory(new PropertyValueFactory<PanierLp, String>("raisonPerte"));
            tabTriPerte.setItems(lperteDataf);
            
            
        }
        
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
    public void actualiserTableInvTri(Date d1, Date d2){
        DateFormat format = new SimpleDateFormat("yyyy-MM-DD",Locale.ENGLISH);
        ServiceInventaireStock sca = new ServiceInventaireStock();
        List<InventaireStock> lsca = new ArrayList<>();
        lsca = sca.afficher();
        List<Invent> lc = new ArrayList<>();
        for(int i = 0 ; i < sca.afficher().size(); i++){
            try {
                Date date = format.parse(lsca.get(i).getDateCreation());
            if(compareDate(d1,date,d2)){
                Invent c = new Invent(lsca.get(i).getId(),lsca.get(i).getProduitAchat().getLibelle(),lsca.get(i).getEmplacement().getAdresse(),lsca.get(i).getDateCreation(),lsca.get(i).getQunatiteInventiare(),lsca.get(i).getEcart(),lsca.get(i).getQuantiteTheorique());          
                lc.add(c);
            }
            } catch (ParseException ex) {
                ex.printStackTrace();
            }
            
        }
        inventDataf.clear();
        for(int i = 0 ; i < lc.size(); i++){
            inventDataf.add(lc.get(i));
        }
       
        tabTriInvent.setEditable(true);               
        numerocolIvf.setCellValueFactory(new PropertyValueFactory<Invent, Integer>("id"));       
        produitcolIvf.setCellValueFactory(new PropertyValueFactory<Invent, String>("produitAchat"));
        emplacecolIvf.setCellValueFactory(new PropertyValueFactory("emplacement"));
        datecolIvf.setCellValueFactory(new PropertyValueFactory<Invent, String>("dateCreation"));
        qtecolIvf.setCellValueFactory(new PropertyValueFactory<Invent, Integer>("qunatiteInventiare"));
        ecartcolIvf.setCellValueFactory(new PropertyValueFactory<Invent, Integer>("ecart"));
        qtethcolIvf.setCellValueFactory(new PropertyValueFactory<Invent, Integer>("quantiteTheorique"));
        tabTriInvent.setItems(inventDataf);
        
        
    }
    //enregistrerInventaire
    public void actualiserTableIv(){
        inventData.clear();
        ServiceInventaireStock sca = new ServiceInventaireStock();
        List<Invent> lc = new ArrayList<>();
        for(int i = 0 ; i < sca.afficher().size(); i++){
            Invent c = new Invent(sca.afficher().get(i).getId(),sca.afficher().get(i).getProduitAchat().getLibelle(),sca.afficher().get(i).getEmplacement().getAdresse(),sca.afficher().get(i).getDateCreation(),sca.afficher().get(i).getQunatiteInventiare(),sca.afficher().get(i).getEcart(),sca.afficher().get(i).getQuantiteTheorique());
            System.out.print(c.getEmplacement());            
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
            try{ 
                int row = pos.getRow();
                Invent ca = event.getTableView().getItems().get(row);           
                ca.setQunatiteInventiare(newadr);
                inventData.set(row, ca);
            }catch(NumberFormatException e){
                showMessageDialog(null, "Ce champ est numérique", "Title", ERROR_MESSAGE);
            }
        });
                    
        datecolIv.setCellFactory(TextFieldTableCell.<Invent> forTableColumn());
        datecolIv.setMinWidth(50);
        datecolIv.setOnEditCommit((CellEditEvent<Invent, String> event) -> {
            TablePosition<Invent, String> pos = event.getTablePosition();
 
            String newadr = event.getNewValue();
            try{
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                String dateString = format.format( new Date()   );
                Date   date       = format.parse (newadr);
                int row = pos.getRow();
                Invent ca = event.getTableView().getItems().get(row);           
                ca.setDateCreation(newadr);
                inventData.set(row, ca);
            }catch(Exception e){
                showMessageDialog(null, "Respectez le format date en chiffre : yyyy-MM-dd", "Title", ERROR_MESSAGE);
            }
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
        Node anchor6 = childs.get(6);
        Node anchor7 = childs.get(7);
        Node anchor8 = childs.get(8);
        Node anchor9 = childs.get(9);
        /*Node anchor10 = childs.get(10);*/
            
           anchor0.setVisible(true);
           anchor1.setVisible(false);
           anchor2.setVisible(false);
           anchor3.setVisible(false);
           anchor4.setVisible(false);
           anchor5.setVisible(false);
           anchor6.setVisible(false);
           anchor7.setVisible(false);
           anchor8.setVisible(false);
           anchor9.setVisible(false);
           /*anchor10.setVisible(false);*/
           //topNode.toFront();
           //backNode.setVisible(false);
           //backNode.toBack();
        panier.getColumns().addAll(produitcol,qtecol,prixcol,tvacol,action);
        tabCommande.getColumns().addAll(numeroCmdcol,dateCmdcol,fournisseurCmdcol,remiseCmdcol,pthtCmdcol,pttcCmdcol,etatCmdcol,action1Cmd,action2Cmd);
        ServiceProduitAchat spa = new ServiceProduitAchat();
        for(int i=0;i<spa.afficher().size();i++){
            produit.getItems().add(spa.afficher().get(i).getLibelle());           
        }
        ServiceFournisseur sf = new ServiceFournisseur();
        for(int i=0;i<sf.afficher().size();i++){
            fournisseur.getItems().add(sf.afficher().get(i).getAdresseMail());    
        }
        
        actualiserTableCmd();
        
    }
    
    @FXML
    private void chercherCommande(ActionEvent event) {
        ObservableList<Node> childs = com.getChildren();
        Node anchor0 = childs.get(0);
        Node anchor1 = childs.get(1);
        Node anchor2 = childs.get(2);
        Node anchor3 = childs.get(3);
        Node anchor4 = childs.get(4);
        Node anchor5 = childs.get(5);
        Node anchor6 = childs.get(6);
        Node anchor7 = childs.get(7);
        Node anchor8 = childs.get(8);
        Node anchor9 = childs.get(9);
        /*Node anchor10 = childs.get(10);*/
            
           anchor0.setVisible(false);
           anchor1.setVisible(false);
           anchor2.setVisible(false);
           anchor3.setVisible(false);
           anchor4.setVisible(false);
           anchor5.setVisible(false);
           anchor6.setVisible(true);
           anchor7.setVisible(false);
           anchor8.setVisible(false);
           anchor9.setVisible(false);
           /*anchor10.setVisible(false);*/
           
        tabTriCmd.getColumns().addAll(numeroCmdcolf,dateCmdcolf,fournisseurCmdcolf,remiseCmdcolf,pthtCmdcolf,pttcCmdcolf,etatCmdcolf,action1CmdMail,action2CmdBon);
        ServiceFournisseur sf = new ServiceFournisseur();
        triParFour.getItems().add("All");
        for(int i=0;i<sf.afficher().size();i++){
            triParFour.getItems().add(sf.afficher().get(i).getAdresseMail());    
        }
                                       
    }

    @FXML
    private void ajouterPanier(ActionEvent event) {
        if((produit.getValue() != null) && (prixUnitaire.getText().length() > 0) && (qteCmd.getText().length() > 0) && (tva.getText().length() > 0)){                
            int input = JOptionPane.showConfirmDialog(null, "Voulez-vous mettre au panier?");
            if(input == 0){
                try{        
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
                catch (NumberFormatException e) {
                    showMessageDialog(null, "Les champs prix, qté et tva numériques", "Title", ERROR_MESSAGE);
                }       
            }
        }
        else{
            showMessageDialog(null, "Remplissez tous les champs", "Title", ERROR_MESSAGE);
        }            
        
    }

    @FXML
    private void annulerCommande(ActionEvent event) {
        int input = JOptionPane.showConfirmDialog(null, "Voulez-vous vraiment annuler la commande ?");
            if(input == 0){
                dateCreationCmd.setValue(null);
                //fournisseur.getSelectionModel().clearSelection();
                //produit.getSelectionModel().clearSelection();
                tauxRemise.setText("");
                qteCmd.setText("");
                prixUnitaire.setText("");
                tva.setText("");
                panier.getItems().clear();
            }
    }
    
    private void addButtonDeleteToTablePanier() {
        
        Callback<TableColumn<Panier, Void>, TableCell<Panier, Void>> cellFactory = new Callback<TableColumn<Panier, Void>, TableCell<Panier, Void>>() {
            @Override
            public TableCell<Panier, Void> call(final TableColumn<Panier, Void> param) {
                final TableCell<Panier, Void> cell = new TableCell<Panier, Void>() {

                    private final Button btn = new Button("Supprimer");

                    {
                        btn.setOnAction((ActionEvent event) -> {
                            int input = JOptionPane.showConfirmDialog(null, "Voulez-vous suprimer du panier?");
                            if(input == 0){
                                getTableView().getItems().remove(getTableView().getSelectionModel().getSelectedItem());
                                panierDonne.remove(getTableView().getSelectionModel().getSelectedItem());
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

        action.setCellFactory(cellFactory);
            
    }
    //effectuerCommande
    private void addButtonDeleteToTableCommande() {
        
        ServiceCommandeDApprovisionnment sca = new ServiceCommandeDApprovisionnment();
        Callback<TableColumn<CommandeApp, Void>, TableCell<CommandeApp, Void>> cellFactory = new Callback<TableColumn<CommandeApp, Void>, TableCell<CommandeApp, Void>>() {
            @Override
            public TableCell<CommandeApp, Void> call(final TableColumn<CommandeApp, Void> param) {
                final TableCell<CommandeApp, Void> cell = new TableCell<CommandeApp, Void>() {

                    private final Button btn = new Button("Supprimer");

                    {
                        btn.setOnAction((ActionEvent event) -> {
                            int input = JOptionPane.showConfirmDialog(null, "Voulez-vous suprimer cette commande?");
                            if(input == 0){
                                getTableView().getItems().remove(getTableView().getSelectionModel().getSelectedItem());
                                sca.supprimer(sca.rechercher(sca.afficher(), getTableView().getSelectionModel().getSelectedItem().getNumero()));
                                showMessageDialog(null, "La commande a été supprimée !", "Title", ERROR_MESSAGE);
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
                            int input = JOptionPane.showConfirmDialog(null, "Voulez-vous suprimer cette emplacement?");
                            if(input == 0){
                                getTableView().getItems().remove(getTableView().getSelectionModel().getSelectedItem());
                                sca.supprimer(sca.rechercher(sca.afficher(), getTableView().getSelectionModel().getSelectedItem().getId()));
                                showMessageDialog(null, "L'emplacement a été supprimée !", "Title", ERROR_MESSAGE);
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
                                int input = JOptionPane.showConfirmDialog(null, "Voulez-vous modifier cette emplacement?");
                                if(input == 0){
                                    Emplacement c = sca.rechercher(sca.afficher(), getTableView().getSelectionModel().getSelectedItem().getId());
                                    c.setAdresse(getTableView().getSelectionModel().getSelectedItem().getAdresse());
                                    c.setCapaciteStockage(getTableView().getSelectionModel().getSelectedItem().getCapaciteStockage());
                                    c.setQuantiteStocker(getTableView().getSelectionModel().getSelectedItem().getQuantiteStocker());
                                    c.setClasse(getTableView().getSelectionModel().getSelectedItem().getAdresse());
                                    sca.modifier(c);
                                    showMessageDialog(null, "L'emplacement a été modifié !", "Title", ERROR_MESSAGE);
                                }
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
                            int input = JOptionPane.showConfirmDialog(null, "Voulez-vous supprimer cette inventaire?");
                                if(input == 0){
                                    getTableView().getItems().remove(getTableView().getSelectionModel().getSelectedItem());
                                    sca.supprimer(sca.rechercher(sca.afficher(), getTableView().getSelectionModel().getSelectedItem().getId()));
                                    showMessageDialog(null, "L'inventaire a été supprimé !", "Title", ERROR_MESSAGE);
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
                                int input = JOptionPane.showConfirmDialog(null, "Voulez-vous modifier cette inventaire?");
                                if(input == 0){
                                    InventaireStock c = sca.rechercher(sca.afficher(), getTableView().getSelectionModel().getSelectedItem().getId());
                                    c.setDateCreation(getTableView().getSelectionModel().getSelectedItem().getDateCreation());
                                    c.setQunatiteInventiare(getTableView().getSelectionModel().getSelectedItem().getQunatiteInventiare());                    
                                    sca.modifier(c);
                                showMessageDialog(null, "L'inventaire a été modifié !", "Title", ERROR_MESSAGE);
                                }
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
                                int input = JOptionPane.showConfirmDialog(null, "Voulez-vous modifier cette commande?");
                                if(input == 0){
                                    CommandeDApprovisionnement c = sca.rechercher(sca.afficher(), getTableView().getSelectionModel().getSelectedItem().getNumero());
                                    c.setDateCreation(getTableView().getSelectionModel().getSelectedItem().getDate());         
                                    sca.modifier(c);
                                    showMessageDialog(null, "La commande a été modifié !", "Title", ERROR_MESSAGE);
                                }
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
                            int input = JOptionPane.showConfirmDialog(null, "Voulez-vous supprimer cette ligne de perte?");
                                if(input == 0){
                                getTableView().getItems().remove(getTableView().getSelectionModel().getSelectedItem());
                                lperteDonne.remove(getTableView().getSelectionModel().getSelectedItem());
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
        if((dateCreationCmd.getValue() != null) && (fournisseur.getValue().length() > 0) && (tauxRemise.getText().length() > 0)){                
            int input = JOptionPane.showConfirmDialog(null, "Voulez-vous enrégistrer la comande?");
            if(input == 0){
                try{
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
                    CommandeDApprovisionnement ca = new CommandeDApprovisionnement(ptht,dateCreationCmd.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")),"non_facturée",Double.valueOf(tauxRemise.getText()),pttc,sf.rechercherf(sf.afficher(), fournisseur.getValue()));
                    sca.ajouter(ca);
                    ca.setNumeroC(sca.lastCmd().getNumeroC());
                    for(int i=0;i<panierDonne.size();i++){
                        Double t = panierDonne.get(i).getPrix()*panierDonne.get(i).getQuantite(); //sp.rechercher(sp.afficher(), panierDonne.get(i).getProduit())
                        LigneCommandeDApprovisionnement lc = new LigneCommandeDApprovisionnement(ca,sp.rechercher(sp.afficher(), produit.getValue()),panierDonne.get(i).getPrix(),panierDonne.get(i).getQuantite(),t,panierDonne.get(i).getTva());
                        slc.ajouter(lc);
                    }
                    CmdData.clear();
                    actualiserTableCmd();
                    showMessageDialog(null, "Commande enrégistrée !", "Title", ERROR_MESSAGE);
                    
                }
                catch (NumberFormatException e) {
                    showMessageDialog(null, "Le champ taux de remise est numérique", "Title", ERROR_MESSAGE);
                }       
            }
        }
        else{
            showMessageDialog(null, "Remplissez tous les champs", "Title", ERROR_MESSAGE);
        }           
                         
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
        Node anchor6 = childs.get(6);
        Node anchor7 = childs.get(7);
        Node anchor8 = childs.get(8);
        Node anchor9 = childs.get(9);
        /*Node anchor10 = childs.get(10);*/
            
           anchor0.setVisible(false);
           anchor1.setVisible(false);
           anchor2.setVisible(false);
           anchor3.setVisible(true);
           anchor4.setVisible(false);
           anchor5.setVisible(false);
           anchor6.setVisible(false);
           anchor7.setVisible(false);
           anchor8.setVisible(false);
           anchor9.setVisible(false);
           /*anchor10.setVisible(false);*/
        
        tabEmp.getColumns().addAll(numeroEmp,adresseEmp,capaciteStockageEmp,qteStockerEmp,classe,action1Emp,action2Emp);

        
        actualiserTableEmp();

    }

    @FXML
    private void gererEspace(ActionEvent event) {
        ObservableList<Node> childs = com.getChildren();
        Node anchor0 = childs.get(0);
        Node anchor1 = childs.get(1);
        Node anchor2 = childs.get(2);
        Node anchor3 = childs.get(3);
        Node anchor4 = childs.get(4);
        Node anchor5 = childs.get(5);
        Node anchor6 = childs.get(6);
        Node anchor7 = childs.get(7);
        Node anchor8 = childs.get(8);
        Node anchor9 = childs.get(9);
        /*Node anchor10 = childs.get(10);*/
            
           anchor0.setVisible(false);
           anchor1.setVisible(false);
           anchor2.setVisible(false);
           anchor3.setVisible(false);
           anchor4.setVisible(false);
           anchor5.setVisible(false);
           anchor6.setVisible(false);
           anchor7.setVisible(false);
           anchor8.setVisible(false);
           anchor9.setVisible(true);
           /*anchor10.setVisible(false);*/
        
        tabBonEntrer.getColumns().addAll(numeroBon,dateBon,numCom,action1Bon); 
        ServiceEmplacement se = new ServiceEmplacement();
        choiEmplf.getItems().clear();
        for(int i=0;i<se.afficher().size();i++){
            choiEmplf.getItems().add(se.afficher().get(i).getAdresse());    
        }
        actualiserTableBon();
    }

    private void actualiserTableBon(){
       /* ServiceBonEntree sb = new ServiceBonEntree();
        ServiceCommandeDApprovisionnment sc = new ServiceCommandeDApprovisionnment();
       
        for(int i = 0 ; i < sb.afficher().size(); i++){
            
            CommandeDApprovisionnement ca = sc.rechercher(sc.afficher(), sb.afficher().get(i).getCommandeDApprovisionnement().getNumeroC());
            if(ca.getEtat().equals("non_facturée")){
                DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                String todayAsString = df.format(sb.afficher().get(i).getDate());
                BonE c = new BonE(sb.afficher().get(i).getId(),todayAsString,sb.afficher().get(i).getCommandeDApprovisionnement().getNumeroC());
                bonDonne.add(c);
            }
            
        }
        for(int i = 0 ; i < bonDonne.size(); i++){
            bonData.add(bonDonne.get(i));
        }
        tabBonEntrer.setEditable(true);               
        numeroBon.setCellValueFactory(new PropertyValueFactory<BonE, Integer>("Numéro"));       
        dateBon.setCellValueFactory(new PropertyValueFactory<BonE, String>("Date"));
        numCom.setCellValueFactory(new PropertyValueFactory<BonE, Integer>("ID COMD"));
        tabBonEntrer.setItems(bonData);
        addButtonValidateToTableBon();*/
        
    }
    private void addButtonValidateToTableBon() {
        ServiceBonEntree sb = new ServiceBonEntree();
        
        Callback<TableColumn<BonE, Void>, TableCell<BonE, Void>> cellFactory = new Callback<TableColumn<BonE, Void>, TableCell<BonE, Void>>() {
            @Override
            public TableCell<BonE, Void> call(final TableColumn<BonE, Void> param) {
                final TableCell<BonE, Void> cell = new TableCell<BonE, Void>() {

                    private final Button btn = new Button("Charger");

                    {
                        btn.setOnAction(new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent event) {
                                int a = getTableView().getSelectionModel().getSelectedItem().getCommandeDApprovisionnement();
                                ServiceLigneCommandeDApprovisionnement sca = new ServiceLigneCommandeDApprovisionnement();
                                ServiceEmplacement se = new ServiceEmplacement();
                                List<Emplacement> lse = new ArrayList<>();
                                List<LigneCommandeDApprovisionnement> llc = new ArrayList<>();
                                llc = sca.afficher();
                                lse = se.afficher();
                                ServiceLigneCommandeDApprovisionnement slc = new ServiceLigneCommandeDApprovisionnement();
                                
                                for(int j=0; j < llc.size();j++){
                                    
                                    if(llc.get(j).getCommandeDApprovisionnement().getNumeroC()== a){
                                        for(int i=0; i < lse.size(); i++){
                                            int r = lse.get(i).getCapaciteStockage() - lse.get(i).getQuantiteStocker();
                                            if((lse.get(i).getClasse().equals(llc.get(j).getProduitAchat().getClasse())) && (llc.get(j).getQuantite() < r )){
                                                lse.get(i).setQuantiteStocker(lse.get(i).getQuantiteStocker() + llc.get(j).getQuantite());
                                                Emplacement e = lse.get(i);
                                                se.modifier(e);
                                                
                                                String title = "CHARGEMENT EFFECTUE";
                                                String message = llc.get(j).getQuantite() +" "+ llc.get(j).getProduitAchat().getLibelle()+" stocké(s) à "+ lse.get(i).getAdresse();
                                                TrayNotification tray = new TrayNotification();
                                                AnimationType type = AnimationType.POPUP;
                                                tray.setAnimationType(type);
                                                tray.setTitle(title);
                                                tray.setMessage(message);
                                                tray.setNotificationType(NotificationType.SUCCESS);
                                                tray.showAndDismiss(Duration.millis(4000));
                                                break;
                                            }
                                        }
                                    }
                                }    
                               
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

        action1Bon.setCellFactory(cellFactory);

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
        Node anchor6 = childs.get(6);
        Node anchor7 = childs.get(7);
        Node anchor8 = childs.get(8);
        Node anchor9 = childs.get(9);
        /*Node anchor10 = childs.get(10);*/
            
           anchor0.setVisible(false);
           anchor1.setVisible(false);
           anchor2.setVisible(false);
           anchor3.setVisible(false);
           anchor4.setVisible(true);
           anchor5.setVisible(false);
           anchor6.setVisible(false);
           anchor7.setVisible(false);
           anchor8.setVisible(false);
           anchor9.setVisible(false);
           /*anchor10.setVisible(false);*/
        tabIv.getColumns().addAll(numerocolIv,produitcolIv,emplacecolIv,datecolIv,qtecolIv,ecartcolIv,qtethcolIv,action1Iv,action2Iv);
        ServiceProduitAchat spa = new ServiceProduitAchat();
        for(int i=0;i<spa.afficher().size();i++){
            produitIv.getItems().add(spa.afficher().get(i).getLibelle());           
        }
        for(int i = 0; i<se.afficher().size();i++){
            emplaceIv.getItems().add(se.afficher().get(i).getAdresse());
        }
        inventData.clear();
        actualiserTableIv();   
    
    }

    @FXML
    private void FiltrerImprimerInventaire(ActionEvent event) {
        ServiceEmplacement se = new ServiceEmplacement();
        ObservableList<Node> childs = com.getChildren();
        Node anchor0 = childs.get(0);
        Node anchor1 = childs.get(1);
        Node anchor2 = childs.get(2);
        Node anchor3 = childs.get(3);
        Node anchor4 = childs.get(4);
        Node anchor5 = childs.get(5);
        Node anchor6 = childs.get(6);
        Node anchor7 = childs.get(7);
        Node anchor8 = childs.get(8);
        Node anchor9 = childs.get(9);
        /*Node anchor10 = childs.get(10);*/
            
           anchor0.setVisible(false);
           anchor1.setVisible(false);
           anchor2.setVisible(false);
           anchor3.setVisible(false);
           anchor4.setVisible(false);
           anchor5.setVisible(false);
           anchor6.setVisible(false);
           anchor7.setVisible(false);
           anchor8.setVisible(true);
           anchor9.setVisible(false);
           /*anchor10.setVisible(false);*/
        tabTriInvent.getColumns().addAll(numerocolIvf,produitcolIvf,emplacecolIvf,datecolIvf,qtecolIvf,ecartcolIvf,qtethcolIvf);

        
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
        Node anchor6 = childs.get(6);
        Node anchor7 = childs.get(7);
        Node anchor8 = childs.get(8);
        Node anchor9 = childs.get(9);
        /*Node anchor10 = childs.get(10);*/
            
           anchor0.setVisible(false);
           anchor1.setVisible(true);
           anchor2.setVisible(false);
           anchor3.setVisible(false);
           anchor4.setVisible(false);
           anchor5.setVisible(false);
           anchor6.setVisible(false);
           anchor7.setVisible(false);
           anchor8.setVisible(false);
           anchor9.setVisible(false);
           /*anchor10.setVisible(false);*/
        tabPert.getColumns().addAll(produitcolLp,qtecolLp,raisoncolLp,actionLp);
        tabPerte.getColumns().addAll(numeroPertecol,datePertecol,action1Pert,action2Pert);
        ServiceProduitAchat spa = new ServiceProduitAchat();
        for(int i=0;i<spa.afficher().size();i++){
            produitPerdu.getItems().add(spa.afficher().get(i).getLibelle());           
        }
            raisonPerte.getItems().add("Vol");
            raisonPerte.getItems().add("Hors usage");
            raisonPerte.getItems().add("Inconnu");
       
        actualiserTablePerte();
    }

    @FXML
    private void filtrerEtImprimerPertes(ActionEvent event) {
        ObservableList<Node> childs = com.getChildren();
        Node anchor0 = childs.get(0);
        Node anchor1 = childs.get(1);
        Node anchor2 = childs.get(2);
        Node anchor3 = childs.get(3);
        Node anchor4 = childs.get(4);
        Node anchor5 = childs.get(5);
        Node anchor6 = childs.get(6);
        Node anchor7 = childs.get(7);
        Node anchor8 = childs.get(8);
        Node anchor9 = childs.get(9);
        /*Node anchor10 = childs.get(10);*/
            
           anchor0.setVisible(false);
           anchor1.setVisible(false);
           anchor2.setVisible(false);
           anchor3.setVisible(false);
           anchor4.setVisible(false);
           anchor5.setVisible(false);
           anchor6.setVisible(false);
           anchor7.setVisible(true);
           anchor8.setVisible(false);
           anchor9.setVisible(false);
           /*anchor10.setVisible(false);*/
        tabTriPerte.getColumns().addAll(produitcolLpf,qtecolLpf,raisoncolLpf);   
        ServicePerte spa = new ServicePerte();
        for(int i=0;i<spa.afficher().size();i++){
            choixDatePerte.getItems().add(spa.afficher().get(i).getDate());           
        }
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
        Node anchor6 = childs.get(6);
        Node anchor7 = childs.get(7);
        Node anchor8 = childs.get(8);
        Node anchor9 = childs.get(9);
        /*Node anchor10 = childs.get(10);*/
            
           anchor0.setVisible(false);
           anchor1.setVisible(false);
           anchor2.setVisible(true);
           anchor3.setVisible(false);
           anchor4.setVisible(false);
           anchor5.setVisible(false);
           anchor6.setVisible(false);
           anchor7.setVisible(false);
           anchor8.setVisible(false);
           anchor9.setVisible(false);
           /*anchor10.setVisible(false);*/
        ServiceEntrepot se = new ServiceEntrepot();
        
        mf.setText(se.afficher().get(0).getMatriculeFiscale());
        adr.setText(se.afficher().get(0).getAdresse());
        rs.setText(se.afficher().get(0).getRaisonSociale());
        mail.setText(se.afficher().get(0).getAdresseMail());
        tel.setText(se.afficher().get(0).getNumeroTel());
           
    }

    @FXML
    private void sauvegarder(ActionEvent event) throws IOException {
        try {
            File fbackup = new File("C:\\backup.sql");
            // execute mysqldump command
            String[] command = new String[] {"cmd.exe", "C:\\wamp64\\bin\\mysql\\mysql5.7.26\\bin\\mysqldump.exe --quick --lock-tables --user=root --password= gentrepot"};
            final Process process = Runtime.getRuntime().exec(command);
            // write process output line by line to file
            if(process!=null) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try{
                            try(BufferedReader reader = new BufferedReader(new InputStreamReader(new DataInputStream(process.getInputStream())));
                                    BufferedWriter writer = new BufferedWriter(new FileWriter(fbackup))) {
                                String line;
                                while((line=reader.readLine())!=null)   {
                                    writer.write(line);
                                    writer.newLine();
                                }
                            }
                        } catch(Exception ex){
                            // handle or log exception ...
                        }
                    }
                }).start();
            }
            if(process!=null && process.waitFor()==0) {
                showMessageDialog(null, "Sauvegarde réussie !", "Title", ERROR_MESSAGE);
            } else {
                showMessageDialog(null, "Echec de sauvegarde !", "Title", ERROR_MESSAGE);
            }
        } catch (InterruptedException ex) {
            ex.getStackTrace();
        }
    }

    @FXML
    private void ajouterCorbeille(ActionEvent event) {
         if((produitPerdu.getValue() != null) && (qtePerdu.getText().length() > 0)&& (raisonPerte.getValue() != null)){   
             try{             
                PanierLp p = new PanierLp(produitPerdu.getValue(),Integer.valueOf(qtePerdu.getText()),raisonPerte.getValue());      
                lperteData.add(p); 
                lperteDonne.add(p);
                produitcolLp.setCellValueFactory(new PropertyValueFactory<PanierLp, String>("produit"));
                qtecolLp.setCellValueFactory(new PropertyValueFactory<PanierLp, Integer>("quantite"));
                raisoncolLp.setCellValueFactory(new PropertyValueFactory<PanierLp, String>("raisonPerte"));
                tabPert.setItems(lperteData);
                addButtonDeleteToTablePanierLp();
             }
             catch (NumberFormatException e) {
                    showMessageDialog(null, "Le champ qté est numérique", "Title", ERROR_MESSAGE);
                }
         }else{
             showMessageDialog(null, "Remplissez tous les champs vides", "Title", ERROR_MESSAGE);
         }
    }

    @FXML
    private void enregitrerPerte(ActionEvent event) {
        if((datePerte.getValue() != null) && (lperteDonne != null)){                
            int input = JOptionPane.showConfirmDialog(null, "Voulez-vous enrégistrer l'inventaire?");
            if(input == 0){
                try{
                ServiceProduitAchat sp = new ServiceProduitAchat();
                ServiceLignePerte slc = new ServiceLignePerte();
                ServicePerte sca = new ServicePerte();

                Perte ca = new Perte(datePerte.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
                sca.ajouter(ca);
                ca.setId(sca.lastPert().getId());
                for(int i=0;i<lperteDonne.size();i++){
                    //sp.rechercher(sp.afficher(), panierDonne.get(i).getProduit())
                    LignePerte lc = new LignePerte(ca,sp.rechercher(sp.afficher(), produitPerdu.getValue()),lperteDonne.get(i).getQuantite(),lperteDonne.get(i).getRaisonPerte());
                    slc.ajouter(lc);
                }
                perteData.clear();
                actualiserTablePerte();
                showMessageDialog(null, "Perte enrégistrée !", "Title", ERROR_MESSAGE);
                 }
                catch (NumberFormatException e) {
                    showMessageDialog(null, "Les champs à remplir sont numériques", "Title", ERROR_MESSAGE);
                }       
            }
    }
       
    }

    @FXML
    private void annulerPerte(ActionEvent event) {
        int input = JOptionPane.showConfirmDialog(null, "Voulez-vous annuler?");
            if(input == 0){
                datePerte.setValue(null);
                //produitPerdu.getSelectionModel().clearSelection();
                qtePerdu.setText("");
                //raisonPerte.getSelectionModel().clearSelection();
                tabPert.getItems().clear();
        }
    }
          

    @FXML
    private void enregistrerEntrepot(ActionEvent event) {
        if((matrifis.getText().length() > 0) && (adresseEnp.getText().length() > 0) && (telEnp.getText().length() > 0) &&(raisonSocialEnp.getText().length() > 0) && (adresseMail.getText().length() > 0)){                
            int input = JOptionPane.showConfirmDialog(null, "Voulez-vous enrégistrer l'inventaire?");
            if(input == 0){
                try{
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
                    catch (NumberFormatException e) {
                    showMessageDialog(null, "Les champs à remplir sont numériques", "Title", ERROR_MESSAGE);
                }       
            }
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
        int input = JOptionPane.showConfirmDialog(null, "Voulez-vous modifier?");
            if(input == 0){
        ServiceEntrepot se = new ServiceEntrepot();
        
        matrifis.setText(se.afficher().get(0).getMatriculeFiscale());
        adresseEnp.setText(se.afficher().get(0).getAdresse());
        raisonSocialEnp.setText(se.afficher().get(0).getRaisonSociale());
        adresseMail.setText(se.afficher().get(0).getAdresseMail());
        telEnp.setText(se.afficher().get(0).getNumeroTel());
            }
        
    }

  
    @FXML
    private void supprimerEntrepot(ActionEvent event) {
        int input = JOptionPane.showConfirmDialog(null, "Voulez-vous supprimer ?");
            if(input == 0){
                ServiceEntrepot se = new ServiceEntrepot();
                se.supprimer(new Entrepot(se.afficher().get(0).getMatriculeFiscale(),se.afficher().get(0).getAdresse(),se.afficher().get(0).getRaisonSociale(),se.afficher().get(0).getAdresseMail(),se.afficher().get(0).getNumeroTel()));
                mf.setText("");
                adr.setText("");
                rs.setText("");
                mail.setText("");
                tel.setText("");
            }
    }

    @FXML
    private void enregistrerEmplacement(ActionEvent event) {
        
        if((clasEmpl.getText().length() > 0) && (adrEmpl.getText().length() > 0) && (capStoEmpl.getText().length() > 0) && (qteEmpl.getText().length() > 0)){                
            int input = JOptionPane.showConfirmDialog(null, "Voulez-vous créer un emplacement?");
            if(input == 0){
                try{
                ServiceEntrepot se = new ServiceEntrepot();
                ServiceEmplacement sp = new ServiceEmplacement();
                Emplacement ca = new Emplacement(adrEmpl.getText(),Integer.valueOf(capStoEmpl.getText()),Integer.valueOf(qteEmpl.getText()),clasEmpl.getText(),se.afficher().get(0));        
                //ca.setEntrepot(se.afficher().get(0));
                sp.ajouter(ca);
                tabEmp.getItems().clear();
                actualiserTableEmp();
                }
                catch (NumberFormatException e) {
                    showMessageDialog(null, "Les champs à remplir sont numériques", "Title", ERROR_MESSAGE);
                }       
            }
        }
        
    }

    @FXML
    private void annulerEmplacement(ActionEvent event) {
        int input = JOptionPane.showConfirmDialog(null, "Voulez-vous annuler?");
            if(input == 0){
               
                    adrEmpl.clear();
                    capStoEmpl.clear();
                    qteEmpl.clear();
                    clasEmpl.clear();
            }
        
    }


    @FXML 
    private void enregistrerInventaire(ActionEvent event) {
        if((dateIv.getValue() != null) && (produitIv.getValue() != null) && (emplaceIv.getValue() != null) && (qteIv.getText().length() > 0)){                
            int input = JOptionPane.showConfirmDialog(null, "Voulez-vous enrégistrer l'inventaire?");
            if(input == 0){
                try{
                    ServiceProduitAchat sp = new ServiceProduitAchat();
                    ServiceInventaireStock si = new ServiceInventaireStock();
                    ServiceEmplacement se = new ServiceEmplacement();
                    int e = Math.abs(se.rechercher(se.afficher(), emplaceIv.getValue()).getQuantiteStocker()-Integer.valueOf(qteIv.getText()));
                    InventaireStock ca = new InventaireStock(sp.rechercher(sp.afficher(), produitIv.getValue()),se.rechercher(se.afficher(), emplaceIv.getValue()),dateIv.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")),Integer.valueOf(qteIv.getText()),e,se.rechercher(se.afficher(), emplaceIv.getValue()).getQuantiteStocker());        
                    si.ajouter(ca);
                    tabEmp.getItems().clear();
                    actualiserTableIv();
                    showMessageDialog(null, "Inventaire enrégistré", "SUCCESS", ERROR_MESSAGE);
                    }
                catch (NumberFormatException e) {
                    showMessageDialog(null, "Les champs quantié capacité sont numériques", "Title", ERROR_MESSAGE);
                }       
            }
        }else{
            showMessageDialog(null, "Remplissez tous les champs Svp!", "Title", ERROR_MESSAGE);
        }
    }

    @FXML
    private void AnnulerInventaire(ActionEvent event) {

    int input = JOptionPane.showConfirmDialog(null, "Voulez-vous annuler ?");
            if(input == 0){
        qteIv.clear();
            }
    }

    private void creerEmplacementPieChart(String adr){
        EmplaPieChart.getData().clear();
        ServiceEmplacement se = new ServiceEmplacement();
        int o = 0;
        int v = 0;
        
        for(int i = 0; i < se.afficher().size(); i++){
            if(se.afficher().get(i).getAdresse().equals(adr)) o += se.afficher().get(i).getQuantiteStocker();
            if(se.afficher().get(i).getAdresse().equals(adr)) v += se.afficher().get(i).getCapaciteStockage() - se.afficher().get(i).getQuantiteStocker();
        }
        PieChart.Data slice1 = new PieChart.Data("Occupé", o);
        PieChart.Data slice2 = new PieChart.Data("Vide", v);
        ;
        EmplaPieChart.getData().add(slice1);
        EmplaPieChart.getData().add(slice2);
        
        EmplaPieChart.setPrefSize(400, 300);
     
 
        EmplaPieChart.setLegendSide(Side.LEFT);
        EmplaPieChart.setStartAngle(30);
 
        final Label caption = new Label("");
        caption.setTextFill(Color.WHITE);
        caption.setStyle("-fx-font: 12 arial;");
        
        for (final PieChart.Data data : EmplaPieChart.getData()) {
            data.getNode().addEventHandler(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent e) {
                    caption.setTranslateX(e.getSceneX());
                    caption.setTranslateY(e.getSceneY());
 
                    caption.setText(String.valueOf(data.getPieValue()));
                }
            });
        }
        //PieBarAnchro.getChildren().addAll(pieChart, caption);
    }
    
    private void creerEmplacementBarChart(String clas){
        BarchartPerte.getData().clear();
        ServiceLignePerte slp = new ServiceLignePerte();
        CategoryAxis xAxis = new CategoryAxis();
        xAxis.setLabel("Raison de la perte");
 
        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Qté");
 

        XYChart.Series<String, Number> dataSeries1 = new XYChart.Series<String, Number>();
        dataSeries1.setName(clas);
        int v = 0;
        int i = 0;
        int h = 0;
        for(int j=0 ; j < slp.afficher().size(); j++){
            if(slp.afficher().get(j).getClass().equals(clas)){
                if(slp.afficher().get(j).getRaisonPerte().equals("Vol")) v += slp.afficher().get(j).getQuantite();
                if(slp.afficher().get(j).getRaisonPerte().equals("Inconnu")) i += slp.afficher().get(j).getQuantite();
                if(slp.afficher().get(j).getRaisonPerte().equals("Hors usage")) h += slp.afficher().get(j).getQuantite();
            }
        }
        
        dataSeries1.getData().add(new XYChart.Data<String, Number>("Vol", v));
        dataSeries1.getData().add(new XYChart.Data<String, Number>("Inconnu", i));
        dataSeries1.getData().add(new XYChart.Data<String, Number>("Hors usage", h));
        BarchartPerte.getData().add(dataSeries1);
        BarchartPerte.setTitle("Qté de pertes par raison");
        BarchartPerte.setPrefSize(400, 300);
        
      
    }
    @FXML
    private void Acceuil(ActionEvent event) {
        ServiceEmplacement se = new ServiceEmplacement();
        
        ObservableList<Node> childs = com.getChildren();
        Node anchor0 = childs.get(0);
        Node anchor1 = childs.get(1);
        Node anchor2 = childs.get(2);
        Node anchor3 = childs.get(3);
        Node anchor4 = childs.get(4);
        Node anchor5 = childs.get(5);
        Node anchor6 = childs.get(6);
        Node anchor7 = childs.get(7);
        Node anchor8 = childs.get(8);
        Node anchor9 = childs.get(9);
        /*Node anchor10 = childs.get(10);*/
            
        anchor0.setVisible(false);
        anchor1.setVisible(false);
        anchor2.setVisible(false);
        anchor3.setVisible(false);
        anchor4.setVisible(false);
        anchor5.setVisible(true);
        anchor6.setVisible(false);
           anchor7.setVisible(false);
           anchor8.setVisible(false);
           anchor9.setVisible(false);
           /*anchor10.setVisible(false);*/
        choixAdrEplace.getItems().clear();
        for(int i=0;i<se.afficher().size();i++){
            
            choixAdrEplace.getItems().add(se.afficher().get(i).getAdresse());           
        }
        for(int i=0;i<se.afficher().size();i++){
            choixClassPerte.getItems().clear();
            choixClassPerte.getItems().addAll("A","B","C");           
        }
           
    }
    


    @FXML
    private void VoirBarChartPerte(ActionEvent event) {
        
        creerEmplacementBarChart(choixClassPerte.getValue());
    }

    @FXML
    private void VoirPieChartEmplace(ActionEvent event) {
        
        creerEmplacementPieChart(choixAdrEplace.getValue());

    }
    public boolean verifyNumeric(JComponent input) {
        String text = ((JTextField) input).getText();
        return text.matches("\\d{10}");
        
    }
    private int showAlertWithHeaderText(String h,String m) {
        System.out.print("************************");
        Stage stage = (Stage) anchorCom.getScene().getWindow();
        Alert.AlertType type = Alert.AlertType.CONFIRMATION;
        Alert alert = new Alert(type, "");
        alert.initModality(Modality.APPLICATION_MODAL);
        alert.initOwner(stage);
        alert.getDialogPane().setContentText(m);
        alert.getDialogPane().setHeaderText(h);
        Optional<ButtonType> result = alert.showAndWait();
        if(result.get() == ButtonType.OK){
            return 1;
        }
        else if(result.get() == ButtonType.CANCEL){
            return 0;
        }
           return 2;
    }

    @FXML
    private void filtrerLesCmd(ActionEvent event) {
        if((dateDebutCmd.getValue() != null) && (triParFour.getValue().length() > 0) && (dateFinCmd.getValue() != null)){                
            int input = JOptionPane.showConfirmDialog(null, "Voulez-vous voir le tri?");
            if(input == 0){
                Date d1 = Date.from(dateDebutCmd.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
                Date d2 = Date.from(dateFinCmd.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
                actualiserTableCmdTri(d1,d2);    
            }
        }
        else{
            showMessageDialog(null, "Remplissez tous les champs", "Title", ERROR_MESSAGE);
        }         
        
    }
    public boolean compareDate(Date d1, Date d2, Date d3){
        
        long t2 = d2.getTime();
        long t1 = d1.getTime();
        long t3 = d3.getTime();
        return (t2 >= t1)&&(t2 <= t3);
          
    }

    @FXML
    private void choixDatePerte(ActionEvent event) {
        actualiserTableChoixPerte();
    }

    @FXML
    private void inventParPeriode(ActionEvent event) {
        if((dateDebInv.getValue() != null) && (dateFinInv.getValue() != null)){                
            int input = JOptionPane.showConfirmDialog(null, "Voulez-vous voir les inventaires correspondants?");
            if(input == 0){
                Date d1 = Date.from(dateDebInv.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
                Date d2 = Date.from(dateFinInv.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
                actualiserTableInvTri(d1,d2);    
            }
        }
        else{
            showMessageDialog(null, "Remplissez tous les champs Svp", "Title", ERROR_MESSAGE);
        } 
    }

    @FXML
    private void imprimerInventaires(ActionEvent event) {
        List<Invent> lc = new ArrayList<>();
        if((dateDebInv.getValue() != null) && (dateFinInv.getValue() != null)){                
            int input = JOptionPane.showConfirmDialog(null, "Voulez-vous voir les inventaires correspondants?");
            if(input == 0){
                Date d1 = Date.from(dateDebInv.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
                Date d2 = Date.from(dateFinInv.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
                DateFormat format = new SimpleDateFormat("yyyy-MM-DD",Locale.ENGLISH);
                ServiceInventaireStock sca = new ServiceInventaireStock();
                List<InventaireStock> lsca = new ArrayList<>();
                lsca = sca.afficher();
                
                for(int i = 0 ; i < sca.afficher().size(); i++){
                    try {
                        Date date = format.parse(lsca.get(i).getDateCreation());
                    if(compareDate(d1,date,d2)){
                        Invent c = new Invent(lsca.get(i).getId(),lsca.get(i).getProduitAchat().getLibelle(),lsca.get(i).getEmplacement().getAdresse(),lsca.get(i).getDateCreation(),lsca.get(i).getQunatiteInventiare(),lsca.get(i).getEcart(),lsca.get(i).getQuantiteTheorique());          
                        lc.add(c);
                    }
                    } catch (ParseException ex) {
                        ex.printStackTrace();
                    }

                }   
            }
        }
        else{
            showMessageDialog(null, "Remplissez tous les champs Svp", "Title", ERROR_MESSAGE);
        } 
      
        try {
            CSVWriter csvWriter = new CSVWriter(new FileWriter("C:\\Users\\guiforodrigue\\Desktop\\PDF\\new.csv"));
            List<String[]> theRows = new ArrayList<>();
            String[] header = new String[]{"NUMERO","PRODUIT","EMPLACEMENT","DATE","QTE INV","ECART","QTE TH"};
            theRows.add(header);
            for(int i=0; i < lc.size(); i++){
                String[] head = new String[]{String.valueOf(lc.get(i).getId()),lc.get(i).getProduitAchat(),lc.get(i).getEmplacement(),lc.get(i).getDateCreation(),String.valueOf(lc.get(i).getQuantiteTheorique()),String.valueOf(lc.get(i).getQunatiteInventiare()),String.valueOf(lc.get(i).getEcart())};
                theRows.add(head);
            }
            csvWriter.writeAll(theRows);
            csvWriter.close();
            Desktop.getDesktop().open(new File("C:\\Users\\guiforodrigue\\Desktop\\PDF\\new.csv"));
        } catch (IOException ex) {
            ex.getStackTrace();
        }
    }

    @FXML
    private void chercherEmplacementf(ActionEvent event) {
        ServiceEmplacement se = new ServiceEmplacement();
        if(choiEmplf.getValue() != null){
            for(int i=0; i < se.afficher().size(); i++){
                if(se.afficher().get(i).getAdresse().equals(choiEmplf.getValue())){
                    titreInfoEmpl.setText("ADRESSE : "+se.afficher().get(i).getAdresse());
                    classef.setText(se.afficher().get(i).getClasse());
                    capacitef.setText(Integer.toString(se.afficher().get(i).getCapaciteStockage()));
                    float f = (se.afficher().get(i).getQuantiteStocker() / se.afficher().get(i).getCapaciteStockage()) * 100;
                    percentOccup.setText("Occupé à "+ Float.toString(f)+" %");
                }
            }
        }else{
            showMessageDialog(null, "Chosisez l'adresse Svp", "Title", ERROR_MESSAGE);
        }
        
    }
}