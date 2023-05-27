package com.example.usearch.Controladores;

import com.example.usearch.Logica.CargadorEscenas;
import com.example.usearch.Logica.SesionUsuario;
import com.example.usearch.Logica.Usuario;
import com.example.usearch.Persistencia.RepositoryNotificacion;
import com.example.usearch.Persistencia.RepositoryUsuario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.IOException;

public class InicioSesionController implements ControladorGeneral{

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
            // cargar las notificaciones del usuario
            SesionUsuario.setNotificaciones(repositoryNotificacion.consultarListaPorId(SesionUsuario.getId()));
            cargadorEscenas.CambiarEscenas("InterfazUsuario.fxml", "Menu usuario");
        }
        else if (SesionUsuario.getRol().equals("personal")) {
            cargadorEscenas.CambiarEscenas("InterfazPersonal.fxml", "Menu Personal");
        }
    }

    @FXML
    void AccionRegistrar(ActionEvent event) {
        CargadorEscenas cargadorEscenas = new CargadorEscenas(stage);
        cargadorEscenas.CambiarEscenas("RegistrarCuenta.fxml", "Registro de cuenta");
    }

}
