package com.example.usearch.Controladores;

import javafx.scene.control.Alert;

public class Alertas {

    /**
     * Muestra una alerta de error
     * @param mensaje mensaje a mostrar
     */
    public static void mostrarError(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText("ERROR");
        alert.setContentText(mensaje);
        alert.show();
    }

    /**
     * Muestra una alerta de informacion
     * @param mensaje mensaje a mostrar
     */
    public static void informar(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("INFORMACION");
        alert.setContentText(mensaje);
        alert.show();
    }
}
