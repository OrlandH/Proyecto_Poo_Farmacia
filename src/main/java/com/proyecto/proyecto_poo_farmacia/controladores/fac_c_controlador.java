package com.proyecto.proyecto_poo_farmacia.controladores;

import com.proyecto.proyecto_poo_farmacia.controladores.POO.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.sql.*;
public class fac_c_controlador extends LoginControlador{
    //Cabecera variables
    @FXML
    private Label nom_label;
    @FXML
    private Label fecha_label;
    @FXML
    private Label nombre_emp_label;
    @FXML
    private Label ruc_emp_label;
    @FXML
    private Label dir_emp_label;
    @FXML
    private Label estado_bus_label;


    //Factura general variables
    @FXML
    private TextField num_fac_textfield;
    @FXML
    private TextField nom_textfield;
    @FXML
    private TextField idcli_textfield;
    @FXML
    private TextField tel_textfield;
    @FXML
    private TextField correo_textfield;
    @FXML
    private TextField total_textfield;
    @FXML
    private Button crearfac_button;
    @FXML
    private Button enviar_button;
    @FXML
    private Button salir_button;
    @FXML
    private Button cancelar_button;

    //Variables para la busqueda
    @FXML
    private Button buscar_nom_button;
    @FXML
    private Button buscar_cod_button;
    @FXML
    private Button clean_button;
    @FXML
    private Button cargar_button;
    @FXML
    private Button quitar_button;
    @FXML
    private Spinner<Integer> cantidad_box;
    @FXML
    private TextField nombre_field;
    @FXML
    private TextField cod_field;

    //Tablas Producto Busqueda
    @FXML
    private TableView tabla_busqueda;
    @FXML
    private TableColumn columna_nombre;
    @FXML
    private TableColumn stock_column;
    @FXML
    private TableColumn pvp_column;
    private ObservableList<Producto_class> listaProductos = FXCollections.observableArrayList();
    //Tabla Factura
    @FXML
    private TableView tabla_fac;
    @FXML
    private TableColumn codprod_column;
    @FXML
    private TableColumn product_column_fac;
    @FXML
    private TableColumn cantidad_column;
    @FXML
    private TableColumn pvp_column_fact;
    @FXML
    private TableColumn subtotal_column;
    //Aux
    private double total=0.0;

    //CONEXION SQL
    static final String DB_URL = "jdbc:mysql://localhost/FARMACIA";
    static final String USER = "root";
    static final String PASS = "24_Diolove";

