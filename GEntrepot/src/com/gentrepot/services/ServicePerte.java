/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gentrepot.services;

import com.gentrepot.models.Perte;
import com.gentrepot.utils.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author rodrigue
 */
public class ServicePerte implements IService<Perte>{
    Connection cnx = DataSource.getInstance().getCnx();

    @Override
    public void ajouter(Perte p) {
        try {
            String requete = "INSERT INTO perte (dateCreation) VALUES ('" + p.getDate() + "')";
            Statement st = cnx.createStatement();
            st.executeUpdate(requete);
            System.out.println("Perte ajoutée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
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
                list.add(new Perte(rs.getInt(1), rs.getDate(2)));
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
    
    
}
