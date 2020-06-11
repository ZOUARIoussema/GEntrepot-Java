/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gentrepot.controllers;

import com.gentrepot.models.ProduitAchat;
import com.gentrepot.services.ServiceProduitAchat;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Mohamed
 */
public class ModifierProduitController implements Initializable {
    public static ProduitAchat chsel ;

    private TextField ref;
    @FXML
    private TextField lib;
    @FXML
    private TextField qteS;
    @FXML
    private TextField classe;
    private TextField pa;
    @FXML
    private TextField tva;
    @FXML
    private TextField dim;
    @FXML
    private TextField des;
    @FXML
    private TextField type;
    
    @FXML
    private Button btn;
    private TextField qtse;
    @FXML
    private TextField R;
    @FXML
    private TextField qte;
    @FXML
    private TextField dP;
    @FXML
    private TextField prixV;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
                

         R.setText(chsel.getReference());
        lib.setText(chsel.getLibelle());
        Integer i =chsel.getQuantiteStock();
                Integer iq =chsel.getQuantiteStock();

         qte.setText(iq.toString()); 
       qteS.setText(i.toString());  
        classe.setText(chsel.getClasse());
        Double j = chsel.getDernierPrixAchat();
        dP.setText(j.toString());
        Double k =chsel.getTva();
          tva.setText(k.toString());
          Double l =chsel.getDimension();
            dim.setText(l.toString());
              des.setText(chsel.getDescription());
                type.setText(chsel.getTypeDeConditionnement());
                Double m = chsel.getPrixVente();
                  prixV.setText(m.toString()); 
     
                              
                  
    }   
/*
    private void ModifierProduit(MouseEvent event) throws IOException {
        ServiceProduitAchat  sp = new ServiceProduitAchat();
        sp.modifier(chsel,lib.getText(),Integer.parseInt(qteS.getText()),Integer.parseInt(qtse.getText()),Double.parseDouble(dim.getText()),Double.parseDouble(tva.getText()),Double.parseDouble(dim.getText()),des.getText(),type.getText(),Double.parseDouble(pv.getText()),im1.getText(),im2.getText(),im3.getText(),im4.getText(),im5.getText());
        
                System.err.println(chsel.getReference());
                Parent root = FXMLLoader.load(getClass().getResource("Acceuil.fxml"));
                Scene scene = new Scene(root);
                Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
                window.setScene(scene);
                window.show();

    }
    */

   @FXML
    void ModifierProduit(ActionEvent event) throws IOException {
        
        ServiceProduitAchat  sp = new ServiceProduitAchat();
                ProduitAchat p = new ProduitAchat(R.getText(), lib.getText(), Integer.parseInt(qte.getText()), classe.getText(), Integer.parseInt(qteS.getText()), Double.parseDouble(dP.getText()), Double.parseDouble(tva.getText()), Double.parseDouble(dim.getText()), des.getText(), type.getText(), Double.parseDouble(prixV.getText()));

        sp.modifier(p);
        
                System.err.println(chsel.getReference());
                Parent root = FXMLLoader.load(getClass().getResource("Acceuil.fxml"));
                Scene scene = new Scene(root);
                Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
                window.setScene(scene);
                window.show();

    }

    void Upload(ActionEvent event) {

    }
}
