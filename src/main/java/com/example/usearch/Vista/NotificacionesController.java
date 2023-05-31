package com.example.usearch.Vista;

import com.example.usearch.Sesion.SesionUsuario;
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

        // Si el usuario tiene notificaciones
        if(!this.ListaNotificaciones.getItems().isEmpty())
        {
            // Eliminar las notificaciones de la base de datos
            if(repositoryNotificacion.eliminarPorId(SesionUsuario.getId())) {

                // Elimimar las notificaciones de la lista y de la sesion del usuario
                ListaNotificaciones.getItems().clear();
                SesionUsuario.EliminarNotificaciones();

                Alertas.informar("Notificaciones eliminadas");
            }
            else
                Alertas.mostrarError("No se pudieron eliminar las notificaciones");
        }
        else
            Alertas.mostrarError("No hay notificaciones para eliminar");
    }

    /**
     * Muestra las notificaciones de la sesion activa del usuario con formato de string
     */
    public void mostrarNotificaciones(){

        //SesionUsuario.mostrarDatosUsuario();
        //SesionUsuario.mostrarObjetos();
        //SesionUsuario.mostrarNotificaciones();

        ListaNotificaciones.getItems().addAll(SesionUsuario.obtenerMensajes());
    }
}
