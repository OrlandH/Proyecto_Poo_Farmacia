package com.proyecto.proyecto_poo_farmacia.controladores;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class Ventas_por_cajero_controlador {
    @FXML
    private Button Cajero1_button;
    @FXML
    private Button Cajero2_button;
    @FXML
    private Button salir_por_cajero_button;

    @FXML
    private void initialize(){
        salir_por_cajero_button.setOnAction(event -> salir_menu());
        Cajero1_button.setOnAction(event -> Cajero1());
        Cajero2_button.setOnAction(event -> Cajero2());
    }

    private void salir_menu(){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/proyecto/proyecto_poo_farmacia/Revisar_Ventas.fxml"));
        Parent root;
        try {
            root = fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        // Cambiar la escena
        Scene scene = new Scene(root);
        Stage stage = (Stage) salir_por_cajero_button.getScene().getWindow();
        stage.setScene(scene);
    }

    private void Cajero1(){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/proyecto/proyecto_poo_farmacia/Cajero1.fxml"));
        Parent root;
        try {
            root = fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        // Cambiar la escena
        Scene scene = new Scene(root);
        Stage stage = (Stage) Cajero1_button.getScene().getWindow();
        stage.setScene(scene);
    }
    private void Cajero2(){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/proyecto/proyecto_poo_farmacia/Cajero2.fxml"));
        Parent root;
        try {
            root = fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        // Cambiar la escena
        Scene scene = new Scene(root);
        Stage stage = (Stage) Cajero2_button.getScene().getWindow();
        stage.setScene(scene);
    }

}
