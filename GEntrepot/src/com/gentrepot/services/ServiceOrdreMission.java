/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gentrepot.services;

import com.gentrepot.models.BonLivraison;
import com.gentrepot.models.OrdreMission;
import com.gentrepot.utils.DataSource;
import java.sql.Array;
import java.sql.Connection;
import java.sql.Date;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;



import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author oussema
 */
public class ServiceOrdreMission  implements IService<OrdreMission> {
    
    ServiceBonLivraison sb = new ServiceBonLivraison();
     ServiceAideChauffeur sa = new ServiceAideChauffeur();
      ServiceChauffeur sch = new ServiceChauffeur();
       ServiceVehicule sv = new ServiceVehicule();
Connection cnx = DataSource.getInstance().getCnx();

    @Override
    public void ajouter(OrdreMission A) {
try {
            String requete = "INSERT INTO ordremission (id_vehicule  ,id_chauffeur ,id_aidechauff,datecreation,datesortie,dateretour ) VALUES (?,?,?,?,?,?);";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1, A.getVehicule().getMatricule());
            System.err.println("1");
            pst.setString(2, A.getChauffeur().getCin());
             pst.setString(3, A.getAideChauffeur().getCin());
             System.err.println("2");
              pst.setDate(4, A.getDateCreation());
               pst.setDate(6,  A.getDateRetour());
                pst.setDate(5,  A.getDateSortie()); 
                System.err.println("3");
                //Object [] ob = new Object[A.getBonLivraisons().size()];
                
               
              //  Array hama = cnx.createArrayOf("bon_livraison", A.getBonLivraisons().toArray());
              
               
            pst.executeUpdate();
            System.out.println("ordre de mission ajouté !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

       ServiceBonLivraison sb = new ServiceBonLivraison();
       for(BonLivraison b :A.getBonLivraisons())
       {    b.setOrdreMission(A);
           sb.modifierBonLivordre(b);
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
                    + "=?,aidechauff=?,datecreation=?,datesortie=?,dateretour=? WHERE id=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
             pst.setObject(1, A.getVehicule());
            pst.setObject(2, A.getAideChauffeur());
             pst.setObject(3, A.getAideChauffeur());
              pst.setDate(4, (java.sql.Date) (Date) A.getDateCreation());
               pst.setDate(6, (java.sql.Date) (Date) A.getDateRetour());
                pst.setDate(5, (java.sql.Date) (Date) A.getDateSortie());     
            pst.executeUpdate();
            System.out.println("ordre de mission modifiée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    @Override
    public ObservableList<OrdreMission> afficher() {
     ObservableList<OrdreMission> c = FXCollections.observableArrayList();
        ObservableList<OrdreMission> bon = FXCollections.observableArrayList();


        try {
            String requete = "SELECT * FROM ordremission";
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                
                
               Date dateC = rs.getDate("dateSortie");
               Date dateS = rs.getDate("dateSortie");

                Date dateR = rs.getDate("dateRetour");

                OrdreMission o = new OrdreMission(rs.getInt(1), dateC, dateS, dateR);
                
                o.setVehicule(sv.findByMatricule(rs.getInt(2)));
              
                o.setChauffeur(sch.findByCin(rs.getString(3)));
                o.setAideChauffeur(sa.findByCin(rs.getString(4)));
                ////o.setBonLivraisons((List<BonLivraison>) sb.findById(rs.getInt()));
                
                bon.add(o);
                
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return bon;

    }
    
         public ObservableList<BonLivraison> afficherBon() {
        ObservableList<BonLivraison> bon =  FXCollections.observableArrayList();


        try {
            String requete = "SELECT * FROM bon_livraison";
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                
                Integer id =rs.getInt("id");
                String adresseLivraison = rs.getString("adresseLivraison");
                String etat = rs.getString("etat");
                java.util.Date dateC = rs.getDate("dateCreation");
                  java.util.Date dates = rs.getDate("datesortie");
                String nom = rs.getString("nom");
                String prenom = rs.getString("prenom");
                

                

                BonLivraison bl = new BonLivraison(id,adresseLivraison, etat, dateC,dates, nom, prenom);
                
                //bl.setCommandeVente(serviceCommandeVente.findById(rs.getInt(9)));
                
                bon.add(bl);
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return bon;

    }
    
    
    }
    

