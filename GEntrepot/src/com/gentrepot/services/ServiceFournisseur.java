/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gentrepot.services;

import com.gentrepot.models.Fournisseur;
import com.gentrepot.utils.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javax.swing.JOptionPane;


/**
 *
 * @author oussema
 */
public class ServiceFournisseur implements IService<Fournisseur>{
        
        
    
     Connection cnx = DataSource.getInstance().getCnx();

    @Override
    public void ajouter(Fournisseur t) {
        if (!this.verifParMatricule(t.getMatriculeFiscale())) {
        try {
            String requete = "INSERT INTO fournisseur (raisonSociale,numeroTelephone,adresse,adresseMail,matriculeFiscale,codePostale) VALUES (?,?,?,?,?,?)";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setString(1, t.getRaisonSociale());
            pst.setInt(2, t.getNumeroTelephone());
            pst.setString(3, t.getAdresse());
            pst.setString(4, t.getAdresseMail());
            pst.setString(5, t.getMatriculeFiscale());
            pst.setInt(6, t.getCodePostale());
            pst.executeUpdate();
            System.out.println("Fournisseur ajouté !");
            JOptionPane.showMessageDialog(null, "Fournissseur ajoutée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
         } else {

            
            JOptionPane.showMessageDialog(null, "matricule fiscale deja existe deja !");

        }
    }

    
    public void supprimerFornisseur(int id) {
         try {
            String requete = "DELETE FROM fournisseur WHERE id="+id;
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.executeUpdate();
            System.out.println("Fournisseur supprimé !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public void modifier(Fournisseur t) {
       try {
            String requete = "UPDATE fournisseur SET raisonSociale=?,numeroTelephone=?,adresse=?,adresseMail=?,matriculeFiscale=?,codePostale=? WHERE id=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(7, t.getId());
            pst.setString(1, t.getRaisonSociale());
            pst.setInt(2, t.getNumeroTelephone());
            pst.setString(3, t.getAdresse());
            pst.setString(4, t.getAdresseMail());
            pst.setString(5, t.getMatriculeFiscale());
            pst.setInt(6, t.getCodePostale());
            pst.executeUpdate();
            System.out.println("Fournisseur modifié !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        } 
    }

    @Override
    public List<Fournisseur> afficher() {
        List<Fournisseur> list = new ArrayList<>();

        try {
            String requete = "SELECT * FROM fournisseur";
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                list.add(new Fournisseur(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getInt(7)));
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return list;
    }
    
    
       public boolean validerEmail(String s){
    Pattern p = Pattern.compile("[a-zA-Z0-9][a-zA-Z0-9._]*@[a-zA-Z0-9]+([.][a-zA-Z]+)+");
    Matcher m = p.matcher(s);
    if (m.find() && m.group().equals(s)){
        return false;
    }
    else 
    {
      
        return true;
    
        
}
        
 }
   public Fournisseur rechercherf(List<Fournisseur> et, String m){
        int a = 0;
        for(int i=0;i<et.size();i++){
            if(et.get(i).getAdresseMail().equals(m)){
                a = i;
            }
        }
        return et.get(a);
    }
 
      public Fournisseur rechercher(List<Fournisseur> et, int in){
        int a = 0;
        for(int i=0;i<et.size();i++){
            if(et.get(i).getId() == in){
                a = i;
            }
        }
        return et.get(a);
    }

    @Override
    public void supprimer(Fournisseur t) {
    }
    
    public boolean verifParMatricule(String mt) {

        for (Fournisseur f : this.afficher()) {

            if (f.getMatriculeFiscale().toUpperCase().equals(mt.toUpperCase())) {
                return true;
            }

        }

        return false;
    }
}
