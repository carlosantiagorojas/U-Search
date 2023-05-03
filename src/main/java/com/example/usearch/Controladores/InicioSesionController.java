package com.example.usearch.Controladores;

import com.example.usearch.Logica.CargadorEscenas;
import com.example.usearch.Logica.SesionUsuario;
import com.example.usearch.Persistencia.ConexionBD;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.IOException;

public class InicioSesionController implements ControladorGeneral{
    private Stage stage;
    @Override
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    private TextField CampoContrasena;

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
        ConexionBD conexion = new ConexionBD();
        usuarioEncontrado = conexion.consultarUsuarioSesion(CampoCorreo.getText(), CampoContrasena.getText());

        // Comprobar el tipo de usuario que inicio sesion
        if(!usuarioEncontrado)
            Alertas.mostrarError("Correo y/o contraseña incorrectos");
        else if(SesionUsuario.getRol().equals("usuario"))
            cargadorEscenas.CambiarEscenas("InterfazUsuario.fxml", "Menu usuario");
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
