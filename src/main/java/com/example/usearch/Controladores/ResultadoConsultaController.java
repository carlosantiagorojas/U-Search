package com.example.usearch.Controladores;

import com.example.usearch.Logica.CargadorEscenas;
import com.example.usearch.Logica.Notificacion;
import com.example.usearch.Logica.ObjetoPerdido;
import com.example.usearch.Memento.Caretaker;
import com.example.usearch.Memento.Originator;
import com.example.usearch.Persistencia.ConexionBD;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import java.sql.Date;
import java.util.ArrayList;

public class ResultadoConsultaController implements ControladorGeneral {

    private Stage stage;
    ObservableList<ObjetoPerdido> listaObjetos;
    Originator originator = new Originator();

    Caretaker caretaker = new Caretaker();

    @Override
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    private Button ActualizarButton;

    @FXML
    private ImageView RegresarButton;

    @FXML
    private Button RegresarInicio;

    @FXML
    private TableColumn<ObjetoPerdido, String> caracteristicas;

    @FXML
    private TableColumn<ObjetoPerdido, String> estado;

    @FXML
    private TableColumn<ObjetoPerdido, String> ubicacion;

    @FXML
    private TableColumn<ObjetoPerdido, Date> fecha;

    @FXML
    private TableColumn<ObjetoPerdido, String> tipo;

    @FXML
    private TableView<ObjetoPerdido> tablaObjetos;

    @FXML
    void AccionRegresar(MouseEvent event) {
        CargadorEscenas cargadorEscenas = new CargadorEscenas(stage);
        cargadorEscenas.CambiarEscenas("ConsultarObjetoPersonal.fxml", "Consultar Objetos");
    }

    @FXML
    void AccionRegresarInicio(ActionEvent event) {
        CargadorEscenas cargadorEscenas = new CargadorEscenas(stage);
        cargadorEscenas.CambiarEscenas("InterfazPersonal.fxml", "Menu Personal");
    }

    @FXML
    void AccionActualizar(ActionEvent event) {

        ObjetoPerdido objetoperdido = null;

        try
        {
            objetoperdido = tablaObjetos.getSelectionModel().getSelectedItem();
            objetoperdido.verInformacion();
        }catch (Exception e) {
            Alertas.mostrarError("No se ha seleccionado ningun objeto");
        }

        boolean resultadoActualizacion = false;

        if (objetoperdido != null) {
            if (objetoperdido.getEstado().equals("perdido")) {
                ConexionBD conexion = new ConexionBD();
                resultadoActualizacion = conexion.actualizarEncontrado(objetoperdido.getId());

                if (resultadoActualizacion) {

                    ObjetoPerdido objetoActualizado = null;
                    ObservableList<ObjetoPerdido> objetosTabla = tablaObjetos.getItems();

                    for (ObjetoPerdido obj : objetosTabla) {
                        if (obj.getId() == objetoperdido.getId()) {
                            obj.setEstado("encontrado");
                            objetoActualizado = obj;
                            break;
                        }
                    }

                    // Actualizar la tabla si se encontro un objeto
                    if (objetoActualizado != null) {
                        int index = tablaObjetos.getItems().indexOf(objetoActualizado);
                        tablaObjetos.getItems().set(index, objetoActualizado);

                        Alertas.informar("Actualizacion exitosa");
                    } else
                        Alertas.informar("No se pudo actualizar la tabla");

                    // Enviar la notificacion al usuario
                    boolean resultadoNotificacion = false;
                    String mensaje;
                    mensaje = "Su objeto " + objetoperdido.getTipo() + " con la siguiente informacion: \n" +
                            "Fecha: " + objetoperdido.getFechaPerdida() + "\n" +
                            "Ubicacion: " + objetoperdido.getUbicacion() + "\n" +
                            "ha sido ENCONTRADO";
                    Notificacion notificacion = new Notificacion(objetoperdido.getIdUsuario(), mensaje);
                    resultadoNotificacion = conexion.enviarNotificacion(notificacion.getIdUsuario(), notificacion.getMensaje());

                    if(resultadoNotificacion)
                        System.out.println("Se ha enviado una notificacion al usuario");
                    else
                        System.out.println("No se pudo enviar la notificacion al usuario");

                } else
                    Alertas.mostrarError("Error en la actualización de la base de datos");
            } else
                Alertas.mostrarError("El objeto ya fue encontrado, no se puede actualizar");
        }
    }

    public void actualizarTabla(ArrayList<ObjetoPerdido> objetosPerdidos)
    {
        try {
            this.listaObjetos = FXCollections.observableArrayList(objetosPerdidos);
            tablaObjetos.setItems(listaObjetos);
            this.fecha.setCellValueFactory(new PropertyValueFactory<ObjetoPerdido, Date>("fechaPerdida"));
            this.ubicacion.setCellValueFactory(new PropertyValueFactory<ObjetoPerdido, String>("ubicacion"));
            this.tipo.setCellValueFactory(new PropertyValueFactory<ObjetoPerdido, String>("tipo"));
            this.caracteristicas.setCellValueFactory(new PropertyValueFactory<ObjetoPerdido, String>("caracteristicas"));
            this.estado.setCellValueFactory(new PropertyValueFactory<ObjetoPerdido, String>("estado"));

        } catch (Exception e) {
            Alertas.mostrarError("Error al actualizar la tabla");
        }
    }

}
