/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gentrepot.controllers;

import com.gentrepot.models.ProduitAchat;
import com.gentrepot.models.SousCategorieAchat;
import com.gentrepot.services.ServiceProduitAchat;
import com.gentrepot.services.ServiceSousCategorieAchat;
import com.gentrepot.utils.DataSource;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
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
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;
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
    private Button btn;
    @FXML
    private ComboBox<?> sousC;
    @FXML
    private ImageView imv;
    
    int c;
    int file;
    File pDir;
    File pfile;
    String lien;
    
    
    
    
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
        c = (int) (Math.random() * (300000 - 2 + 1)) + 2;
        pDir = new File("C:/wamp64/www/ProjetSymfonyMaster/PROJET-SYMFONY-GENTREPOT/Gentrepot/web/uploads/images/Profile" + c + ".jpg");
        lien = "Profile" + c + ".jpg";
        
        sousC.setItems(catlist());
        // TODO
    }    

    @FXML
    private void AjouterProduit(ActionEvent event) throws IOException {
        
                 if( R.getText().equals("")  || lib.getText().equals("")|| type.getText().equals("") ||  des.getText().equals("")|| tva.getText().equals(0)|| prixV.getText().equals(0)||  dP.getText().equals(0) ||  classe.getText().equals("") || dim.getText().equals(0) || qte.equals(0) || qteS.equals(0)   ){
              
       JOptionPane.showMessageDialog(null, "Vérifier votre champs");
          }
                 else if (  qte.getText().matches("^[a-zA-Z]+$") || qteS.getText().matches("^[a-zA-Z]+$") || dim.getText().matches("^[a-zA-Z]+$") || tva.getText().matches("^[a-zA-Z]+$") ){
               
       JOptionPane.showMessageDialog(null,"Vérifier vos Entier");
    
    }
                 else{
        
         ServiceProduitAchat sp = new ServiceProduitAchat();
         String req = "select * from sous_categorie_achat where name=?";
        try{
                  PreparedStatement pst = cnx.prepareStatement(req);
                  pst.setString(1, (String)sousC.getSelectionModel().getSelectedItem());
                  ResultSet rs =pst.executeQuery();
                  while(rs.next()){
                     SousCategorieAchat   c= new SousCategorieAchat(rs.getInt(1),rs.getString(2));
                     copier(pfile,pDir);
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
            
                     ProduitAchat w = new ProduitAchat(a,b,z,d,e,f,g,h,i,j,k,lien,lien,lien,lien,lien,c.getId());
                      sp.ajouter(w);
                      System.out.println(c.getId());
                      System.out.println(c.getNom());
                      
                  }
              }           catch(SQLException ex) {
            System.out.println(ex.getMessage());
        }
         // sp.ajouter(new ProduitAchat(R.getText(), lib.getText(), 0, classe.getText(), 0, 0, 0, 0, des.getText(), type.getText(), 0, im1.getText(), im2.getText(), im3.getText(), im4.getText(), im5.getText(), new SousCategorieAchat(sousC.getText())));
        
        JOptionPane.showMessageDialog(null, "Produit ajoutée !");
        Parent root = FXMLLoader.load(getClass().getResource("Acceuil.fxml"));
                Scene scene = new Scene(root);
                Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
                window.setScene(scene);
                window.show();
    }
                 }

    @FXML
    private void Upload(ActionEvent event) throws MalformedURLException {
                 FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choisir une image: ");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                new FileChooser.ExtensionFilter("JPEG", "*.jpeg"),
                new FileChooser.ExtensionFilter("PNG", "*.png"),
                new FileChooser.ExtensionFilter("BMP", "*.bmp")
        );
        Window stage = null;
        pfile = fileChooser.showOpenDialog(stage);

        /* - draw image */
        if (pfile != null) {
            file=1;
            Image image = new Image(pfile.toURI().toURL().toExternalForm());
            imv.setImage(image);
    }
    
}
    
         public static boolean copier(File source, File dest) {
        try (InputStream sourceFile = new java.io.FileInputStream(source);
                OutputStream destinationFile = new FileOutputStream(dest)) {
            // Lecture par segment de 0.5Mo  
            byte buffer[] = new byte[512 * 1024];
            int nbLecture;
            while ((nbLecture = sourceFile.read(buffer)) != -1) {
                destinationFile.write(buffer, 0, nbLecture);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return false; // Erreur 
        }
        return true; // Résultat OK   
    }
    
}
