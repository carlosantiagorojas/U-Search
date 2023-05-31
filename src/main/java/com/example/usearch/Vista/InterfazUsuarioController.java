package com.example.usearch.Vista;

import com.example.usearch.Entidades.ObjetoPerdido;
import com.example.usearch.Sesion.SesionUsuario;
import com.example.usearch.Persistencia.Repository.RepositoryObjetoPerdido;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import java.sql.Date;

/**
 * Clase controladora de la interfaz de usuario
 */
public class InterfazUsuarioController implements ControladorGeneral {

    RepositoryObjetoPerdido repositoryObjetoPerdido = RepositoryObjetoPerdido.getInstance();

    private Stage stage;

    ObservableList<ObjetoPerdido> listaObjetos;

    @Override
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    private ImageView NotificacionesButtton;

    @FXML
    private ImageView RegistrarObjetoButton;

    @FXML
    private TableColumn <ObjetoPerdido, String> caracteristicas;

    @FXML
    private TableColumn <ObjetoPerdido, String> estado;

    @FXML
    private TableColumn <ObjetoPerdido, Date> fecha;

    @FXML
    private TableColumn <ObjetoPerdido, String> ubicacion;

    @FXML
    private TableView <ObjetoPerdido> tablaObjetos;

    @FXML
    private TableColumn <ObjetoPerdido, String>tipo;

    @FXML
    void AccionNotificaciones(MouseEvent event) {
        CargadorEscenas cargadorEscenas = new CargadorEscenas(stage);
        cargadorEscenas.CambiarEscenas("Notificaciones.fxml", "Notificaciones");
    }

    @FXML
    void AccionRegistrarObjeto(MouseEvent event) {
        CargadorEscenas cargadorEscenas = new CargadorEscenas(stage);
        cargadorEscenas.CambiarEscenas("UsuarioRegistrarObjeto.fxml", "Registrar Objeto");
    }

    /**
     * Metodo para mostrar los objetos perdidos del usuario
     */
    public void mostrarObjetosPerdidos(){

        SesionUsuario.setObjetosPerdidos(repositoryObjetoPerdido.consultarListaPorId(SesionUsuario.getId()));
        //SesionUsuario.mostrarDatosUsuario();
        //SesionUsuario.mostrarObjetos();

        if(SesionUsuario.getObjetosPerdidos().isEmpty()){
            tablaObjetos.setPlaceholder(new javafx.scene.control.Label("No hay objetos registrados"));
        }
        else{
            //Colocar los objetos en la tabla
            this.listaObjetos = FXCollections.observableArrayList(SesionUsuario.getObjetosPerdidos());

            tablaObjetos.setItems(listaObjetos);
            this.fecha.setCellValueFactory(new PropertyValueFactory<>("fechaPerdida"));
            this.ubicacion.setCellValueFactory(new PropertyValueFactory<>("ubicacion"));
            this.tipo.setCellValueFactory(new PropertyValueFactory<>("tipo"));
            this.caracteristicas.setCellValueFactory(new PropertyValueFactory<>("caracteristicas"));
            this.estado.setCellValueFactory(new PropertyValueFactory<>("estado"));
        }
    }
}
