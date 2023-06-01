package com.example.usearch.Vista;

import com.example.usearch.Entidades.Notificacion;
import com.example.usearch.Entidades.ObjetoPerdido;
import com.example.usearch.Persistencia.Repository.IRepository;
import com.example.usearch.Persistencia.Repository.RepositoryNotificacion;
import com.example.usearch.Persistencia.Repository.RepositoryObjetoPerdido;
import com.example.usearch.Utilidades.Alertas;
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

/**
 * Clase controladora del resultado de la consulta
 */
public class ResultadoConsultaController implements ControladorGeneral {

    IRepository repositoryObjetoPerdido = RepositoryObjetoPerdido.getInstance();

    IRepository repositoryNotificacion = RepositoryNotificacion.getInstance();

    private Stage stage;

    ObservableList<ObjetoPerdido> listaObjetos;

    private String escenaAnterior;

    private String tituloEscenaAnterior;

    public String getEscenaAnterior() {
        return escenaAnterior;
    }

    public void setEscenaAnterior(String escenaAnterior) {
        this.escenaAnterior = escenaAnterior;
    }

    public String getTituloEscenaAnterior() {
        return tituloEscenaAnterior;
    }

    public void setTituloEscenaAnterior(String tituloEscenaAnterior) {
        this.tituloEscenaAnterior = tituloEscenaAnterior;
    }

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
        cargadorEscenas.CambiarEscenas(getEscenaAnterior(), getTituloEscenaAnterior());
    }

    @FXML
    void AccionRegresarInicio(ActionEvent event) {
        CargadorEscenas cargadorEscenas = new CargadorEscenas(stage);
        cargadorEscenas.CambiarEscenas("InterfazPersonal.fxml", "Menu Personal");
    }

    /**
     * metodo para actualizar el estado de un objeto perdido
     * @param event Evento de la accion
     */
    @FXML
    void AccionActualizar(ActionEvent event) {

        ObjetoPerdido objetoperdido = null;

        // Se identifica primero si se selecciono un objeto de la tabla
        try
        {
            objetoperdido = tablaObjetos.getSelectionModel().getSelectedItem();
            //objetoperdido.verInformacion();
        }catch (Exception e) {
            Alertas.mostrarError("No se ha seleccionado ningun objeto");
        }

        boolean resultadoActualizacion = false;

        // Si se selecciono un objeto, se verifica que el estado sea perdido
        if (objetoperdido != null) {
            if (objetoperdido.getEstado().equals("perdido")) {
                resultadoActualizacion = repositoryObjetoPerdido.actualizarPorId(objetoperdido.getId());

                //Si se pudo realizar la actualizacion en la base de datos
                if (resultadoActualizacion) {

                    ObjetoPerdido objetoActualizado = null;
                    ObservableList<ObjetoPerdido> objetosTabla = tablaObjetos.getItems();

                    // Buscar en los objetos de la tabla el objeto que se actualizo
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

                    // Se crea el mensaje que se le enviara al usuario, este tiene de manera resumida la informacion del objeto perdido
                    mensaje = "Su objeto [" + objetoperdido.getTipo() + "] con la siguiente informacion: \n" +
                            "Fecha: " + objetoperdido.getFechaPerdida() + "\n" +
                            "Ubicacion: " + objetoperdido.getUbicacion() + "\n" +
                            "ha sido ENCONTRADO";
                    Notificacion notificacion = new Notificacion(objetoperdido.getIdUsuario(), mensaje);

                    // Se crea la notificacion en la base de datos
                    resultadoNotificacion = repositoryNotificacion.crear(notificacion);

                    // Si se pudo enviar la notificacion
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

    /**
     * Metodo para actualizar la tabla de objetos perdidos
     * @param objetosPerdidos Lista de objetos perdidos
     */
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
