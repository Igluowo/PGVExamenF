module com.mycompany.proyectopgvdad {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.mycompany.proyectopgvdad to javafx.fxml;
    exports com.mycompany.proyectopgvdad;
}
