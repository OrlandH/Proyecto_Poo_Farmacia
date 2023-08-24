package com.proyecto.proyecto_poo_farmacia.controladores;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class buscar_c_controlador {
    @FXML
    private Button buscar_nom_button;
    @FXML
    private Button buscar_cod_button;
    @FXML
    private Button clean_button;
    @FXML
    private Button volver_button;
    @FXML
    private TextField nombre_field;
    @FXML
    private TextField cod_field;
    @FXML
    private TextField stock_field;
    @FXML
    private TextField pvp_field;
    //Principal
    @FXML
    private void initialize() {
        buscar_nom_button.setOnAction(event -> buscarnombre());
        buscar_cod_button.setOnAction(event -> buscarcodigo());
        clean_button.setOnAction(event -> limpiar());
        volver_button.setOnAction(event -> volver());
    }
    //Funciones
    private void buscarnombre(){

    }
    private void buscarcodigo(){

    }
    private void limpiar(){

    }
    private void volver(){

    }
}
