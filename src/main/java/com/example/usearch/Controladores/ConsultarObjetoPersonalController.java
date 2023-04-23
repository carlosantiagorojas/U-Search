package com.example.usearch.Controladores;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class ConsultarObjetoPersonalController implements ControladorGeneral{
    private Stage stage;
    @Override
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    private Button ConsultarButton;

    @FXML
    private TextField FechaPerdida;

    @FXML
    private TextField IdObjeto;

    @FXML
    private ImageView RegresarButton;

    @FXML
    private ComboBox<?> TipoObjeto;

    @FXML
    private TextField UbicacionPerdida;

    @FXML
    void AccionConsultar(ActionEvent event) {

    }

    @FXML
    void AccionRegresar(MouseEvent event) {

    }

}
