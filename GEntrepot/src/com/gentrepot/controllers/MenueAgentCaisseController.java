/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gentrepot.controllers;

import animatefx.animation.ZoomIn;
import com.gentrepot.models.CommandeDApprovisionnement;
import com.gentrepot.models.FactureAchat;
import com.gentrepot.models.FactureVente;
import com.gentrepot.models.Fournisseur;
import com.gentrepot.models.InventaireCaisse;
import com.gentrepot.models.LettreDeRelance;
import com.gentrepot.models.LigneCommandeDApprovisionnement;
import com.gentrepot.models.RecouvrementClientCheque;
import com.gentrepot.models.RecouvrementClientEspece;
import com.gentrepot.models.ReglementFournisseurCheque;
import com.gentrepot.models.ReglementFournisseurEspece;
import com.gentrepot.services.MailService;
import com.gentrepot.services.ServiceFactureAchat;
import com.gentrepot.services.ServiceFactureVente;
import com.gentrepot.services.ServiceInventaireCaisse;
import com.gentrepot.services.ServiceLettreDeRelance;
import com.gentrepot.services.ServiceRecouvrementClientCheque;
import com.gentrepot.services.ServiceRecouvrementClientEspece;
import com.gentrepot.services.ServiceReglementFournisseurCheque;
import com.gentrepot.services.ServiceReglementFournisseurEspece;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.Duration;
import javax.management.Notification;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author oussema
 */
public class MenueAgentCaisseController implements Initializable {

    ObservableList<LigneCommandeDApprovisionnement> listeLigneCommandeDA = FXCollections.observableArrayList();
    ObservableList<InventaireCaisse> listInventaire = FXCollections.observableArrayList();
    ObservableList<LettreDeRelance> listeLettreDeRelance = FXCollections.observableArrayList();
    ObservableList<FactureVente> listeFactureVenteLettre = FXCollections.observableArrayList();
    ObservableList<FactureAchat> listeFactureAchat = FXCollections.observableArrayList();
    ObservableList<FactureVente> ListeFactureVente = FXCollections.observableArrayList();
    ObservableList<RecouvrementClientEspece> ListeRecouvrementClientEspece = FXCollections.observableArrayList();
    ObservableList<RecouvrementClientCheque> ListeRecouvrementClientCheque = FXCollections.observableArrayList();
    ObservableList<ReglementFournisseurEspece> listeReglementFournisseurEspece = FXCollections.observableArrayList();
    ObservableList<ReglementFournisseurCheque> listeReglementFournisseurCheque = FXCollections.observableArrayList();
    ObservableList<CommandeDApprovisionnement> listeCommandeA = FXCollections.observableArrayList();
    ServiceInventaireCaisse serviceInventaireCaisse = new ServiceInventaireCaisse();
    ServiceRecouvrementClientCheque serviceRecouvrementClientCheque = new ServiceRecouvrementClientCheque();
    ServiceRecouvrementClientEspece serviceRecouvrementClientEspece = new ServiceRecouvrementClientEspece();
    ServiceLettreDeRelance serviceLettreDeRelance = new ServiceLettreDeRelance();
    ServiceFactureAchat serviceFactureAchat = new ServiceFactureAchat();
    ServiceFactureVente serviceFactureVente = new ServiceFactureVente();

    ServiceReglementFournisseurCheque serviceReglementFournisseurCheque = new ServiceReglementFournisseurCheque();
    ServiceReglementFournisseurEspece serviceReglementFournisseurEspece = new ServiceReglementFournisseurEspece();
    double totalT = 0;

