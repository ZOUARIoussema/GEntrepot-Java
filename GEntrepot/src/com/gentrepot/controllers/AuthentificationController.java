/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gentrepot.controllers;

import animatefx.animation.ZoomIn;
import com.gentrepot.models.User;
import com.gentrepot.services.MailService;
import com.gentrepot.services.ServiceUser;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author oussema
 */
public class AuthentificationController implements Initializable {

    //user global
    
    public static User userG=null;
    
    
    ServiceUser serviceUser = new ServiceUser();
    Stage primaryStage = new Stage();
    String code;
    User userNewPassword = null;
    
    
    
    
    

    @FXML
    private Pane paneAjouterUser;
    @FXML
    private JFXTextField paneAjouterUsertextNomUser;
    @FXML
    private JFXPasswordField paneAjouterUsertextMotPasse;
    @FXML
    private JFXTextField paneAjouterUsertextAdresseMail;
    @FXML
    private JFXButton btnValider;
    @FXML
    private JFXButton btnAnnuler;
    @FXML
    private Pane paneConnection;
    @FXML
    private JFXTextField paneConnectionNUser;
    @FXML
    private JFXPasswordField paneConnectionMotPasse;
    @FXML
    private JFXButton btnCreerCompte;
    @FXML
    private Pane paneVerifierCode;
    @FXML
    private Pane paneMotPasseOublie;
    @FXML
    private Pane paneValiderNouveauMotDePasse;
    @FXML
    private JFXTextField textFUserC;
    @FXML
    private JFXTextField textFMailC;
    @FXML
    private JFXButton btnEnvoyerCode;
    @FXML
    private JFXTextField textFCode;
    @FXML
    private JFXButton btnValiderCode;
    @FXML
    private JFXPasswordField textFNewPassword;
    @FXML
    private JFXPasswordField textFVerifPasseword;
    @FXML
    private Label labelNUA;
    @FXML
    private JFXButton btnValiderNewPassword;
    @FXML
    private ImageView btnCreerNewPassword;

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

