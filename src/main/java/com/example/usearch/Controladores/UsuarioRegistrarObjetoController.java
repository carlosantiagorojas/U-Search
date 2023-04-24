package com.example.usearch.Controladores;

import com.example.usearch.Logica.CargadorEscenas;
import com.example.usearch.Persistencia.ConexionBD;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.sql.Date;

public class UsuarioRegistrarObjetoController implements ControladorGeneral{
    private Stage stage;
    @Override
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    private TextArea CareteristicasFisicas;

    @FXML
    private TextField FechaPerdida;

    @FXML
    private Button RegistrarObjetoButton;

    @FXML
    private ImageView RegresarButton;

    @FXML
    private TextField TipoObjeto;

    @FXML
    private TextField UbicacionPerdida;

    @FXML
    void AccionRegistrarObjeto(ActionEvent event) {
        boolean resultadoRegistro = false;
        ConexionBD conexion = new ConexionBD();
        String fecha = FechaPerdida.getText();
        Date fechaConvertida = Date.valueOf(fecha);

        resultadoRegistro = conexion.registrarObjeto(fechaConvertida, UbicacionPerdida.getText(), TipoObjeto.getText(), CareteristicasFisicas.getText(), "perdido", 1-);

        if(resultadoRegistro)
            System.out.println("Objeto registrado");
        else
            System.out.println("No se pudo registrar el objeto");
    }

    @FXML
    void AccionRegresar(MouseEvent event) {
        CargadorEscenas cargadorEscenas = new CargadorEscenas(stage);
        cargadorEscenas.CambiarEscenas("InterfazUsuario.fxml", "Menu usuario");
    }

}
