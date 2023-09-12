package com.proyecto.proyecto_poo_farmacia.controladores;

import com.proyecto.proyecto_poo_farmacia.controladores.POO.Venta;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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

public class Revisar_Ventas_Controlador {
    @FXML
    private TableView<Venta> tabla_revisar_ventas;
    @FXML
    private TableColumn<Venta, Integer> ventaID_colum;
    @FXML
    private TableColumn<Venta, String> fecha_venta_colum;
    @FXML
    private TableColumn<Venta, String> cajero_colum_ventas;
    @FXML
    private TableColumn<Venta, String> producto_colum_ventas;
    @FXML
    private TableColumn<Venta, Integer> cantidad_colum_ventas;
    @FXML
    private TableColumn<Venta, Double> subtotal_colum_ventas;
    @FXML
    private Button revisar_ventas_button;
    @FXML
    private Button ventas_cajero_button;
    @FXML
    private Button salir_button;

    static final String DB_URL = "jdbc:mysql://localhost/FARMACIA";
    static final String USER = "root";
    private static String PASS = "24_Diolove";

    private ObservableList<Venta> ventasData = FXCollections.observableArrayList();

    @FXML
    private void initialize() {
        salir_button.setOnAction(event -> salir_menu());
        revisar_ventas_button.setOnAction(event -> Revisar_ventas_func());
        ventas_cajero_button.setOnAction(event -> ver_ventas_x_cajero());

        // Configurando las columnas de la tabla
        ventaID_colum.setCellValueFactory(new PropertyValueFactory<>("ventaID"));
        fecha_venta_colum.setCellValueFactory(new PropertyValueFactory<>("fechaVenta"));
        cajero_colum_ventas.setCellValueFactory(new PropertyValueFactory<>("cajero"));
        producto_colum_ventas.setCellValueFactory(new PropertyValueFactory<>("producto"));
        cantidad_colum_ventas.setCellValueFactory(new PropertyValueFactory<>("cantidad"));
        subtotal_colum_ventas.setCellValueFactory(new PropertyValueFactory<>("subtotal"));
    }

    private void cargarDatosDesdeBD() {
        ventasData.clear();

        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASS);
             Statement statement = connection.createStatement()) {

            String sqlQuery = "SELECT * FROM VistaVentas ORDER BY VentaID ASC";
            ResultSet resultSet = statement.executeQuery(sqlQuery);

            while (resultSet.next()) {
                int ventaID = resultSet.getInt("VentaID");
                String fechaVenta = resultSet.getString("FechaVenta");
                String cajero = resultSet.getString("Cajero");
                String producto = resultSet.getString("Producto");
                int cantidad = resultSet.getInt("Cantidad");
                double subtotal = resultSet.getDouble("Subtotal");

                ventasData.add(new Venta(ventaID, fechaVenta, cajero, producto, cantidad, subtotal));
            }


            tabla_revisar_ventas.setItems(ventasData);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private void Revisar_ventas_func() {
        cargarDatosDesdeBD();
    }
    private void ver_ventas_x_cajero(){
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
        Stage stage = (Stage) ventas_cajero_button.getScene().getWindow();
        stage.setScene(scene);
    }
    private void salir_menu() {
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

