/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gentrepot.services;

import com.gentrepot.models.OrdreMission;
import com.gentrepot.utils.DataSource;
import java.sql.Array;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author oussema
 */
public class ServiceOrdreMission  implements IService<OrdreMission> {
Connection cnx = DataSource.getInstance().getCnx();
    @Override
    public void ajouter(OrdreMission A) {
try {
            String requete = "INSERT INTO ordremission (id_vehicule  ,id_chauffeur ,id_aidechauff, bondelivraison,datecreation,datesortie,dateretour ) VALUES (?,?,?,?,?,?,?);";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1, A.getVehicule().getId());
            pst.setInt(2, A.getChauffeur().getId());
             pst.setInt(3, A.getAideChauffeur().getId());
              pst.setDate(5, (Date) A.getDateCreation());
               pst.setDate(7, (Date) A.getDateRetour());
                pst.setDate(6, (Date) A.getDateSortie()); 
                 pst.setArray(4, (Array) A.getBonLivraisons());    

            pst.executeUpdate();
            System.out.println("ordre de mission ajouté !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public void supprimer(OrdreMission A) {
        try {
            String requete = "DELETE FROM ordremission WHERE id=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1, A.getId());
            pst.executeUpdate();
            System.out.println("ordre demission supprimée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public void modifier(OrdreMission A) {
        try {
            String requete = "UPDATE ordremission SET vehicule=?,chauffeur"
                    + "=?,aidechauff=?,datecreation=?,datesortie=?,dateretour=? WHERE cin=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
             pst.setObject(1, A.getVehicule());
            pst.setObject(2, A.getAideChauffeur());
             pst.setObject(3, A.getAideChauffeur());
              pst.setDate(4, (Date) A.getDateCreation());
               pst.setDate(6, (Date) A.getDateRetour());
                pst.setDate(5, (Date) A.getDateSortie());     
            pst.executeUpdate();
            System.out.println("ordre de mission modifiée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    @Override
    public List<OrdreMission> afficher() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
