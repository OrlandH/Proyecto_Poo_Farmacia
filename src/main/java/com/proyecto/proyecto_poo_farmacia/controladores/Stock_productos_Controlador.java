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

public class Stock_productos_Controlador {
    @FXML
    private Button salir_button_stock;
    @FXML
    private Button Actualizar_Stock_button;
    @FXML
    private Button Buscar_producto_stock;
    @FXML
    private TextField Texfield_id_producto;
    @FXML
    private TextField TextFiedl_Nombre_producto;
    @FXML
    private TextField TextField_stock_actual;
    @FXML
    private TextField TextFiedl_stock_nuevo;
    @FXML
    private Button limpiar_campos_stock;

    static final String DB_URL = "jdbc:mysql://localhost/FARMACIA";
    static final String USER = "root";
    private static String PASS = "24_Diolove";

    private int idProductoBuscado;

    @FXML
    private void initialize(){
        salir_button_stock.setOnAction(event -> salir_menu_admin());
        Buscar_producto_stock.setOnAction(event -> buscar_producto());
        Actualizar_Stock_button.setOnAction(event -> actualizar_stock());
        limpiar_campos_stock.setOnAction(event -> limpiar_campos());
    }

    private void buscar_producto(){
        String idProductoStr = Texfield_id_producto.getText().trim(); // Obtener el texto sin espacios alrededor
        if (idProductoStr.isEmpty()) {
            mostrarAlerta("Campo Vacío", "Por favor, ingrese un ID de producto antes de buscar.");
            return;
        }

        try {
            int idProducto = Integer.parseInt(idProductoStr);
            Connection connection = DriverManager.getConnection(DB_URL, USER, PASS);
            String SQL_Query_SELECT = "SELECT Nombre,Stock FROM Productos WHERE ID = ?";
            PreparedStatement statement = connection.prepareStatement(SQL_Query_SELECT);
            statement.setInt(1, idProducto);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()){
                String nombreProducto = resultSet.getString("Nombre");
                int stockProdcuto = resultSet.getInt("Stock");

                TextFiedl_Nombre_producto.setText(nombreProducto);
                TextField_stock_actual.setText(String.valueOf(stockProdcuto));

                // Almacena el ID del producto buscado
                idProductoBuscado = idProducto;
            } else {
                mostrarAlerta("Producto no encontrado", "No se encontró un producto con el ID especificado.");
                Texfield_id_producto.setText("");
            }
        } catch (SQLException e){
            e.printStackTrace();
            mostrarAlerta("Error en la Base de Datos", "Ocurrió un error al acceder a la base de datos.");
        } catch (NumberFormatException e) {
            mostrarAlerta("Formato Incorrecto", "El ID del producto debe ser un número válido.");
            Texfield_id_producto.setText("");
        }
    }

    private void actualizar_stock(){
        String nuevoStockStr = TextFiedl_stock_nuevo.getText().trim(); // Obtener el texto sin espacios alrededor
        if (nuevoStockStr.isEmpty()) {
            mostrarAlerta("Campo Vacío", "Por favor, ingrese un nuevo stock antes de actualizar.");
            return;
        }

        try {
            int nuevoStock = Integer.parseInt(nuevoStockStr);
            if (nuevoStock < 0){
                mostrarAlerta("Stock no válido", "El nuevo stock debe ser mayor o igual a cero.");
                Texfield_id_producto.setText("");
                return;
            }

            Connection connection = DriverManager.getConnection(DB_URL, USER, PASS);
            String SQL_QUERY_UPDATE = "UPDATE Productos SET Stock = ? WHERE ID = ?";
            PreparedStatement statement = connection.prepareStatement(SQL_QUERY_UPDATE);
            statement.setInt(1, nuevoStock);
            statement.setInt(2, idProductoBuscado); // Utiliza el ID almacenado

            int filasAfectadas = statement.executeUpdate();

            if (filasAfectadas > 0){
                mostrarAlerta("Actualización Exitosa","El Stock del producto fue actualizado con éxito.");
                TextField_stock_actual.setText(String.valueOf(nuevoStock));
                Texfield_id_producto.setText("");
                TextFiedl_Nombre_producto.setText("");
                TextField_stock_actual.setText("");
                TextFiedl_stock_nuevo.setText("");
            } else {
                mostrarAlerta("Error al Actualizar","No se pudo actualizar el stock del producto.");
                Texfield_id_producto.setText("");
                TextFiedl_Nombre_producto.setText("");
                TextField_stock_actual.setText("");
                TextFiedl_stock_nuevo.setText("");
            }
        } catch (SQLException e){
            e.printStackTrace();
            mostrarAlerta("Error en la Base de Datos", "Ocurrió un error al acceder a la base de datos.");
        } catch (NumberFormatException e) {
            mostrarAlerta("Formato Incorrecto", "El nuevo stock debe ser un número válido.");
        }
    }

    private void salir_menu_admin(){
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
        Stage stage = (Stage) salir_button_stock.getScene().getWindow();
        stage.setScene(scene);
    }

    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    private void limpiar_campos(){
        Texfield_id_producto.setText("");
        TextFiedl_Nombre_producto.setText("");
        TextField_stock_actual.setText("");
        TextFiedl_stock_nuevo.setText("");
    }
}