    //Principal
    @FXML
    public void initialize() {
        tabla_busqueda.setItems(listaProductos);
        columna_nombre.setCellValueFactory(new PropertyValueFactory<>("Producto"));
        pvp_column.setCellValueFactory(new PropertyValueFactory<>("PVP"));
        stock_column.setCellValueFactory(new PropertyValueFactory<>("Stock"));

        codprod_column.setCellValueFactory(new PropertyValueFactory<>("ID"));
        product_column_fac.setCellValueFactory(new  PropertyValueFactory<>("Productos"));
        cantidad_column.setCellValueFactory(new  PropertyValueFactory<>("Cantidad"));
        pvp_column_fact.setCellValueFactory(new  PropertyValueFactory<>("PVP"));
        subtotal_column.setCellValueFactory(new  PropertyValueFactory<>("subtotal"));

        //Link de Botones con metodos
        buscar_nom_button.setOnAction(event -> buscarnombre());
        buscar_cod_button.setOnAction(event -> buscarcodigo());
        clean_button.setOnAction(event -> limpiar());
        crearfac_button.setOnAction(event -> activarfac());
        cargar_button.setOnAction(event -> agregarprod());
        quitar_button.setOnAction(event -> eliminarprod());
        enviar_button.setOnAction(event -> enviarfac());
        salir_button.setOnAction(event -> regresar());
        cancelar_button.setOnAction(event -> cancelarfac());


        //Contador del Spinner y deteccion de click en tabla
        tabla_busqueda.setOnMouseClicked(event -> {
            if (crearfac_button.isDisabled()) {
                cantidad_box.setDisable(false);
                cargar_button.setDisable(false);
            }
            Producto_class productoSeleccionado = (Producto_class) tabla_busqueda.getSelectionModel().getSelectedItem();
            if (productoSeleccionado != null) {
                if (crearfac_button.isDisabled()) {
                    cargar_button.setDisable(false);
                    cantidad_box.setDisable(false);
                }
                int contadorSpinner_Limite = Integer.parseInt(productoSeleccionado.getStock());
                SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, contadorSpinner_Limite);
                valueFactory.setValue(1);
                cantidad_box.setValueFactory(valueFactory);
            }

            else {
                if (crearfac_button.isDisabled()) {
                    cargar_button.setDisable(true);
                    cantidad_box.setDisable(true);
                }
            }
        });
        tabla_fac.setOnMouseClicked(event -> {
            if (crearfac_button.isDisabled()){
                quitar_button.setDisable(false);
            }
        });
        // Obtener la fecha actual
        LocalDate fechaActual = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String fechaFormateada = fechaActual.format(formatter);
        fecha_label.setText(fechaFormateada);
    }
    private void mostrarMensajeError(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
    private void mostrarMensajeExito(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Éxito");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
    public void setNomLabel(String text) {
        nom_label.setText(text);
    }

    //Funciones busqueda
    private void buscarnombre() {
        String nombre_aux = nombre_field.getText().trim();
        if (nombre_aux.isEmpty()) {
            estado_bus_label.setText("Ingresa algo en el campo");
            return;
        }
        listaProductos.clear();
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS)) {
            String SQL_Query_select = "SELECT * FROM Productos WHERE Nombre LIKE ?";
            try (PreparedStatement pstm = conn.prepareStatement(SQL_Query_select)) {
                pstm.setString(1, "%" + nombre_aux + "%");
                try (ResultSet rs = pstm.executeQuery()) {
                    while (rs.next()) {
                        Producto_class producto = new Producto_class(
                                String.valueOf(rs.getInt("ID")),
                                rs.getString("Nombre"),
                                String.valueOf(rs.getDouble("Precio")),
                                String.valueOf(rs.getInt("Stock"))
                        );
                        listaProductos.add(producto);
                        cod_field.setEditable(false);
                        nombre_field.setEditable(false);
                        buscar_cod_button.setDisable(true);
                        buscar_nom_button.setDisable(true);
                        clean_button.setDisable(false);
                    }
                    if (!listaProductos.isEmpty()) {
                        tabla_busqueda.setItems(FXCollections.observableArrayList(listaProductos));
                    } else {
                        estado_bus_label.setText("Producto No Encontrado");
                        nombre_field.clear();
                    }
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private void buscarcodigo(){
        String codigo_aux = cod_field.getText().trim();
        if (codigo_aux.isEmpty()) {
            estado_bus_label.setText("Ingresa algo en el campo");
            return;
        }
        int codigo = Integer.parseInt(cod_field.getText());
        listaProductos.clear();
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS)) {
            String SQL_Query_select = "SELECT * FROM Productos WHERE ID = ?";
            try (PreparedStatement pstm = conn.prepareStatement(SQL_Query_select)) {
                pstm.setInt(1, codigo);
                try (ResultSet rs = pstm.executeQuery()) {
                    if (rs.next()) {
                        Producto_class producto = new Producto_class(
                                String.valueOf(rs.getInt("ID")),
                                rs.getString("Nombre"),
                                String.valueOf(rs.getDouble("Precio")),
                                String.valueOf(rs.getInt("Stock"))
                        );
                        listaProductos.add(producto);
                        cod_field.setEditable(false);
                        nombre_field.setEditable(false);
                        buscar_cod_button.setDisable(true);
                        buscar_nom_button.setDisable(true);
                        clean_button.setDisable(false);
                        estado_bus_label.setText("Producto Encontrado");
                        if (!listaProductos.isEmpty()) {
                            tabla_busqueda.setItems(FXCollections.observableArrayList(listaProductos));
                        }
                    } else {
                        estado_bus_label.setText("Producto No Encontrado");
                        cod_field.clear();
                    }
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    private void limpiar() {
        nombre_field.clear();
        cod_field.clear();
        listaProductos.clear();
        tabla_busqueda.getItems().clear();
        cod_field.setEditable(true);
        nombre_field.setEditable(true);
        cantidad_box.setDisable(true);
        buscar_nom_button.setDisable(false);
        buscar_cod_button.setDisable(false);
        clean_button.setDisable(true);
        cantidad_box.setDisable(true);
        quitar_button.setDisable(true);
        cargar_button.setDisable(true);
    }
    private void agregarprod() {
        Producto_class productoSeleccionado = (Producto_class) tabla_busqueda.getSelectionModel().getSelectedItem();
        if (productoSeleccionado != null) {
            String idProducto = productoSeleccionado.getID();
            String nombreProducto = productoSeleccionado.getProducto();
            int cantidad = cantidad_box.getValue();
            double pvp = Double.parseDouble(productoSeleccionado.getPVP());
            double subtotal = cantidad * pvp;
            total = total + subtotal;
            DecimalFormat decimalFormat = new DecimalFormat("#.00");
            String totalFormateado = decimalFormat.format(total);
            String subtotalFormateado = decimalFormat.format(subtotal);
            ItemFactura itemFactura = new ItemFactura(idProducto, nombreProducto, productoSeleccionado.getPVP(), productoSeleccionado.getStock(), cantidad, subtotalFormateado);

            tabla_fac.getItems().add(itemFactura);
            total_textfield.setText(totalFormateado);
            System.out.println(idProducto + " " + nombreProducto + " " + cantidad + " " + subtotal);
            cantidad_box.getValueFactory().setValue(1);
            cod_field.clear();
            nombre_field.clear();
            estado_bus_label.setText("Producto Registrado con Éxito");

            int resta_stock = Integer.parseInt(productoSeleccionado.getStock());
            int nuevo_stock = resta_stock - cantidad;

            try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASS)) {
                String updateStockQuery = "UPDATE Productos SET Stock = ? WHERE ID = ?";
                PreparedStatement pstmtUpdateStock = connection.prepareStatement(updateStockQuery);
                pstmtUpdateStock.setInt(1, nuevo_stock);
                pstmtUpdateStock.setString(2, idProducto);
                pstmtUpdateStock.executeUpdate();

                pstmtUpdateStock.close();

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    private void eliminarprod() {
        Object itemSeleccionado = tabla_fac.getSelectionModel().getSelectedItem();
        if (itemSeleccionado != null) {
            String subtotalstring = (String) subtotal_column.getCellData(itemSeleccionado);
            subtotalstring = subtotalstring.replace(",", ".");
            double subtotal = Double.parseDouble(subtotalstring);
            total = total - subtotal;
            quitar_button.setDisable(true);
            DecimalFormat decimalFormat = new DecimalFormat("#.00");
            String totalFormateado = decimalFormat.format(total);
            total_textfield.setText(totalFormateado);
            tabla_fac.getItems().remove(itemSeleccionado);

        } else {
            quitar_button.setDisable(true);
        }
    }
    //Funciones Factura
    private void activarfac(){
        salir_button.setDisable(true);
        crearfac_button.setDisable(true);
        cancelar_button.setDisable(false);
        enviar_button.setDisable(false);
        num_fac_textfield.setDisable(false);
        nom_textfield.setDisable(false);
        idcli_textfield.setDisable(false);
        tel_textfield.setDisable(false);
        correo_textfield.setDisable(false);
        total_textfield.setDisable(false);
        try {
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);

            String sql = "SELECT MAX(ID) FROM Ventas";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            if (rs.next()) {
                int ultimoID = rs.getInt(1);
                int nuevoID = ultimoID + 1;
                num_fac_textfield.setText(String.valueOf(nuevoID));
            }

            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private void cancelarfac(){
        salir_button.setDisable(false);
        num_fac_textfield.setText("");
        tabla_fac.getItems().clear();
        crearfac_button.setDisable(false);
        cancelar_button.setDisable(true);
        enviar_button.setDisable(true);
        num_fac_textfield.setDisable(true);
        nom_textfield.setDisable(true);
        idcli_textfield.setDisable(true);
        tel_textfield.setDisable(true);
        correo_textfield.setDisable(true);
        total_textfield.setDisable(true);
    }
    private void enviarfac() {
        int nuevoID = 0;
        int idVenta = Integer.parseInt(num_fac_textfield.getText());
        LocalDate fechaActual = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String fechaFormateada = fechaActual.format(formatter);

        String nombreCajero = nom_label.getText();

        try {
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            String consultaCajero = "SELECT ID FROM Usuarios WHERE Nombre = ?";
            PreparedStatement pstmtConsultaCajero = conn.prepareStatement(consultaCajero);
            pstmtConsultaCajero.setString(1, nombreCajero);

            ResultSet rs = pstmtConsultaCajero.executeQuery();
            int idCajero = 0;

            if (rs.next()) {
                idCajero = rs.getInt("ID");
            }

            String insertVentasQuery = "INSERT INTO Ventas (ID, ID_Cajero, Fecha) VALUES (?, ?, ?)";
            PreparedStatement pstmtVentas = conn.prepareStatement(insertVentasQuery);
            pstmtVentas.setInt(1, idVenta);
            pstmtVentas.setInt(2, idCajero);
            pstmtVentas.setString(3, fechaFormateada);
            pstmtVentas.executeUpdate();

            String sql = "SELECT MAX(ID) FROM DetalleVenta";
            Statement stmt = conn.createStatement();
            ResultSet rs2 = stmt.executeQuery(sql);

            if (rs2.next()) {
                int ultimoID = rs2.getInt(1);
                nuevoID = ultimoID + 1;
            }
            rs.close();
            stmt.close();
            ObservableList<ItemFactura> items = tabla_fac.getItems();

            for (ItemFactura rowData : items) {
                int codProducto = Integer.parseInt(rowData.getID());
                int cantidad = rowData.getCantidad();


                String insertDetalleVentaQuery = "INSERT INTO DetalleVenta (ID, ID_Venta, ID_Producto, Cantidad) VALUES (?, ?, ?, ?)";
                PreparedStatement pstmtDetalleVenta = conn.prepareStatement(insertDetalleVentaQuery);
                pstmtDetalleVenta.setInt(1, nuevoID);
                pstmtDetalleVenta.setInt(2, idVenta);
                pstmtDetalleVenta.setInt(3, codProducto);
                pstmtDetalleVenta.setInt(4, cantidad);
                pstmtDetalleVenta.executeUpdate();

                nuevoID++;

                pstmtDetalleVenta.close();

            }
            conn.close();
            mostrarMensajeExito("Factura creada con Éxito");
            cancelarfac();
        } catch (SQLException e) {
            e.printStackTrace();
            mostrarMensajeError("Hubo un error al crear la factura");
            cancelarfac();
        }
    }


    private void regresar(){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/proyecto/proyecto_poo_farmacia/Principal_Cajero.fxml"));
        Parent root;
        try {
            root = fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        Scene scene = new Scene(root);
        Stage stage = (Stage) salir_button.getScene().getWindow();
        stage.setScene(scene);
    }
}
