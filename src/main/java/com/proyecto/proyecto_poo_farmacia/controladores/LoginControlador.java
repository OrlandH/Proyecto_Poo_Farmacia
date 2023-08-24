package com.proyecto.proyecto_poo_farmacia.controladores;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
public class LoginControlador {
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

    }
    private void limpiarcampos(){

    }
}