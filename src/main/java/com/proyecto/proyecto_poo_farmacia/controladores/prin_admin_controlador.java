package com.proyecto.proyecto_poo_farmacia.controladores;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class prin_admin_controlador {
    @FXML
    private Button ingresar_productos_button;
    @FXML
    private Button actualizar_stock_productos;
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

        nuevouser_button.setOnAction(event -> crear_user());
        revisar_button.setOnAction(event -> his_ventas());
        logout_button.setOnAction(event -> logout());
        ingresar_productos_button.setOnAction(event -> Ingresar_Productos());
        actualizar_stock_productos.setOnAction(event -> Actualizar_stock_productos());
    }
    public void setNombreUsuario(String nombreUsuario){
        user_label.setText(nombreUsuario);
    }
    private void ir_stock(){

    }
    private void crear_user(){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/proyecto/proyecto_poo_farmacia/Crear_Usuario.fxml"));
        Parent root;
        try {
            root = fxmlLoader.load();
        }catch (IOException ex){
            ex.printStackTrace();
            return;
        }
        Scene scene = new Scene(root);
        Stage stage = (Stage) nuevouser_button.getScene().getWindow();
        stage.setScene(scene);
    }
    private void his_ventas(){
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
        Stage stage = (Stage) revisar_button.getScene().getWindow();
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

    private void Ingresar_Productos (){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/proyecto/proyecto_poo_farmacia/Ingresar_Productos.fxml"));
        Parent root;
        try {
            root = fxmlLoader.load();
        }catch (IOException e){
            e.printStackTrace();
            return;
        }
        Scene scene = new Scene(root);
        Stage stage = (Stage) ingresar_productos_button.getScene().getWindow();
        stage.setScene(scene);
    }

    private void Actualizar_stock_productos (){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/proyecto/proyecto_poo_farmacia/Stock_productos.fxml"));
        Parent root;
        try {
            root = fxmlLoader.load();
        }catch (IOException e){
            e.printStackTrace();
            return;
        }
        Scene scene = new Scene(root);
        Stage stage = (Stage) ingresar_productos_button.getScene().getWindow();
        stage.setScene(scene);
    }
}
