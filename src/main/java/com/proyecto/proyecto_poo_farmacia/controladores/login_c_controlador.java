package com.proyecto.proyecto_poo_farmacia.controladores;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class login_c_controlador extends LoginControlador{
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
        //Cambiar el FXML
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/proyecto/proyecto_poo_farmacia/Fac_Cajero.fxml"));
        Parent root;
        try {
            root = fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        // Cambiar la escena
        Scene scene = new Scene(root);
        Stage stage = (Stage) nueva_fac_button.getScene().getWindow();
        stage.setScene(scene);
    }
    private void logout(){
        //Cambiar el FXML
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/proyecto/proyecto_poo_farmacia/Login.fxml"));
        Parent root;
        try {
            root = fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        // Cambiar la escena
        Scene scene = new Scene(root);
        Stage stage = (Stage) logout_button.getScene().getWindow();
        stage.setScene(scene);

    }

}
