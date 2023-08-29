package com.proyecto.proyecto_poo_farmacia.controladores;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
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
    private int contadorSpinner_Limite; //Este contador debe tener el limite de stock del producto. Validar con base de datos y cambiar por el 20 en SpinnerValueFactory

    //CONEXION SQL
    static final String DB_URL = "jdbc:mysql://localhost/FARMACIA";
    static final String USER = "root";
    static final String PASS = "root_bas3";

    //Principal
    @FXML
    public void initialize() {
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
        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 20);
        valueFactory.setValue(1);
        cantidad_box.setValueFactory(valueFactory);

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

    }
    private void limpiar(){
        num_fac_textfield.setText("");
        nom_textfield.setText("");
        idcli_textfield.setText("");
        tel_textfield.setText("");
        correo_textfield.setText("");
        nom_textfield.setText("");
        cod_field.setText("");
        total_textfield.setText("");
    }
    private void agregarprod(){


    }
    private void eliminarprod(){

    }
    private void seleccionaritem(){

    }
    //Funciones Factura
    private void activarfac(){

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

    // OJO -----> FALTA implementar los datos de la empresa

}
