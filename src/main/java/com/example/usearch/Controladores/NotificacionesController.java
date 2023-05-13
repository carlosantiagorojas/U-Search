package com.example.usearch.Controladores;

import com.example.usearch.Logica.CargadorEscenas;
import com.example.usearch.Logica.Notificacion;
import com.example.usearch.Logica.SesionUsuario;
import com.example.usearch.Persistencia.ConexionBD;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class NotificacionesController implements ControladorGeneral {

    private Stage stage;

    @Override
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    private ImageView RegresarButton;

    @FXML
    private ListView<String> ListaNotificaciones;

    @FXML
    void AccionRegresar(MouseEvent event) {
        CargadorEscenas cargadorEscenas = new CargadorEscenas(stage);
        cargadorEscenas.CambiarEscenas("InterfazUsuario.fxml", "Menu usuario");
    }

    public void mostrarNotificaciones(){
        ConexionBD conexion = new ConexionBD();

        //SesionUsuario.mostrarDatosUsuario();
        //SesionUsuario.mostrarObjetos();
        SesionUsuario.mostrarNotificaciones();
        ListaNotificaciones.getItems().addAll(SesionUsuario.obtenerMensajes());
    }
}