    private InventaireCaisse inventaireCaisseG = null;
    private LettreDeRelance lettreDeRelanceG = null;
    private CommandeDApprovisionnement commandeDApprovisionnementG = null;
    private FactureVente factureVenteG = null;
    private FactureAchat factureAchatG = null;
    private RecouvrementClientEspece recouvrementClientEspeceG = null;
    private RecouvrementClientCheque recouvrementClientChequeG = null;
    private ReglementFournisseurCheque reglementFournisseurChequeG = null;
    private ReglementFournisseurEspece reglementFournisseurEspeceG = null;
    Stage primaryStage = new Stage();

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
    private Pane paneInvetaireC;
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
    @FXML
    private Pane paneAjouterLettreDeRelance;
    @FXML
    private TableView<FactureVente> tableVLettreListeFactureClient;
    @FXML
    private TableColumn<FactureVente, Integer> tableVLettreListeFactureClientNumeroF;
    @FXML
    private TableColumn<FactureVente, Date> tableVLettreListeFactureClientDateCreation;
    @FXML
    private TableColumn<FactureVente, Date> tableVLettreListeFactureClientDatePay;
    @FXML
    private TableColumn<FactureVente, String> tableVLettreListeFactureClientEtat;
    @FXML
    private TableColumn<FactureVente, Double> tableVLettreListeFactureClientTotal;
    @FXML
    private TableColumn<FactureVente, String> tableVLettreListeFactureClientAction;
    @FXML
    private JFXButton btnValiderAjouterLettre;
    @FXML
    private TableColumn<LettreDeRelance, CheckBox> tableViewLettreDeRelanceCAction;
    @FXML
    private TableColumn<InventaireCaisse, CheckBox> tableViewInventaireAction;
    @FXML
    private TableView<FactureAchat> tableVListeFactureFournisseur;
    @FXML
    private JFXButton btnAjouterFactureFournisseur;
    @FXML
    private Pane paneGFactureF;
    @FXML
    private TableColumn<FactureAchat, Integer> tableVListeFactureFournisseurNumeroF;
    @FXML
    private TableColumn<FactureAchat, Date> tableVListeFactureFournisseurDateC;
    @FXML
    private TableColumn<FactureAchat, Double> tableVListeFactureFournisseurDateE;
    @FXML
    private TableColumn<FactureAchat, Double> tableVListeFactureFournisseurTTC;
    @FXML
    private TableColumn<FactureAchat, Double> tableVListeFactureFournisseurEtat;
    @FXML
    private TableColumn<FactureAchat, Double> tableVListeFactureFournisseurTPayer;
    @FXML
    private TableColumn<FactureAchat, Double> tableVListeFactureFournisseurRestePayer;
    @FXML
    private Pane paneAjouterFacture;
    @FXML
    private TableView<CommandeDApprovisionnement> paneAjouterFactureTableVListeCommande;
    @FXML
    private TableColumn<CommandeDApprovisionnement, Integer> paneAjouterFactureTableVListeCommandeNumeroC;
    @FXML
    private TableColumn<CommandeDApprovisionnement, Date> paneAjouterFactureTableVListeCommandeDateC;
    @FXML
    private TableColumn<CommandeDApprovisionnement, String> paneAjouterFactureTableVListeCommandeEtatC;
    @FXML
    private TableColumn<CommandeDApprovisionnement, Double> paneAjouterFactureTableVListeCommandeTotalC;
    @FXML
    private TableColumn<CommandeDApprovisionnement, Fournisseur> paneAjouterFactureTableVListeCommandeFournisseur;
    @FXML
    private Pane paneEnregistrerFacture;
    @FXML
    private JFXTextField textFNumeroF;
    @FXML
    private JFXDatePicker textFDateEchaillanceFacture;
    @FXML
    private JFXTextField textFFraisTransport;
    @FXML
    private JFXTextField textFTimbreFiscale;
    @FXML
    private JFXTextField textFTTC;
    @FXML
    private JFXButton btnValiderAjouterFactureAchat;
    @FXML
    private Pane paneGRecouvrementClient;
    @FXML
    private Pane paneGRecouvrementClientEspece;
    @FXML
    private JFXButton btnAjouterRecouvrementCEspece;
    @FXML
    private JFXButton btnModiferRecouvrementCEspece;
    @FXML
    private JFXButton btnSupprimerRecouvrementC;
    @FXML
    private Pane paneAjouterRecouvrementClientEspece;
    @FXML
    private TableView<FactureVente> tableViewAjouterRecouvrementCEListeFacture;
    @FXML
    private TableColumn<FactureVente, Double> tableViewAjouterRecouvrementCEListeFactureNumeroF;
    @FXML
    private TableColumn<FactureVente, Double> tableViewAjouterRecouvrementCEListeFactureDateC;
    @FXML
    private TableColumn<FactureVente, Double> tableViewAjouterRecouvrementCEListeFactureEtat;
    @FXML
    private TableColumn<FactureVente, Double> tableViewAjouterRecouvrementCEListeFactureTotalTTC;
    @FXML
    private TableColumn<FactureVente, Double> tableViewAjouterRecouvrementCEListeFactureTotalPayer;
    @FXML
    private TableColumn<FactureVente, Double> tableViewAjouterRecouvrementCEListeFactureResteAPayer;
    @FXML
    private JFXButton btnValiderAjouterRecouvrementCEspece;
    @FXML
    private Pane paneGrecouvrementClientcheque;
    @FXML
    private JFXTextField textFAjouterRCEMontant;
    @FXML
    private JFXButton btnAnnulerAjouterRecouvrementCEspece;
    @FXML
    private TableView<RecouvrementClientEspece> tableVGRecouvrementClientEspece;
    @FXML
    private TableColumn<RecouvrementClientEspece, Date> tableVGRecouvrementClientEspeceDC;
    @FXML
    private TableColumn<RecouvrementClientEspece, Double> tableVGRecouvrementClientEspeceMontant;
    @FXML
    private TableColumn<RecouvrementClientEspece, Integer> tableVGRecouvrementClientEspeceFacture;
    @FXML
    private TableColumn<RecouvrementClientEspece, CheckBox> tableVGRecouvrementClientEspeceAction;
    @FXML
    private Pane paneModifierRecouvrementCEspece;
    @FXML
    private TableView<FactureVente> tableViewModiferRecouvrementClientEspeceListeFacture;
    @FXML
    private TableColumn<FactureVente, Integer> tableViewModiferRecouvrementClientEspeceListeFactureNumeroF;
    @FXML
    private TableColumn<FactureVente, Date> tableViewModiferRecouvrementClientEspeceListeFactureDC;
    @FXML
    private TableColumn<FactureVente, String> tableViewModiferRecouvrementClientEspeceListeFactureEtat;
    @FXML
    private TableColumn<FactureVente, Double> tableViewModiferRecouvrementClientEspeceListeFactureTTc;
    @FXML
    private TableColumn<FactureVente, Double> tableViewModiferRecouvrementClientEspeceListeFactureTP;
    @FXML
    private TableColumn<FactureVente, Double> tableViewModiferRecouvrementClientEspeceListeFactureRP;
    @FXML
    private JFXTextField textFModifierRecouvrementCEspece;
    @FXML
    private JFXButton btnModifierRecouvrementCEspece;
    @FXML
    private JFXButton btnAnnulerAjouterRecouvrementCEspece1;
    @FXML
    private TableView<RecouvrementClientCheque> tableViewGRecouvrementClientCheque;
    @FXML
    private TableColumn<RecouvrementClientCheque, Date> tableViewGRecouvrementClientChequeDC;
    @FXML
    private TableColumn<RecouvrementClientCheque, Double> tableViewGRecouvrementClientChequeMontant;
    @FXML
    private TableColumn<RecouvrementClientCheque, Date> tableViewGRecouvrementClientChequeDateCheque;
    @FXML
    private TableColumn<RecouvrementClientCheque, Integer> tableViewGRecouvrementClientChequeNumeroCheque;
    @FXML
    private TableColumn<RecouvrementClientCheque, Integer> tableViewGRecouvrementClientChequeFactureVente;
    @FXML
    private TableColumn<RecouvrementClientCheque, CheckBox> tableViewGRecouvrementClientChequeAction;
    @FXML
    private JFXButton btnAjouterRecouvrementCheque;
    @FXML
    private JFXButton btnModifierRecouvrementCheque;
    @FXML
    private JFXButton btnSupprimerRecouvrementCheque;
    @FXML
    private Pane paneAjouterRecouvrementClientCheque;
    @FXML
    private TableView<FactureVente> tableViewListeFactureAjouterRecouvrementClientCheque;
    @FXML
    private TableColumn<FactureVente, Integer> tableViewListeFactureAjouterRecouvrementClientChequeNumeroF;
    @FXML
    private TableColumn<FactureVente, Date> tableViewListeFactureAjouterRecouvrementClientChequeDateC;
    @FXML
    private TableColumn<FactureVente, String> tableViewListeFactureAjouterRecouvrementClientChequeEtat;
    @FXML
    private TableColumn<FactureVente, Double> tableViewListeFactureAjouterRecouvrementClientChequeTTC;
    @FXML
    private TableColumn<FactureVente, Double> tableViewListeFactureAjouterRecouvrementClientChequeTP;
    @FXML
    private TableColumn<FactureVente, Double> tableViewListeFactureAjouterRecouvrementClientChequeRSP;
    @FXML
    private JFXButton btnAnnulerAjouterRecouvrementCEspece2;
    @FXML
    private JFXTextField textFAjouterRecouvrementChequeMontant;
    @FXML
    private JFXButton btnValiderAjouterRecouvrementClientCheque;
    @FXML
    private ImageView btnAnnulerAjouterRecouvrementClientCheque;
    @FXML
    private JFXDatePicker dateChequeAjouterRecouvrementClientCheque;
    @FXML
    private JFXTextField textFAjouterRecouvrementClientChequeNumeroCheque;
    @FXML
    private Pane paneModifierRecouvrementCheque;
    @FXML
    private TableView<FactureVente> tableViewListeFactureModifierRecouvrementClientCheque;
    @FXML
    private TableColumn<FactureVente, Integer> tableViewListeFactureModifierRecouvrementClientChequeNumeroF;
    @FXML
    private TableColumn<FactureVente, Date> tableViewListeFactureModifierRecouvrementClientChequeDC;
    @FXML
    private TableColumn<FactureVente, String> tableViewListeFactureModifierRecouvrementClientChequeEtat;
    @FXML
    private TableColumn<FactureVente, Double> tableViewListeFactureModifierRecouvrementClientChequeTTC;
    @FXML
    private TableColumn<FactureVente, Double> tableViewListeFactureModifierRecouvrementClientChequeTP;
    @FXML
    private TableColumn<FactureVente, Double> tableViewListeFactureModifierRecouvrementClientChequeRP;
    @FXML
    private JFXTextField textFModiiferRecouvrementChequeMontant;
    @FXML
    private JFXButton btnValiderModifierRecouvrementClientCheque;
    @FXML
    private ImageView btnAnnulerAjouterRecouvrementClientCheque1;
    @FXML
    private JFXTextField textFModifierRecouvrementClientChequeNumeroCheque;
    @FXML
    private JFXDatePicker dateChequeModifierRecouvrementClientCheque;
    @FXML
    private JFXButton btnAnnulerModifierRecouvrementCheque;
    @FXML
    private Pane paneGReglementFournisseur;
    @FXML
    private JFXButton btnAnnulerAjouterRecouvrementCEspece11;
    @FXML
    private JFXButton btnAnnulerAjouterRecouvrementCEspece21;
    @FXML
    private ImageView btnAnnulerAjouterRecouvrementClientCheque11;
    @FXML
    private Pane paneGReglementFournisseurEspece;
    @FXML
    private TableView<ReglementFournisseurEspece> tableViewGreglementEspece;
    @FXML
    private TableColumn<ReglementFournisseurEspece, Date> tableViewGreglementEspeceDC;
    @FXML
    private TableColumn<ReglementFournisseurEspece, Double> tableViewGreglementEspeceMontant;
    @FXML
    private TableColumn<ReglementFournisseurEspece, Integer> tableViewGreglementEspeceFacture;
    @FXML
    private TableColumn<ReglementFournisseurEspece, CheckBox> tableViewGreglementEspeceAction;
    @FXML
    private JFXButton btnAjouterReglementEspece;
    @FXML
    private JFXButton btnModifierReglementEspece;
    @FXML
    private JFXButton btnSupprimerReglementEspece;
    @FXML
    private Pane paneGReglementCheque;
    @FXML
    private TableView<ReglementFournisseurCheque> tableViewGReglementCheque;
    @FXML
    private TableColumn<ReglementFournisseurCheque, Date> tableViewGReglementChequeDateC;
    @FXML
    private TableColumn<ReglementFournisseurCheque, Double> tableViewGReglementChequeMontant;
    @FXML
    private TableColumn<ReglementFournisseurCheque, Date> tableViewGReglementChequeDCheque;
    @FXML
    private TableColumn<ReglementFournisseurCheque, Integer> tableViewGReglementChequeNumeroCheque;
    @FXML
    private TableColumn<ReglementFournisseurCheque, Integer> tableViewGReglementChequeFacture;
    @FXML
    private TableColumn<ReglementFournisseurCheque, CheckBox> tableViewGReglementChequeAction;
    @FXML
    private JFXButton btnAjouterReglementCheque;
    @FXML
    private JFXButton btnModifierReglementCheque;
    @FXML
    private JFXButton btnSupprimerReglementcheque;
    @FXML
    private Pane paneajouterReglementEspece;
    @FXML
    private JFXTextField textFAjouterReglementEspece;
    @FXML
    private JFXButton btnAnnulerAjouterReglementEspece;
    @FXML
    private TableView<FactureAchat> tableViewAjouterReglementEspeceListeFactureAchat;
    @FXML
    private TableColumn<FactureAchat, Integer> tableViewAjouterReglementEspeceListeFactureAchatNF;
    @FXML
    private TableColumn<FactureAchat, Date> tableViewAjouterReglementEspeceListeFactureAchatDC;
    @FXML
    private TableColumn<FactureAchat, String> tableViewAjouterReglementEspeceListeFactureAchatEtat;
    @FXML
    private TableColumn<FactureAchat, Double> tableViewAjouterReglementEspeceListeFactureAchatTTC;
    @FXML
    private TableColumn<FactureAchat, Double> tableViewAjouterReglementEspeceListeFactureAchatTP;
    @FXML
    private TableColumn<FactureAchat, Double> tableViewAjouterReglementEspeceListeFactureAchatRP;
    @FXML
    private Pane paneModifierReglementEspece;
    @FXML
    private TableView<FactureAchat> tableViewModifierReglementEspeceListeFactureAchat;
    @FXML
    private TableColumn<FactureAchat, Integer> tableViewModifierReglementEspeceListeFactureAchatNF;
    @FXML
    private TableColumn<FactureAchat, Date> tableViewModifierReglementEspeceListeFactureAchatDC;
    @FXML
    private TableColumn<FactureAchat, String> tableViewModifierReglementEspeceListeFactureAchatEtat;
    @FXML
    private TableColumn<FactureAchat, Double> tableViewModifierReglementEspeceListeFactureAchatTTc;
    @FXML
    private TableColumn<FactureAchat, Double> tableViewModifierReglementEspeceListeFactureAchatTP;
    @FXML
    private TableColumn<FactureAchat, Double> tableViewModifierReglementEspeceListeFactureAchatRP;
    @FXML
    private JFXTextField textFModifierReglementEspece;
    @FXML
    private Pane paneAjouterReglementCheque;
    @FXML
    private TableView<FactureAchat> tableViewAjouterRegChequeListeFacture;
    @FXML
    private TableColumn<FactureAchat, Integer> tableViewAjouterRegChequeListeFactureNF;
    @FXML
    private TableColumn<FactureAchat, Date> tableViewAjouterRegChequeListeFactureDC;
    @FXML
    private TableColumn<FactureAchat, String> tableViewAjouterRegChequeListeFactureEtat;
    @FXML
    private TableColumn<FactureAchat, Double> tableViewAjouterRegChequeListeFactureTTC;
    @FXML
    private TableColumn<FactureAchat, Double> tableViewAjouterRegChequeListeFactureTP;
    @FXML
    private TableColumn<FactureAchat, Double> tableViewAjouterRegChequeListeFactureRP;
    @FXML
    private JFXTextField textAjouterRegChequeMontant;
    @FXML
    private JFXButton btnValiderAjouterReglementCheque;
    @FXML
    private JFXTextField textfAjouterNumeroReglementCheque;
    @FXML
    private JFXDatePicker dateChequeAjouterReglCheque;
    @FXML
    private TableView<FactureAchat> tableViewListeFactureModifierRegCheque;
    @FXML
    private TableColumn<FactureAchat, Double> tableViewListeFactureModifierRegChequeNF;
    @FXML
    private TableColumn<FactureAchat, Date> tableViewListeFactureModifierRegChequeDC;
    @FXML
    private TableColumn<FactureAchat, Double> tableViewListeFactureModifierRegChequeEtat;
    @FXML
    private TableColumn<FactureAchat, Double> tableViewListeFactureModifierRegChequeTTC;
    @FXML
    private TableColumn<FactureAchat, Double> tableViewListeFactureModifierRegChequeTP;
    @FXML
    private TableColumn<FactureAchat, Double> tableViewListeFactureModifierRegChequeRP;
    @FXML
    private JFXTextField textMontantModifierRegCheque;
    @FXML
    private JFXTextField textFNumeroModiferRegCheque;
    @FXML
    private JFXDatePicker dateChequeModifierRegCheque;
    @FXML
    private JFXButton btnModifierRegCheque;
    @FXML
    private JFXButton btnAnnulerModifierRegCheque;
    @FXML
    private Pane paneModifierReglementCheque;
    @FXML
    private JFXButton btnAnnulerAjouterFacture;
    @FXML
    private TableView<LigneCommandeDApprovisionnement> tableViewListeLigneAjouterFacture;
    @FXML
    private TableColumn<LigneCommandeDApprovisionnement, String> tableViewListeLigneAjouterFactureProduit;
    @FXML
    private TableColumn<LigneCommandeDApprovisionnement, Double> tableViewListeLigneAjouterFacturePrix;
    @FXML
    private TableColumn<LigneCommandeDApprovisionnement, Integer> tableViewListeLigneAjouterFactureQTe;
    @FXML
    private TableColumn<LigneCommandeDApprovisionnement, Double> tableViewListeLigneAjouterFactureTVA;
    @FXML
    private TableColumn<LigneCommandeDApprovisionnement, Double> tableViewListeLigneAjouterFactureTotal;
    @FXML
    private JFXTextField textFTotalCommande;
    @FXML
    private Pane paneDetailFacture;
    @FXML
    private JFXTextField textFNumeroFD;
    @FXML
    private JFXTextField textFFraisTransportD;
    @FXML
    private JFXTextField textFTimbreFiscaleD;
    @FXML
    private JFXTextField textFTTCD;
    @FXML
    private JFXDatePicker textFDateEchaillanceFactureD;
    @FXML
    private JFXButton btnRetourDetailFacture;
    @FXML
    private TableView<LigneCommandeDApprovisionnement> tableViewListeLigneAjouterFactureDetail;
    @FXML
    private TableColumn<LigneCommandeDApprovisionnement, String> tableViewListeLigneAjouterFactureProduitD;
    @FXML
    private TableColumn<LigneCommandeDApprovisionnement, Double> tableViewListeLigneAjouterFacturePrixD;
    @FXML
    private TableColumn<LigneCommandeDApprovisionnement, Integer> tableViewListeLigneAjouterFactureQTeD;
    @FXML
    private TableColumn<LigneCommandeDApprovisionnement, Double> tableViewListeLigneAjouterFactureTVAD;
    @FXML
    private TableColumn<LigneCommandeDApprovisionnement, Double> tableViewListeLigneAjouterFactureTotalD;
    @FXML
    private JFXTextField textFTotalCommandeDetail;
    @FXML
    private PieChart pieChartRecouvrement;
    @FXML
    private PieChart pieChartReglement;
    @FXML
    private JFXButton btnEnvoeyMailLettre;
    @FXML
    private JFXTextField textFAdresseMailClient;
    @FXML
    private ImageView imageFermer;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        afficheStatReglement();
        afficheStatRecouvrement();

