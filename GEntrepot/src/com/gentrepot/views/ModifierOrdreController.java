/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gentrepot.views;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author Rym
 */
public class ModifierOrdreController implements Initializable {

    @FXML
    private ComboBox<?> txtC;
    @FXML
    private ComboBox<?> txtA;
    @FXML
    private DatePicker c;
    @FXML
    private DatePicker r;
    @FXML
    private DatePicker s;
    @FXML
    private TextField bon;
    @FXML
    private Button btn1;
    @FXML
    private Button btn;
    @FXML
    private Text txtV;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void SuOrdre(MouseEvent event) {
    }

    @FXML
    private void ModOr(MouseEvent event) {
    }
    
}
