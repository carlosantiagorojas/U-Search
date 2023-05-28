package com.example.usearch.Vista;

import com.example.usearch.AbstractFactory.ControladorGeneral;
import com.example.usearch.AbstractFactory.CargadorEscenas;
import com.example.usearch.Entidades.Consulta;
import com.example.usearch.Entidades.ObjetoPerdido;
import com.example.usearch.Persistencia.Repository.RepositoryObjetoPerdido;
import com.example.usearch.Utilidades.Alertas;
import com.example.usearch.Strategy.Context;
import com.example.usearch.Strategy.ActualizarFecha;
import com.example.usearch.Strategy.ActualizarTipo;
import com.example.usearch.Strategy.ActualizarUbicacion;
import com.example.usearch.Strategy.ActualizarTodosLlenos;
import com.example.usearch.Utilidades.Fecha;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import java.sql.Date;
import java.util.ArrayList;

/**
 * Clase controlador de la interfaz de consultar objetos perdidos
 */
public class ConsultarObjetoPersonalController implements ControladorGeneral {

    RepositoryObjetoPerdido repositoryObjetoPerdido = RepositoryObjetoPerdido.getInstance();

    private Stage stage;
    @Override
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    private Button ConsultarButton;

    @FXML
    private TextField FechaPerdida;

    @FXML
    private TextField TipoObjeto;

    @FXML
    private TextField UbicacionPerdida;

    public Date getFechaPerdida() {
        return Date.valueOf(FechaPerdida.getText());
    }

    public String getTipoObjeto() {
        return TipoObjeto.getText();
    }

    public String getUbicacionPerdida() {
        return UbicacionPerdida.getText();
    }

    @FXML
    private ImageView RegresarButton;

    /**
     * Consultar los objetos perdidos en base a los parametros ingresados
     * @param event evento de la accion
     */
    @FXML
    void AccionConsultar(ActionEvent event) {

        ArrayList<ObjetoPerdido> resultadoConsulta;
        boolean camposVacios = camposVacios();

        if(!camposVacios){
            if(cantidadCamposLLenos() == 1) {
                if(!FechaPerdida.getText().isEmpty() && !Fecha.fechaValidaCampo(FechaPerdida.getText()))
                {
                    Alertas.mostrarError("Fecha no valida, digite la fecha en formato aaaa-mm-dd");
                }
                else
                {
                    resultadoConsulta = tipoConsulta();
                    cambiarTabla(resultadoConsulta);
                }
            }
            else if(camposLlenos() && Fecha.fechaValidaCampo(FechaPerdida.getText()))
            {
                if(!FechaPerdida.getText().isEmpty() && !Fecha.fechaValidaCampo(FechaPerdida.getText()))
                {
                    Alertas.mostrarError("Fecha no valida, digite la fecha en formato aaaa-mm-dd");
                }
                else
                {
                    Date fecha = Date.valueOf(FechaPerdida.getText());

                    Consulta consulta = new Consulta(fecha, TipoObjeto.getText(), UbicacionPerdida.getText());
                    //Utilizar la estrategia de todos los campos llenos
                    Context contextLlenos = new Context(new ActualizarTodosLlenos());
                    resultadoConsulta = contextLlenos.actualizar(consulta);
                    cambiarTabla(resultadoConsulta);
                }
            }
            else {
                Alertas.mostrarError("Por favor llenar un solo campo");
            }
        }
        else {
            Alertas.mostrarError("Campos vacios, por favor llenar uno o todos los campos");
        }
    }

    @FXML
    void AccionRegresar(MouseEvent event) {
        CargadorEscenas cargadorEscenas = new CargadorEscenas(stage);
        cargadorEscenas.CambiarEscenas("InterfazPersonal.fxml", "Menu Personal");
    }

    /**
     * comprobar si los campos estan vacios
     * @return true si los campos estan vacios, false si no
     */
    public boolean camposVacios(){
        return TipoObjeto.getText().isEmpty() && UbicacionPerdida.getText().isEmpty() && FechaPerdida.getText().isEmpty();
    }

    /**
     * contar la cantidad de campos llenos
     * @return cantidad de campos
     */
    public int cantidadCamposLLenos(){
        int camposLlenos = 0;

        if(!TipoObjeto.getText().isEmpty())
            camposLlenos += 1;
        if(!UbicacionPerdida.getText().isEmpty())
            camposLlenos += 1;
        if(!FechaPerdida.getText().isEmpty())
            camposLlenos += 1;

        return camposLlenos;
    }

