/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gentrepot.services;

import com.gentrepot.models.Entrepot;
import com.gentrepot.models.Perte;
import com.gentrepot.utils.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author oussema
 */
public class ServiceEntrepot implements IService<Entrepot>{
    Connection cnx = DataSource.getInstance().getCnx();

    @Override
    public void ajouter(Entrepot et) {
        try {
            String requete = "INSERT INTO entrepot (matriculeFiscal,adresse,adresseMail,numeroTel) VALUES ('" + et.getMatriculeFiscale() + "','" + et.getAdresse() + "','" + et.getAdresseMail() + "','" + et.getNumeroTel() + "')";
            Statement st = cnx.createStatement();
            st.executeUpdate(requete);
            System.out.println("Entrepot ajouté !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }  
    }

    @Override
    public void supprimer(Entrepot et) {
        try {
            String requete = "DELETE FROM entrepot WHERE matriculeFiscal=" + et.getMatriculeFiscale();
            Statement st = cnx.createStatement();
            st.executeUpdate(requete);
            System.out.println("Entrepot supprimé !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public void modifier(Entrepot et) {
        try {
            String requete = "UPDATE entrepot SET adresse='" + et.getAdresse() + "',adresseMail='" + et.getAdresseMail() + "',numeroTel='" + et.getNumeroTel() + "' WHERE matriculeFiscal=" + et.getMatriculeFiscale();
            Statement st = cnx.createStatement();
            st.executeUpdate(requete);
            System.out.println("Entrepot modifiée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public List<Entrepot> afficher() {
        List<Entrepot> list = new ArrayList<>();
        
        try {
            String requete = "SELECT * FROM entrepot";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                list.add(new Entrepot(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5)));
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return list;
    
    }
    public Entrepot rechercher(List<Entrepot> l, String mf){
        int a = 0;
        for(int i=0;i<l.size();i++){
            if(l.get(i).getMatriculeFiscale().equals(mf)){
                a = i;
            }
        }
        return l.get(a);
    }
    
}
