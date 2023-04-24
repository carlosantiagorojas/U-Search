package com.example.usearch.Controladores;

import com.example.usearch.Logica.CargadorEscenas;
import com.example.usearch.Persistencia.ConexionBD;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.sql.Connection;

public class RegistrarCuentaController implements ControladorGeneral {

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
    private TextField contrasena;

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
        {
            System.out.println("no se pudo registrar");
        }
    }

    @FXML
    void AccionRegresar(MouseEvent event) {
        CargadorEscenas cargadorEscenas = new CargadorEscenas(stage);
        cargadorEscenas.CambiarEscenas("InicioSesion.fxml", "Bienvenido");
    }

    public String registroCuenta(){

        boolean registroExitoso = false;
        String registro = "";
        ConexionBD conexionBD = new ConexionBD();
        String correo = this.correoElectronico.getText();
        String contrasena = this.contrasena.getText();

        if (correoValido())
        {
            if (esAdministrador()) {
                System.out.println("entre aqui");
                registroExitoso = conexionBD.registrarUsuario("personal", correo, contrasena);
                if (registroExitoso)
                    registro = "personal";
                else
                    registro = "fallido";
            }
            else {
                registroExitoso = conexionBD.registrarUsuario("usuario", correo, contrasena);
                if (registroExitoso)
                    registro = "usuario";
                else
                    registro = "fallido";
            }
        }
        else {
            registro = "fallido";
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