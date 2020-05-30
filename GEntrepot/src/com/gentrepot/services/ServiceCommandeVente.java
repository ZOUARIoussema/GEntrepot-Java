/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gentrepot.services;

import com.gentrepot.models.CommandeVente;
import com.gentrepot.models.LigneCommande;
import com.gentrepot.models.ProduitAchat;
import com.gentrepot.models.User;
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
public class ServiceCommandeVente {
    
    
    
    
     //methode recupere id 
    Connection cnx = DataSource.getInstance().getCnx();

    public int recupere() {

        int id = 0;

        try {
            String requete = "SELECT max(`id`) from commande_vente";
            PreparedStatement pst = cnx.prepareStatement(requete);

            ResultSet rs = pst.executeQuery();

            while (rs.next()) {

                id = rs.getInt(1);

            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return id;

    }

    //ajouter
    public void ajouterCommande(CommandeVente c) {
        try {
            String requete = "INSERT INTO commande_vente (id,totalC,dateC,etat,user) VALUES (?,?,?,?,?)";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1, c.getId());
            pst.setDouble(2, c.getTotalC());
            pst.setDate(3, new java.sql.Date(c.getDateC().getTime()));
            pst.setString(4, c.getEtat());
             pst.setInt(5, c.getUser().getId());

            pst.executeUpdate();
            System.out.println("Commande  ajoutée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        //ajouter ligne commande
        ServiceLigneCommande serviceLigneCommande = new ServiceLigneCommande();

        for (LigneCommande ligne : c.getLigneCommande()) {

            serviceLigneCommande.ajouter(ligne);

        }

    }

    public void supprimerCommande(CommandeVente c) {
        try {
            String req
                    = "DELETE FROM commande WHERE id_commande=?";

            PreparedStatement ps
                    = cnx.prepareStatement(req);
            ps.setInt(1, c.getId());
            ps.executeUpdate();

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    
    
    
    public CommandeVente findById(int id){
        
        CommandeVente c =null;
        
        
        
        for(CommandeVente co : this.afficherCommande()){
            
            
            if(co.getId()==id){
                
                c=co;
                return c;
            }
        }
        
        return c;
    }
    

    public List<CommandeVente> afficherCommande() {
        List<CommandeVente> commande = new ArrayList<>();

        ServiceLigneCommande ligneService = new ServiceLigneCommande();

        try {
            String requete = "SELECT * FROM commande_vente";
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                int totalC = rs.getInt("totalC");
                java.util.Date dateC = rs.getDate("dateC");
                String etat = rs.getString("etat");

                int tauxRemise = rs.getInt("tauxRemise");
              int user = rs.getInt("user");
                CommandeVente co = new CommandeVente(id, totalC, dateC, etat, tauxRemise,new User(user));
                co.getLigneCommande().addAll(ligneService.RecupereParCommande(co));
                commande.add(co); 
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return commande;

    }

    public void modifierCommande(CommandeVente l) {
        try {
            String requete
                    = "UPDATE commande_vente SET etat=? WHERE id=?";
            PreparedStatement pst
                    = cnx.prepareStatement(requete);
            pst.setString(1, l.getEtat());
             pst.setInt(2, l.getId());

            pst.executeUpdate();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

    }
   
     public List<CommandeVente> afficherCommandeByUser(User user) {
        List<CommandeVente> commande = new ArrayList<>();
        
        
        ServiceLigneCommande ligneService = new ServiceLigneCommande();

        try {
            String requete = "SELECT * FROM commande_vente where user ="+ user.getId();
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                int totalC = rs.getInt("totalC");
                java.util.Date dateC = rs.getDate("dateC");
                String etat = rs.getString("etat");

                int tauxRemise = rs.getInt("tauxRemise");
               // String user = rs.getString("user");
                CommandeVente co = new CommandeVente(id, totalC, dateC, etat, tauxRemise);
                co.getLigneCommande().addAll(ligneService.RecupereParCommande(co));
                commande.add(co); 
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return commande;

    }
    
    
    
    /*public List<ProduitAchat> findByLibelle(String libelle){
        
        
        List<ProduitAchat> liste = new ArrayList<>();
        
        
        
        
          try {
            String requete
                    = "select * from produit_achat where libelle=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            
            pst.setString(1, libelle);
            ResultSet res = pst.executeQuery();

            while (res.next()) {
                
 
                liste.add(new ProduitAchat(res.getString("reference"), res.getString("libelle"), res.getInt("quantiteEnStock"),res.getDouble("prixVente")));

            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        
        
        
        
        
        return  liste;
        
    }*/
    
    
    public List<ProduitAchat> afficherProduit(){
        
        
        List<ProduitAchat> liste = new ArrayList<>();
        
        
        
        
          try {
            String requete
                    = "select * from produit_achat ";
            PreparedStatement pst = cnx.prepareStatement(requete);
            
          
            ResultSet res = pst.executeQuery();

            while (res.next()) {
                
 
                liste.add(new ProduitAchat(res.getString("reference"), res.getString("libelle"), res.getInt("quantiteEnStock"),res.getDouble("prixVente")));

            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        
        
        
        
        
        return  liste;
        
    }
    
    
     public List<CommandeVente> stat(){
        
        
        List<CommandeVente> liste = new ArrayList<>();
        
        
        
        
          try {
            String requete
                    = "SELECT  dateC, sum(`totalC`) FROM `commande_vente` group by year(dateC)";
            PreparedStatement pst = cnx.prepareStatement(requete);
            
          
            ResultSet res = pst.executeQuery();

            while (res.next()) {
                
                CommandeVente c = new CommandeVente();
                        
                c.setTotalC(res.getDouble(2));
                c.setDateC( res.getDate(1));
 
                liste.add(c);

            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        
        
        
        
        
        return  liste;
        
    }
    
    
    
    
}
