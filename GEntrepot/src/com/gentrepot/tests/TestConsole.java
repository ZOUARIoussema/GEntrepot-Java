/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gentrepot.tests;

import com.gentrepot.models.CommandeDApprovisionnement;
import com.gentrepot.models.FactureAchat;
import com.gentrepot.models.Fournisseur;
import com.gentrepot.models.ReglementFournisseurCheque;
import com.gentrepot.models.ReglementFournisseurEspece;
import com.gentrepot.services.ServiceCommandeDApprovisionnment;
import com.gentrepot.services.ServiceFactureAchat;
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

        System.out.println("*********** Reglement fourniseeur *********** ");

        ServiceReglementFournisseurEspece serviceReglementFournisseurEspece = new ServiceReglementFournisseurEspece();

        ServiceReglementFournisseurCheque serviceReglementFournisseurCheque = new ServiceReglementFournisseurCheque();

        System.out.println("*********** Ajouter Reglement fourniseeur en espece *********** ");

        ReglementFournisseurEspece reglementFournisseurEspece = new ReglementFournisseurEspece(factureAchat, 50, new Date());

        // serviceReglementFournisseurEspece.ajouter(reglementFournisseurEspece);
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

        System.out.println("********* Afficher reglement fournisseur Cheque *********");

        for (ReglementFournisseurCheque r : serviceReglementFournisseurCheque.afficher()) {

            System.out.println(r);

        }

    }

}
