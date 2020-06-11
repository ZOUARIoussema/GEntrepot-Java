/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.gentrepot.controllers;


import com.gentrepot.utils.DataSource;
import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author AYOUB
 */
public class StatController implements Initializable {

    @FXML
    private BarChart<String, Integer> barCh;
    @FXML
    private JFXButton close;
    PreparedStatement pst;
    ResultSet res;
    private DataSource conn;
    @FXML
    private JFXButton load;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
   
        conn = DataSource.getInstance();
        loaddata();
      
    }    

    @FXML
    private void close(ActionEvent event) {
        final Node source = (Node) event.getSource();
    final Stage stage = (Stage) source.getScene().getWindow();
    stage.close();
    }

    @FXML
    private void loaddata(){
        conn = DataSource.getInstance();    
        try {
                String query="SELECT reference,quantiteEnStock FROM produit_achat";
                XYChart.Series<String,Integer> series=new XYChart.Series<>();
                ResultSet rs=conn.getCnx().createStatement().executeQuery(query);
                try {
                    while (rs.next())
                    {
                        series.getData().add(new XYChart.Data<>(rs.getString(1),rs.getInt(2)));
                        barCh.getData().add(series);
                    }
                } catch (SQLException ex) {
                    System.err.println(ex.getMessage());
                }
            } catch (SQLException ex) {
                System.err.println(ex.getMessage());
            }
        } 
    }
    
    

