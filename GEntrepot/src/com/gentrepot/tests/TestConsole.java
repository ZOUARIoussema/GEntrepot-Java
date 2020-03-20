/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gentrepot.tests;

import com.gentrepot.models.CommandeDApprovisionnement;
import com.gentrepot.models.FactureAchat;
import com.gentrepot.models.FactureVente;
import com.gentrepot.models.Fournisseur;
import com.gentrepot.models.InventaireCaisse;
import com.gentrepot.models.LettreDeRelance;
import com.gentrepot.models.RecouvrementClientCheque;
import com.gentrepot.models.RecouvrementClientEspece;
import com.gentrepot.models.ReglementFournisseurCheque;
import com.gentrepot.models.ReglementFournisseurEspece;
import com.gentrepot.services.ServiceCommandeDApprovisionnment;
import com.gentrepot.services.ServiceFactureAchat;
import com.gentrepot.services.ServiceInventaireCaisse;
import com.gentrepot.services.ServiceLettreDeRelance;
import com.gentrepot.services.ServiceRecouvrementClientCheque;
import com.gentrepot.services.ServiceRecouvrementClientEspece;
import com.gentrepot.services.ServiceReglementFournisseurCheque;
import com.gentrepot.services.ServiceReglementFournisseurEspece;
import java.util.Date;
import static javafx.application.Application.launch;

/**
 *
 * @author oussema
 */
public class TestConsole {

