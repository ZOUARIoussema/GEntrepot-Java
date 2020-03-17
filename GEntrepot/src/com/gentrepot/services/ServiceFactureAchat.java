/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gentrepot.services;

import com.gentrepot.models.FactureAchat;
import com.gentrepot.utils.DataSource;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author oussema
 */
public class ServiceFactureAchat implements IService<FactureAchat>{
    
    
     Connection cnx = DataSource.getInstance().getCnx();

    @Override
    public void ajouter(FactureAchat f) {
        
        
    }

    @Override
    public void supprimer(FactureAchat t) {
    }

    @Override
    public void modifier(FactureAchat t) {
    }

    @Override
    public List<FactureAchat> afficher() {
        
        return new ArrayList<>();
    }
    
}
