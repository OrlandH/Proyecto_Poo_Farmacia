module com.proyecto.proyecto_poo_farmacia {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    exports com.proyecto.proyecto_poo_farmacia.controladores.POO;
    opens com.proyecto.proyecto_poo_farmacia.controladores.POO to javafx.fxml;
    opens com.proyecto.proyecto_poo_farmacia to javafx.fxml;
    exports com.proyecto.proyecto_poo_farmacia;
    exports com.proyecto.proyecto_poo_farmacia.controladores;
    opens com.proyecto.proyecto_poo_farmacia.controladores to javafx.fxml;
}