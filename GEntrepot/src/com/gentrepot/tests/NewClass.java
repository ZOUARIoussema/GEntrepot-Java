/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gentrepot.tests;

import com.gentrepot.models.CommandeDApprovisionnement;
import com.gentrepot.models.Fournisseur;
import com.gentrepot.services.ServiceCommandeDApprovisionnment;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;


/**
 *
 * @author oussema
 */
public class NewClass {
    public static void main(String[] args) {
        ServiceCommandeDApprovisionnment sp = new ServiceCommandeDApprovisionnment();
        //sp.ajouter(new CommandeDApprovisionnement(1000,"2019-04-01","payé",1.5,1550,new Fournisseur(2,"SA",56562222,"Alger","Apple@gmail.com","AG441",65)));
        //List<CommandeDApprovisionnement> l = sp.afficher();
            for(int i = 0 ; i < sp.afficher().size(); i++){
            System.out.println(sp.afficher().get(i).getFournisseur().getId());
        }
            System.out.println("la taille est :"+sp.afficher().size());
              
            
        
    }
}
