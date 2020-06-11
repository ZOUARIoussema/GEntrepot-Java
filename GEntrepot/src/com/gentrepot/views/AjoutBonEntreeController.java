/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gentrepot.views;

import com.gentrepot.models.BonEntree;
import com.gentrepot.services.ServiceBonEntree;
import com.gentrepot.utils.PdfB;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.DocumentException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author Mohamed
 */
public class AjoutBonEntreeController implements Initializable {
PdfB pdf = new PdfB ();
    @FXML
    private Button aj;
    @FXML
    private TextField d;
    @FXML
    private DatePicker a;
    @FXML
    private DatePicker b;
    @FXML
    private DatePicker c;

   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      
                
    }    

    @FXML
    private void ajoutbe(MouseEvent event) throws DocumentException, BadElementException, IOException, FileNotFoundException, InterruptedException, SQLException {
          ServiceBonEntree sp = new ServiceBonEntree();

          Date s1=java.sql.Date.valueOf(a.getValue());
                     Date s2=java.sql.Date.valueOf(b.getValue());
                    Date s3=java.sql.Date.valueOf(c.getValue());

            sp.ajouter(new BonEntree(s1, s2, s3));
            pdf.GeneratePdf("bonentree", new BonEntree(s1, s2, s3));

       
    }
    
}
