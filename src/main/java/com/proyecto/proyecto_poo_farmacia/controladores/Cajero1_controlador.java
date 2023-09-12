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

public class Cajero1_controlador {
    @FXML
    private Button Salir_cajero1;
    @FXML
    private Button consultar_button;
    @FXML
    private TableView table_cajero1;
    @FXML
    private TableColumn<Venta, Integer> ventaID_colum;
    @FXML
    private TableColumn<Venta, String> cajero_colum;
    @FXML
    private TableColumn<Venta, String> fecha_colum;
    @FXML
    private TableColumn<Venta, String> producto_colum;
    @FXML
    private TableColumn<Venta, Integer> cantidad_colum;

    static final String DB_URL = "jdbc:mysql://localhost/FARMACIA";
    static final String USER = "root";
    private static String PASS = "24_Diolove";

    @FXML
    private void initialize(){
        Salir_cajero1.setOnAction(evet -> salir_menu_caj1());
        consultar_button.setOnAction(event -> Consultar_ventas_cajero1());

        ventaID_colum.setCellValueFactory(new PropertyValueFactory<>("ventaID"));
        cajero_colum.setCellValueFactory(new PropertyValueFactory<>("Cajero"));
        fecha_colum.setCellValueFactory(new PropertyValueFactory<>("FechaVenta"));
        producto_colum.setCellValueFactory(new PropertyValueFactory<>("Producto"));
        cantidad_colum.setCellValueFactory(new PropertyValueFactory<>("Cantidad"));
    }

    private void Consultar_ventas_cajero1(){
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM VistaVentasPorCajero WHERE Cajero = ?");
        ) {
            // Configura el parámetro de la consulta con el nombre del cajero
            preparedStatement.setString(1, "Cajero1");

            // Ejecuta la consulta
            ResultSet resultSet = preparedStatement.executeQuery();

            // Borra los datos existentes en la tabla
            table_cajero1.getItems().clear();

            // Recorre los resultados y agrega filas a la tabla
            while (resultSet.next()) {
                int ventaID = resultSet.getInt("VentaID");
                String cajero = resultSet.getString("Cajero");
                String fechaVenta = resultSet.getString("FechaVenta");
                String producto = resultSet.getString("Producto");
                int cantidad = resultSet.getInt("Cantidad");

                // Crea una instancia de Venta con los datos y agrega a la tabla
                Venta venta = new Venta(ventaID, cajero, fechaVenta, producto, cantidad,null);
                table_cajero1.getItems().add(venta);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void salir_menu_caj1(){
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
        Stage stage = (Stage) Salir_cajero1.getScene().getWindow();
        stage.setScene(scene);
    }
}