    public static void main(String[] args) {

        ServiceFactureAchat serviceFactureAchat = new ServiceFactureAchat();
        ServiceCommandeDApprovisionnment serviceCommandeDApprovisionnment = new ServiceCommandeDApprovisionnment();

        Fournisseur fournisseur = new Fournisseur(1, "fournissseur1", 24812689, "tunis", "fournisseur1@gmail.com", "2", 0);

        CommandeDApprovisionnement commande = new CommandeDApprovisionnement(789, 0, new Date(), "non paye", 0, 0, fournisseur);

        FactureAchat factureAchat = new FactureAchat(3, new Date(), new Date(), 0, "non paye", 0, 0, 0, 0, commande);

        System.out.println("***********  facture achat *********** ");
        System.out.println("*********** Ajouter facture achat *********** ");

        serviceFactureAchat.ajouter(factureAchat);

        System.out.println("*********** Modifier etat commande d'approvisionnement *********** ");

        CommandeDApprovisionnement c = factureAchat.getCommandeDApprovisionnement();

        c.setEtat("facturer");

        serviceCommandeDApprovisionnment.modifierEtatCommande(c);

        System.out.println("*********** Afficher facture achat *********** ");
        for (FactureAchat f : serviceFactureAchat.afficher()) {

            System.out.println(f);

        }

        System.out.println("*********** ******** *********** ");

        System.out.println("*********** Reglement fourniseeur *********** ");

        ServiceReglementFournisseurEspece serviceReglementFournisseurEspece = new ServiceReglementFournisseurEspece();

        ServiceReglementFournisseurCheque serviceReglementFournisseurCheque = new ServiceReglementFournisseurCheque();

        System.out.println("*********** Ajouter Reglement fourniseeur en espece *********** ");

        ReglementFournisseurEspece reglementFournisseurEspece = new ReglementFournisseurEspece(factureAchat, 50, new Date());

        serviceReglementFournisseurEspece.ajouter(reglementFournisseurEspece);
        System.out.println("********* Afficher reglement fournisseur espece *********");

        for (ReglementFournisseurEspece r : serviceReglementFournisseurEspece.afficher()) {

            System.out.println(r);

        }

        System.out.println("******************* Modifer reglement fournisseur espece****** ");

        reglementFournisseurEspece.setId(5);
        reglementFournisseurEspece.setMontant(25);

        serviceReglementFournisseurEspece.modifier(reglementFournisseurEspece);

        System.out.println("******************* Supprimer reglement fournisseur espece****** ");

        reglementFournisseurEspece.setId(21);
        serviceReglementFournisseurEspece.supprimer(reglementFournisseurEspece);

        System.out.println("********* Afficher reglement fournisseur espece *********");

        for (ReglementFournisseurEspece r : serviceReglementFournisseurEspece.afficher()) {

            System.out.println(r);

        }

        System.out.println("********* Ajouter reglement fournisseur Cheque *********");

        ReglementFournisseurCheque reglementFournisseurCheque = new ReglementFournisseurCheque(new Date("2020/03/17"), 11135, factureAchat, 50, new Date());

        serviceReglementFournisseurCheque.ajouter(reglementFournisseurCheque);

        System.out.println("********* Modifier reglement fournisseur Cheque *********");

        reglementFournisseurCheque.setId(4);
        reglementFournisseurCheque.setMontant(50);
        serviceReglementFournisseurCheque.modifier(reglementFournisseurCheque);

        System.out.println("********* Syupprimer reglement fournisseur Cheque *********");

        serviceReglementFournisseurCheque.supprimer(reglementFournisseurCheque);

        System.out.println("********* Afficher reglement fournisseur Cheque *********");

        for (ReglementFournisseurCheque r : serviceReglementFournisseurCheque.afficher()) {

            System.out.println(r);

        }

        System.out.println("*********  Recouvrement client  *********");

        ServiceRecouvrementClientEspece serviceRecouvrementClientEspece = new ServiceRecouvrementClientEspece();
        ServiceRecouvrementClientCheque serviceRecouvrementClientCheque = new ServiceRecouvrementClientCheque();

        System.out.println("*********  Ajouter Recouvrement client espece  *********");

        FactureVente factureVente = new FactureVente(1);

        RecouvrementClientEspece recouvrementClientEspece = new RecouvrementClientEspece(factureVente, 50, new Date());

        serviceRecouvrementClientEspece.ajouter(recouvrementClientEspece);

        System.out.println("*********  Modifier Recouvrement client espece  *********");

        recouvrementClientEspece.setId(8);
        recouvrementClientEspece.setMontant(25);
        serviceRecouvrementClientEspece.modifier(recouvrementClientEspece);

        System.out.println("*********  Supprimer Recouvrement client espece  *********");

        serviceRecouvrementClientEspece.supprimer(recouvrementClientEspece);

        System.out.println("*********  Afficher Recouvrement client espece  *********");

        for (RecouvrementClientEspece r : serviceRecouvrementClientEspece.afficher()) {

            System.out.println(r);
        }

        System.out.println("*********  ***********  *********");

        System.out.println("*********  Recouvrement Client cheque  *********");

        System.out.println("*********  Ajouter Recouvrement Client cheque  *********");

        RecouvrementClientCheque recouvrementClientCheque = new RecouvrementClientCheque(new Date("2020/03/18"), 1125, factureVente, 25, new Date());
         serviceRecouvrementClientCheque.ajouter(recouvrementClientCheque);

        System.out.println("*********  Modfier Recouvrement Client cheque  *********");

        recouvrementClientCheque.setId(4);
        recouvrementClientCheque.setMontant(55);
        serviceRecouvrementClientCheque.modifier(recouvrementClientCheque);

        System.out.println("*********  Supprimer Recouvrement Client cheque  *********");
        serviceRecouvrementClientCheque.supprimer(recouvrementClientCheque);

        System.out.println("************* Afficher Recouvremet client cheque*********");

        for (RecouvrementClientCheque r : serviceRecouvrementClientCheque.afficher()) {

            System.out.println(r);

        }

        System.out.println("************* ******  *********");

        System.out.println("**********  Inventaire caisse **********");
        
        
       
        
        System.out.println("Total recouvrement client en cheque : "+serviceRecouvrementClientEspece.totalEspece());
        
        System.out.println("Total recouvrement clent en cheque : "+serviceRecouvrementClientCheque.totalCheque());
        
        
        
        
        

        ServiceInventaireCaisse serviceInventaireCaisse = new ServiceInventaireCaisse();

        System.out.println("********** Ajouter Inventaire caisse **********");

        InventaireCaisse inventaireCaisse = new InventaireCaisse(new Date(), 25, serviceRecouvrementClientCheque.totalCheque()+serviceRecouvrementClientEspece.totalEspece(), serviceRecouvrementClientCheque.totalCheque(), serviceRecouvrementClientEspece.totalEspece(), 25);

        serviceInventaireCaisse.ajouter(inventaireCaisse);

        System.out.println("********** Modifer Inventaire caisse **********");
        inventaireCaisse.setId(20);
        inventaireCaisse.setEcart(0);
        serviceInventaireCaisse.modifier(inventaireCaisse);

        System.out.println("********** Supprimer Inventaire caisse **********");

        inventaireCaisse.setId(21);
        serviceInventaireCaisse.supprimer(inventaireCaisse);

        System.out.println("**********  Afficher inventaire  **********");

        for (InventaireCaisse i : serviceInventaireCaisse.afficher()) {

            System.out.println(i);

        }

        System.out.println("**********  **********  **********");

        System.out.println("*********** lettre de relance**************");

        ServiceLettreDeRelance serviceLettreDeRelance = new ServiceLettreDeRelance();

        System.out.println("**********   Ajouter lettre de relance ***********");

        LettreDeRelance lettreDeRelance = new LettreDeRelance(new Date(), factureVente);
        serviceLettreDeRelance.ajouter(lettreDeRelance);
        
        
         System.out.println("**********   supprimer lettre de relance ***********");
         lettreDeRelance.setId(3);
         serviceLettreDeRelance.supprimer(lettreDeRelance);
        
        System.out.println("*********  Afficher lettre de relance  *******");
        
        for(LettreDeRelance l :serviceLettreDeRelance.afficher()){
            
            
            System.out.println(l);
            
        }
        
        System.out.println("********* *******  *******");
        
        
        
       
        
        
      
        
        
        

    }

}
