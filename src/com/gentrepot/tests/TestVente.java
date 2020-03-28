/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gentrepot.tests;

import com.gentrepot.models.BonLivraison;
import com.gentrepot.models.CommandeVente;
import com.gentrepot.models.FactureVente;
import com.gentrepot.models.LigneCommande;
import com.gentrepot.models.ProduitAchat;
import com.gentrepot.services.ServiceBonLivraison;
import com.gentrepot.services.ServiceCommandeDApprovisionnment;
import com.gentrepot.services.ServiceCommandeVente;
import com.gentrepot.services.ServiceFactureVente;
import com.gentrepot.services.ServiceLigneCommande;
import java.util.Date;

/**
 *
 * @author LENOVO
 */
public class TestVente {
    
    
    
    public static void main(String[] args) {
        
        
       ProduitAchat p1 = new  ProduitAchat("444", "dsqd"); 
       ServiceCommandeVente  serviceCommandeVente = new ServiceCommandeVente();

        
      CommandeVente commandeVente = new CommandeVente(serviceCommandeVente.recupere()+1, 500, new Date(), "non facturer", 0);
     
     
       LigneCommande l1 = new LigneCommande(commandeVente, p1 , p1.getPrixVente(), p1.getQuantiteStock(),p1.getPrixVente(),p1.getTva());

        
        commandeVente.getLigneCommande().add(l1);

         serviceCommandeVente.ajouterCommande(commandeVente);
       
      
       
       
        
        System.out.println(serviceCommandeVente.recupere());
         
         
         
       serviceCommandeVente.afficherCommande().forEach(System.out::println);
         
        BonLivraison bl = new BonLivraison("nabeul", "en attente", new Date(), "mehdy", "ben romdhane", commandeVente);
        
         
           ServiceBonLivraison serviceBonLivraison =  new ServiceBonLivraison();

            serviceBonLivraison.ajouterBon(bl);

              
        ServiceFactureVente serviceFactureVente = new ServiceFactureVente();
        
        //ajouter id bl
        bl.setId(1);
        
         FactureVente f1 = new FactureVente(new Date(), new Date(), 2154, "en attente", 546, 564, 2154, 641, bl);
        
        serviceFactureVente.ajouterFactureV(f1);
       
        
        
        
    
}



}
