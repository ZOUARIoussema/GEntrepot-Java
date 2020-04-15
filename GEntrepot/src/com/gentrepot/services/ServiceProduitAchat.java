/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gentrepot.services;

import com.gentrepot.models.ProduitAchat;
import com.gentrepot.models.SousCategorieAchat;
import com.gentrepot.utils.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author oussema
 */
public class ServiceProduitAchat  implements IService<ProduitAchat>{
       
    
    
    
    
     Connection cnx = DataSource.getInstance().getCnx();

    @Override
    public void ajouter(ProduitAchat t) {
        try {
            String requete = "INSERT INTO produit_achat (reference,libelle,quantiteEnStock,classe,quantiteStockSecurite,dernierPrixAchat,tva,dimension,description,typeDeConditionnement,prixVente,image,image1,image2,image3,image4,sousCategorie_id) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setString(1, t.getReference());
            pst.setString(2, t.getLibelle());
            pst.setInt(3, t.getQuantiteStock());
            pst.setString(4, t.getClasse());
            pst.setInt(5, t.getQuantiteStockSecurite());
            pst.setDouble(6, t.getDernierPrixAchat());
            pst.setDouble(7, t.getTva());
            pst.setDouble(8, t.getDimension());
            pst.setString(9, t.getDescription());
            pst.setString(10, t.getTypeDeConditionnement());
            pst.setDouble(11, t.getPrixVente());
            pst.setString(12, t.getImage());
            pst.setString(13, t.getImage1());
            pst.setString(14, t.getImage2());
            pst.setString(15, t.getImage3());
            pst.setString(16, t.getImage4());
            pst.setInt(17, t.getSousCategorieAchat().getId());
            pst.executeUpdate();
            System.out.println("Personne ajoutée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public void supprimer(ProduitAchat t) {
        try {
            String requete = "DELETE FROM produit_achat WHERE reference=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setString(1, t.getReference());
            pst.executeUpdate();
            System.out.println("Personne supprimée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

  
    public void modifier(ProduitAchat t,String l,Integer s,Integer stcs, Double d,Double tv,Double Dim ,String des ,String type , Double pv , String im , String im1 ,String im2 , String im3 , String im4) {
       try {
            String requete = "UPDATE produit_achat SET libelle=?,quantiteStock=?,classe=?,quantiteStockSecurite=?,dernierPrixAchat=?,tva=?,dimension=?,description=?,typeDeConditionnement=?,prixVente=?,image=?,image1=?,image2=?,image3=?,image4=?,sousCategorieAchat=? WHERE reference like ?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setString(17, t.getReference());
            pst.setString(1,l);
            pst.setInt(2,s);
            pst.setString(3, t.getClasse());
            pst.setInt(4,stcs );
            pst.setDouble(5,d);
            pst.setDouble(6, tv);
            pst.setDouble(7, Dim);
            pst.setString(8,des);
            pst.setString(9,type);
            pst.setDouble(10,pv);
            pst.setString(11,im);
            pst.setString(12,im1);
            pst.setString(13, im2);
            pst.setString(14, im3);
            pst.setString(15,im4);
            pst.setInt(16, t.getSousCategorieAchat().getId());
            pst.executeUpdate();
            System.out.println("Personne modifiée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public ObservableList<ProduitAchat> afficher() {
        List<ProduitAchat> list = new ArrayList<>();
         ObservableList<ProduitAchat> c = FXCollections.observableArrayList();

        try {
            String requete = "SELECT * FROM produit_achat";
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
               c.add(new ProduitAchat(rs.getString(1), rs.getString(2), rs.getInt(3), rs.getString(4), rs.getInt(5), rs.getDouble(6), rs.getDouble(7), rs.getDouble(8), rs.getString(9), rs.getString(10), rs.getDouble(11), rs.getString(12), rs.getString(13), rs.getString(14), rs.getString(15), rs.getString(16), new SousCategorieAchat(rs.getString(17))));
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return c;
    }

    @Override
    public void modifier(ProduitAchat t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
     public ProduitAchat rechercher(List<ProduitAchat> et, String nom){
        int a = 0;
        for(int i=0;i<et.size();i++){
            if(et.get(i).getReference().equals(nom)){
                a = i;
            }
        }
        return et.get(a);
    }
}
