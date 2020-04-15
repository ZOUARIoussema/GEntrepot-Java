/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gentrepot.services;

import com.gentrepot.models.Perte;
import com.gentrepot.models.ProduitAchat;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author oussema
 */
public class ServiceProduitAchat implements IService<ProduitAchat>{
    @Override
    public List<ProduitAchat> afficher() {
     
    
        
        return new ArrayList<ProduitAchat>();
    }
    public ProduitAchat rechercher(List<ProduitAchat> et, String nom){
        int a = 0;
        for(int i=0;i<et.size();i++){
            if(et.get(i).getReference().equals(nom)){
                a = i;
            }
        }
        return et.get(a);
    }

    @Override
    public void ajouter(ProduitAchat t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void supprimer(ProduitAchat t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void modifier(ProduitAchat t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
