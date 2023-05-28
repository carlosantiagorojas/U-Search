package com.example.usearch.Controladores;

import com.example.usearch.AbstractFactory.ControladorGeneral;
import com.example.usearch.AbstractFactory.CargadorEscenas;
import com.example.usearch.Entidades.Consulta;
import com.example.usearch.Entidades.ObjetoPerdido;
import com.example.usearch.Memento.Caretaker;
import com.example.usearch.Memento.Originator;
import com.example.usearch.Persistencia.Repository.RepositoryObjetoPerdido;
import com.example.usearch.Utilidades.Alertas;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import java.sql.Date;
import java.util.ArrayList;

/**
 * Clase controlador del historial de consultas
 */
public class HistorialController implements ControladorGeneral {

    RepositoryObjetoPerdido repositoryObjetoPerdido = RepositoryObjetoPerdido.getInstance();

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

    /**
     * Metodo para resturar una consulta
     * @param event Evento de la accion
     */
    @FXML
    void AccionRestaurar(ActionEvent event) {

        int indiceSeleccionado = ListaConsultas.getSelectionModel().getSelectedIndex();
        System.out.println(indiceSeleccionado);

        if(indiceSeleccionado >= 0) {
            originator.restoreFromMemento(caretaker.getMementoIndice(indiceSeleccionado));
            //originator.getConsulta().MostrarConsulta();

            // Se obtiene la consulta del originator y se muestra el resultado en la otra escena
            cambiarTabla(resturarConsulta(originator.getConsulta()));
        }
        else
            Alertas.mostrarError("No se ha seleccionado ninguna consulta");
    }

    /**
     * actualizar dependiendo de la consulta
     * @return lista de objetos actualizada
     */
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

    /**
     * Cargar los objetos si todos los campos estan llenos
     * @param tipo tipo de objeto
     * @param ubicacion ubicacion del objeto
     * @param fecha fecha de perdida del objeto
     * @return lista de objetos
     */
    public ArrayList<ObjetoPerdido> ActualizarTodosLLenos(String tipo, String ubicacion, Date fecha) {

        ObjetoPerdido objetoPerdido = new ObjetoPerdido(tipo, ubicacion, fecha);
        return repositoryObjetoPerdido.consultarListaPorEntidad(objetoPerdido);
    }

    /**
     * Cargar los objetos si el tipo esta lleno
     * @param tipo tipo de objeto
     * @return lista de objetos
     */
    public ArrayList<ObjetoPerdido> ActualizarTipo(String tipo) {

        return repositoryObjetoPerdido.consultarListaPorTipo(tipo);
    }

    /**
     * Cargar los objetos si la ubicacion esta llena
     * @param ubicacion ubicacion del objeto
     * @return lista de objetos
     */
    public ArrayList<ObjetoPerdido> ActualizarUbicacion(String ubicacion) {

        return repositoryObjetoPerdido.consultarListaPorUbicacion(ubicacion);
    }

    /**
     * Cargar los objetos si la fecha esta llena
     * @param fecha fecha de perdida del objeto
     * @return lista de objetos
     */
    public ArrayList<ObjetoPerdido> ActualizarFecha(Date fecha) {

        return repositoryObjetoPerdido.consultarListaFecha(fecha);
    }

    /**
     * cambiar de escena y valores de la tabla
     * @param objetosPerdidos lista de objetos
     */
    public void cambiarTabla(ArrayList<ObjetoPerdido> objetosPerdidos)
    {
        CargadorEscenas cargadorEscenas = new CargadorEscenas(stage);
        cargadorEscenas.CambiarEscenas("ResultadoConsulta.fxml", "Resultado de su Consulta");
        ResultadoConsultaController controllerlocal = (ResultadoConsultaController) cargadorEscenas.controladorGeneral;
        controllerlocal.setEscenaAnterior("HistorialConsultas.fxml");
        controllerlocal.setTituloEscenaAnterior("Historial consulta");
        controllerlocal.actualizarTabla(objetosPerdidos);
    }

    /**
     * Metodo para mostrar el historial de consultas
     */
    public void mostrarHistorial()
    {
        ListaConsultas.getItems().addAll(getCaretaker().getMementosString());
    }

}
