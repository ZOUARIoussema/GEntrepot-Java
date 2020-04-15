/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gentrepot.views;

import com.gentrepot.models.ProduitAchat;
import com.gentrepot.services.ServiceProduitAchat;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Mohamed
 */
public class ModifierProduitController implements Initializable {
    public static ProduitAchat chsel ;

    @FXML
    private TextField ref;
    @FXML
    private TextField lib;
    @FXML
    private TextField qteS;
    @FXML
    private TextField classe;
    @FXML
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
    private TextField pv;
    @FXML
    private TextField im1;
    @FXML
    private TextField im2;
    @FXML
    private TextField im3;
    @FXML
    private TextField im4;
    @FXML
    private TextField im5;
    @FXML
    private TextField sc;
    @FXML
    private Button btn;
    @FXML
    private TextField qtse;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         ref.setText(chsel.getReference());
        lib.setText(chsel.getLibelle());
        Integer i =chsel.getQuantiteStock();
       qteS.setText(i.toString());
        classe.setText(chsel.getClasse());
        Double j = chsel.getDernierPrixAchat();
        pa.setText(j.toString());
        Double k =chsel.getTva();
          tva.setText(k.toString());
          Double l =chsel.getDimension();
            dim.setText(l.toString());
              des.setText(chsel.getDescription());
                type.setText(chsel.getTypeDeConditionnement());
                Double m = chsel.getPrixVente();
                  pv.setText(m.toString()); 
                  im1.setText(chsel.getImage());
                    im2.setText(chsel.getImage1());
                      im3.setText(chsel.getImage2());
                        im4.setText(chsel.getImage3());
                          im5.setText(chsel.getImage4());
                           
                              
                  
    }    /*ProduitAchat t,String l,Integer s,Integer stcs, Double d,Double tv,Double Dim ,String des ,
            String type , Double pv
            , String im , String im1 ,String im2 , String im3 , String im4)*/

    @FXML
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
    
}
