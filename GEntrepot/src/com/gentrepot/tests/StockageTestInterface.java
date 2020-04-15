/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gentrepot.tests;

import com.gentrepot.services.ServiceCommandeDApprovisionnment;
import com.gentrepot.models.CommandeDApprovisionnement;
import com.gentrepot.models.Fournisseur;
import java.text.SimpleDateFormat;
import java.util.Date;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 *
 * @author guiforodrigue
 */
public class StockageTestInterface  extends Application{
    
    
    
     public void start(Stage primaryStage) {
        
        try {

            AnchorPane root = (AnchorPane) FXMLLoader.load(getClass().getResource("/com/gentrepot/views/STOCKAGE.fxml"));
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Menue stockage");
            primaryStage.show();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
          
        launch(args);

    }

    
    
    
    
}
