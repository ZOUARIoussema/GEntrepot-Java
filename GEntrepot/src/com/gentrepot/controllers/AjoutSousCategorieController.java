/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gentrepot.controllers;

import com.gentrepot.models.CategorieAchat;
import com.gentrepot.models.SousCategorieAchat;
import com.gentrepot.services.ServiceCategorieAchat;
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
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Mohamed
 */
public class AjoutSousCategorieController implements Initializable {
    
    Connection cnx = DataSource.getInstance().getCnx();
    ServiceCategorieAchat cs = new ServiceCategorieAchat();
    @FXML
    private TextField nom;
    @FXML
    private ComboBox<?> cat;
    @FXML
    private Button ajout;
    
    public ObservableList catlist (){
    ObservableList<String> l = FXCollections.observableArrayList();
    ObservableList<CategorieAchat> k = cs.afficher();
    Iterator<CategorieAchat> i = k.iterator();
    while(i.hasNext())
    {
        l.add(i.next().getNom());
    }
              return l;
    }
    
    
    
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cat.setItems(catlist());
        
        // TODO
    }    

    @FXML
    private void AjouterSouC(MouseEvent event) {
        ServiceSousCategorieAchat sp = new ServiceSousCategorieAchat();
        String req = "select * from categorie_achat where nom=?";
        try{
                  PreparedStatement pst = cnx.prepareStatement(req);
                  pst.setString(1, (String)cat.getSelectionModel().getSelectedItem());
                  ResultSet rs =pst.executeQuery();
                  while(rs.next()){
                    CategorieAchat   c= new CategorieAchat(rs.getInt(1),rs.getString(2));
                     String t = nom.getText();
                     SousCategorieAchat l = new SousCategorieAchat(t,c.getId());
                      sp.ajouter(l);
                      System.out.println(c.getId());
                      System.out.println(c.getNom());
                      
                  }
              }           catch(SQLException ex) {
            System.out.println(ex.getMessage());
        }
      //  sp.ajouter(new SousCategorieAchat(nom.getText(),new CategorieAchat(cat.getSelectionModel().getSelectedItem())));
        
        JOptionPane.showMessageDialog(null, "Sous categorie ajoutée !");
    }
    
}
