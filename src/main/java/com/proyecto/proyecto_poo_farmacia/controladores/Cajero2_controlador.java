package com.proyecto.proyecto_poo_farmacia.controladores;

import com.proyecto.proyecto_poo_farmacia.controladores.POO.Venta;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;

public class Cajero2_controlador {
    @FXML
    private Button Salir_cajero2_button;
    @FXML
    private Button consultar_caj2_button;
    @FXML
    private TableView<Venta> table_cajero2;
    @FXML
    private TableColumn<Venta, Integer> ventaID_colum;
    @FXML
    private TableColumn<Venta, String> FechaVenta_colum;
    @FXML
    private TableColumn<Venta, String> Cajero_colum;
    @FXML
    private TableColumn<Venta, String> Producto_Colum;
    @FXML
    private TableColumn<Venta, Integer> Cantidad_Colum;

    static final String DB_URL = "jdbc:mysql://localhost/FARMACIA";
    static final String USER = "root";
    static final String PASS = "admin";
    @FXML
    private void initialize(){
        Salir_cajero2_button.setOnAction(event -> salir_cajero2_menu());
        consultar_caj2_button.setOnAction(event -> Consultar_ventas_cajero2());

        ventaID_colum.setCellValueFactory(new PropertyValueFactory<>("ventaID"));
        FechaVenta_colum.setCellValueFactory(new PropertyValueFactory<>("fechaVenta"));
        Cajero_colum.setCellValueFactory(new PropertyValueFactory<>("cajero"));
        Producto_Colum.setCellValueFactory(new PropertyValueFactory<>("producto"));
        Cantidad_Colum.setCellValueFactory(new PropertyValueFactory<>("cantidad"));
    }

    private void Consultar_ventas_cajero2(){
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM VistaVentasPorCajero WHERE Cajero = ?");
        ) {
            // Configura el parámetro de la consulta con el nombre del cajero
            preparedStatement.setString(1, "Cajero2");

            // Ejecuta la consulta
            ResultSet resultSet = preparedStatement.executeQuery();

            // Borra los datos existentes en la tabla
            table_cajero2.getItems().clear();

            // Recorre los resultados y agrega filas a la tabla
            while (resultSet.next()) {
                int ventaID = resultSet.getInt("VentaID");
                String cajero = resultSet.getString("Cajero");
                String fechaVenta = resultSet.getString("FechaVenta");
                String producto = resultSet.getString("Producto");
                int cantidad = resultSet.getInt("Cantidad");

                // Crea una instancia de Venta con los datos y agrega a la tabla
                Venta venta = new Venta(ventaID, cajero, fechaVenta, producto, cantidad,null);
                table_cajero2.getItems().add(venta);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void salir_cajero2_menu(){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/proyecto/proyecto_poo_farmacia/Cajeros_ventas.fxml"));
        Parent root;
        try {
            root = fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        // Cambiar la escena
        Scene scene = new Scene(root);
        Stage stage = (Stage) Salir_cajero2_button.getScene().getWindow();
        stage.setScene(scene);
    }
}
