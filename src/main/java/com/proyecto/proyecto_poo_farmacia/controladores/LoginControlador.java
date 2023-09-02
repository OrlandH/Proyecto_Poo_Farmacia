package com.proyecto.proyecto_poo_farmacia.controladores;

import com.proyecto.proyecto_poo_farmacia.controladores.POO.Login_class_user;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import java.sql.*;

import java.util.concurrent.ExecutionException;

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
    private static String DB_URL = "jdbc:mysql://localhost/FARMACIA_PROYECTO";
    private static String USER = "root";
    private static String PASS = "24@Diolove";
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
        Login_class_user inicio_sesion = new Login_class_user("","","");
        inicio_sesion.setNombre(user_field.getText());
        inicio_sesion.setContrasena(pass_field.getText());
        inicio_sesion.setTipo(rol_field.getValue());

        if (inicio_sesion.getNombre().isEmpty() || inicio_sesion.getContrasena().isEmpty() || inicio_sesion.getTipo() == null) {
            estado_label.setText("Por favor, complete todos los campos.");
            return;
        }

        try{ Connection connection = DriverManager.getConnection(DB_URL,USER,PASS);
                String SQL_QUERY_LOGIN =  "SELECT * FROM Usuarios WHERE Nombre = ? AND Contraseña = ? AND Tipo = ?";
                PreparedStatement statement = connection.prepareStatement(SQL_QUERY_LOGIN);
                statement.setString(1,inicio_sesion.getNombre());
                statement.setString(2,inicio_sesion.getContrasena());
                statement.setString(3,inicio_sesion.getTipo());
            
                ResultSet resultSet = statement.executeQuery();
                if (resultSet.next()){
                    estado_label.setText("Inicio de Sesion exitoso.");
                    if (inicio_sesion.getTipo().equals("Cajero")){
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
                    } else {
                        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/proyecto/proyecto_poo_farmacia/Principal_Admin.fxml"));
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
                } else {
                    estado_label.setText("Credenciales Incorrectas ! Ingrese nuevamente los datos");
                }
        }catch (Exception e){
            e.printStackTrace();
            estado_label.setText("Ocurrió un error al intentar iniciar sesión.");
        }

    }
    private void limpiarcampos(){
        user_field.setText("");
        pass_field.setText("");
    }
}