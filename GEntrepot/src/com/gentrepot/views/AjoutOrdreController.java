/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gentrepot.views;

import com.gentrepot.models.AideChauffeur;
import com.gentrepot.models.BonLivraison;
import com.gentrepot.models.Chauffeur;
import com.gentrepot.models.OrdreMission;
import com.gentrepot.models.Vehicule;
import com.gentrepot.services.ServiceAideChauffeur;
import com.gentrepot.services.ServiceChauffeur;
import com.gentrepot.services.ServiceOrdreMission;
import com.gentrepot.services.ServiceVehicule;
import com.gentrepot.utils.DataSource;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Rym
 */
public class AjoutOrdreController implements Initializable {

    @FXML
    private ComboBox<Integer> txtV;
    @FXML
    private ComboBox<String> txtC;
    @FXML
    private ComboBox<String> txtA;
    @FXML
    private TextField bon;
    @FXML
    private Button btn;
    @FXML
    private DatePicker cr;
    @FXML
    private DatePicker s;
    @FXML
    private DatePicker r;
    ZoneId defaultZoneId = ZoneId.systemDefault();
    
    public ArrayList<BonLivraison> oumayma = new ArrayList<BonLivraison>();
    BonLivraison b1 = new BonLivraison();
     Connection cnx = DataSource.getInstance().getCnx();
     
     ServiceVehicule sv = new ServiceVehicule();
     ServiceChauffeur sc = new ServiceChauffeur();
     ServiceAideChauffeur sa = new ServiceAideChauffeur();
     
            
     
     public ObservableList tv (){
    ObservableList<Integer> l = FXCollections.observableArrayList();
    ObservableList<Vehicule> k = sv.afficher();
    Iterator<Vehicule> i = k.iterator();
    while(i.hasNext())
    {
        l.add(i.next().getMatricule());
    }
              return l;
    }
     
     public ObservableList tc (){
    ObservableList<String> l = FXCollections.observableArrayList();
    ObservableList<Chauffeur> k = sc.afficher();
    Iterator<Chauffeur> i = k.iterator();
    while(i.hasNext())
    {
        l.add(i.next().getNom());
    }
              return l;
    }

    public ObservableList tc1 (){
    ObservableList<String> l = FXCollections.observableArrayList();
    ObservableList<AideChauffeur> k = sa.afficher();
    Iterator<AideChauffeur> i = k.iterator();
    while(i.hasNext())
    {
        l.add(i.next().getNom());
    }
              return l;
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        oumayma.add(b1);
        txtA.setItems(tc1());
        txtC.setItems(tc());
        txtV.setItems(tv());
        // TODO
    }    

    @FXML
    private void AjoutOrdre(MouseEvent event) throws SQLException {
         ServiceOrdreMission sp = new ServiceOrdreMission();
       // sp.ajouter(new OrdreMission(txtV.getSelectionModel().getSelectedItem(),txtC.getSelectionModel().getSelectedItem(), txtA.getSelectionModel().getSelectedItem(), c.getDayCellFactory(),s.getDayCellFactory(),r.getDayCellFactory(), bon.get);
           String req = "select * from vehicule where matricule = ? ;";
                  PreparedStatement pst = cnx.prepareStatement(req);
                  pst.setInt(1, txtV.getSelectionModel().getSelectedItem());
                  ResultSet rs =pst.executeQuery();
                  while(rs.next()){
                    Vehicule   c= new Vehicule(rs.getInt(1),rs.getString(2),rs.getInt(3),rs.getInt(4),rs.getString(5));
                       
                  
                  String req1 = "select * from aide_chauffeur where nom like ? ;";
                  PreparedStatement pst1 = cnx.prepareStatement(req1);
                  pst1.setString(1, (String)txtC.getSelectionModel().getSelectedItem());
                  ResultSet rs1 =pst1.executeQuery();
                  while(rs1.next()){
                    AideChauffeur   c1= new AideChauffeur(rs1.getString(1),rs1.getString(2),rs1.getString(3),rs1.getString(4));
                       
                  
                  String req2 = "select * from chauffeur where nom like ? ;";
                  PreparedStatement pst2 = cnx.prepareStatement(req1);
                  pst2.setString(1,(String)txtA.getSelectionModel().getSelectedItem());
                  ResultSet rs2 =pst2.executeQuery();
                  while(rs2.next()){
                    Chauffeur   c2= new Chauffeur(rs2.getString(1),rs2.getString(2),rs2.getString(3),rs2.getString(4),rs2.getInt(5),rs2.getString(6));
                       
                   Date s1= Date.from(s.getValue().atStartOfDay(defaultZoneId).toInstant());
                   Date s2= Date.from(cr.getValue().atStartOfDay(defaultZoneId).toInstant());
                   Date s3= Date.from(r.getValue().atStartOfDay(defaultZoneId).toInstant());
                   

                     OrdreMission o = new OrdreMission(c, c2, c1, s2, s1, s3, oumayma);
                     sp.ajouter(o);
                  
                  }}}
       
       
        JOptionPane.showMessageDialog(null, "ordre de mission ajoutée !");
    }
    
}
