/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gentrepot.controllers;

import com.gentrepot.models.AideChauffeur;
import com.gentrepot.models.BonLivraison;
import com.gentrepot.models.Chauffeur;
import com.gentrepot.models.OrdreMission;
import com.gentrepot.models.Vehicule;
import com.gentrepot.services.ServiceAideChauffeur;
import com.gentrepot.services.ServiceBonLivraison;
import com.gentrepot.services.ServiceChauffeur;
import com.gentrepot.services.ServiceOrdreMission;
import com.gentrepot.services.ServiceVehicule;
import com.gentrepot.utils.DataSource;
import com.gentrepot.utils.PDF;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.DocumentException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;

import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Rym
 */
public class AjoutOrdreController implements Initializable {

    @FXML
    private ComboBox<Integer> txtV;
    @FXML
    private ComboBox<String> txtC;
    @FXML
    private ComboBox<String> txtA;
    @FXML
    private Button btn;
    @FXML
    private DatePicker cr;
    @FXML
    private DatePicker s;
    @FXML
    private DatePicker r;
      PDF pdf = new PDF();
  
     Connection cnx = DataSource.getInstance().getCnx();
     ServiceBonLivraison b1 =new ServiceBonLivraison();
     ServiceVehicule sv = new ServiceVehicule();
     ServiceChauffeur sc = new ServiceChauffeur();
     ServiceAideChauffeur sa = new ServiceAideChauffeur();
     ServiceOrdreMission so = new ServiceOrdreMission();
     
    @FXML
    private ComboBox<Date> txtB;
    @FXML
    private Button bh;
     
            
     
     public ObservableList tv (){
    ObservableList<Integer> l = FXCollections.observableArrayList();
    ObservableList<Vehicule> k = sv.afficher();
    Iterator<Vehicule> i = k.iterator();
    while(i.hasNext())
    {
        l.add(i.next().getMatricule());
    }
              return l;
    }
     
     public ObservableList tc (){
    ObservableList<String> l = FXCollections.observableArrayList();
    ObservableList<Chauffeur> k = sc.afficher();
    Iterator<Chauffeur> i = k.iterator();
    while(i.hasNext())
    {
        l.add(i.next().getNom());
    }
              return l;
    }

    public ObservableList tc1 (){
    ObservableList<String> l = FXCollections.observableArrayList();
    ObservableList<AideChauffeur> k = sa.afficher();
    Iterator<AideChauffeur> i = k.iterator();
    while(i.hasNext())
    {
        l.add(i.next().getNom());
    }
              return l;
    }
    
    
     public ObservableList tc2 (){
    ObservableList<Date> l = FXCollections.observableArrayList();
    ObservableList<BonLivraison> k =  so.afficherBon();
    Iterator<BonLivraison> i = k.iterator();
    while(i.hasNext())
    {   
        l.add((Date) i.next().getDateSortie()); 
    }
              return l;
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       // oumayma.add(b1);
        txtA.setItems(tc1());
        txtC.setItems(tc());
        txtV.setItems(tv());
        txtB.setItems(tc2());
        // TODO
    }    

    @FXML
    private void AjoutOrdre(MouseEvent event) throws SQLException, DocumentException, BadElementException, IOException, FileNotFoundException, InterruptedException {
        
                  
       AideChauffeur ai = sa.findBynom((String)txtA.getSelectionModel().getSelectedItem());
       Chauffeur ch=sc.findBynom((String)txtC.getSelectionModel().getSelectedItem());
       Vehicule v = sv.findByMatriculeorder(txtV.getSelectionModel().getSelectedItem());
      List <BonLivraison> b= new ArrayList<>();
             b.add( b1.findBydates((java.sql.Date) txtB.getSelectionModel().getSelectedItem()));
       
         Date s1=java.sql.Date.valueOf(s.getValue());
                     Date s2=java.sql.Date.valueOf(cr.getValue());
                    Date s3=java.sql.Date.valueOf(r.getValue());
                     OrdreMission o = new OrdreMission(v, ch, ai, s2, s1, s3, b);
                     so.ajouter(o);
                      pdf.GeneratePdf("ordreMission", o);
                     
       JOptionPane.showMessageDialog(null, "ordre de mission ajoutée !");
    }

   
    
    }
