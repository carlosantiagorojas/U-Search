package com.example.usearch.Controladores;

import com.example.usearch.Logica.CargadorEscenas;
import com.example.usearch.Logica.SesionUsuario;
import com.example.usearch.Persistencia.RepositoryNotificacion;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class NotificacionesController implements ControladorGeneral {

    RepositoryNotificacion repositoryNotificacion = RepositoryNotificacion.getInstance();

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

    @FXML
    void AcccionEliminar(ActionEvent event) {

        if(!this.ListaNotificaciones.getItems().isEmpty())
        {
            if(repositoryNotificacion.eliminarPorId(SesionUsuario.getId())) {
                // Elimimar las notificaciones de la lista
                ListaNotificaciones.getItems().clear();
                Alertas.informar("Notificaciones eliminadas");
            }
            else
                Alertas.mostrarError("No se pudieron eliminar las notificaciones");
        }
        else
            Alertas.mostrarError("No hay notificaciones para eliminar");
    }

    public void mostrarNotificaciones(){

        //SesionUsuario.mostrarDatosUsuario();
        //SesionUsuario.mostrarObjetos();
        //SesionUsuario.mostrarNotificaciones();

        ListaNotificaciones.getItems().addAll(SesionUsuario.obtenerMensajes());
    }
}
