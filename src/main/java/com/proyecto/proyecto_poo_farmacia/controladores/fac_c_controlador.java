package com.proyecto.proyecto_poo_farmacia.controladores;

import javafx.fxml.FXML;
import javafx.scene.control.*;

public class fac_c_controlador {
    //Variables para la busqueda
    @FXML
    private Label nom_label;
    @FXML
    private Label user_label;
    @FXML
    private Button buscar_nom_button;
    @FXML
    private Button buscar_cod_button;
    @FXML
    private Button clean_button;
    @FXML
    private Button crearfac_button;
    @FXML
    private Button cargar_button;
    @FXML
    private Spinner<Integer> cantidad_box;
    @FXML
    private TextField nombre_field;
    @FXML
    private TextField cod_field;
    @FXML
    private TextField stock_field;
    @FXML
    private TextField pvp_field;
    private int contadorSpinner;
    //Principal
    @FXML
    public void initialize() {
        buscar_nom_button.setOnAction(event -> buscarnombre());
        buscar_cod_button.setOnAction(event -> buscarcodigo());
        clean_button.setOnAction(event -> limpiar());
        crearfac_button.setOnAction(event -> activarfac());
        cargar_button.setOnAction(event -> agregarprod());

        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 20);
        valueFactory.setValue(1);
        cantidad_box.setValueFactory(valueFactory);
    }
    //Funciones busqueda
    private void buscarnombre(){

    }
    private void buscarcodigo(){

    }
    private void limpiar(){

    }
    private void activarfac(){

    }
    private void agregarprod(){

    }
}
