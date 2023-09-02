package com.proyecto.proyecto_poo_farmacia.controladores;

import com.proyecto.proyecto_poo_farmacia.controladores.POO.Producto_class;
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
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.sql.*;
public class fac_c_controlador {
    //Cabecera variables
    @FXML
    private Label nom_label;
    @FXML
    private Label user_label;
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
    private Button selec_button;
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

        //Link de Botones con metodos
        buscar_nom_button.setOnAction(event -> buscarnombre());
        buscar_cod_button.setOnAction(event -> buscarcodigo());
        clean_button.setOnAction(event -> limpiar());
        crearfac_button.setOnAction(event -> activarfac());
        cargar_button.setOnAction(event -> agregarprod());
        selec_button.setOnAction(event -> seleccionaritem());
        quitar_button.setOnAction(event -> eliminarprod());
        enviar_button.setOnAction(event -> enviarfac());
        salir_button.setOnAction(event -> regresar());



        //Contador del Spinner


        // Obtener la fecha actual
        LocalDate fechaActual = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String fechaFormateada = fechaActual.format(formatter);
        fecha_label.setText(fechaFormateada);
    }
    //Funciones busqueda
    private void buscarnombre(){

    }

    private void buscarcodigo(){
        int contadorSpinner_Limite;
        int codigo = Integer.parseInt(cod_field.getText());
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
                        contadorSpinner_Limite = Integer.parseInt(producto.getStock());
                        cod_field.setEditable(false);
                        nombre_field.setEditable(false);
                        buscar_nom_button.setDisable(true);
                        clean_button.setDisable(false);
                        estado_bus_label.setText("Producto Encontrado");
                        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, contadorSpinner_Limite);
                        valueFactory.setValue(1);
                        cantidad_box.setValueFactory(valueFactory);
                    } else {
                        estado_bus_label.setText("Producto No Encontrado");
                        cod_field.setText("");
                    }
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    private void limpiar(){
        nombre_field.clear();
        cod_field.clear();
        tabla_busqueda.getItems().clear();
        cod_field.setEditable(true);
        nombre_field.setEditable(true);
        buscar_nom_button.setDisable(false);
        buscar_cod_button.setDisable(false);
        clean_button.setDisable(true);
    }
    private void agregarprod(){


    }
    private void eliminarprod(){

    }
    private void seleccionaritem(){

    }
    //Funciones Factura
    private void activarfac(){
        cantidad_box.setDisable(false);
        cargar_button.setDisable(false);
        quitar_button.setDisable(false);
        crearfac_button.setDisable(true);
        cancelar_button.setDisable(false);
    }
    private void enviarfac(){

    }
    private void regresar(){
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
        Stage stage = (Stage) salir_button.getScene().getWindow();
        stage.setScene(scene);
    }

}
