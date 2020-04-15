/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gentrepot.services;

import com.gentrepot.models.Perte;
import com.gentrepot.utils.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author oussema
 */
public class ServicePerte implements IService<Perte>{
    
    
    
    Connection cnx = DataSource.getInstance().getCnx();

    @Override
    public void ajouter(Perte p) {
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd"); // your template here
            java.util.Date dateStr = formatter.parse(p.getDate());
            java.sql.Date dateDB = new java.sql.Date(dateStr.getTime());
            String requete = "INSERT INTO perte (dateCreation) VALUES (?)";
            PreparedStatement pst = cnx.prepareStatement(requete);           
            pst.setDate(1, dateDB);            
            pst.executeUpdate();
            System.out.println("Perte ajoutée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        } catch (ParseException ex) {
            Logger.getLogger(ServicePerte.class.getName()).log(Level.SEVERE, null, ex);
        }  
    }

    @Override
    public void supprimer(Perte p) {
        try {
            String requete = "DELETE FROM perte WHERE id=" + p.getId();
            Statement st = cnx.createStatement();
            st.executeUpdate(requete);
            System.out.println("Perte supprimée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public void modifier(Perte p) {
        try {
            String requete = "UPDATE perte SET dateCreation='" + p.getDate() + "' WHERE id=" + p.getId();
            Statement st = cnx.createStatement();
            st.executeUpdate(requete);
            System.out.println("Perte modifiée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public List<Perte> afficher() {
        List<Perte> list = new ArrayList<>();

        try {
            String requete = "SELECT * FROM perte";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                list.add(new Perte(rs.getInt(1), rs.getString(2)));
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return list;
    
    }
    public Perte rechercher(List<Perte> l, int in){
        int a = 0;
        for(int i=0;i<l.size();i++){
            if(l.get(i).getId() == in){
                a = i;
            }
        }
        return l.get(a);
    }
    public List<Perte> rechercherPerte(List<Perte> l, Date date){
        List<Perte> lv = new ArrayList<>();
        for(int i=0;i<l.size();i++){
            if(l.get(i).getDate().equals(date)){
                lv.add(l.get(i));
            }
        }
        return lv;
    }
    public Perte lastPert() {
        Perte ca = null;
        try {
            String requete = "SELECT * FROM perte ORDER BY id DESC LIMIT 1";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(requete);
            if ( rs.next() ){
            ca = new Perte(rs.getInt(1), rs.getString(2));
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return ca;
    
    }
    
}
