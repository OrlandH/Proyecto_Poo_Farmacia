package com.proyecto.proyecto_poo_farmacia.controladores;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import javafx.scene.control.*;
public class LoginControlador{
    @FXML
    private TextField user_field;
    @FXML
    private PasswordField pass_field;
    @FXML
    private ComboBox <String> rol_field;
    @FXML
    private Button login_button;
    @FXML
    private Button limpiar_button;
    @FXML
    private Label estado_label;
    //Principal
    @FXML
    private void initialize() {
        rol_field.setItems(FXCollections.observableArrayList("Administrador", "Cajero"));
        login_button.setOnAction(event -> login_validar());
        limpiar_button.setOnAction(event -> limpiarcampos());
    }
    //Funciones
    private void login_validar(){
        //Cambiar el FXML
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/proyecto/proyecto_poo_farmacia/Principal_Cajero.fxml"));
        Parent root;
        try {
            root = fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        // Cambiar la escena
        Scene scene = new Scene(root);
        Stage stage = (Stage) login_button.getScene().getWindow();
        stage.setScene(scene);

    }
    private void limpiarcampos(){

    }
}