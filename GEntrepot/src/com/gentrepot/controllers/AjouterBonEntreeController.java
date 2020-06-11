/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gentrepot.controllers;

import com.gentrepot.models.BonEntree;
import com.gentrepot.models.CategorieAchat;
import com.gentrepot.models.CommandeDApprovisionnement;
import com.gentrepot.services.ServiceBonEntree;
import com.gentrepot.services.ServiceCommandeDApprovisionnment;
import com.gentrepot.utils.DataSource;
import com.gentrepot.utils.PdfB;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.DocumentException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Date;
import java.util.Iterator;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javax.swing.JOptionPane;


/**
 * FXML Controller class
 *
 * @author Mohamed
 */
public class AjouterBonEntreeController implements Initializable {
    PdfB pdf = new PdfB ();

    @FXML
    private DatePicker dateprod;
    @FXML
    private DatePicker datexp;
    @FXML
    private ChoiceBox<Integer> commandedap;
    
    ServiceBonEntree sb = new ServiceBonEntree();

    /**
     * Initializes the controller class.
     */
  
   public ObservableList catlist (){
       ServiceCommandeDApprovisionnment sc = new ServiceCommandeDApprovisionnment();
    ObservableList<Integer> l = FXCollections.observableArrayList();
    ObservableList<CommandeDApprovisionnement> k = sc.afficherCommande();
    Iterator<CommandeDApprovisionnement> i = k.iterator();
    while(i.hasNext())
    {
        l.add(i.next().getNumeroC());
    }
              return l;
    }
                 
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
            Connection cnx = DataSource.getInstance().getCnx();
            ServiceBonEntree sb = new ServiceBonEntree();
            
            commandedap.setItems(catlist());

            
   
        // TODO
    }    

    @FXML
    private void Ajouter(ActionEvent event) throws DocumentException, BadElementException, IOException, FileNotFoundException, InterruptedException, SQLException {
         
         LocalDate d = LocalDate.now();
          Date date = java.sql.Date.valueOf(d);
          Date dp=  new java.sql.Date(  new Date(dateprod.getEditor().getText()).getTime());      
          Date de=  new java.sql.Date(  new Date(datexp.getEditor().getText()).getTime());      

          System.out.println("date prod :"+dp);
                   System.out.println("date experation :"+de);
       long diff1 = de.getTime() - date.getTime();
       long diff = dp.getTime() - date.getTime();
             
       float res1 = (diff / (1000*60*60*24));
       float res2 = (diff1 / (1000*60*60*24));
       
                 if(datexp.getValue()== null  || dateprod.getValue()==null  ){
              
              
              
                
       JOptionPane.showMessageDialog(null, "Vérifier votre champs");
          }   
                 
                        
       else if((res2<0) ){
        
               
       JOptionPane.showMessageDialog(null, "vérifier votre date");
          }
       else if( (res1 > 0)){
       
        
       
       JOptionPane.showMessageDialog(null, "Aujourd'hui est "+date);
          }
       else{
       
       
       
        System.out.println("com.gentrepot.views.AjouterBonEntreeController.Ajouter()"+commandedap.getValue());
        BonEntree be  = new BonEntree(date, (java.sql.Date)dp, (java.sql.Date)de, sb.findCommndeById(commandedap.getValue()).getNumeroC()) ;
        sb.ajouter(be);
        pdf.GeneratePdf("bonentree", be);
    }
    }
    
}
