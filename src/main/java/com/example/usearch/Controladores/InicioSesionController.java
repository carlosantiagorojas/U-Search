package com.example.usearch.Controladores;

import com.example.usearch.Logica.CargadorEscenas;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class InicioSesionController implements ControladorGeneral{
    private Stage stage;
    @Override
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    private TextField CampoContrasena;

    @FXML
    private TextField CampoCorreo;

    @FXML
    private Button IniciarSesionButton;

    @FXML
    private Button RegistroCuenta;

    @FXML
    void AccionIniciarSesion(ActionEvent event) throws IOException {
        CargadorEscenas cargadorEscenas = new CargadorEscenas(stage);
        cargadorEscenas.changeScene("InterfazUsuario.fxml", "Menu usuario");
    }

    @FXML
    void AccionRegistrar(ActionEvent event) {

    }

}