    @FXML
    private void validerConnecter(ActionEvent event) {

        User user = serviceUser.verifUser(paneConnectionNUser.getText(), paneConnectionMotPasse.getText());

        if (user != null) {

            System.out.println("correcte");
            
            userG=user;

            if (user.getRole().equals("Admin")) {

                ((Stage) this.btnValider.getScene().getWindow()).close();

                System.out.println("admin");

                try {

                    BorderPane root = (BorderPane) FXMLLoader.load(getClass().getResource("/com/gentrepot/views/MenueAdmin.fxml"));
                    Scene scene = new Scene(root);
                    primaryStage.setScene(scene);
                    primaryStage.setTitle("Menue Agent Caisse : " + user.getUsername());
                    primaryStage.show();
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }

            }

            if (user.getRole().equals("Responsable Achat")) {
                System.out.println("achat");

                ((Stage) this.btnValider.getScene().getWindow()).close();
                
                
                
                 try {

                    AnchorPane root = (AnchorPane) FXMLLoader.load(getClass().getResource("/com/gentrepot/views/Acceuil.fxml"));
                    Scene scene = new Scene(root);
                    primaryStage.setScene(scene);
                    primaryStage.setTitle("Menue Responsable Achat");
                    primaryStage.show();
                     System.out.println("nnnnnnnnnnnnnnnnn");
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                    System.out.println("bbbbbbbbbbbbbbbbbbb");
                }

            }

            if (user.getRole().equals("Responsable Vente")) {
                System.out.println("vente");
                ((Stage) this.btnValider.getScene().getWindow()).close();
                
                
                
                
                  try {

                    BorderPane root = (BorderPane) FXMLLoader.load(getClass().getResource("/com/gentrepot/views/admin.fxml"));
                    Scene scene = new Scene(root);
                    primaryStage.setScene(scene);
                    primaryStage.setTitle("Menue Vente");
                    primaryStage.show();
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }

            }

            if (user.getRole().equals("Agent Caisse")) {
                System.out.println("caisse");
                ((Stage) this.btnValider.getScene().getWindow()).close();

                try {

                    BorderPane root = (BorderPane) FXMLLoader.load(getClass().getResource("/com/gentrepot/views/MenueAgentCaisse.fxml"));
                    Scene scene = new Scene(root);
                    primaryStage.setScene(scene);
                    primaryStage.setTitle("Menue Agent Caisse : " + user.getUsername());
                    primaryStage.show();
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }

            }

            if (user.getRole().equals("Chef De Parc")) {
                System.out.println("parc");
                ((Stage) this.btnValider.getScene().getWindow()).close();
                
                
                
                try {

                    AnchorPane root = (AnchorPane) FXMLLoader.load(getClass().getResource("/com/gentrepot/views/ChefParc.fxml"));
                    Scene scene = new Scene(root);
                    primaryStage.setScene(scene);
                    primaryStage.setTitle("Menue Chef Parc : " + user.getUsername());
                    primaryStage.show();
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
                

            }

            if (user.getRole().equals("Client")) {
                System.out.println("client");
                ((Stage) this.btnValider.getScene().getWindow()).close();
                
                
                  try {

                    AnchorPane root = (AnchorPane) FXMLLoader.load(getClass().getResource("/com/gentrepot/views/menu.fxml"));
                    Scene scene = new Scene(root);
                    primaryStage.setScene(scene);
                    primaryStage.setTitle("Ajouter Commande");
                    primaryStage.show();
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }

            }

            if (user.getRole().equals("Responsable Stockage")) {
                System.out.println("Responsable Stockage");
                ((Stage) this.btnValider.getScene().getWindow()).close();

                try {

                    AnchorPane root = (AnchorPane) FXMLLoader.load(getClass().getResource("/com/gentrepot/views/STOCKAGE.fxml"));
                    Scene scene = new Scene(root);
                    primaryStage.setScene(scene);
                    primaryStage.setTitle("Menue stockage");
                    primaryStage.show();
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }

            }

        } else {
            System.out.println("incorrecte");
            creerAlerte("Non d'utlisateur ou mot de passe incorrecte ! ", Alert.AlertType.WARNING).showAndWait();

        }

    }

    @FXML
    private void annulerConnecter(ActionEvent event) {

        paneConnectionNUser.setText("");
        paneConnectionMotPasse.setText("");

    }

    @FXML
    private void creerCompte(ActionEvent event) {

        paneAjouterUser.setVisible(true);
        new ZoomIn(paneAjouterUser).play();
        paneAjouterUser.toFront();

    }

    @FXML
    private void validerajouterUser(ActionEvent event) {

        if (paneAjouterUsertextNomUser.getText().equals("")) {

            creerAlerte("Non d'utlisateur vide ! ", Alert.AlertType.WARNING).showAndWait();
            return;
        }

        if (paneAjouterUsertextMotPasse.getText().equals("")) {

            creerAlerte("mot de passe vide ! ", Alert.AlertType.WARNING).showAndWait();
            return;
        }
        if (paneAjouterUsertextAdresseMail.getText().equals("")) {

            creerAlerte("adresse mail vide ! ", Alert.AlertType.WARNING).showAndWait();
            return;
        }

        User user = new User(0, paneAjouterUsertextNomUser.getText(), paneAjouterUsertextAdresseMail.getText(), paneAjouterUsertextNomUser.getText(), paneAjouterUsertextAdresseMail.getText(), paneAjouterUsertextMotPasse.getText(), "Client");

      if( serviceUser.ajouterU(user)){

        paneConnection.setVisible(true);
        new ZoomIn(paneConnection).play();
        paneConnection.toFront();
      }

    }

    @FXML
    private void annulerAjouterUser(ActionEvent event) {

        paneConnection.setVisible(true);
        new ZoomIn(paneConnection).play();
        paneConnection.toFront();
    }

    @FXML
    private void afficherPaneMotPasseOublie(MouseEvent event) {

        paneMotPasseOublie.setVisible(true);
        new ZoomIn(paneMotPasseOublie).play();
        paneMotPasseOublie.toFront();
    }

    @FXML
    private void validerEnvoyerCode(ActionEvent event) {

        userNewPassword = serviceUser.verifLoginMail(textFUserC.getText(), textFMailC.getText());

        if (userNewPassword != null) {

            Random r = new Random();

            code = String.valueOf(r.nextInt(9999));

            MailService.EnvoyerMail(textFMailC.getText(), "code verification", "code:" + code);

            paneVerifierCode.setVisible(true);
            new ZoomIn(paneVerifierCode).play();
            paneVerifierCode.toFront();

        } else {

            creerAlerte("Non d'utlisateur ou adresse mail incorrecte ! ", Alert.AlertType.WARNING).showAndWait();
        }

    }

    @FXML
    private void validerCode(ActionEvent event) {

        if (code.equals(textFCode.getText())) {

            paneValiderNouveauMotDePasse.setVisible(true);
            new ZoomIn(paneValiderNouveauMotDePasse).play();
            paneValiderNouveauMotDePasse.toFront();

        } else {
            creerAlerte("code de verification incorrecte ! ", Alert.AlertType.WARNING).showAndWait();

        }

    }

    @FXML
    private void validerCreationPasseWord(ActionEvent event) {

        String password1 = textFNewPassword.getText();
        String password2 = textFVerifPasseword.getText();

        if (password1.equals("") || password2.equals("")) {

            creerAlerte("Remplier tout les champs ! ", Alert.AlertType.WARNING).showAndWait();
            return;
        }

        if (!(password1.equals(password2))) {

            creerAlerte("mot de passe incorrecte ! ", Alert.AlertType.WARNING).showAndWait();
            return;
        }

        userNewPassword.setPassword(password1);

        serviceUser.modifier(userNewPassword);

        paneConnection.setVisible(true);
        new ZoomIn(paneConnection).play();
        paneConnection.toFront();

    }

}
