package com.example.usearch.Controladores;

import javafx.scene.control.Alert;

public class Alertas {

    public static void mostrarError(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText("ERROR");
        alert.setContentText(mensaje);
        alert.show();
    }

    public static void informar(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("INFORMACION");
        alert.setContentText(mensaje);
        alert.show();
    }
}
