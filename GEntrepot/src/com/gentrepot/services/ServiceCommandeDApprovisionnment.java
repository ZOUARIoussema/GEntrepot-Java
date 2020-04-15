/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gentrepot.services;

import com.gentrepot.models.CommandeDApprovisionnement;
import com.gentrepot.models.Fournisseur;
import com.gentrepot.utils.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author oussema
 */
public class ServiceCommandeDApprovisionnment implements IService<CommandeDApprovisionnement> {
    
    Connection cnx = DataSource.getInstance().getCnx();
    
    
    
    
    

    @Override
    public void ajouter(CommandeDApprovisionnement ca) {
        try {

            
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd"); // your template here
            java.util.Date dateStr = formatter.parse(ca.getDateCreation());
            java.sql.Date dateDB = new java.sql.Date(dateStr.getTime());
            String requete = "INSERT INTO commande_d_aprovisionnement (id_fournisseur,total_c,dateCreation,etat,taux_remise,total_tva) VALUES (?,?,?,?,?,?)";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1, ca.getFournisseur().getId());
            pst.setDouble(2, ca.getTotalC());
            pst.setDate(3, dateDB);
            pst.setString(4, ca.getEtat());
            pst.setDouble(5, ca.getTauxRemise());
            pst.setDouble(6, ca.getTotalTva());
            pst.executeUpdate();
            System.out.println("commande ajoutée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        } catch (ParseException ex) {
            Logger.getLogger(ServiceCommandeDApprovisionnment.class.getName()).log(Level.SEVERE, null, ex);
        }  
    }

    @Override
    public void supprimer(CommandeDApprovisionnement ca) {
        try {
            String requete = "DELETE FROM commande_d_aprovisionnement WHERE numeroC=" + ca.getNumeroC();
            Statement st = cnx.createStatement();
            st.executeUpdate(requete);
            System.out.println("commande supprimée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public void modifier(CommandeDApprovisionnement ca) {
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd"); // your template here
            java.util.Date dateStr = formatter.parse(ca.getDateCreation());
            java.sql.Date dateDB = new java.sql.Date(dateStr.getTime());
            String requete = "UPDATE commande_d_aprovisionnement SET id_fournisseur=?,total_c=?,dateCreation=?,etat=?,taux_remise=?,total_tva=? WHERE numeroC=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1, ca.getFournisseur().getId());
            pst.setDouble(2, ca.getTotalC());
            pst.setDate(3, dateDB);
            pst.setString(4, ca.getEtat());
            pst.setDouble(5, ca.getTauxRemise());
            pst.setDouble(6, ca.getTotalTva());
            pst.setInt(7, ca.getNumeroC());
            pst.executeUpdate();
            System.out.println("commande modifiée !");
    
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        } catch (ParseException ex) {
            Logger.getLogger(ServiceCommandeDApprovisionnment.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<CommandeDApprovisionnement> afficher() {
        List<CommandeDApprovisionnement> list = new ArrayList<>();
        ServiceFournisseur f = new ServiceFournisseur();
        try {
            String requete = "SELECT * FROM commande_d_aprovisionnement";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                list.add(new CommandeDApprovisionnement(rs.getInt(2), rs.getDouble(3), rs.getString(4), rs.getString(5), rs.getDouble(6), rs.getDouble(7), new Fournisseur(rs.getInt(1),"SA",56562222,"Alger","Apple@gmail.com","AG441",65)));
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return list;
    
    }
    public CommandeDApprovisionnement lastCmd() {
        CommandeDApprovisionnement ca = null;
        try {
            String requete = "SELECT * FROM commande_d_aprovisionnement ORDER BY numeroC DESC LIMIT 1";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(requete);
            if ( rs.next() ){
            ca = new CommandeDApprovisionnement(rs.getInt(2), rs.getDouble(3), rs.getString(4), rs.getString(5), rs.getDouble(6), rs.getDouble(7), new Fournisseur(rs.getInt(1),"SA",56562222,"Alger","Apple@gmail.com","AG441",65));
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return ca;
    
    }
    public CommandeDApprovisionnement rechercher(List<CommandeDApprovisionnement> l, int in){
        int a = 0;
        for(int i=0;i<l.size();i++){
            if(l.get(i).getNumeroC() == in){
                a = i;
            }
        }
        return l.get(a);
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
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