        /*
        *table view liste ligne commande deatil facture
         */
        tableViewListeLigneAjouterFactureDetail.setItems(listeLigneCommandeDA);

        tableViewListeLigneAjouterFactureProduitD.setCellValueFactory(new PropertyValueFactory<>("refP"));
        tableViewListeLigneAjouterFacturePrixD.setCellValueFactory(new PropertyValueFactory<>("prix"));
        tableViewListeLigneAjouterFactureQTeD.setCellValueFactory(new PropertyValueFactory<>("quantite"));
        tableViewListeLigneAjouterFactureTVAD.setCellValueFactory(new PropertyValueFactory<>("tva"));
        tableViewListeLigneAjouterFactureTotalD.setCellValueFactory(new PropertyValueFactory<>("total"));

        /*
        *table view liste ligne commande ajouter facture
         */
        tableViewListeLigneAjouterFacture.setItems(listeLigneCommandeDA);

        tableViewListeLigneAjouterFactureProduit.setCellValueFactory(new PropertyValueFactory<>("refP"));
        tableViewListeLigneAjouterFacturePrix.setCellValueFactory(new PropertyValueFactory<>("prix"));
        tableViewListeLigneAjouterFactureQTe.setCellValueFactory(new PropertyValueFactory<>("quantite"));
        tableViewListeLigneAjouterFactureTVA.setCellValueFactory(new PropertyValueFactory<>("tva"));
        tableViewListeLigneAjouterFactureTotal.setCellValueFactory(new PropertyValueFactory<>("total"));

        /*
        *table view liste facture pane modifier reglement Reglement cheque
         */
        tableViewListeFactureModifierRegCheque.setItems(listeFactureAchat);

        tableViewListeFactureModifierRegChequeNF.setCellValueFactory(new PropertyValueFactory<>("numeroF"));
        tableViewListeFactureModifierRegChequeDC.setCellValueFactory(new PropertyValueFactory<>("dateCreation"));
        tableViewListeFactureModifierRegChequeEtat.setCellValueFactory(new PropertyValueFactory<>("etat"));
        tableViewListeFactureModifierRegChequeTTC.setCellValueFactory(new PropertyValueFactory<>("totalTTC"));
        tableViewListeFactureModifierRegChequeTP.setCellValueFactory(new PropertyValueFactory<>("totalPaye"));
        tableViewListeFactureModifierRegChequeRP.setCellValueFactory(new PropertyValueFactory<>("restePaye"));

        /*
        *table view pane ajouter reglement cheque liste facture
         */
        tableViewAjouterRegChequeListeFacture.setItems(listeFactureAchat);

        tableViewAjouterRegChequeListeFactureNF.setCellValueFactory(new PropertyValueFactory<>("numeroF"));
        tableViewAjouterRegChequeListeFactureDC.setCellValueFactory(new PropertyValueFactory<>("dateCreation"));
        tableViewAjouterRegChequeListeFactureEtat.setCellValueFactory(new PropertyValueFactory<>("etat"));
        tableViewAjouterRegChequeListeFactureTTC.setCellValueFactory(new PropertyValueFactory<>("totalTTC"));
        tableViewAjouterRegChequeListeFactureTP.setCellValueFactory(new PropertyValueFactory<>("totalPaye"));
        tableViewAjouterRegChequeListeFactureRP.setCellValueFactory(new PropertyValueFactory<>("restePaye"));

        /*
        *table view pane modidifier reglement espece
         */
        tableViewModifierReglementEspeceListeFactureAchat.setItems(listeFactureAchat);

        tableViewModifierReglementEspeceListeFactureAchatNF.setCellValueFactory(new PropertyValueFactory<>("numeroF"));
        tableViewModifierReglementEspeceListeFactureAchatDC.setCellValueFactory(new PropertyValueFactory<>("dateCreation"));
        tableViewModifierReglementEspeceListeFactureAchatEtat.setCellValueFactory(new PropertyValueFactory<>("etat"));
        tableViewModifierReglementEspeceListeFactureAchatTTc.setCellValueFactory(new PropertyValueFactory<>("totalTTC"));
        tableViewModifierReglementEspeceListeFactureAchatTP.setCellValueFactory(new PropertyValueFactory<>("totalPaye"));
        tableViewModifierReglementEspeceListeFactureAchatRP.setCellValueFactory(new PropertyValueFactory<>("restePaye"));

        /*
        *table view liste facture achat pane ajouter reglement fournisseur espece 
         */
        tableViewAjouterReglementEspeceListeFactureAchat.setItems(listeFactureAchat);

        tableViewAjouterReglementEspeceListeFactureAchatNF.setCellValueFactory(new PropertyValueFactory<>("numeroF"));
        tableViewAjouterReglementEspeceListeFactureAchatDC.setCellValueFactory(new PropertyValueFactory<>("dateCreation"));
        tableViewAjouterReglementEspeceListeFactureAchatEtat.setCellValueFactory(new PropertyValueFactory<>("etat"));
        tableViewAjouterReglementEspeceListeFactureAchatTTC.setCellValueFactory(new PropertyValueFactory<>("totalTTC"));
        tableViewAjouterReglementEspeceListeFactureAchatTP.setCellValueFactory(new PropertyValueFactory<>("totalPaye"));
        tableViewAjouterReglementEspeceListeFactureAchatRP.setCellValueFactory(new PropertyValueFactory<>("restePaye"));

        /*
        *table view pane GReglement espece
         */
        tableViewGreglementEspeceDC.setCellValueFactory(new PropertyValueFactory<>("dateCreation"));
        tableViewGreglementEspeceMontant.setCellValueFactory(new PropertyValueFactory<>("montant"));
        tableViewGreglementEspeceFacture.setCellValueFactory(new PropertyValueFactory<>("numeroF"));
        tableViewGreglementEspeceAction.setCellValueFactory(new PropertyValueFactory<>("checkBox"));
        tableViewGreglementEspece.setItems(listeReglementFournisseurEspece);

        /*
        *table view pane GReglement Cheque
         */
        tableViewGReglementChequeDateC.setCellValueFactory(new PropertyValueFactory<>("dateCreation"));
        tableViewGReglementChequeMontant.setCellValueFactory(new PropertyValueFactory<>("montant"));
        tableViewGReglementChequeDCheque.setCellValueFactory(new PropertyValueFactory<>("dateCheque"));
        tableViewGReglementChequeNumeroCheque.setCellValueFactory(new PropertyValueFactory<>("numeroCheque"));
        tableViewGReglementChequeFacture.setCellValueFactory(new PropertyValueFactory<>("numeroF"));
        tableViewGReglementChequeAction.setCellValueFactory(new PropertyValueFactory<>("checkBox"));
        tableViewGReglementCheque.setItems(listeReglementFournisseurCheque);

        /*
        *tTable view liste facture pane modifier recouvrement client cheque
         */
        tableViewListeFactureModifierRecouvrementClientCheque.setItems(ListeFactureVente);

        tableViewListeFactureModifierRecouvrementClientChequeNumeroF.setCellValueFactory(new PropertyValueFactory<>("numeroF"));
        tableViewListeFactureModifierRecouvrementClientChequeDC.setCellValueFactory(new PropertyValueFactory<>("dateCreation"));
        tableViewListeFactureModifierRecouvrementClientChequeEtat.setCellValueFactory(new PropertyValueFactory<>("etat"));
        tableViewListeFactureModifierRecouvrementClientChequeTTC.setCellValueFactory(new PropertyValueFactory<>("totalTTC"));
        tableViewListeFactureModifierRecouvrementClientChequeTP.setCellValueFactory(new PropertyValueFactory<>("totalPaye"));
        tableViewListeFactureModifierRecouvrementClientChequeRP.setCellValueFactory(new PropertyValueFactory<>("restePaye"));

        /*
        *tabele view liste facture pane ajouter recouvrement client Cheque
         */
        tableViewListeFactureAjouterRecouvrementClientCheque.setItems(ListeFactureVente);

