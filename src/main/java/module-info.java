module com.proyecto.proyecto_poo_farmacia {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.proyecto.proyecto_poo_farmacia to javafx.fxml;
    exports com.proyecto.proyecto_poo_farmacia;
}