    /**
     * comprobar si todos los campos estan llenos
     * @return true estan llenos, false si no
     */
    public boolean camposLlenos(){
        return !TipoObjeto.getText().isEmpty() && !UbicacionPerdida.getText().isEmpty() && !FechaPerdida.getText().isEmpty();
    }

    /**
     * actualizar dependiendo de la consulta
     * se declara un contexto y se le pasa la estrategia a utilizar
     * @return lista de objetos actualizada
     */
    public ArrayList<ObjetoPerdido> tipoConsulta()
    {
        ArrayList<ObjetoPerdido> objetosEncontrados = new ArrayList<>();

        // Se implementan las estrategias de acuerdo a cada campo lleno
        if(!TipoObjeto.getText().isEmpty())
        {
            Consulta consulta = new Consulta();
            consulta.setTipo(TipoObjeto.getText());

            Context contextTipo = new Context(new ActualizarTipo());
            objetosEncontrados = contextTipo.actualizar(consulta);
        }
        else if(!UbicacionPerdida.getText().isEmpty())
        {
            Consulta consulta = new Consulta();
            consulta.setUbicacion(UbicacionPerdida.getText());

            Context contextUbicacion = new Context(new ActualizarUbicacion());
            objetosEncontrados = contextUbicacion.actualizar(consulta);
        }
        else if(!FechaPerdida.getText().isEmpty() && Fecha.fechaValidaCampo(FechaPerdida.getText()))
        {
            Date fecha = Date.valueOf(FechaPerdida.getText());

            Consulta consulta = new Consulta();
            consulta.setFecha(fecha);

            Context contextFecha = new Context(new ActualizarFecha());
            objetosEncontrados = contextFecha.actualizar(consulta);
        }

        return objetosEncontrados;
    }

    /**
     * cambiar de escena y valores de la tabla
     * @param objetosPerdidos lista de objetos
     */
    public void cambiarTabla(ArrayList<ObjetoPerdido> objetosPerdidos)
    {
        if(validarConsulta(objetosPerdidos))
        {
            CargadorEscenas cargadorEscenas = new CargadorEscenas(stage);
            cargadorEscenas.CambiarEscenas("ResultadoConsulta.fxml", "Resultado de su Consulta");

            ResultadoConsultaController controllerlocal = (ResultadoConsultaController) cargadorEscenas.controladorGeneral;
            // Cargar datos de la consulta con el memento para poder guardar el historial
            cargarDatosConsulta();
            controllerlocal.setEscenaAnterior("ConsultarObjetoPersonal.fxml");
            controllerlocal.setTituloEscenaAnterior("Consultar Objetos");
            controllerlocal.actualizarTabla(objetosPerdidos);
        }
        else
            Alertas.mostrarError("No se encontraron objetos con los parametros ingresados");
    }

    /**
     * validar si la consulta es valida
     * @param objetosPerdidos lista de objetos
     * @return true si es valida, false si no
     */
    public boolean validarConsulta(ArrayList<ObjetoPerdido> objetosPerdidos)
    {
        return objetosPerdidos.size() >= 1;
    }

    /**
     * crear consulta
     */
    public Consulta crearConsulta()
    {
        Consulta consulta = new Consulta();

        if(camposLlenos())
        {
            consulta.setFecha(getFechaPerdida());
            consulta.setUbicacion(getUbicacionPerdida());
            consulta.setTipo(getTipoObjeto());
        }
        else if(!TipoObjeto.getText().isEmpty())
        {
            consulta.setTipo(getTipoObjeto());
        }
        else if(!UbicacionPerdida.getText().isEmpty())
        {
            consulta.setUbicacion(getUbicacionPerdida());
        }
        else if(!FechaPerdida.getText().isEmpty())
        {
            consulta.setFecha(getFechaPerdida());
        }

        return consulta;
    }

    /**
     * cargar datos de la consulta al memento con el originator
     */
    public void cargarDatosConsulta()
    {
        HistorialController.getOriginator().setConsulta(crearConsulta());
        HistorialController.getCaretaker().addMemento(HistorialController.getOriginator().createMemento());
    }

}
