package com.example.usearch.Logica;

import com.example.usearch.Controladores.ControladorGeneral;
import com.example.usearch.Controladores.InterfazUsuarioController;
import com.example.usearch.Controladores.NotificacionesController;
import com.example.usearch.Main;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class CargadorEscenas {
    public ControladorGeneral controladorGeneral;
    private Stage stage;

    public CargadorEscenas(Stage stage) {
        this.stage = stage;
    }

    public void CambiarEscenas(String fxml, String title) {

        try {
            
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(fxml));
            Scene scene = new Scene(fxmlLoader.load(), 385, 660);
            stage.setScene(scene);
            this.controladorGeneral = fxmlLoader.getController();
            this.controladorGeneral.setStage(stage);

            if(controladorGeneral instanceof InterfazUsuarioController)
                ((InterfazUsuarioController) controladorGeneral).mostrarObjetosPerdidos();
            else if(controladorGeneral instanceof NotificacionesController)
                ((NotificacionesController) controladorGeneral).mostrarNotificaciones();

            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
