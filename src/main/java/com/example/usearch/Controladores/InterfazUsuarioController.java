package com.example.usearch.Controladores;

import com.example.usearch.Logica.CargadorEscenas;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class InterfazUsuarioController implements ControladorGeneral{

    private Stage stage;

    @Override
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    private ImageView NotificacionesButtton;

    @FXML
    private ImageView RegistrarObjetoButton;

    @FXML
    void AccionNotificaciones(MouseEvent event) {
        CargadorEscenas cargadorEscenas = new CargadorEscenas(stage);
        cargadorEscenas.CambiarEscenas("Notificaciones.fxml", "Notificaciones");
    }

    @FXML
    void AccionRegistrarObjeto(MouseEvent event) {
        CargadorEscenas cargadorEscenas = new CargadorEscenas(stage);
        cargadorEscenas.CambiarEscenas("UsuarioRegistrarObjeto.fxml", "Registrar Objeto");
    }

}
