package com.example.usearch.Utilidades;

import javafx.scene.control.Alert;

/**
 * Clase utilitaria para mostrar alertas
 */
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

    /*
    public static void Confirmacion() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText("CONFIRMACION");
        alert.setContentText("¿Esta seguro de realizar esta accion?");
        alert.show();
    }*/
}
