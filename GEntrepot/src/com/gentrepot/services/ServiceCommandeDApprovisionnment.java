/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gentrepot.services;

import com.gentrepot.models.CommandeDApprovisionnement;
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
public class ServiceCommandeDApprovisionnment implements IService<CommandeDApprovisionnement>{
    Connection cnx = DataSource.getInstance().getCnx();

    @Override
    public void ajouter(CommandeDApprovisionnement ca) {
        try {
            String requete = "INSERT INTO commande_d_aprovisionnement (id_fournisseur,total_c,dateCreation,etat,taux_remise,total_tva) VALUES ('" + ca.getFournisseur().getId() + "','" + ca.getTotalC() + "','" + ca.getDateCreation() + "','" + ca.getEtat() + "','" + ca.getTauxRemise() + "','" + ca.getTotalTva() +"')";
            Statement st = cnx.createStatement();
            st.executeUpdate(requete);
            System.out.println("commande ajoutée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
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
            String requete = "UPDATE commande_d_aprovisionnement SET id_fournisseur='" + ca.getFournisseur().getId() + "',total_c='" + ca.getTotalC() + "',dateCreation='" + ca.getDateCreation() + "',etat='" + ca.getEtat() + "',taux_remise='" + ca.getTauxRemise() + "',total_tva='" + ca.getTotalTva() + "' WHERE numeroC=" + ca.getNumeroC();
            Statement st = cnx.createStatement();
            st.executeUpdate(requete);
            System.out.println("commande modifiée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
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
                list.add(new CommandeDApprovisionnement(rs.getInt(2), rs.getDouble(3), rs.getDate(4), rs.getString(5), rs.getDouble(6), rs.getDouble(7), f.rechercher(f.afficher(),rs.getInt(1))));
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return list;
    
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
    
}
