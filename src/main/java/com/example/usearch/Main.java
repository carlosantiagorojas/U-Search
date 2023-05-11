package com.example.usearch;

import com.example.usearch.Logica.CargadorEscenas;
import com.example.usearch.Persistencia.ConexionBD;
import javafx.application.Application;
import javafx.stage.Stage;
import java.sql.Connection;

public class Main extends Application {
    @Override
    public void start(Stage stage) {
        CargadorEscenas cargadorEscenas = new CargadorEscenas(stage);
        cargadorEscenas.CambiarEscenas("InicioSesion.fxml", "Bienvenido");
        Connection conexion = ConexionBD.conectar();
    }

    public static void main(String[] args) {
        launch();
    }
}