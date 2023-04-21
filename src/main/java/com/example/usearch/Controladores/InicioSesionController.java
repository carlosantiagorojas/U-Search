package com.example.usearch.Controladores;

import com.example.usearch.HelloApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class InicioSesionController {
    private Stage stage;

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
    void AccionIniciarSesion(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("InterfazUsuario.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 385, 660);
        stage.setScene(scene);
        stage.setTitle("Menu usuario");
        InterfazUsuarioController controladorInUsuario = fxmlLoader.getController();
        controladorInUsuario.setStage(stage);
        stage.show();
        this.stage.close();
    }

}
