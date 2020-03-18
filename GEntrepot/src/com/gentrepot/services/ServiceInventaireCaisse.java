/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gentrepot.services;

import com.gentrepot.models.FactureVente;
import com.gentrepot.models.InventaireCaisse;
import com.gentrepot.models.RecouvrementClientCheque;
import com.gentrepot.utils.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author oussema
 */
public class ServiceInventaireCaisse implements IService<InventaireCaisse> {

    Connection cnx = DataSource.getInstance().getCnx();

    @Override
    public void ajouter(InventaireCaisse i) {

        try {
            String requete = "INSERT INTO inventaire_caisse (date_creation,solde_calculer,solde_theorique,solde_cheque,solde_espece,ecart) VALUES (?,?,?,?,?,?)";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setDate(1, new java.sql.Date(i.getDateCreation().getTime()));
            pst.setDouble(2, i.getSoldeCalculer());
            pst.setDouble(3, i.getSoldeTheorique());
            pst.setDouble(4, i.getSoldeCheque());
            pst.setDouble(5, i.getSoldeEspece());
            pst.setDouble(6, i.getEcart());
            pst.executeUpdate();
            System.out.println("Inventaire caisse ajoutée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

    }

    @Override
    public void supprimer(InventaireCaisse i) {

        try {
            String requete = "DELETE FROM inventaire_caisse WHERE id=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1, i.getId());
            pst.executeUpdate();
            System.out.println("Inventaire caisse  supprimée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

    }

    @Override
    public void modifier(InventaireCaisse i) {

        try {
            String requete = "update inventaire_caisse  set date_creation=?,solde_calculer=?,solde_theorique=?,solde_cheque=?,solde_espece=?,ecart=? where id=?  ";

            PreparedStatement pst = cnx.prepareStatement(requete);

            pst.setDate(1, new java.sql.Date(i.getDateCreation().getTime()));
            pst.setDouble(2, i.getSoldeCalculer());
            pst.setDouble(3, i.getSoldeTheorique());
            pst.setDouble(4, i.getSoldeCheque());
            pst.setDouble(5, i.getSoldeEspece());
            pst.setDouble(6, i.getEcart());
            pst.setInt(7, i.getId());

            pst.executeUpdate();
            System.out.println("Inventaire caiise modifiée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

    }
    

    @Override
    public List<InventaireCaisse> afficher() {
        
        
          
         List<InventaireCaisse> list = new ArrayList<>();

        try {
            String requete = "SELECT * FROM inventaire_caisse";
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                
                
                
                
                list.add( new InventaireCaisse(rs.getInt(1),rs.getDate(2), rs.getDouble(3), rs.getDouble(4), rs.getDouble(5), rs.getDouble(6), rs.getDouble(7)));
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return list;
        
        
        

    }

}
