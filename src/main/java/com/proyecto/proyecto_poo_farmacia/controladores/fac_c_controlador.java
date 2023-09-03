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
public class fac_c_controlador {
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
    //CONEXION SQL
    static final String DB_URL = "jdbc:mysql://localhost/FARMACIA_PROYECTO";
    static final String USER = "root";
    static final String PASS = "24@Diolove";
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
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String fechaFormateada = fechaActual.format(formatter);
        fecha_label.setText(fechaFormateada);
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
            DecimalFormat decimalFormat = new DecimalFormat("#.00");
            String subtotalFormateado = decimalFormat.format(subtotal);
            ItemFactura itemFactura = new ItemFactura(idProducto, nombreProducto, productoSeleccionado.getPVP(), productoSeleccionado.getStock(), cantidad, subtotalFormateado);
            tabla_fac.getItems().add(itemFactura);
            cantidad_box.getValueFactory().setValue(1);
            cod_field.clear();
            nombre_field.clear();
            estado_bus_label.setText("Producto Registrado con Exito");
            buscar_cod_button.setDisable(false);
            buscar_nom_button.setDisable(false);
        }
    }
    private void eliminarprod() {
        Object itemSeleccionado = tabla_fac.getSelectionModel().getSelectedItem();
        if (itemSeleccionado != null) {
            quitar_button.setDisable(true);
            tabla_fac.getItems().remove(itemSeleccionado);
        }
        else{
            quitar_button.setDisable(true);
        }
    }
    //Funciones Factura
    private void activarfac(){
        crearfac_button.setDisable(true);
        cancelar_button.setDisable(false);
        num_fac_textfield.setDisable(false);
        nom_textfield.setDisable(false);
        idcli_textfield.setDisable(false);
        tel_textfield.setDisable(false);
        correo_textfield.setDisable(false);
        total_textfield.setDisable(false);
    }
    private void cancelarfac(){
        tabla_fac.getItems().clear();
        crearfac_button.setDisable(false);
        cancelar_button.setDisable(true);
        num_fac_textfield.setDisable(true);
        nom_textfield.setDisable(true);
        idcli_textfield.setDisable(true);
        tel_textfield.setDisable(true);
        correo_textfield.setDisable(true);
        total_textfield.setDisable(true);
    }
    private void enviarfac(){
    }
    private void regresar(){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/proyecto/proyecto_poo_farmacia/Login.fxml"));
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