        tableViewListeFactureAjouterRecouvrementClientChequeNumeroF.setCellValueFactory(new PropertyValueFactory<>("numeroF"));
        tableViewListeFactureAjouterRecouvrementClientChequeDateC.setCellValueFactory(new PropertyValueFactory<>("dateCreation"));
        tableViewListeFactureAjouterRecouvrementClientChequeEtat.setCellValueFactory(new PropertyValueFactory<>("etat"));
        tableViewListeFactureAjouterRecouvrementClientChequeTTC.setCellValueFactory(new PropertyValueFactory<>("totalTTC"));
        tableViewListeFactureAjouterRecouvrementClientChequeTP.setCellValueFactory(new PropertyValueFactory<>("totalPaye"));
        tableViewListeFactureAjouterRecouvrementClientChequeRSP.setCellValueFactory(new PropertyValueFactory<>("restePaye"));

        /**
         * table view affiche liste recouvrement client cheque
         */
        tableViewGRecouvrementClientChequeDC.setCellValueFactory(new PropertyValueFactory<>("dateCreation"));
        tableViewGRecouvrementClientChequeMontant.setCellValueFactory(new PropertyValueFactory<>("montant"));
        tableViewGRecouvrementClientChequeDateCheque.setCellValueFactory(new PropertyValueFactory<>("dateCheque"));
        tableViewGRecouvrementClientChequeNumeroCheque.setCellValueFactory(new PropertyValueFactory<>("numeroCheque"));
        tableViewGRecouvrementClientChequeFactureVente.setCellValueFactory(new PropertyValueFactory<>("numeroF"));
        tableViewGRecouvrementClientChequeAction.setCellValueFactory(new PropertyValueFactory<>("checkBox"));
        tableViewGRecouvrementClientCheque.setItems(ListeRecouvrementClientCheque);

        /**
         *
         *
         * table view affiche liste Recouvrement client espece
         */
        tableVGRecouvrementClientEspeceDC.setCellValueFactory(new PropertyValueFactory<>("dateCreation"));
        tableVGRecouvrementClientEspeceMontant.setCellValueFactory(new PropertyValueFactory<>("montant"));
        tableVGRecouvrementClientEspeceFacture.setCellValueFactory(new PropertyValueFactory<>("numeroF"));
        tableVGRecouvrementClientEspeceAction.setCellValueFactory(new PropertyValueFactory<>("checkBox"));
        tableVGRecouvrementClientEspece.setItems(ListeRecouvrementClientEspece);

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
        tableViewInventaireAction.setCellValueFactory(new PropertyValueFactory<>("checkBox"));

        /**
         * **
         *
         * Lettre de relance
         *
         *
         */
        tableViewLettreDeRelance.setItems(listeLettreDeRelance);
        cLettreDateCreation.setCellValueFactory(new PropertyValueFactory<>("date"));
        cLettreFactureClient.setCellValueFactory(new PropertyValueFactory<>("numeroFacture"));
        tableViewLettreDeRelanceCAction.setCellValueFactory(new PropertyValueFactory<>("chekBox"));

        tableVLettreListeFactureClientNumeroF.setCellValueFactory(new PropertyValueFactory<>("numeroF"));
        tableVLettreListeFactureClientDateCreation.setCellValueFactory(new PropertyValueFactory<>("dateCreation"));
        tableVLettreListeFactureClientDatePay.setCellValueFactory(new PropertyValueFactory<>("dateEchaillancePaiement"));
        tableVLettreListeFactureClientEtat.setCellValueFactory(new PropertyValueFactory<>("etat"));
        tableVLettreListeFactureClientTotal.setCellValueFactory(new PropertyValueFactory<>("totalPaye"));
        tableVLettreListeFactureClientAction.setCellValueFactory(new PropertyValueFactory<>("checkBoxLettreRelance"));

        tableVLettreListeFactureClient.setItems(listeFactureVenteLettre);

        /**
         *
         *
         *
         *
         * commande d'approvisionnement
         */
        paneAjouterFactureTableVListeCommandeNumeroC.setCellValueFactory(new PropertyValueFactory<>("numeroC"));
        paneAjouterFactureTableVListeCommandeDateC.setCellValueFactory(new PropertyValueFactory<>("dateCreation"));
        paneAjouterFactureTableVListeCommandeEtatC.setCellValueFactory(new PropertyValueFactory<>("etat"));
        paneAjouterFactureTableVListeCommandeTotalC.setCellValueFactory(new PropertyValueFactory<>("tauxRemise"));
        paneAjouterFactureTableVListeCommandeFournisseur.setCellValueFactory(new PropertyValueFactory<>("idF"));

        paneAjouterFactureTableVListeCommande.setItems(listeCommandeA);

        /**
         * *
         *
         *
         *
         * facture fournisseur
         */
        tableVListeFactureFournisseurNumeroF.setCellValueFactory(new PropertyValueFactory<>("numeroF"));
        tableVListeFactureFournisseurDateC.setCellValueFactory(new PropertyValueFactory<>("dateCreation"));
        tableVListeFactureFournisseurDateE.setCellValueFactory(new PropertyValueFactory<>("dateEchaillancePaiement"));
        tableVListeFactureFournisseurTTC.setCellValueFactory(new PropertyValueFactory<>("totalTTC"));
        tableVListeFactureFournisseurEtat.setCellValueFactory(new PropertyValueFactory<>("etat"));
        tableVListeFactureFournisseurTPayer.setCellValueFactory(new PropertyValueFactory<>("totalPaye"));
        tableVListeFactureFournisseurRestePayer.setCellValueFactory(new PropertyValueFactory<>("restePaye"));

        tableVListeFactureFournisseur.setItems(listeFactureAchat);

        /**
         * **
         *
         *
         * Facture vente pane ajouter recouvrement espece
         */
        tableViewAjouterRecouvrementCEListeFacture.setItems(ListeFactureVente);

        tableViewAjouterRecouvrementCEListeFactureNumeroF.setCellValueFactory(new PropertyValueFactory<>("numeroF"));
        tableViewAjouterRecouvrementCEListeFactureDateC.setCellValueFactory(new PropertyValueFactory<>("dateCreation"));
        tableViewAjouterRecouvrementCEListeFactureEtat.setCellValueFactory(new PropertyValueFactory<>("etat"));
        tableViewAjouterRecouvrementCEListeFactureTotalTTC.setCellValueFactory(new PropertyValueFactory<>("totalTTC"));
        tableViewAjouterRecouvrementCEListeFactureTotalPayer.setCellValueFactory(new PropertyValueFactory<>("totalPaye"));
        tableViewAjouterRecouvrementCEListeFactureResteAPayer.setCellValueFactory(new PropertyValueFactory<>("restePaye"));

        /**
         *
         * facture vente pane modifier recouvrement espece
         */
        tableViewModiferRecouvrementClientEspeceListeFacture.setItems(ListeFactureVente);

        tableViewModiferRecouvrementClientEspeceListeFactureNumeroF.setCellValueFactory(new PropertyValueFactory<>("numeroF"));
        tableViewModiferRecouvrementClientEspeceListeFactureDC.setCellValueFactory(new PropertyValueFactory<>("dateCreation"));
        tableViewModiferRecouvrementClientEspeceListeFactureEtat.setCellValueFactory(new PropertyValueFactory<>("etat"));
        tableViewModiferRecouvrementClientEspeceListeFactureTTc.setCellValueFactory(new PropertyValueFactory<>("totalTTC"));
        tableViewModiferRecouvrementClientEspeceListeFactureTP.setCellValueFactory(new PropertyValueFactory<>("totalPaye"));
        tableViewModiferRecouvrementClientEspeceListeFactureRP.setCellValueFactory(new PropertyValueFactory<>("restePaye"));

        /**
         * *
         *
         * ajouter button liste facture fournisseur
         */
        TableColumn<FactureAchat, Void> colBtn = new TableColumn("Action");

