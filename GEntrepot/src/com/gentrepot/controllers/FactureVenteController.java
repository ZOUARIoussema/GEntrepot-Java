/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gentrepot.controllers;

import com.gentrepot.models.FactureVente;
import com.gentrepot.services.ServiceBonLivraison;
import com.gentrepot.services.ServiceCommandeVente;
import com.gentrepot.services.ServiceFactureVente;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.time.LocalDate;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class FactureVenteController implements Initializable {

    @FXML
    private JFXTextField text_totalfac;
    @FXML
    private JFXTextField text_frais;
    @FXML
    private JFXTextField text_timbre;
    @FXML
    private JFXButton btnValiderF;
    @FXML
    private JFXDatePicker date_ech;

    /**
     * Initializes the controller class.
     * 
     * 
     */
    
    ServiceCommandeVente serviceCommandeVente = new  ServiceCommandeVente();
    ServiceFactureVente serviceFactureVente = new  ServiceFactureVente();
    
    ServiceBonLivraison serviceBonLivraison = new ServiceBonLivraison();
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        
       System.out.println(AdminController.blG.getId()); 
        System.out.println(AdminController.blG.getCommandeVente()); 
    }    

    @FXML
    private void ValiderFacture(ActionEvent event) {
        
        
        LocalDate date = date_ech.getValue();
        
        
        double totalF=Double.valueOf(text_frais.getText())+Double.valueOf(text_timbre.getText())+AdminController.blG.getCommandeVente().getTotalC();
        
        
      // 
        FactureVente f = new FactureVente(new Date(), java.sql.Date.valueOf(date),totalF, "non payée", 0, totalF, Double.valueOf(text_timbre.getText()), Double.valueOf(text_frais.getText()), AdminController.blG);
        
        
        serviceFactureVente.ajouterFactureV(f);
        
        f.getBonLivraison().setEtat("facturé");
        
        serviceBonLivraison.modifierBonLiv(f.getBonLivraison());
        
        
        f.getBonLivraison().getCommandeVente().setEtat("Valider");
            
            
           serviceCommandeVente.modifierCommande( f.getBonLivraison().getCommandeVente());
    }

    }
    

