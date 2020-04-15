/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gentrepot.services;

import com.gentrepot.models.LigneCommandeDApprovisionnement;
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
public class ServiceLigneCommandeDApprovisionnement   implements IService<LigneCommandeDApprovisionnement>{
        
        
        
    Connection cnx = DataSource.getInstance().getCnx();

    @Override
    public void ajouter(LigneCommandeDApprovisionnement la) {
        try {
            String requete = "INSERT INTO ligne_commande_d_approvisionnement (ref_produit,prix,quantite,total,tva,numeroC_commandeAp) VALUES ('" + la.getProduitAchat().getReference() + "','" + la.getPrix() + "','" + la.getQuantite() + "','" + la.getTotal() + "','" + la.getTva() + "','" + la.getCommandeDApprovisionnement().getNumeroC() +"')";
            Statement st = cnx.createStatement();
            st.executeUpdate(requete);
            System.out.println("Ligne commande ajoutée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }  
    }

    @Override
    public void supprimer(LigneCommandeDApprovisionnement la) {
        try {
            String requete = "DELETE FROM ligne_commande_d_approvisionnement WHERE id=" + la.getId();
            Statement st = cnx.createStatement();
            st.executeUpdate(requete);
            System.out.println("Ligne commande supprimée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public void modifier(LigneCommandeDApprovisionnement la) {
        try {
            String requete = "UPDATE ligne_commande_d_approvisionnement SET ref_produit='" + la.getProduitAchat().getReference() + "',prix='" + la.getPrix() + "',quantite='" + la.getQuantite() + "',total='" + la.getTotal() + "',tva='" + la.getTva() + "',numeroC_commandeAp='" + la.getCommandeDApprovisionnement().getNumeroC() + "' WHERE id=" + la.getId();
            Statement st = cnx.createStatement();
            st.executeUpdate(requete);
            System.out.println("Ligne commande modifiée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public List<LigneCommandeDApprovisionnement> afficher() {
        List<LigneCommandeDApprovisionnement> list = new ArrayList<>();
        ServiceProduitAchat sa = new ServiceProduitAchat();
        ServiceCommandeDApprovisionnment f = new ServiceCommandeDApprovisionnment();
        try {
            String requete = "SELECT * FROM ligne_commande_d_approvisionnement";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                list.add(new LigneCommandeDApprovisionnement(rs.getInt(1), f.rechercher(f.afficher(),rs.getInt(7)), sa.rechercher(sa.afficher(),rs.getString(2)),rs.getDouble(3), rs.getInt(4), rs.getDouble(5), rs.getDouble(6)));
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return list;
    
    }
}
