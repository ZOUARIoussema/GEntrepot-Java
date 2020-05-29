/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gentrepot.views;

import com.gentrepot.models.Chauffeur;
import com.gentrepot.services.ServiceChauffeur;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Rym
 */
public class UpdateChauffeurController implements Initializable {
      ObservableList<Chauffeur> cls = FXCollections.observableArrayList();
    public static Chauffeur chsel ;

    @FXML
    private TextField txtN;
    @FXML
    private TextField txtP;
  
   
    @FXML
     private TextField txtA;
    @FXML
    private Button btn;
    @FXML
    private TextField txtcin;
    @FXML
    private Button btn1;
    ServiceChauffeur sp = new ServiceChauffeur();
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        txtN.setText(chsel.getNom());
        txtP.setText(chsel.getPrenom());
       txtA.setText(chsel.getAdresse());
        
        txtcin.setText(chsel.getCin());
        
        
        
        
        // TODO
    }    
    /*private void ModifierChauffeur(ActionEvent event) throws IOException {
        ServiceChauffeur sp = new ServiceChauffeur();
        sp.modifier(new Chauffeur(txtcin.getText(), txtN.getText(), txtP.getText(), txtA.getText(),0, txtE.getText()));
        
        JOptionPane.showMessageDialog(null, "chauffeur Modifier !");
    }
     */

    @FXML
    private void modifier(MouseEvent event) throws IOException {
        
        sp.modifier(chsel,txtN.getText(), txtP.getText(), txtA.getText());
        
        System.err.println(chsel.getCin());
                Parent root = FXMLLoader.load(getClass().getResource("ChefParc.fxml"));
                Scene scene = new Scene(root);
                Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
                window.setScene(scene);
                window.show();
    }

    
      /* public void fournisseurclear()
    {
        txtN.setText("");
        txtP.setText("");
        txtA.setText("");
        txtE.setText("");
        txtcin.setText("");
      
    }*/
    @FXML
   private void SupprimerChauffeur(MouseEvent event) throws IOException {
       /* table.setItems(cls);
        ObservableList<Chauffeur> allFourn,fr ;
        allFourn=table.getItems();
        fr=table.getSelectionModel().getSelectedItems();
        Chauffeur f = fr.get(0);
        ServiceChauffeur STD = new ServiceChauffeur(); // STD = Service TAB DEMANDE
        STD.supprimer(f);
        fr.forEach(allFourn::remove);
        cls.clear();
        
        fournisseurclear();*/
       sp.supprimer(chsel);
        
       Parent root = FXMLLoader.load(getClass().getResource("ChefParc.fxml"));
                Scene scene = new Scene(root);
                Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
                window.setScene(scene);
                window.show();
       
    }
    
    }