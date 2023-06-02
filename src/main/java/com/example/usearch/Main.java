package com.example.usearch;

import com.example.usearch.Vista.CargadorEscenas;
import com.example.usearch.Persistencia.Utilidades.ConexionBD;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage stage) {
        CargadorEscenas cargadorEscenas = new CargadorEscenas(stage);
        cargadorEscenas.CambiarEscenas("InicioSesion.fxml", "Bienvenido");
        // Conectar con la base de datos
        ConexionBD.conectarBaseDeDatos();
    }

    public static void main(String[] args) {
        launch();
    }
}