/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gentrepot.services;

import com.gentrepot.models.Fournisseur;
import java.util.List;

/**
 *
 * @author oussema
 */
public class ServiceFournisseur {
    @Override
    public List<Fournisseur> afficher() {
     
    
    }
    public Fournisseur rechercher(List<Fournisseur> et, int in){
        int a = 0;
        for(int i=0;i<et.size();i++){
            if(et.get(i).getId() == in){
                a = i;
            }
        }
        return et.get(a);
    }
}
