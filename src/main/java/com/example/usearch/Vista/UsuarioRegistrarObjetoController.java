package com.example.usearch.Vista;

import com.example.usearch.AbstractFactory.ControladorGeneral;
import com.example.usearch.AbstractFactory.CargadorEscenas;
import com.example.usearch.Entidades.ObjetoPerdido;
import com.example.usearch.Sesion.SesionUsuario;
import com.example.usearch.Persistencia.Repository.RepositoryObjetoPerdido;
import com.example.usearch.Utilidades.Alertas;
import com.example.usearch.Utilidades.Fecha;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import java.sql.Date;

/**
 * Clase controladora de registrar objeto perdido
 */
public class UsuarioRegistrarObjetoController implements ControladorGeneral {

    RepositoryObjetoPerdido repositoryObjetoPerdido = RepositoryObjetoPerdido.getInstance();

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

    /**
     * Registrar un objeto perdido
     * @param event evento de accion
     */
    @FXML
    void AccionRegistrarObjeto(ActionEvent event) {

        if(comprobarCampos()) {
            if(Fecha.fechaValidaCampo(FechaPerdida.getText()))
            {
                CargadorEscenas cargadorEscenas = new CargadorEscenas(stage);
                boolean resultadoRegistro = false;
                String fecha = FechaPerdida.getText();
                Date fechaConvertida = Date.valueOf(fecha);

                ObjetoPerdido objetoPerdido = new ObjetoPerdido(fechaConvertida, UbicacionPerdida.getText(), TipoObjeto.getText(), CareteristicasFisicas.getText(), "perdido", SesionUsuario.getId());
                resultadoRegistro = repositoryObjetoPerdido.crear(objetoPerdido);

                if (resultadoRegistro) {
                    Alertas.informar("Registro exitoso");
                    cargadorEscenas.CambiarEscenas("InterfazUsuario.fxml", "Menu usuario");
                } else
                    Alertas.mostrarError("Error al registrar objeto");
            }
            else
                Alertas.mostrarError("Fecha no valida, digite la fecha en formato aaaa-mm-dd");
        }
        else
            Alertas.mostrarError("Por favor llene todos los campos");
    }

    @FXML
    void AccionRegresar(MouseEvent event) {
        CargadorEscenas cargadorEscenas = new CargadorEscenas(stage);
        cargadorEscenas.CambiarEscenas("InterfazUsuario.fxml", "Menu usuario");
    }

    /**
     * Comprobar que los campos no esten vacios
     * @return true si los campos no estan vacios, false si lo estan
     */
    public boolean comprobarCampos(){
        return !FechaPerdida.getText().isEmpty() && !UbicacionPerdida.getText().isEmpty() && !TipoObjeto.getText().isEmpty() && !CareteristicasFisicas.getText().isEmpty();
    }

}
