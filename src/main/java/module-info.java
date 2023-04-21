module com.example.usearch {
    requires javafx.controls;
    requires javafx.fxml;
            
        requires org.controlsfx.controls;
                        requires org.kordamp.bootstrapfx.core;
            
    opens com.example.usearch to javafx.fxml;
    exports com.example.usearch;
    exports com.example.usearch.Controladores;
    opens com.example.usearch.Controladores to javafx.fxml;
}