package com.proyecto.proyecto_poo_farmacia.controladores;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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

    }
}
