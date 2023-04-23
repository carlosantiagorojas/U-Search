package com.example.usearch.Controladores;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class RegistrarCuentaController implements ControladorGeneral {

    private Stage stage;
    @Override
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    private TextField FechaPerdida;

    @FXML
    private Button RegistrarCuenta;

    @FXML
    private ImageView RegresarButton;

    @FXML
    private TextField UbicacionPerdida;

    @FXML
    void AccionRegistrarCuenta(ActionEvent event) {

    }

    @FXML
    void AccionRegresar(MouseEvent event) {

    }

}
