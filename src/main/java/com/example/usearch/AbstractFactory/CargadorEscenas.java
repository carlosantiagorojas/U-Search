package com.example.usearch.AbstractFactory;

import com.example.usearch.Vista.HistorialController;
import com.example.usearch.Vista.InterfazUsuarioController;
import com.example.usearch.Vista.NotificacionesController;
import com.example.usearch.Main;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

/**
 * Clase que se encarga de cambiar las escenas
 */
public class CargadorEscenas {
    public ControladorGeneral controladorGeneral;
    private final Stage stage;

    public CargadorEscenas(Stage stage) {
        this.stage = stage;
    }

    /**
     * Metodo que cambia las escenas
     * @param fxml ruta del archivo fxml
     * @param title titulo de la ventana
     */
    public void CambiarEscenas(String fxml, String title) {

        try {
            
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(fxml));
            Scene scene = new Scene(fxmlLoader.load(), 385, 660);
            stage.setScene(scene);
            stage.setTitle(title);
            this.controladorGeneral = fxmlLoader.getController();
            this.controladorGeneral.setStage(stage);

            if(controladorGeneral instanceof InterfazUsuarioController)
                ((InterfazUsuarioController) controladorGeneral).mostrarObjetosPerdidos();
            else if(controladorGeneral instanceof NotificacionesController)
                ((NotificacionesController) controladorGeneral).mostrarNotificaciones();
            else if(controladorGeneral instanceof HistorialController)
                ((HistorialController) controladorGeneral).mostrarHistorial();

            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
