/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gentrepot.services;

import com.gentrepot.models.InventaireStock;
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
public class ServiceInventaireStock   implements IService<InventaireStock>{
        
        
       
    Connection cnx = DataSource.getInstance().getCnx();

    @Override
    public void ajouter(InventaireStock iv) {
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd"); // your template here
            java.util.Date dateStr = formatter.parse(iv.getDateCreation());
            java.sql.Date dateDB = new java.sql.Date(dateStr.getTime());
            String requete = "INSERT INTO inventaire_stock (ref_produit,id_emplacement,dateCreation,quantiteInventaire,ecart,quantiteTheorique) VALUES (?,?,?,?,?,?)";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setString(1, iv.getProduitAchat().getReference());
            pst.setInt(2, iv.getEmplacement().getId());
            pst.setDate(3, dateDB);
            pst.setInt(4, iv.getQunatiteInventiare());
            pst.setInt(5, iv.getEcart());
            pst.setInt(6, iv.getQuantiteTheorique());
            pst.executeUpdate();
            System.out.println("inventaire ajoutée !");
            
            
  
            
            

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        } catch (ParseException ex) {
            Logger.getLogger(ServiceInventaireStock.class.getName()).log(Level.SEVERE, null, ex);
        }  
    }

    @Override
    public void supprimer(InventaireStock iv) {
        try {
            String requete = "DELETE FROM inventaire_stock WHERE id=" + iv.getId();
            Statement st = cnx.createStatement();
            st.executeUpdate(requete);
            System.out.println("InventaireStock  supprimé !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public void modifier(InventaireStock iv) {
        try {
            String requete = "UPDATE inventaire_stock SET ref_produit='" + iv.getProduitAchat().getReference() + "',id_emplacement='" + iv.getEmplacement().getId() + "',dateCreation='" + iv.getDateCreation() + "',quantiteInventaire='" + iv.getQunatiteInventiare() + "',ecart='" + iv.getEcart() + "',quantiteTheorique='" + iv.getQuantiteTheorique() + "' WHERE id=" + iv.getId();
            Statement st = cnx.createStatement();
            st.executeUpdate(requete);
            System.out.println("InventaireStock modifié !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public List<InventaireStock> afficher() {
        List<InventaireStock> list = new ArrayList<>();
        ServiceEmplacement sp = new ServiceEmplacement();
        ServiceProduitAchat sa = new ServiceProduitAchat();
        try {
            String requete = "SELECT * FROM inventaire_stock";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                list.add(new InventaireStock(rs.getInt(1), sa.rechercher(sa.afficher(),rs.getString(2)), sp.rechercher(sp.afficher(),rs.getInt(3)), rs.getString(4), rs.getInt(5), rs.getInt(6), rs.getInt(7)));
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return list;
    
    }
    public List<InventaireStock> rechercherInventaireStockParDate(List<InventaireStock> l, Date date){
        List<InventaireStock> lv = new ArrayList<>();
        for(int i=0;i<l.size();i++){
            if(l.get(i).getDateCreation().equals(date)){
                lv.add(l.get(i));
            }
        }
        return lv;
    }
    public InventaireStock rechercher(List<InventaireStock> l, int n){
        
        for(int i=0;i<l.size();i++){
            if(l.get(i).getId() == n){
                 return l.get(i);
            }
        }
        return null;
    }
    
    
}
