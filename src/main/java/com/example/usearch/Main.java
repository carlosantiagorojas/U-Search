package com.example.usearch;

import com.example.usearch.Logica.CargadorEscenas;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) {
        CargadorEscenas cargadorEscenas = new CargadorEscenas(stage);
        cargadorEscenas.changeScene("InicioSesion.fxml", "Bienvenido");
    }

    public static void main(String[] args) {
        launch();
    }
}