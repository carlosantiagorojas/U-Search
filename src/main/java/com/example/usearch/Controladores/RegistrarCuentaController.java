package com.example.usearch.Controladores;

import com.example.usearch.AbstractFactory.ControladorGeneral;
import com.example.usearch.AbstractFactory.CargadorEscenas;
import com.example.usearch.Entidades.Usuario;
import com.example.usearch.Persistencia.Repository.RepositoryUsuario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class RegistrarCuentaController implements ControladorGeneral {

    RepositoryUsuario repositoryUsuario = RepositoryUsuario.getInstance();
    private Stage stage;
    @Override
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    private Button RegistrarCuenta;

    @FXML
    private ImageView RegresarButton;

    @FXML
    private PasswordField contrasena;

    @FXML
    private TextField correoElectronico;

    @FXML
    void AccionRegistrarCuenta(ActionEvent event) {

        String resultadoRegistro = registroCuenta();

        if(resultadoRegistro.equals("usuario")){
            CargadorEscenas cargadorEscenas = new CargadorEscenas(stage);
            cargadorEscenas.CambiarEscenas("InterfazUsuario.fxml", "Menu usuario");
        }
        else if (resultadoRegistro.equals("personal")){
            CargadorEscenas cargadorEscenas = new CargadorEscenas(stage);
            cargadorEscenas.CambiarEscenas("InterfazPersonal.fxml", "Menu personal");
        }
        else
            Alertas.mostrarError("Error, no se pudo registrar el usuario");

    }

    @FXML
    void AccionRegresar(MouseEvent event) {
        CargadorEscenas cargadorEscenas = new CargadorEscenas(stage);
        cargadorEscenas.CambiarEscenas("InicioSesion.fxml", "Bienvenido");
    }

    public String registroCuenta(){

        boolean registroExitoso = false;
        String registro = "";

        String correo = this.correoElectronico.getText();
        String contrasena = this.contrasena.getText();

        if (correoValido())
        {
            if (esAdministrador()) {

                Usuario usuario = new Usuario("personal",correo, contrasena);
                registroExitoso = repositoryUsuario.crear(usuario);

                if (registroExitoso)
                    registro = "personal";
                else
                    registro = "fallido";
            }
            else {
                Usuario usuario = new Usuario("usuario",correo, contrasena);
                registroExitoso = repositoryUsuario.crear(usuario);
                if (registroExitoso)
                    registro = "usuario";
                else
                    registro = "fallido";
            }
        }
        else {
            Alertas.mostrarError("El correo ingresado no es valido, por favor ingrese un correo con el siguiente formato: ejemplo@javeriana.edu.co");
        }
        return registro;
    }

    public boolean correoValido(){

        String correo = this.correoElectronico.getText();

        if (correo.matches(".+@.+")) {
            if (correo.endsWith("@javeriana.edu.co"))
                return true;
            else
                return false;
        }
        else
            return false;
    }

    public boolean esAdministrador(){
        String correo = this.correoElectronico.getText();
        if (correo.endsWith("@javeriana.edu.co") && correo.startsWith("admin"))
            return true;
        else
            return false;
    }
}