        Callback<TableColumn<FactureAchat, Void>, TableCell<FactureAchat, Void>> cellFactory = new Callback<TableColumn<FactureAchat, Void>, TableCell<FactureAchat, Void>>() {
            @Override
            public TableCell<FactureAchat, Void> call(TableColumn<FactureAchat, Void> param) {

                final TableCell<FactureAchat, Void> cell = new TableCell<FactureAchat, Void>() {

                    private Button btn = new Button("Detail");

                    {
                        btn.setOnAction((ActionEvent event) -> {

                            FactureAchat facture = tableVListeFactureFournisseur.getSelectionModel().getSelectedItem();

                            if (facture != null) {
                                System.out.println("***: " + facture.getNumeroF());

                                textFNumeroFD.setText(String.valueOf(facture.getNumeroF()));
                                textFDateEchaillanceFactureD.setValue(LocalDate.parse(facture.getDateEchaillancePaiement().toString()));
                                textFFraisTransportD.setText(String.valueOf(facture.getFraisTransport()));
                                textFTimbreFiscaleD.setText(String.valueOf(facture.getTimbreFiscale()));
                                textFTTCD.setText(String.valueOf(facture.getTotalTTC()));
                                textFTotalCommandeDetail.setText(String.valueOf(facture.getCommandeDApprovisionnement().getTotalC()));

                                listeLigneCommandeDA.setAll(facture.getCommandeDApprovisionnement().getLigneCommandeDApprovisionnements());

                                paneDetailFacture.setVisible(true);
                                new ZoomIn(paneDetailFacture).play();
                                paneDetailFacture.toFront();

                            } else {
                                creerAlerte("Selectionner une facture ! ", AlertType.WARNING).showAndWait();
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

        tableVListeFactureFournisseur.getColumns().add(colBtn);

        ajouterButonTabViewListeCommande();

    }

    public void ajouterButonTabViewListeCommande() {

        /**
         * *
         *
         * ajouter button
         */
        TableColumn<CommandeDApprovisionnement, Void> colBtn = new TableColumn("Action");

        Callback<TableColumn<CommandeDApprovisionnement, Void>, TableCell<CommandeDApprovisionnement, Void>> cellFactory = new Callback<TableColumn<CommandeDApprovisionnement, Void>, TableCell<CommandeDApprovisionnement, Void>>() {
            @Override
            public TableCell<CommandeDApprovisionnement, Void> call(TableColumn<CommandeDApprovisionnement, Void> param) {

                final TableCell<CommandeDApprovisionnement, Void> cell = new TableCell<CommandeDApprovisionnement, Void>() {

                    private Button btn = new Button("Facturer");

                    {
                        btn.setOnAction((ActionEvent event) -> {

                            commandeDApprovisionnementG = paneAjouterFactureTableVListeCommande.getSelectionModel().getSelectedItem();

                            if (commandeDApprovisionnementG != null) {

                                System.out.println("* id commande selectionner **: " + commandeDApprovisionnementG.getNumeroC());

                                listeLigneCommandeDA.setAll(commandeDApprovisionnementG.getLigneCommandeDApprovisionnements());
                                textFTotalCommande.setText(String.valueOf(commandeDApprovisionnementG.getTotalC()));
                                paneEnregistrerFacture.setVisible(true);
                                new ZoomIn(paneEnregistrerFacture).play();
                                paneEnregistrerFacture.toFront();

                            } else {

                                creerAlerte("Selectionner une commande ! ", AlertType.WARNING).showAndWait();
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

        paneAjouterFactureTableVListeCommande.getColumns().add(colBtn);
    }

    Alert creerAlerte(String text, AlertType type) {
        Alert alert = new Alert(type);
        alert.setHeaderText(null);
        alert.setContentText(text);

        return alert;
    }

    public void chargerListeFactureAchat() {

        listeFactureAchat.setAll(serviceFactureAchat.afficher());
    }

    public void chargerReglementFournisseurEspece() {

        listeReglementFournisseurEspece.setAll(serviceReglementFournisseurEspece.afficher());
    }

    public void chargerReglementFournisseurCheque() {

        listeReglementFournisseurCheque.setAll(serviceReglementFournisseurCheque.afficher());
    }

    public void chargerListeRecouvrementClientCheque() {
        ListeRecouvrementClientCheque.setAll(serviceRecouvrementClientCheque.afficher());
    }

    public void chargerListeRecouvrementClientEspece() {

        ListeRecouvrementClientEspece.setAll(serviceRecouvrementClientEspece.afficher());
    }

    public void chargerFactureVente() {

        ListeFactureVente.setAll(serviceRecouvrementClientEspece.chargerFactureVente());
    }

    public void chargerInventaire() {

        listInventaire.setAll(serviceInventaireCaisse.afficher());

    }

    public void chargerLettreDeRelance() {

        listeLettreDeRelance.setAll(serviceLettreDeRelance.afficher());

    }

    public void chargerCommandeDapprovisionnement() {

        listeCommandeA.setAll(serviceFactureAchat.chargerCommandeNonFacture());

    }

    public void chargerFactureFournisseur() {

        paneGFactureF.setVisible(true);
        new ZoomIn(paneGFactureF).play();
        paneGFactureF.toFront();

        listeFactureAchat.setAll(serviceFactureAchat.afficher());

    }

    private void afficheFacture(ActionEvent event) {

        if (event.getSource().equals(btnFactureF)) {

            chargerFactureFournisseur();
        }

    }

    @FXML
    private void afficheReglementF(ActionEvent event) {

        chargerReglementFournisseurCheque();
        chargerReglementFournisseurEspece();
        paneGReglementFournisseur.setVisible(true);
        new ZoomIn(paneGReglementFournisseur).play();
        paneGReglementFournisseur.toFront();

        for (FactureAchat facture : serviceFactureAchat.chargerParDateSysteme()) {

            String title = " Reglement fournisseur ";
            String message = " Echaillance reglment fature n° " + facture.getNumeroF();

            TrayNotification tray = new TrayNotification();
            AnimationType type = AnimationType.POPUP;

            tray.setAnimationType(type);
            tray.setTitle(title);
            tray.setMessage(message);
            tray.setNotificationType(NotificationType.INFORMATION);
            tray.showAndDismiss(Duration.millis(3000));

        }

    }

    @FXML
    private void afficheRecouvrementC(ActionEvent event) {

        chargerListeRecouvrementClientCheque();
        chargerListeRecouvrementClientEspece();
        paneGRecouvrementClient.setVisible(true);
        new ZoomIn(paneGRecouvrementClient).play();
        paneGRecouvrementClient.toFront();

        for (FactureVente facture : serviceRecouvrementClientEspece.chargerFactureVenteParDateSysteme()) {

            String title = " Recouvrement Client ";
            String message = " Echaillance reglment fature n° " + facture.getNumeroF();

            TrayNotification tray = new TrayNotification();
            AnimationType type = AnimationType.POPUP;

            tray.setAnimationType(type);
            tray.setTitle(title);
            tray.setMessage(message);
            tray.setNotificationType(NotificationType.INFORMATION);
            tray.showAndDismiss(Duration.millis(3000));

        }

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

        for (InventaireCaisse inventaireCaisse : listInventaire) {

            if (inventaireCaisse.getCheckBox().isSelected()) {

                serviceInventaireCaisse.supprimer(inventaireCaisse);

            }
        }

        chargerInventaire();

    }

    @FXML
    private void selectionner(MouseEvent event) {

        inventaireCaisseG = tableViewInventaire.getSelectionModel().getSelectedItem();

    }

    @FXML
    private void calculerEcart(KeyEvent event) {

        try {

            Double total = Double.parseDouble(soldeTCalculer.getText());

            if (total < 0) {

                soldeTCalculer.setText(String.valueOf(0));
            }

            ecartC.setText(String.valueOf(total - totalT));

        } catch (Exception ex) {

            soldeTCalculer.setText("");
            ecartC.setText("");

        }
    }

    @FXML
    private void afficherPaneAjouterInventaire(ActionEvent event) {
        
        
        double totalC=serviceRecouvrementClientCheque.totalCheque();
        
        double totalE=serviceRecouvrementClientEspece.totalEspece();
        
        
        this.totalT=totalC+totalE;
        

        totalTheorique.setText(String.valueOf(totalC+totalE));
        totalCheque.setText(String.valueOf(totalC));
        totalEspece.setText(String.valueOf(totalE));

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

        if (soldeTCalculer.getText().equals("")) {
            creerAlerte("champt solde calculer vide ! ", AlertType.WARNING).showAndWait();
        } else {

            InventaireCaisse inventaireCaisse = new InventaireCaisse(new Date(), Double.valueOf(soldeTCalculer.getText()), Double.valueOf(totalTheorique.getText()), Double.valueOf(totalCheque.getText()), Double.valueOf(totalEspece.getText()), Double.valueOf(ecartC.getText()));

            serviceInventaireCaisse.ajouter(inventaireCaisse);

            clear();

            chargerPaneGInventaire();
        }
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

        } else {
            creerAlerte("Selectionner un inventaire ! ", AlertType.WARNING).showAndWait();
        }
    }

    @FXML
    private void calculerEcartM(KeyEvent event) {
        
        
        
         try {

            Double total = Double.parseDouble(soldeTCalculerM.getText());

            if (total < 0) {

                soldeTCalculerM.setText(String.valueOf(0));
            }

            ecartCM.setText(String.valueOf(total - totalT));

        } catch (Exception ex) {

            soldeTCalculerM.setText("");
            ecartCM.setText("");

        }

       

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

        listeFactureVenteLettre.setAll(serviceLettreDeRelance.chargerFacture());
        paneAjouterLettreDeRelance.setVisible(true);
        new ZoomIn(paneAjouterLettreDeRelance).play();
        paneAjouterLettreDeRelance.toFront();

    }

    @FXML
    private void supprimerLettre(ActionEvent event) {

        for (LettreDeRelance lettre : listeLettreDeRelance) {

            if (lettre.getChekBox().isSelected()) {

                serviceLettreDeRelance.supprimer(lettre);

            }

        }

        chargerLettreDeRelance();

    }

    public void afficherPaneGLettre() {

        chargerLettreDeRelance();
        paneGLettreR.setVisible(true);
        new ZoomIn(paneGLettreR).play();
        paneGLettreR.toFront();
    }

    @FXML
    private void afficherPanelGLettreDeRelance(ActionEvent event) {

        if (event.getSource().equals(btnLettreRelance)) {

            afficherPaneGLettre();
        }

    }

    @FXML
    private void validerAjouterLettre(ActionEvent event) {

        for (FactureVente factureVente : listeFactureVenteLettre) {

            if (factureVente.getCheckBoxLettreRelance().isSelected()) {

                serviceLettreDeRelance.ajouter(new LettreDeRelance(new Date(), factureVente));

            }

        }

        afficherPaneGLettre();

    }

    @FXML
    private void afficherPaneAjouterFactureFournisseur(ActionEvent event) {

        chargerCommandeDapprovisionnement();
        paneAjouterFacture.setVisible(true);
        new ZoomIn(paneAjouterFacture).play();
        paneAjouterFacture.toFront();

    }

    @FXML
    private void affichePaneGFacture(ActionEvent event) {

        if (event.getSource().equals(btnFactureF)) {

            chargerFactureFournisseur();

        }
    }

    @FXML
    private void validerAjouterFactureAchat(ActionEvent event) {

        if (textFNumeroF.getText().equals("") || Integer.valueOf(textFNumeroF.getText()) == 0) {

            creerAlerte("champs numero facture vide !", AlertType.WARNING).showAndWait();
            return;
        }
        if (textFFraisTransport.getText().equals("") || Double.valueOf(textFFraisTransport.getText()) == 0) {

            creerAlerte("champs frais de transport vide !", AlertType.WARNING).showAndWait();
            return;
        }
        if (textFTimbreFiscale.getText().equals("") || Double.valueOf(textFTimbreFiscale.getText()) == 0) {

            creerAlerte("champs timbre fiscale vide !", AlertType.WARNING).showAndWait();
            return;
        }

        if (textFTTC.getText().equals("") || Double.valueOf(textFTTC.getText()) == 0) {

            creerAlerte("champs TTC vide !", AlertType.WARNING).showAndWait();
            return;
        }

        LocalDate date = textFDateEchaillanceFacture.getValue();

        FactureAchat factureAchat = new FactureAchat(Integer.valueOf(textFNumeroF.getText()), new Date(), java.sql.Date.valueOf(date), Double.valueOf(textFTTC.getText()), "non_paye", 0, Double.valueOf(textFTTC.getText()), Double.valueOf(textFTimbreFiscale.getText()), Double.valueOf(textFFraisTransport.getText()), commandeDApprovisionnementG);

        textFNumeroF.setText("");
        textFTTC.setText("");
        textFTimbreFiscale.setText("");
        textFFraisTransport.setText("");
        textFDateEchaillanceFacture.setValue(null);

        serviceFactureAchat.ajouter(factureAchat);
        chargerFactureFournisseur();

    }

    @FXML
    private void chargerPaneAjouterRecouvrementCEspece(ActionEvent event) {

        chargerFactureVente();
        paneAjouterRecouvrementClientEspece.setVisible(true);
        new ZoomIn(paneAjouterRecouvrementClientEspece).play();
        paneAjouterRecouvrementClientEspece.toFront();
    }

    @FXML
    private void chargerPaneModifierRecouvrementCEspece(ActionEvent event) {

        if (recouvrementClientEspeceG != null) {

            chargerFactureVente();
            paneModifierRecouvrementCEspece.setVisible(true);
            new ZoomIn(paneModifierRecouvrementCEspece).play();
            paneModifierRecouvrementCEspece.toFront();

            textFModifierRecouvrementCEspece.setText(String.valueOf(recouvrementClientEspeceG.getMontant()));

        } else {
            creerAlerte("Selectionner un recouvrement ! ", AlertType.WARNING).showAndWait();
        }

    }

    @FXML
    private void supprimerRecouvrementClientEspece(ActionEvent event) {

        for (RecouvrementClientEspece r : ListeRecouvrementClientEspece) {

            if (r.getCheckBox().isSelected()) {

                serviceRecouvrementClientEspece.supprimer(r);

            }

        }

        chargerListeRecouvrementClientEspece();

    }

    @FXML
    private void paneAjouterRecouvrementCESelectionnerFacture(MouseEvent event) {

        factureVenteG = tableViewAjouterRecouvrementCEListeFacture.getSelectionModel().getSelectedItem();
    }

    @FXML
    private void validerAjouterRecouvrementCEspece(ActionEvent event) {

        if (factureVenteG != null) {

            if (textFAjouterRCEMontant.getText().equals("") || Double.valueOf(textFAjouterRCEMontant.getText()) == 0) {

                creerAlerte("Champ montant vide !", AlertType.WARNING).showAndWait();
            } else {

                RecouvrementClientEspece recouvrementClientEspece = new RecouvrementClientEspece(factureVenteG, Double.valueOf(textFAjouterRCEMontant.getText()), new Date());

                if (recouvrementClientEspece.getMontant() <= recouvrementClientEspece.getFactureVente().getRestePaye()) {

                    serviceRecouvrementClientEspece.ajouter(recouvrementClientEspece);

                    factureVenteG = null;
                    chargerListeRecouvrementClientEspece();
                    paneGRecouvrementClientEspece.setVisible(true);
                    new ZoomIn(paneGRecouvrementClientEspece).play();
                    paneGRecouvrementClientEspece.toFront();
                } else {
                    creerAlerte("Le montant du recouvrement doit etre inferieur au egale du reste a payer du facture ! ", AlertType.WARNING).showAndWait();
                }

            }

        } else {

            creerAlerte("Selectionner une facture ! ", AlertType.WARNING).showAndWait();
        }

    }

    @FXML
    private void AnnulerAjouterRecouvrementCEspece(ActionEvent event) {

        chargerListeRecouvrementClientEspece();
        paneGRecouvrementClientEspece.setVisible(true);
        new ZoomIn(paneGRecouvrementClientEspece).play();
        paneGRecouvrementClientEspece.toFront();
    }

    @FXML
    private void selectListeRecouvrementCEspece(MouseEvent event) {

        recouvrementClientEspeceG = tableVGRecouvrementClientEspece.getSelectionModel().getSelectedItem();
    }

    private void paneModifierRecouvrementClientEspeceSelectionnerFature(MouseEvent event) {

        factureVenteG = tableViewAjouterRecouvrementCEListeFacture.getSelectionModel().getSelectedItem();
    }

    @FXML
    private void validerModifierRecouvrementCEspece(ActionEvent event) {

        if (factureVenteG != null) {

            if (textFModifierRecouvrementCEspece.getText().equals("") || Double.valueOf(textFModifierRecouvrementCEspece.getText()) == 0) {

                creerAlerte("Champt montant vide !", AlertType.WARNING).showAndWait();
            } else {

                double aM = recouvrementClientEspeceG.getMontant();

                recouvrementClientEspeceG.setMontant(Double.valueOf(textFModifierRecouvrementCEspece.getText()));
                recouvrementClientEspeceG.setFactureVente(factureVenteG);

                serviceRecouvrementClientEspece.modifier(recouvrementClientEspeceG, aM);

                recouvrementClientEspeceG = null;
                factureVenteG = null;

                chargerListeRecouvrementClientEspece();
                paneGRecouvrementClientEspece.setVisible(true);
                new ZoomIn(paneGRecouvrementClientEspece).play();
                paneGRecouvrementClientEspece.toFront();
            }

        } else {
            creerAlerte("Selectionner une facture ! ", AlertType.WARNING).showAndWait();
        }

    }

    @FXML
    private void modifierRecouvrementClientEspeceSelectFacture(MouseEvent event) {

        factureVenteG = tableViewModiferRecouvrementClientEspeceListeFacture.getSelectionModel().getSelectedItem();
    }

    @FXML
    private void ChargerPaneAjouterRecouvrementCheque(ActionEvent event) {

        chargerFactureVente();
        paneAjouterRecouvrementClientCheque.setVisible(true);
        new ZoomIn(paneAjouterRecouvrementClientCheque).play();
        paneAjouterRecouvrementClientCheque.toFront();

    }

    @FXML
    private void chargerPaneModiferRecouvrementCheque(ActionEvent event) {

        if (recouvrementClientChequeG != null) {

            dateChequeModifierRecouvrementClientCheque.setValue(LocalDate.parse(recouvrementClientChequeG.getDateCheque().toString()));
            textFModifierRecouvrementClientChequeNumeroCheque.setText(String.valueOf(recouvrementClientChequeG.getNumeroCheque()));
            textFModiiferRecouvrementChequeMontant.setText(String.valueOf(recouvrementClientChequeG.getMontant()));

            chargerFactureVente();
            paneModifierRecouvrementCheque.setVisible(true);
            new ZoomIn(paneModifierRecouvrementCheque).play();
            paneModifierRecouvrementCheque.toFront();
        } else {
            creerAlerte("Selectionner un recouvrement ! ", AlertType.WARNING).showAndWait();
        }

    }

    @FXML
    private void supprimerRecouvrementClientCheque(ActionEvent event) {

        for (RecouvrementClientCheque r : ListeRecouvrementClientCheque) {

            if (r.getCheckBox().isSelected()) {

                serviceRecouvrementClientCheque.supprimer(r);

            }
        }

        chargerListeRecouvrementClientCheque();

    }

    @FXML
    private void paneAjouterRecouvrementClientChequeSelectionner(MouseEvent event) {

        factureVenteG = tableViewListeFactureAjouterRecouvrementClientCheque.getSelectionModel().getSelectedItem();
    }

    @FXML
    private void validerAjouterRecouvrementClientCheque(ActionEvent event) {

        if (factureVenteG != null) {

            if (textFAjouterRecouvrementChequeMontant.getText().equals("") || Double.valueOf(textFAjouterRecouvrementChequeMontant.getText()) == 0) {

                creerAlerte("champs mobtant vide !", AlertType.WARNING).showAndWait();
                return;
            }

            if (textFAjouterRecouvrementClientChequeNumeroCheque.getText().equals("") || Double.valueOf(Integer.valueOf(textFAjouterRecouvrementClientChequeNumeroCheque.getText())) == 0) {

                creerAlerte("champs numero cheque vide !", AlertType.WARNING).showAndWait();
                return;
            }

            LocalDate date = dateChequeAjouterRecouvrementClientCheque.getValue();

            RecouvrementClientCheque recouvrementClientCheque = new RecouvrementClientCheque(java.sql.Date.valueOf(date), Integer.valueOf(textFAjouterRecouvrementClientChequeNumeroCheque.getText()), factureVenteG, Double.valueOf(textFAjouterRecouvrementChequeMontant.getText()), new Date());

            if (recouvrementClientCheque.getMontant() <= recouvrementClientCheque.getFactureVente().getRestePaye()) {
                serviceRecouvrementClientCheque.ajouter(recouvrementClientCheque);
                factureVenteG = null;

                dateChequeAjouterRecouvrementClientCheque.setValue(null);
                textFAjouterRecouvrementClientChequeNumeroCheque.setText("");
                textFAjouterRecouvrementChequeMontant.setText("");

                chargerListeRecouvrementClientCheque();
                paneGrecouvrementClientcheque.setVisible(true);
                new ZoomIn(paneGrecouvrementClientcheque).play();
                paneGrecouvrementClientcheque.toFront();
            } else {
                creerAlerte("Le montant du recouvrement doit etre inferieur au egale du reste a payer du facure ! ", AlertType.WARNING).showAndWait();

            }

        } else {
            creerAlerte("Selectionner une facture ! ", AlertType.WARNING).showAndWait();
        }

    }

    @FXML
    private void AnnulerAjouterRecouvrementCCheque(ActionEvent event) {

        chargerListeRecouvrementClientCheque();
        paneGrecouvrementClientcheque.setVisible(true);
        new ZoomIn(paneGrecouvrementClientcheque).play();
        paneGrecouvrementClientcheque.toFront();
    }

    @FXML
    private void paneGRecouvrementChequeSelectionner(MouseEvent event) {

        recouvrementClientChequeG = tableViewGRecouvrementClientCheque.getSelectionModel().getSelectedItem();

    }

    @FXML
    private void validerModifierRecouvrementClientCheque(ActionEvent event) {

        if (factureVenteG != null) {

            if (textFModiiferRecouvrementChequeMontant.getText().equals("") || Double.valueOf(textFModiiferRecouvrementChequeMontant.getText()) == 0) {

                creerAlerte("champs mobtant vide !", AlertType.WARNING).showAndWait();
                return;
            }

            if (textFModifierRecouvrementClientChequeNumeroCheque.getText().equals("") || Double.valueOf(Integer.valueOf(textFModifierRecouvrementClientChequeNumeroCheque.getText())) == 0) {

                creerAlerte("champs numero cheque vide !", AlertType.WARNING).showAndWait();
                return;
            }

            double aM = recouvrementClientChequeG.getMontant();

            recouvrementClientChequeG.setFactureVente(factureVenteG);
            recouvrementClientChequeG.setNumeroCheque(Integer.valueOf(textFModifierRecouvrementClientChequeNumeroCheque.getText()));
            recouvrementClientChequeG.setDateCheque(java.sql.Date.valueOf(dateChequeModifierRecouvrementClientCheque.getValue()));
            recouvrementClientChequeG.setMontant(Double.valueOf(textFModiiferRecouvrementChequeMontant.getText()));

            serviceRecouvrementClientCheque.modifier(recouvrementClientChequeG, aM);

            textFModifierRecouvrementClientChequeNumeroCheque.setText("");
            dateChequeModifierRecouvrementClientCheque.setValue(null);
            textFModiiferRecouvrementChequeMontant.setText("");

            chargerListeRecouvrementClientCheque();
            paneGrecouvrementClientcheque.setVisible(true);
            new ZoomIn(paneGrecouvrementClientcheque).play();
            paneGrecouvrementClientcheque.toFront();

            recouvrementClientChequeG = null;
        } else {
            creerAlerte("Selectionner une facture ! ", AlertType.WARNING).showAndWait();
        }

    }

    public void afficheStatReglement() {

        /**
         * pie chart reglement
         */
        ObservableList<PieChart.Data> listReglement = FXCollections.observableArrayList();

        double totalA = serviceFactureAchat.totalAchatParAnneSysteme();
        double totalAP = serviceFactureAchat.totalPayerParAnneSysteme();

        PieChart.Data totalAchat = new PieChart.Data("total achat:" + totalA + "DT", totalA);
        PieChart.Data totalPayer = new PieChart.Data("total payé:" + totalAP + "DT", totalAP);

        listReglement.add(totalAchat);
        listReglement.add(totalPayer);

        pieChartReglement.setLabelsVisible(true);
        pieChartReglement.setData(listReglement);

    }

    public void afficheStatRecouvrement() {

        /**
         * pie chart Recouvrement
         */
        ObservableList<PieChart.Data> listRecouvrement = FXCollections.observableArrayList();

        double totalARecouvrement = serviceFactureVente.totalVenteParAnneSysteme();
        double totalAPRecouvrement = serviceFactureVente.totalPayerParAnneSysteme();

        PieChart.Data totalAchatRecouvrement = new PieChart.Data("total vente:" + totalARecouvrement + "DT", totalARecouvrement);
        PieChart.Data totalPayerRecouvrement = new PieChart.Data("total payé:" + totalAPRecouvrement + "DT", totalAPRecouvrement);

        listRecouvrement.add(totalAchatRecouvrement);
        listRecouvrement.add(totalPayerRecouvrement);

        pieChartRecouvrement.setLabelsVisible(true);
        pieChartRecouvrement.setData(listRecouvrement);

    }

    @FXML
    private void paneModifierRecouvrementClientChequeSelectionner(MouseEvent event) {

        factureVenteG = tableViewListeFactureModifierRecouvrementClientCheque.getSelectionModel().getSelectedItem();

    }

    @FXML
    private void AnnulerModifierRecouvrementCCheque(ActionEvent event) {

        chargerListeRecouvrementClientCheque();
        paneGrecouvrementClientcheque.setVisible(true);
        new ZoomIn(paneGrecouvrementClientcheque).play();
        paneGrecouvrementClientcheque.toFront();
    }

    @FXML
    private void selectListReglementEspece(MouseEvent event) {
        reglementFournisseurEspeceG = tableViewGreglementEspece.getSelectionModel().getSelectedItem();

    }

    @FXML
    private void chargerPaneAjouterReglementEspece(ActionEvent event) {

        chargerListeFactureAchat();
        paneajouterReglementEspece.setVisible(true);
        new ZoomIn(paneajouterReglementEspece).play();
        paneajouterReglementEspece.toFront();
    }

    @FXML
    private void chargerPaneModifierReglementEspece(ActionEvent event) {

        if (reglementFournisseurEspeceG != null) {

            textFModifierReglementEspece.setText(String.valueOf(reglementFournisseurEspeceG.getMontant()));
            chargerListeFactureAchat();
            paneModifierReglementEspece.setVisible(true);
            new ZoomIn(paneModifierReglementEspece).play();
            paneModifierReglementEspece.toFront();
        } else {
            creerAlerte("Selectionner un reglement ! ", AlertType.WARNING).showAndWait();
        }
    }

    @FXML
    private void selectListeReglementCheque(MouseEvent event) {

        reglementFournisseurChequeG = tableViewGReglementCheque.getSelectionModel().getSelectedItem();

    }

    @FXML
    private void chargerPaneAjouterReglementCheque(ActionEvent event) {

        chargerListeFactureAchat();
        paneAjouterReglementCheque.setVisible(true);
        new ZoomIn(paneAjouterReglementCheque).play();
        paneAjouterReglementCheque.toFront();

    }

    @FXML
    private void chargerPaneModiiferReglementCheque(ActionEvent event) {

        if (reglementFournisseurChequeG != null) {

            dateChequeModifierRegCheque.setValue(LocalDate.parse(reglementFournisseurChequeG.getDateCheque().toString()));
            textFNumeroModiferRegCheque.setText(String.valueOf(reglementFournisseurChequeG.getNumeroCheque()));
            textMontantModifierRegCheque.setText(String.valueOf(reglementFournisseurChequeG.getMontant()));

            chargerListeFactureAchat();
            paneModifierReglementCheque.setVisible(true);
            new ZoomIn(paneModifierReglementCheque).play();
            paneModifierReglementCheque.toFront();
        } else {
            creerAlerte("Selectionner une reglement ! ", AlertType.WARNING).showAndWait();
        }

    }

    @FXML
    private void supprimerReglementCheque(ActionEvent event) {

        for (ReglementFournisseurCheque r : listeReglementFournisseurCheque) {

            if (r.getCheckBox().isSelected()) {

                serviceReglementFournisseurCheque.supprimer(r);
            }

        }

        chargerReglementFournisseurCheque();

    }

    @FXML
    private void paneAjouterReglementEspceSelectionner(MouseEvent event) {

        factureAchatG = tableViewAjouterReglementEspeceListeFactureAchat.getSelectionModel().getSelectedItem();
    }

    @FXML
    private void AnnulerAjouterReglementEspece(ActionEvent event) {

        chargerReglementFournisseurEspece();
        paneGReglementFournisseurEspece.setVisible(true);
        new ZoomIn(paneGReglementFournisseurEspece).play();
        paneGReglementFournisseurEspece.toFront();
    }

    @FXML
    private void validerAjouterReglementEspece(ActionEvent event) {

        if (factureAchatG != null) {

            if (textFAjouterReglementEspece.getText().equals("") || Double.valueOf(Integer.valueOf(textFAjouterReglementEspece.getText())) == 0) {

                creerAlerte("champs montant  vide !", AlertType.WARNING).showAndWait();
                return;
            }

            ReglementFournisseurEspece r = new ReglementFournisseurEspece(factureAchatG, Double.valueOf(textFAjouterReglementEspece.getText()), new Date());

            if (r.getMontant() <= r.getFactureAchat().getRestePaye()) {

                serviceReglementFournisseurEspece.ajouter(r);

                factureAchatG = null;
                textFAjouterReglementEspece.setText("");

                chargerReglementFournisseurEspece();
                paneGReglementFournisseurEspece.setVisible(true);
                new ZoomIn(paneGReglementFournisseurEspece).play();
                paneGReglementFournisseurEspece.toFront();
            } else {

                creerAlerte("Le montant du reglement doit etre inferieur au egale au reste a payer du facture ! ", AlertType.WARNING).showAndWait();

            }

        } else {
            creerAlerte("Selectionner une facture ! ", AlertType.WARNING).showAndWait();
        }

    }

    @FXML
    private void supprimerReglementEspece(ActionEvent event) {

        for (ReglementFournisseurEspece r : listeReglementFournisseurEspece) {

            if (r.getCheckBox().isSelected()) {

                serviceReglementFournisseurEspece.supprimer(r);
            }
        }

        chargerReglementFournisseurEspece();

    }

    private void validerModifierReglementEspece(ActionEvent event) {

        if (factureAchatG != null) {

            if (textFModifierReglementEspece.getText().equals("") || Double.valueOf(Integer.valueOf(textFModifierReglementEspece.getText())) == 0) {

                creerAlerte("champs montant vide !", AlertType.WARNING).showAndWait();
                return;
            }

            double ancientM = reglementFournisseurEspeceG.getMontant();

            reglementFournisseurEspeceG.setMontant(Double.valueOf(textFModifierReglementEspece.getText()));
            reglementFournisseurEspeceG.setFactureAchat(factureAchatG);

            serviceReglementFournisseurEspece.modifier(reglementFournisseurEspeceG, ancientM);

            reglementFournisseurEspeceG = null;
            factureAchatG = null;

            chargerReglementFournisseurEspece();
            paneGReglementFournisseurEspece.setVisible(true);
            new ZoomIn(paneGReglementFournisseurEspece).play();
            paneGReglementFournisseurEspece.toFront();

        } else {
            creerAlerte("Selectionner une facture ! ", AlertType.WARNING).showAndWait();
        }

    }

    @FXML
    private void AnuulerModiferReglementEspece(ActionEvent event) {

        chargerReglementFournisseurEspece();
        paneGReglementFournisseurEspece.setVisible(true);
        new ZoomIn(paneGReglementFournisseurEspece).play();
        paneGReglementFournisseurEspece.toFront();
    }

    @FXML
    private void modifierReglementEspeceListeFactureAchatSelection(MouseEvent event) {

        factureAchatG = tableViewModifierReglementEspeceListeFactureAchat.getSelectionModel().getSelectedItem();
    }

    @FXML
    private void validerAjouterReglementCheque(ActionEvent event) {

        if (factureAchatG != null) {

            if (textAjouterRegChequeMontant.getText().equals("") || Double.valueOf(textAjouterRegChequeMontant.getText()) == 0) {

                creerAlerte("champs mobtant vide !", AlertType.WARNING).showAndWait();
                return;
            }

            if (textfAjouterNumeroReglementCheque.getText().equals("") || Integer.valueOf(Integer.valueOf(textfAjouterNumeroReglementCheque.getText())) == 0) {

                creerAlerte("champs numero cheque vide !", AlertType.WARNING).showAndWait();
                return;
            }

            LocalDate date = dateChequeAjouterReglCheque.getValue();

            ReglementFournisseurCheque r = new ReglementFournisseurCheque(java.sql.Date.valueOf(date), Integer.valueOf(textfAjouterNumeroReglementCheque.getText()), factureAchatG, Double.valueOf(textAjouterRegChequeMontant.getText()), new Date());

            if (r.getMontant() <= factureAchatG.getRestePaye()) {

                serviceReglementFournisseurCheque.ajouter(r);
                factureAchatG = null;

                dateChequeAjouterReglCheque.setValue(null);
                textfAjouterNumeroReglementCheque.setText("");
                textAjouterRegChequeMontant.setText("");

                chargerReglementFournisseurCheque();
                paneGReglementCheque.setVisible(true);
                new ZoomIn(paneGReglementCheque).play();
                paneGReglementCheque.toFront();

            } else {
                creerAlerte("Montant du regelement doit etre inferieur au egale au reste a payer du facture ! ", AlertType.WARNING).showAndWait();
            }

        } else {
            creerAlerte("Selectionner une facture ! ", AlertType.WARNING).showAndWait();
        }

    }

    @FXML
    private void anuulerAjouterReglementCheque(ActionEvent event) {

        chargerReglementFournisseurCheque();
        paneGReglementCheque.setVisible(true);
        new ZoomIn(paneGReglementCheque).play();
        paneGReglementCheque.toFront();

    }

    @FXML
    private void paneAjouterReglementChequeSelectionnerFacture(MouseEvent event) {

        factureAchatG = tableViewAjouterRegChequeListeFacture.getSelectionModel().getSelectedItem();

    }

    @FXML
    private void paneModiiferRegSelectionnerFacture(MouseEvent event) {

        factureAchatG = tableViewListeFactureModifierRegCheque.getSelectionModel().getSelectedItem();
    }

    @FXML
    private void validerModifierRegCheque(ActionEvent event) {

        if (factureAchatG != null) {

            if (textMontantModifierRegCheque.getText().equals("") || Double.valueOf(textMontantModifierRegCheque.getText()) == 0) {

                creerAlerte("champs mobtant vide !", AlertType.WARNING).showAndWait();
                return;
            }

            if (textFNumeroModiferRegCheque.getText().equals("") || Double.valueOf(Integer.valueOf(textFNumeroModiferRegCheque.getText())) == 0) {

                creerAlerte("champs numero cheque vide !", AlertType.WARNING).showAndWait();
                return;
            }

            double AncientM = 0;

            AncientM = reglementFournisseurChequeG.getMontant();

            reglementFournisseurChequeG.setFactureAchat(factureAchatG);
            reglementFournisseurChequeG.setNumeroCheque(Integer.valueOf(textFNumeroModiferRegCheque.getText()));
            reglementFournisseurChequeG.setDateCheque(java.sql.Date.valueOf(dateChequeModifierRegCheque.getValue()));
            reglementFournisseurChequeG.setMontant(Double.valueOf(textMontantModifierRegCheque.getText()));

            serviceReglementFournisseurCheque.modifier(reglementFournisseurChequeG, AncientM);

            textFNumeroModiferRegCheque.setText("");
            dateChequeModifierRegCheque.setValue(null);
            textMontantModifierRegCheque.setText("");

            chargerReglementFournisseurCheque();
            paneGReglementCheque.setVisible(true);
            new ZoomIn(paneGReglementCheque).play();
            paneGReglementCheque.toFront();

            reglementFournisseurChequeG = null;
            factureAchatG = null;

        } else {
            creerAlerte("Selectionner une facture ! ", AlertType.WARNING).showAndWait();
        }

    }

    @FXML
    private void annulerModifierRegCheque(ActionEvent event) {
        chargerReglementFournisseurCheque();
        paneGReglementCheque.setVisible(true);
        new ZoomIn(paneGReglementCheque).play();
        paneGReglementCheque.toFront();
    }

    @FXML
    private void annulerAjouterFacture(ActionEvent event) {

        chargerFactureFournisseur();
    }

    @FXML
    private void retourDetailFacture(ActionEvent event) {

        chargerFactureFournisseur();
    }

    @FXML
    private void fermer(MouseEvent event) {

        ((Stage) this.btnLettreRelance.getScene().getWindow()).close();
        try {

            HBox root = (HBox) FXMLLoader.load(getClass().getResource("/com/gentrepot/views/Authentification.fxml"));
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Authentification");
            primaryStage.show();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    @FXML
    private void EnvoyerMailLettre(ActionEvent event) {

        if (textFAdresseMailClient.getText().equals("")) {
            creerAlerte("Donner adresse mail ! ", AlertType.WARNING).showAndWait();
            return;
        }

        if (lettreDeRelanceG != null) {

            MailService.EnvoyerMail(textFAdresseMailClient.getText(), "Lettre de relance", "Facture impayée numero°" + lettreDeRelanceG.getFactureVente().getNumeroF());
            lettreDeRelanceG = null;
            textFAdresseMailClient.setText("");

            String title = " Lettre de relance  ";
            String message = " Mail envoyer avec succes ";

            TrayNotification tray = new TrayNotification();
            AnimationType type = AnimationType.POPUP;

            tray.setAnimationType(type);
            tray.setTitle(title);
            tray.setMessage(message);
            tray.setNotificationType(NotificationType.SUCCESS);
            tray.showAndDismiss(Duration.millis(3000));

        } else {
            creerAlerte("Selectionner une lettre ! ", AlertType.WARNING).showAndWait();
        }
    }

    @FXML
    private void selectLettre(MouseEvent event) {

        lettreDeRelanceG = tableViewLettreDeRelance.getSelectionModel().getSelectedItem();
    }

    @FXML
    private void verifNumeroF(KeyEvent event) {

        try {

            int num = Integer.parseInt(textFNumeroF.getText());

            if (num <= 0) {
                textFNumeroF.setText("");
            }

        } catch (Exception ex) {

            textFNumeroF.setText("");

        }

    }

    @FXML
    private void verifFraisT(KeyEvent event) {

        try {
            double f = Double.parseDouble(textFFraisTransport.getText());

            if (f < 0) {
                textFFraisTransport.setText("");
            }

        } catch (Exception ex) {

            textFFraisTransport.setText("");

        }

    }

    @FXML
    private void verifTimbreF(KeyEvent event) {

        try {
            double t = Double.parseDouble(textFTimbreFiscale.getText());

            if (t < 0) {
                textFTimbreFiscale.setText("");
            }
            
            
            textFTTC.setText(String.valueOf(t+Double.valueOf(textFFraisTransport.getText())+Double.valueOf(textFTotalCommande.getText())));

        } catch (Exception ex) {

            textFTimbreFiscale.setText("");

        }

    }

    @FXML
    private void verifTTC(KeyEvent event) {

        try {
            double ttc = Double.parseDouble(textFTTC.getText());

            if (ttc <= 0) {
                textFTTC.setText("");
            }

        } catch (Exception ex) {

            textFTTC.setText("");

        }

    }

    @FXML
    private void verifMontant(KeyEvent event) {

        try {
            double m = Double.parseDouble(textFModifierReglementEspece.getText());

            if (m <= 0) {
                textFModifierReglementEspece.setText("");
            }

        } catch (Exception ex) {

            textFModifierReglementEspece.setText("");

        }
    }

    @FXML
    private void verifMonatantAjouter(KeyEvent event) {

        try {
            double m = Double.parseDouble(textFAjouterReglementEspece.getText());

            if (m <= 0) {
                textFAjouterReglementEspece.setText("");
            }

        } catch (Exception ex) {

            textFAjouterReglementEspece.setText("");

        }
    }

    @FXML
    private void verifMACheque(KeyEvent event) {

        try {
            double m = Double.parseDouble(textAjouterRegChequeMontant.getText());

            if (m <= 0) {
                textAjouterRegChequeMontant.setText("");
            }

        } catch (Exception ex) {

            textAjouterRegChequeMontant.setText("");

        }
    }

    @FXML
    private void verifNChA(KeyEvent event) {

        try {
            int m = Integer.parseInt(textfAjouterNumeroReglementCheque.getText());

            if (m <= 0) {
                textfAjouterNumeroReglementCheque.setText("");
            }

        } catch (Exception ex) {

            textfAjouterNumeroReglementCheque.setText("");

        }

    }

    @FXML
    private void verifMM(KeyEvent event) {

        try {
            double m = Double.parseDouble(textMontantModifierRegCheque.getText());

            if (m <= 0) {
                textMontantModifierRegCheque.setText("");
            }

        } catch (Exception ex) {

            textMontantModifierRegCheque.setText("");

        }
    }

    @FXML
    private void verifNMCH(KeyEvent event) {

        try {
            int m = Integer.parseInt(textFNumeroModiferRegCheque.getText());

            if (m <= 0) {
                textFNumeroModiferRegCheque.setText("");
            }

        } catch (Exception ex) {

            textFNumeroModiferRegCheque.setText("");

        }

    }

    @FXML
    private void verifMRM(KeyEvent event) {

        try {
            double m = Double.parseDouble(textFModifierRecouvrementCEspece.getText());

            if (m <= 0) {
                textFModifierRecouvrementCEspece.setText("");
            }

        } catch (Exception ex) {

            textFModifierRecouvrementCEspece.setText("");

        }

    }

    @FXML
    private void verifMREA(KeyEvent event) {

        try {
            double m = Double.parseDouble(textFAjouterRCEMontant.getText());

            if (m <= 0) {
                textFAjouterRCEMontant.setText("");
            }

        } catch (Exception ex) {

            textFAjouterRCEMontant.setText("");

        }

    }

    @FXML
    private void verifMRecouvrementCM(KeyEvent event) {

        try {
            double m = Double.parseDouble(textFModiiferRecouvrementChequeMontant.getText());

            if (m <= 0) {
                textFModiiferRecouvrementChequeMontant.setText("");
            }

        } catch (Exception ex) {

            textFModiiferRecouvrementChequeMontant.setText("");

        }

    }

    @FXML
    private void verifNumeroChequeNCheque(KeyEvent event) {

        try {
            int m = Integer.parseInt(textFModifierRecouvrementClientChequeNumeroCheque.getText());

            if (m <= 0) {
                textFModifierRecouvrementClientChequeNumeroCheque.setText("");
            }

        } catch (Exception ex) {

            textFModifierRecouvrementClientChequeNumeroCheque.setText("");

        }

    }

    @FXML
    private void verifMAjouterRecouvrementCheque(KeyEvent event) {

        try {
            double m = Double.parseDouble(textFAjouterRecouvrementChequeMontant.getText());

            if (m <= 0) {
                textFAjouterRecouvrementChequeMontant.setText("");
            }

        } catch (Exception ex) {

            textFAjouterRecouvrementChequeMontant.setText("");

        }

    }

    @FXML
    private void verifNumeroChequeAjouterRec(KeyEvent event) {

        try {
            int m = Integer.parseInt(textFAjouterRecouvrementClientChequeNumeroCheque.getText());

            if (m <= 0) {
                textFAjouterRecouvrementClientChequeNumeroCheque.setText("");
            }

        } catch (Exception ex) {

            textFAjouterRecouvrementClientChequeNumeroCheque.setText("");

        }
    }

    @FXML
    private void vModiferReglementEspece(ActionEvent event) {

        if (textFModifierReglementEspece.getText().equals("") || Double.valueOf(textFModifierReglementEspece.getText()) == 0) {

            creerAlerte("champs montant vide !", AlertType.WARNING).showAndWait();

        } else {

            if (factureAchatG != null) {

                double ancientM = reglementFournisseurEspeceG.getMontant();

                reglementFournisseurEspeceG.setMontant(Double.valueOf(textFModifierReglementEspece.getText()));
                reglementFournisseurEspeceG.setFactureAchat(factureAchatG);

                serviceReglementFournisseurEspece.modifier(reglementFournisseurEspeceG, ancientM);

                reglementFournisseurEspeceG = null;
                factureAchatG = null;

                chargerReglementFournisseurEspece();
                paneGReglementFournisseurEspece.setVisible(true);
                new ZoomIn(paneGReglementFournisseurEspece).play();
                paneGReglementFournisseurEspece.toFront();

            } else {
                creerAlerte("Selectionner une facture ! ", AlertType.WARNING).showAndWait();
            }

        }

    }

}
