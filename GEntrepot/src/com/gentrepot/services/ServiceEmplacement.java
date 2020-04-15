/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gentrepot.services;

import com.gentrepot.models.Emplacement;
import com.gentrepot.utils.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author oussema
 */
public class ServiceEmplacement  implements IService<Emplacement>{
    
    
    
    
   
    Connection cnx = DataSource.getInstance().getCnx();

    @Override
    public void ajouter(Emplacement ep) {
        try {
            String requete = "INSERT INTO emplacement (adresse,capaciteStockage,quantiteStocker,classe,matriculeFiscal_Entrepot) VALUES (?,?,?,?,?)";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setString(1, ep.getAdresse());
            pst.setInt(2, ep.getCapaciteStockage());
            pst.setInt(3, ep.getQuantiteStocker());
            pst.setString(4, ep.getClasse());
            pst.setString(5, ep.getEntrepot().getMatriculeFiscale());
            pst.executeUpdate();
            System.out.println("emplacement ajouté !");
           
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }  
    }

    @Override
    public void supprimer(Emplacement ep) {
        try {
            String requete = "DELETE FROM emplacement WHERE id=" + ep.getId();
            Statement st = cnx.createStatement();
            st.executeUpdate(requete);
            System.out.println("emplacement supprimée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public void modifier(Emplacement ep) {
        try {
            String requete = "UPDATE emplacement SET adresse='" + ep.getAdresse() + "',capaciteStockage='" + ep.getCapaciteStockage() + "',classe='" + ep.getClasse() + "',matriculeFiscal_Entrepot='" + ep.getEntrepot().getMatriculeFiscale() + "' WHERE id=" + ep.getId();
            Statement st = cnx.createStatement();
            st.executeUpdate(requete);
            System.out.println("Ligne Perte modifiée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public List<Emplacement> afficher() {
        List<Emplacement> list = new ArrayList<>();
        ServiceEntrepot sp = new ServiceEntrepot();
        ServiceProduitAchat sa = new ServiceProduitAchat();
        try {
            String requete = "SELECT * FROM emplacement";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                list.add(new Emplacement(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4), rs.getString(5),sp.rechercher(sp.afficher(),rs.getString(3))));
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return list;
    
    }
    public Emplacement rechercher(List<Emplacement> l, int in){
        int a = 0;
        for(int i=0;i<l.size();i++){
            if(l.get(i).getId() == in){
                a = i;
            }
        }
        return l.get(a);
    }
    public List<Emplacement> rechercherEmplacementVide(List<Emplacement> l, String classe){
        List<Emplacement> lv = new ArrayList<>();
        for(int i=0;i<l.size();i++){
            if((l.get(i).getClasse().equals(classe))&&(l.get(i).getQuantiteStocker() == 0)){
                lv.add(l.get(i));
            }
        }
        return lv;
    }
    
    
}
