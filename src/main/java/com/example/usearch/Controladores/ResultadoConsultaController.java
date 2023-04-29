package com.example.usearch.Controladores;

import com.example.usearch.Logica.CargadorEscenas;
import com.example.usearch.Logica.ObjetoPerdido;
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
    private TableColumn<ObjetoPerdido, String> id;

    @FXML
    private TableView<ObjetoPerdido> tablaObjetos;

    @FXML
    private TableColumn<ObjetoPerdido, String> tipo;

    @FXML
    public void Actualizar(String tipo, String ubicacion, String fecha) {
        ConexionBD conexion = new ConexionBD();
        ArrayList<ObjetoPerdido> objetosPerdidos=conexion.cargarObjetosPerdidosPer(tipo, ubicacion, fecha);
        this.listaObjetos = FXCollections.observableArrayList(objetosPerdidos);
        tablaObjetos.setItems(listaObjetos);
        this.fecha.setCellValueFactory(new PropertyValueFactory<ObjetoPerdido, Date>("fechaPerdida"));
        this.ubicacion.setCellValueFactory(new PropertyValueFactory<ObjetoPerdido, String>("ubicacion"));
        this.tipo.setCellValueFactory(new PropertyValueFactory<ObjetoPerdido, String>("tipo"));
        this.caracteristicas.setCellValueFactory(new PropertyValueFactory<ObjetoPerdido, String>("caracteristicas"));
        this.estado.setCellValueFactory(new PropertyValueFactory<ObjetoPerdido, String>("estado"));

    }

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

    }
}
