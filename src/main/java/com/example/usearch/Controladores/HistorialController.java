package com.example.usearch.Controladores;

import com.example.usearch.Logica.CargadorEscenas;
import com.example.usearch.Logica.Consulta;
import com.example.usearch.Logica.ObjetoPerdido;
import com.example.usearch.Memento.Caretaker;
import com.example.usearch.Memento.Originator;
import com.example.usearch.Persistencia.ConexionBD;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import java.sql.Date;
import java.util.ArrayList;

public class HistorialController implements ControladorGeneral {

    private Stage stage;

    @Override
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    private static Caretaker caretaker = new Caretaker();

    private static Originator originator = new Originator();

    public static Caretaker getCaretaker() {
        return caretaker;
    }

    public static void setCaretaker(Caretaker caretaker) {
        HistorialController.caretaker = caretaker;
    }

    public static Originator getOriginator() {
        return originator;
    }

    public static void setOriginator(Originator originator) {
        HistorialController.originator = originator;
    }

    @FXML
    private ListView<String> ListaConsultas;

    @FXML
    private ImageView RegresarButton;

    @FXML
    private Button RestaurarButton;

    @FXML
    void AccionRegresar(MouseEvent event) {
        CargadorEscenas cargadorEscenas = new CargadorEscenas(stage);
        cargadorEscenas.CambiarEscenas("InterfazPersonal.fxml", "Menu Personal");
    }

    @FXML
    void AccionRestaurar(ActionEvent event) {


        int indiceSeleccionado = ListaConsultas.getSelectionModel().getSelectedIndex();
        System.out.println(indiceSeleccionado);

        if(indiceSeleccionado >= 0) {
            originator.restoreFromMemento(caretaker.getMementoIndice(indiceSeleccionado));
            originator.getConsulta().MostrarConsulta();

            // Se obtiene la consulta del originator y se muestra el resultado en la otra escena
            cambiarTabla(resturarConsulta(originator.getConsulta()));
        }
        else
            Alertas.mostrarError("No se ha seleccionado ninguna consulta");
    }

    public ArrayList<ObjetoPerdido> resturarConsulta(Consulta consulta)
    {
        ArrayList<ObjetoPerdido> objetosEncontrados = new ArrayList<>();

        if (consulta.getFecha() != null && consulta.getTipo() != null && consulta.getUbicacion() != null)
            objetosEncontrados = ActualizarTodosLLenos(consulta.getTipo(), consulta.getUbicacion(), consulta.getFecha());
        else if(consulta.getTipo() != null)
        {
            objetosEncontrados = ActualizarTipo(consulta.getTipo());
        }
        else if(consulta.getUbicacion() != null)
        {
            objetosEncontrados = ActualizarUbicacion(consulta.getUbicacion());
        }
        else if(consulta.getFecha() != null)
        {
            objetosEncontrados = ActualizarFecha(consulta.getFecha());
        }

        return objetosEncontrados;
    }


    public ArrayList<ObjetoPerdido> ActualizarTodosLLenos(String tipo, String ubicacion, Date fecha) {

        ConexionBD conexion = new ConexionBD();
        return conexion.cargarObjetosPerdidosPer(tipo, ubicacion, fecha);
    }

    public ArrayList<ObjetoPerdido> ActualizarTipo(String tipo) {

        ConexionBD conexion = new ConexionBD();

        return conexion.cargarObjetosPerdidosTipo(tipo);
    }

    public ArrayList<ObjetoPerdido> ActualizarUbicacion(String ubicacion) {

        ConexionBD conexion = new ConexionBD();

        return conexion.cargarObjetosPerdidosUbicacion(ubicacion);
    }

    public ArrayList<ObjetoPerdido> ActualizarFecha(Date fecha) {

        ConexionBD conexion = new ConexionBD();

        return conexion.cargarObjetosPerdidosFecha(fecha);
    }

    public void cambiarTabla(ArrayList<ObjetoPerdido> objetosPerdidos)
    {
        CargadorEscenas cargadorEscenas = new CargadorEscenas(stage);
        cargadorEscenas.CambiarEscenas("ResultadoConsulta.fxml", "Resultado de su Consulta");
        ResultadoConsultaController controllerlocal = (ResultadoConsultaController) cargadorEscenas.controladorGeneral;
        controllerlocal.actualizarTabla(objetosPerdidos);
    }

    public void mostrarHistorial()
    {
        ListaConsultas.getItems().addAll(getCaretaker().getMementosString());
    }

}
