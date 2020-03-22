/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gentrepot.tests;

import com.gentrepot.models.AideChauffeur;
import com.gentrepot.services.ServiceAideChauffeur;

/**
 *
 * @author Rym
 */
public class TestLogistique {

    public static void main(String[] args) {
        ServiceAideChauffeur sp = new ServiceAideChauffeur();
        //sp.ajouter(new AideChauffeur("0122333", "Mahmoud","salim","nabeul"));

        sp.supprimer(new AideChauffeur("0122333", "Mahmoud", "salim", "nabeul"));

        sp.modifier(new AideChauffeur("12634587", "test", "ttt", "nabeul"));
        sp.afficher().forEach(System.out::println);
        
        

    }

}
