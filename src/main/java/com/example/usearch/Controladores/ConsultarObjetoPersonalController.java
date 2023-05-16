package com.example.usearch.Controladores;

import com.example.usearch.Logica.CargadorEscenas;
import com.example.usearch.Logica.Consulta;
import com.example.usearch.Logica.ObjetoPerdido;
import com.example.usearch.Persistencia.ConexionBD;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import java.sql.Date;
import java.util.ArrayList;

public class ConsultarObjetoPersonalController implements ControladorGeneral{

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

    @FXML
    void AccionConsultar(ActionEvent event) {

        ArrayList<ObjetoPerdido> resultadoConsulta;
        boolean camposVacios = camposVacios();

        if(!camposVacios){
            if(cantidadCamposLLenos() == 1) {
                if(!FechaPerdida.getText().isEmpty() && !fechaValida())
                {
                    Alertas.mostrarError("Fecha no valida, digite la fecha en formato aaaa-mm-dd");
                }
                else
                {
                    resultadoConsulta = tipoConsulta();
                    cambiarTabla(resultadoConsulta);
                }
            }
            else if(camposLlenos() && fechaValida())
            {
                if(!FechaPerdida.getText().isEmpty() && !fechaValida())
                {
                    Alertas.mostrarError("Fecha no valida, digite la fecha en formato aaaa-mm-dd");
                }
                else
                {
                    Date fecha = Date.valueOf(FechaPerdida.getText());
                    resultadoConsulta = ActualizarTodosLLenos(TipoObjeto.getText(), UbicacionPerdida.getText(), fecha);
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

    public boolean camposVacios(){
        return TipoObjeto.getText().isEmpty() && UbicacionPerdida.getText().isEmpty() && FechaPerdida.getText().isEmpty();
    }

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

    public boolean camposLlenos(){
        return !TipoObjeto.getText().isEmpty() && !UbicacionPerdida.getText().isEmpty() && !FechaPerdida.getText().isEmpty();
    }

    public ArrayList<ObjetoPerdido> tipoConsulta()
    {
        ArrayList<ObjetoPerdido> objetosEncontrados = new ArrayList<>();

        if(!TipoObjeto.getText().isEmpty())
        {
            objetosEncontrados = ActualizarTipo(TipoObjeto.getText());
        }
        else if(!UbicacionPerdida.getText().isEmpty())
        {
            objetosEncontrados = ActualizarUbicacion(UbicacionPerdida.getText());
        }
        else if(!FechaPerdida.getText().isEmpty() && fechaValida())
        {
            Date fecha = Date.valueOf(FechaPerdida.getText());
            objetosEncontrados = ActualizarFecha(fecha);
        }

        return objetosEncontrados;
    }

    public void cambiarTabla(ArrayList<ObjetoPerdido> objetosPerdidos)
    {
        if(validarConsulta(objetosPerdidos))
        {
            CargadorEscenas cargadorEscenas = new CargadorEscenas(stage);
            cargadorEscenas.CambiarEscenas("ResultadoConsulta.fxml", "Resultado de su Consulta");

            ResultadoConsultaController controllerlocal = (ResultadoConsultaController) cargadorEscenas.controladorGeneral;
            cargarDatosConsulta();
            controllerlocal.actualizarTabla(objetosPerdidos);
        }
        else
            Alertas.mostrarError("No se encontraron objetos con los parametros ingresados");
    }

    public boolean fechaValida()
    {
        try {
            Date fecha = Date.valueOf(FechaPerdida.getText());
            return true;
        } catch (Exception e) {
            return false;
        }
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

    public boolean validarConsulta(ArrayList<ObjetoPerdido> objetosPerdidos)
    {
        return objetosPerdidos.size() >= 1;
    }

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

    public void cargarDatosConsulta()
    {
        HistorialController.getOriginator().setConsulta(crearConsulta());
        HistorialController.getCaretaker().addMemento(HistorialController.getOriginator().createMemento());
    }

}
