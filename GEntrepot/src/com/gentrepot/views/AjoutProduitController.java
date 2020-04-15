/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gentrepot.views;

import com.gentrepot.models.ProduitAchat;
import com.gentrepot.models.SousCategorieAchat;
import com.gentrepot.services.ServiceProduitAchat;
import com.gentrepot.services.ServiceSousCategorieAchat;
import com.gentrepot.utils.DataSource;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Mohamed
 */
public class AjoutProduitController implements Initializable {
    
     Connection cnx = DataSource.getInstance().getCnx();

    
    ServiceSousCategorieAchat scs = new ServiceSousCategorieAchat();

   
    @FXML
    private TextField R;
    @FXML
    private TextField lib;
    @FXML
    private TextField qte;
    @FXML
    private TextField classe;
    @FXML
    private TextField qteS;
    @FXML
    private TextField dP;
    @FXML
    private TextField tva;
    @FXML
    private TextField dim;
    @FXML
    private TextField des;
    @FXML
    private TextField type;
    @FXML
    private TextField prixV;
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
    private Button btn;
    @FXML
    private ComboBox<?> sousC;
    
    public ObservableList catlist (){
    ObservableList<String> l = FXCollections.observableArrayList();
    ObservableList<SousCategorieAchat> k = scs.afficher();
    Iterator<SousCategorieAchat> i = k.iterator();
    while(i.hasNext())
    {
        l.add(i.next().getNom());
    }
              return l;
    }

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        sousC.setItems(catlist());
        // TODO
    }    

    @FXML
    private void AjouterProduit(ActionEvent event) {
         ServiceProduitAchat sp = new ServiceProduitAchat();
         String req = "select * from sous_categorie_achat where name=?";
        try{
                  PreparedStatement pst = cnx.prepareStatement(req);
                  pst.setString(1, (String)sousC.getSelectionModel().getSelectedItem());
                  ResultSet rs =pst.executeQuery();
                  while(rs.next()){
                    SousCategorieAchat   c= new SousCategorieAchat(rs.getInt(1),rs.getString(2));
                     String a = R.getText();
                     String b = lib.getText();
                     int z = Integer.parseInt(qte.getText());
                     String d = classe.getText();
                     int e = Integer.parseInt(qteS.getText());
                     Double f = Double.parseDouble(dP.getText());
                     Double g = Double.parseDouble(tva.getText());
                     Double h = Double.parseDouble(dim.getText());
                     String i = des.getText();
                     String j = type.getText();
                     Double k = Double.parseDouble(prixV.getText());
                     String l = im1.getText();
                     String m = im2.getText();
                     String n = im3.getText();
                     String o = im4.getText();
                     String p = im5.getText();
                     ProduitAchat w = new ProduitAchat(a,b,z,d,e,f,g,h,i,j,k,l,m,n,o,p,c);
                      sp.ajouter(w);
                      System.out.println(c.getId());
                      System.out.println(c.getNom());
                      
                  }
              }           catch(SQLException ex) {
            System.out.println(ex.getMessage());
        }
         // sp.ajouter(new ProduitAchat(R.getText(), lib.getText(), 0, classe.getText(), 0, 0, 0, 0, des.getText(), type.getText(), 0, im1.getText(), im2.getText(), im3.getText(), im4.getText(), im5.getText(), new SousCategorieAchat(sousC.getText())));
        
        JOptionPane.showMessageDialog(null, "Produit ajoutée !");
    }
    
}
