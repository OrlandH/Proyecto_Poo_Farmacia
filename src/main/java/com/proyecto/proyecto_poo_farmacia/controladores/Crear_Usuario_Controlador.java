package com.proyecto.proyecto_poo_farmacia.controladores;

import com.proyecto.proyecto_poo_farmacia.controladores.POO.Crear_Usuario;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class Crear_Usuario_Controlador {
    @FXML
    private TextField id_user_register;
    @FXML
    private TextField name_user_register;
    @FXML
    private TextField pass_user_register;
    @FXML
    private ComboBox <String> conbo_user_tipo_register;
    @FXML
    private Button button_registrar;
    private static String DB_URL = "jdbc:mysql://localhost/FARMACIA";
    private static String USER = "root";
    private static String PASS = "admin";

    @FXML
    private void initialize(){
        conbo_user_tipo_register.setItems(FXCollections.observableArrayList("Administrador","Cajero"));
        button_registrar.setOnAction(actionEvent -> Registrar());
    }

    private void Registrar (){
        Crear_Usuario user_register = new Crear_Usuario(0,"","","");
        String id_aux_convert = id_user_register.getText(); // Guardamos como string el texfiedl de id
        int id_aux = Integer.parseInt(id_aux_convert); // Convertimos el valor obtenido en entero
        user_register.setId_usuario(id_aux);
        user_register.setNombre(name_user_register.getText());
        user_register.setContrasena(pass_user_register.getText());
        user_register.setTipo_usuario(conbo_user_tipo_register.getValue());
        try {
            Connection connection = DriverManager.getConnection(DB_URL,USER,PASS);
            String SQL_QUERY_INSERT = "INSERT INTO Usuarios (ID, Nombre, Contraseña, Tipo) VALUES (?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_QUERY_INSERT);
            preparedStatement.setInt(1,user_register.getId_usuario());
            preparedStatement.setString(2,user_register.getNombre());
            preparedStatement.setString(3,user_register.getContrasena());
            preparedStatement.setString(4,user_register.getTipo_usuario());

            int registrosInsertados = preparedStatement.executeUpdate();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Registro de Usuario");
            alert.setHeaderText(null);

            if (registrosInsertados > 0){
                alert.setContentText("Usuario registrado exitosamente !");
                id_user_register.setText(null);
                name_user_register.setText("");
                pass_user_register.setText("");
                conbo_user_tipo_register.setValue(null);
            } else {
                alert.setContentText("No se pudo registrar el usuario !");
            }
            alert.showAndWait();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
