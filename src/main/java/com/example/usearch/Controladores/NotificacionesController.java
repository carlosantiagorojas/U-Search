package com.example.usearch.Controladores;

import com.example.usearch.Logica.SesionUsuario;
import com.example.usearch.Persistencia.ConexionBD;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class NotificacionesController {

    @FXML
    private ImageView RegresarButton;

    @FXML
    void AccionRegresar(MouseEvent event) {

    }

    public void mostrarNotificaciones(){
        ConexionBD conexion = new ConexionBD();
        SesionUsuario.setObjetosPerdidos(conexion.cargarObjetosPerdidos(SesionUsuario.getId()));

        //SesionUsuario.mostrarDatosUsuario();
        //SesionUsuario.mostrarObjetos();

        this.listaObjetos = FXCollections.observableArrayList(SesionUsuario.getObjetosPerdidos());

        tablaObjetos.setItems(listaObjetos);
        this.fecha.setCellValueFactory(new PropertyValueFactory<>("fechaPerdida"));
        this.ubicacion.setCellValueFactory(new PropertyValueFactory<>("ubicacion"));
        this.tipo.setCellValueFactory(new PropertyValueFactory<>("tipo"));
        this.caracteristicas.setCellValueFactory(new PropertyValueFactory<>("caracteristicas"));
        this.estado.setCellValueFactory(new PropertyValueFactory<>("estado"));
    }
}
