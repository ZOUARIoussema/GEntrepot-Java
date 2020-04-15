/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gentrepot.controllers;

import com.gentrepot.models.BonLivraison;
import com.gentrepot.models.CommandeVente;
import com.gentrepot.services.ServiceBonLivraison;
import com.gentrepot.services.ServiceCommandeVente;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class ValidationBController implements Initializable {

    @FXML
    private Button btnValider;
    @FXML
    private TextField text_nom;
    @FXML
    private TextField text_adress;
    @FXML
    private TextField text_prenom;
    
    
    
      ServiceCommandeVente serviceCommandeVente = new ServiceCommandeVente();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
       
        
        
                System.out.println(AjouterBLController.Commande);

        
        // TODO
    }    

    @FXML
    private void ValiderBL(ActionEvent event) {
        
        
         ServiceBonLivraison serviceBonLivraison = new ServiceBonLivraison();
        if(AjouterBLController.Commande !=null){
            
            
            BonLivraison Bon= new BonLivraison(String.valueOf(text_adress.getText()), "en attente", new java.sql.Date((AjouterBLController.Commande.getDateC().getTime())), (String.valueOf(text_nom.getText())), (String.valueOf(text_prenom.getText())), AjouterBLController.Commande );
            
            
            serviceBonLivraison.ajouterBon(Bon);
            
            
            Bon.setAdresseLivraison(String.valueOf(text_adress.getText()));
            
            
            
            Bon.getCommandeVente().setEtat("Livrer");
            
            
            serviceCommandeVente.modifierCommande( Bon.getCommandeVente());
            
          
            
            
            
            
        }
        
        
        
        
        
    }
    
}
