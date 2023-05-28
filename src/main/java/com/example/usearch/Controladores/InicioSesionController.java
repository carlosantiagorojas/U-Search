package com.example.usearch.Controladores;

import com.example.usearch.AbstractFactory.ControladorGeneral;
import com.example.usearch.AbstractFactory.CargadorEscenas;
import com.example.usearch.Entidades.SesionUsuario;
import com.example.usearch.Entidades.Usuario;
import com.example.usearch.Persistencia.Repository.RepositoryNotificacion;
import com.example.usearch.Persistencia.Repository.RepositoryUsuario;
import com.example.usearch.Utilidades.Alertas;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.IOException;

public class InicioSesionController implements ControladorGeneral {

    RepositoryUsuario repositoryUsuario = RepositoryUsuario.getInstance();
    RepositoryNotificacion repositoryNotificacion = RepositoryNotificacion.getInstance();

    private Stage stage;
    @Override
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    private PasswordField CampoContrasena;

    @FXML
    private TextField CampoCorreo;

    @FXML
    private Button IniciarSesionButton;

    @FXML
    private Button RegistroCuenta;

    /**
     * Metodo que se ejecuta al presionar el boton de iniciar sesion
     * @param event evento de presionar el boton
     * @throws IOException excepcion de entrada y salida
     */
    @FXML
    void AccionIniciarSesion(ActionEvent event) throws IOException {
        CargadorEscenas cargadorEscenas = new CargadorEscenas(stage);

        boolean usuarioEncontrado = false;
        Usuario usuario = new Usuario(CampoCorreo.getText(), CampoContrasena.getText());
        usuarioEncontrado = repositoryUsuario.consultarPorCredenciales(usuario);

        // Comprobar el tipo de usuario que inicio sesion
        if(!usuarioEncontrado)
            Alertas.mostrarError("Correo y/o contraseña incorrectos");
        //si se encuentra el usuario se cargan los datos
        else if(SesionUsuario.getRol().equals("usuario")) {
            // cargar las   notificaciones del usuario
            SesionUsuario.setNotificaciones(repositoryNotificacion.consultarListaPorId(SesionUsuario.getId()));
            cargadorEscenas.CambiarEscenas("InterfazUsuario.fxml", "Menu usuario");
        }
        else if (SesionUsuario.getRol().equals("personal")) {
            cargadorEscenas.CambiarEscenas("InterfazPersonal.fxml", "Menu Personal");
        }
    }

    /**
     * cambiar de escena para registrar a un usuario
     * @param event evento de presionar el boton
     */
    @FXML
    void AccionRegistrar(ActionEvent event) {
        CargadorEscenas cargadorEscenas = new CargadorEscenas(stage);
        cargadorEscenas.CambiarEscenas("RegistrarCuenta.fxml", "Registro de cuenta");
    }

}
