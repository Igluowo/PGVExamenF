module com.mycompany.proyectopgvdad {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;

    opens com.mycompany.proyectopgvdad to javafx.fxml;
    exports com.mycompany.proyectopgvdad;
}
