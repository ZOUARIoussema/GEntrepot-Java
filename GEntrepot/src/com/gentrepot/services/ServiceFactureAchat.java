/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gentrepot.services;

import com.gentrepot.models.CommandeDApprovisionnement;
import com.gentrepot.models.FactureAchat;
import com.gentrepot.models.Fournisseur;
import com.gentrepot.models.LigneCommandeDApprovisionnement;
import com.gentrepot.models.ProduitAchat;
import com.gentrepot.utils.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author oussema
 */
public class ServiceFactureAchat implements IService<FactureAchat>{
    
    
     Connection cnx = DataSource.getInstance().getCnx();

    @Override
    public void ajouter(FactureAchat f) {
        
        
        try {
            String requete = "INSERT INTO facture_achat VALUES (?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1,f.getNumeroF());
            pst.setDate(2,new java.sql.Date( f.getDateCreation().getTime()));
            pst.setDate(3,new java.sql.Date( f.getDateEchaillancePaiement().getTime()));
            pst.setDouble(4, f.getTotalTTC());
            pst.setString(5, f.getEtat());
            pst.setDouble(6, 0);
            pst.setDouble(7, f.getTotalTTC());
            pst.setDouble(8, f.getTimbreFiscale());
            pst.setDouble(9, f.getFraisTransport());
            pst.setInt(10, f.getCommandeDApprovisionnement().getNumeroC());
              
            pst.executeUpdate();
            System.out.println("Facture achat ajoutée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        
        
        
    }

    @Override
    public void supprimer(FactureAchat t) {
    }

    @Override
    public void modifier(FactureAchat f) {
        
         
        
        
    }

    @Override
    public List<FactureAchat> afficher() {
        
        List<FactureAchat> list = new ArrayList<>();

        try {
            String requete = "SELECT * FROM facture_achat";
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                list.add(new FactureAchat(rs.getInt(1),rs.getDate(2), rs.getDate(3), rs.getDouble(4), rs.getString(5),  rs.getDouble(6),  rs.getDouble(7),  rs.getDouble(8),  rs.getDouble(9), getById(rs.getInt(10))));
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return list;
    }
    
    
    public CommandeDApprovisionnement getById(int id){
        
        CommandeDApprovisionnement commande =null;
        
        for(CommandeDApprovisionnement c :chargerCommande()){
            
            if(c.getNumeroC()==id){
                
                commande =c;
                return commande;
                
            }
        }
        
        return commande;
        
    }
    
    
     public List<CommandeDApprovisionnement> chargerCommande() {
        
        List<CommandeDApprovisionnement> list = new ArrayList<>();

        try {
            String requete = "SELECT * FROM commande_d_aprovisionnement";
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                
                CommandeDApprovisionnement c =new CommandeDApprovisionnement(rs.getInt(2), rs.getDouble(3), rs.getDate(4), rs.getString(5), rs.getDouble(6), rs.getDouble(7), new Fournisseur(rs.getInt(1)));
                
                list.add(c);
                
                c.getLigneCommandeDApprovisionnements().addAll(this.chargerLigne(c));
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return list;
    }
    
      public List<LigneCommandeDApprovisionnement> chargerLigne(CommandeDApprovisionnement c) {
        
        List<LigneCommandeDApprovisionnement> list = new ArrayList<>();

        try {
            String requete = "SELECT * FROM ligne_commande_d_approvisionnement";
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                list.add(new LigneCommandeDApprovisionnement(rs.getInt(1), c, new ProduitAchat(rs.getString(2)), rs.getDouble(3), rs.getInt(4), rs.getDouble(5), rs.getDouble(6)));
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return list;
    }
    
    
    
}
