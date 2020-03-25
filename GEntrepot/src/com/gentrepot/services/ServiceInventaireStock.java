/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gentrepot.services;

import com.gentrepot.models.Emplacement;
import com.gentrepot.models.InventaireStock;
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
public class ServiceInventaireStock implements IService<InventaireStock>{
    Connection cnx = DataSource.getInstance().getCnx();

    @Override
    public void ajouter(InventaireStock iv) {
        try {
            String requete = "INSERT INTO inventaire_stock (ref_produit,id_emplacement,dateCreation,quantiteInventaire,ecart,quantiteTheorique) VALUES ('" + iv.getProduitAchat().getReference() + "','" + iv.getEmplacement().getId() + "','" + iv.getDateCreation() + "','" + iv.getQunatiteInventiare() + "','" + iv.getEcart() + "','" + iv.getQuantiteTheorique() + "')";
            Statement st = cnx.createStatement();
            st.executeUpdate(requete);
            System.out.println(" Inventaire effectué !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
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
                list.add(new InventaireStock(rs.getInt(1), sa.rechercher(sa.afficher(),rs.getString(2)), sp.rechercher(sp.afficher(),rs.getInt(3)), rs.getDate(4), rs.getInt(5), rs.getInt(6), rs.getInt(7)));
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return list;
    
    }
    
}
