package com.example.usearch.Controladores;

import com.example.usearch.Logica.CargadorEscenas;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
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
    private Button RegresarInicio;

    @FXML
    private TableColumn<?, ?> caracteristicas;

    @FXML
    private TableColumn<?, ?> estado;

    @FXML
    private TableColumn<?, ?> fecha;

    @FXML
    private TableColumn<?, ?> id;

    @FXML
    private TableView<?> tablaObjetos;

    @FXML
    private TableColumn<?, ?> tipo;

    @FXML
    void AccionActualizar(ActionEvent event) {

    }

    @FXML
    void AccionRegresar(MouseEvent event) {
        CargadorEscenas cargadorEscenas = new CargadorEscenas(stage);
        cargadorEscenas.CambiarEscenas("ConsultarObjetoPersonal.fxml", "Consultar Objetos");
    }

    @FXML
    void AccionRegresarInicio(ActionEvent event) {
        CargadorEscenas cargadorEscenas = new CargadorEscenas(stage);
        cargadorEscenas.CambiarEscenas("InterfazPersonal.fxml", "Menu Personal");
    }

}
