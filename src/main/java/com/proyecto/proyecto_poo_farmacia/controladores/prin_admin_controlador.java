package com.proyecto.proyecto_poo_farmacia.controladores;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class prin_admin_controlador {
    @FXML
    private Button stock_button;
    @FXML
    private Button nuevouser_button;
    @FXML
    private Button revisar_button;
    @FXML
    private Button logout_button;
    @FXML
    private Label user_label;

    //Principal
    @FXML
    private void initialize(){
        stock_button.setOnAction(event -> ir_stock());
        nuevouser_button.setOnAction(event -> crear_user());
        revisar_button.setOnAction(event -> his_ventas());
        logout_button.setOnAction(event -> logout());
    }
    private void ir_stock(){

    }
    private void crear_user(){

    }
    private void his_ventas(){

    }
    private void logout(){

    }
}
