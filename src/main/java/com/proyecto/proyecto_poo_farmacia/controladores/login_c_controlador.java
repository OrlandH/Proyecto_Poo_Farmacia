package com.proyecto.proyecto_poo_farmacia.controladores;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class login_c_controlador {
    @FXML
    private Label nombre_label;
    @FXML
    private Button nueva_fac_button;
    @FXML
    private Button logout_button;
    //Principal
    @FXML
    private void initialize() {
        nueva_fac_button.setOnAction(event -> cambiar_fac());
        logout_button.setOnAction(event -> logout());
    }
    //Funciones
    private void cambiar_fac(){

    }
    private void logout(){

    }
}
