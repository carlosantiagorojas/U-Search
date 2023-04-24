package com.example.usearch.Controladores;

import com.example.usearch.Logica.CargadorEscenas;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class UsuarioRegistrarObjetoController implements ControladorGeneral{
    private Stage stage;
    @Override
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    private TextArea CareteristicasFisicas;

    @FXML
    private TextField FechaPerdida;

    @FXML
    private Button RegistrarObjetoButton;

    @FXML
    private ImageView RegresarButton;

    @FXML
    private TextField TipoObjeto;

    @FXML
    private TextField UbicacionPerdida;

    @FXML
    void AccionRegistrarObjeto(ActionEvent event) {

    }

    @FXML
    void AccionRegresar(MouseEvent event) {
        CargadorEscenas cargadorEscenas = new CargadorEscenas(stage);
        cargadorEscenas.CambiarEscenas("InterfazUsuario.fxml", "Menu usuario");
    }

}
