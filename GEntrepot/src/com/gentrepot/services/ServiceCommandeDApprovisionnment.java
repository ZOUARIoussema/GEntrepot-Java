/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gentrepot.services;

import com.gentrepot.models.CommandeDApprovisionnement;
import com.gentrepot.utils.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author oussema
 */
public class ServiceCommandeDApprovisionnment {
    
    Connection cnx = DataSource.getInstance().getCnx();
    
    /*oussema*/
    
    public void modifierEtatCommande(CommandeDApprovisionnement c){
    
    try {
            String requete = "UPDATE commande_d_aprovisionnement SET etat=? WHERE numeroC=? ";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setString(1, c.getEtat());
             pst.setInt(2, c.getNumeroC());
            
            pst.executeUpdate();
            System.out.println("Etat commande modifer modifiée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    
    }
    
    
    
    /***************/
    
    
    
}
