module com.example.usearch {
    requires javafx.controls;
    requires javafx.fxml;
            
    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;

    opens com.example.usearch to javafx.fxml;
    exports com.example.usearch;
    exports com.example.usearch.Vista;
    exports com.example.usearch.Entidades;
    opens com.example.usearch.Entidades to javafx.base;
    exports com.example.usearch.AbstractFactory;
    opens com.example.usearch.AbstractFactory to javafx.base, javafx.fxml;
    exports com.example.usearch.Utilidades;
    opens com.example.usearch.Utilidades to javafx.fxml;
    exports com.example.usearch.Sesion;
    opens com.example.usearch.Sesion to javafx.base;
    opens com.example.usearch.Vista to javafx.base, javafx.fxml;

}