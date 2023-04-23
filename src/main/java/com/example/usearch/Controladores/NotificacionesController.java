package com.example.usearch.Controladores;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class NotificacionesController implements ControladorGeneral{

    private Stage stage;
    @Override
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    private ImageView RegresarButton;

    @FXML
    void AccionRegresar(MouseEvent event) {

    }

}
