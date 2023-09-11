package com.proyecto.proyecto_poo_farmacia.controladores;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;

public class Ingresar_Productos_Controlador {
    @FXML
    private TextField TextField_id_producto;
    @FXML
    private TextField TextFiedl_Nombre_producto;
    @FXML
    private TextField TextField_Descripcion_producto;
    @FXML
    private TextField TextField_Precio_Productos;
    @FXML
    private TextField TextField_stock_productos;
    @FXML
    private Button Ingresar_prod_button;
    @FXML
    private Button limpiar_campos_button;
    @FXML
    private Button salir_button;

    private static String DB_URL = "jdbc:mysql://localhost/FARMACIA";
    private static String USER = "root";
    private static String PASS = "admin";

    @FXML
    private void initialize(){
        Ingresar_prod_button.setOnAction(event -> ingresar_productos_base_datos());
        limpiar_campos_button.setOnAction(event ->limpiar_datos());
        salir_button.setOnAction(event ->salir());
    }

    private void ingresar_productos_base_datos(){

        String idProductoStr = TextField_id_producto.getText();
        String nombreProducto = TextFiedl_Nombre_producto.getText();
        String descripcionProducto = TextField_Descripcion_producto.getText();
        String precioProductoStr = TextField_Precio_Productos.getText();
        String stockProductoStr = TextField_stock_productos.getText();

        // Validar que los campos no estén vacíos
        if (idProductoStr.isEmpty() || nombreProducto.isEmpty() || descripcionProducto.isEmpty() ||
                precioProductoStr.isEmpty() || stockProductoStr.isEmpty()) {
            mostrarAlerta("Campos Vacíos", "Por favor, completa todos los campos.");
            return;
        }

        try {
            int idProducto = Integer.parseInt(idProductoStr);
            double precioProducto = Double.parseDouble(precioProductoStr);
            int stockProducto = Integer.parseInt(stockProductoStr);

            // Establecer la conexión a la base de datos
            try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASS)) {
                // Crear la consulta SQL para insertar los datos
                String SQL_QUERY_INSERT = "INSERT INTO Productos (ID, Nombre, Descripción, Precio, Stock) VALUES (?, ?, ?, ?, ?)";
                PreparedStatement statement = connection.prepareStatement(SQL_QUERY_INSERT);
                statement.setInt(1, idProducto);
                statement.setString(2, nombreProducto);
                statement.setString(3, descripcionProducto);
                statement.setDouble(4, precioProducto);
                statement.setInt(5, stockProducto);

                int filasAfectadas = statement.executeUpdate();
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                if (filasAfectadas > 0){
                    alert.setTitle("Éxito");
                    alert.setHeaderText("Inserción Exitosa");
                    alert.setContentText("El Producto se ha ingresado correctamente.");
                } else {
                    alert.setTitle("Error");
                    alert.setHeaderText("Error en la inserción");
                    alert.setContentText("No se pudo ingresar el producto en la base de datos.");
                }
                alert.showAndWait();
            } catch (SQLException e){
                e.printStackTrace();
                mostrarAlerta("Error en la Base de Datos", "Ocurrió un error al acceder a la base de datos.");
            }
        } catch (NumberFormatException e){
            mostrarAlerta("Formato Incorrecto", "Asegúrate de que los valores sean números válidos en los campos.");
        }
    }

    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    private void limpiar_datos(){
        TextField_id_producto.setText("");
        TextFiedl_Nombre_producto.setText("");
        TextField_Descripcion_producto.setText("");
        TextField_Precio_Productos.setText("");
        TextField_stock_productos.setText("");
    }

    private void salir (){
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
        Stage stage = (Stage) salir_button.getScene().getWindow();
        stage.setScene(scene);
    }
}
