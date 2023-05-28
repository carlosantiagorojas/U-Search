package com.example.usearch.Controladores;

import com.example.usearch.AbstractFactory.ControladorGeneral;
import com.example.usearch.AbstractFactory.CargadorEscenas;
import com.example.usearch.Entidades.SesionUsuario;
import com.example.usearch.Persistencia.Repository.RepositoryNotificacion;
import com.example.usearch.Utilidades.Alertas;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * Controlador de la interfaz de notificaciones
 */
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

    /**
     * Elimina las notificaciones del usuario
     * @param event Evento de acción
     */
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

    /**
     * Muestra las notificaciones de la sesion activa del usuario
     */
    public void mostrarNotificaciones(){

        //SesionUsuario.mostrarDatosUsuario();
        //SesionUsuario.mostrarObjetos();
        //SesionUsuario.mostrarNotificaciones();

        ListaNotificaciones.getItems().addAll(SesionUsuario.obtenerMensajes());
    }
}
