package com.example.usearch.Controladores;

import com.example.usearch.Logica.CargadorEscenas;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class InterfazPersonalController implements ControladorGeneral{

    private Stage stage;
    @Override
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    void AccionConsultarObjetos(MouseEvent event) {
        CargadorEscenas cargadorEscenas = new CargadorEscenas(stage);
        cargadorEscenas.CambiarEscenas("ConsultarObjetoPersonal.fxml", "Consultar Objetos");
    }

    @FXML
    void AccionHistorialConsultas(MouseEvent event) {
        CargadorEscenas cargadorEscenas = new CargadorEscenas(stage);
        cargadorEscenas.CambiarEscenas("HistorialConsultas.fxml", "Historial consulta");
    }

}
