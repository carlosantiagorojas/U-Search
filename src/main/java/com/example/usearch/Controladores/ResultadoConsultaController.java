package com.example.usearch.Controladores;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class ResultadoConsultaController implements ControladorGeneral {

    private Stage stage;
    @Override
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    private Button ActualizarButton;

    @FXML
    private ImageView RegresarButton;

    @FXML
    void AccionActualizar(ActionEvent event) {

    }

    @FXML
    void AccionRegresar(MouseEvent event) {

    }

}
