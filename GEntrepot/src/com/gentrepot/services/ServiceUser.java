/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gentrepot.services;

import com.gentrepot.models.InventaireCaisse;
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
public class ServiceUser implements IService<User> {

     Connection cnx = DataSource.getInstance().getCnx();
    
    
    
    @Override
    public void ajouter(User u) {

       

        try {
            
           
            
            String requete = "INSERT INTO user (username,email,password,roles,username_canonical,email_canonical,enabled) VALUES (?,?,?,?,?,?,?)";
            PreparedStatement pst = cnx.prepareStatement(requete);

            pst.setString(1, u.getUsername());
            pst.setString(2, u.getEmail());
            pst.setString(3, u.getPassword());
            pst.setString(4, u.getRole());
            pst.setString(5, u.getUsernamCanonical());
            pst.setString(6, u.getEmailCanonical());
            pst.setInt(7, 1);
            

            pst.executeUpdate();
            System.out.println("User ajoutée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

    }

    @Override
    public void supprimer(User u) {
        
        
        try {
            String requete = "DELETE FROM user WHERE id=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1, u.getId());
            pst.executeUpdate();
            System.out.println("user  supprimée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

    }

    @Override
    public void modifier(User u) {
        
        
        
         try {
            String requete = "update user  set username=?,email=?,password=?,roles=?,username_canonical=?,email_canonical=? where id=?  ";

            PreparedStatement pst = cnx.prepareStatement(requete);

            pst.setString(1, u.getUsername());
            pst.setString(2, u.getEmail());
            pst.setString(3, u.getPassword());
            pst.setString(4, u.getRole());
            pst.setString(5, u.getUsernamCanonical());
            pst.setString(6, u.getEmailCanonical());
            pst.setInt(7, u.getId());

            pst.executeUpdate();
            System.out.println("user modifiée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        
        
    }

    @Override
    public List<User> afficher() {
        
         List<User> list = new ArrayList<>();

        try {
            String requete = "SELECT * FROM user";
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                
                
                
                
                list.add(new User(rs.getInt(1), rs.getString(2), rs.getString(4), rs.getString(3), rs.getString(5), rs.getString(8), rs.getString(12)));
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return list;
    }

}
