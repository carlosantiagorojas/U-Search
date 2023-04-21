package com.example.usearch;

import com.example.usearch.Controladores.InicioSesionController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("InicioSesion.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 385, 660);
        stage.setScene(scene);
        stage.setTitle("Bienvenido");
        InicioSesionController controladorInSecion = fxmlLoader.getController();
        controladorInSecion.setStage(stage);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}