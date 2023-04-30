package com.example.usearch.Controladores;

import com.example.usearch.Logica.CargadorEscenas;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import java.sql.Date;

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
    private ImageView RegresarButton;

    @FXML
    private TextField TipoObjeto;

    @FXML
    private TextField UbicacionPerdida;

    @FXML
    void AccionConsultar(ActionEvent event) {

        boolean camposVacios = camposVacios();

        if(!camposVacios){
            if(cantidadCamposLLenos() !=1) {
                tipoConsulta();
            }else {
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
        return TipoObjeto.getText().isEmpty() || UbicacionPerdida.getText().isEmpty() || FechaPerdida.getText().isEmpty();
    }

    public int cantidadCamposLLenos(){
        int camposLlenos = 0;
        if(!TipoObjeto.getText().isEmpty() || !UbicacionPerdida.getText().isEmpty() || !FechaPerdida.getText().isEmpty()){
            camposLlenos += 1;
        }
        return camposLlenos;
    }

    public boolean camposLlenos(){
        return !TipoObjeto.getText().isEmpty() && !UbicacionPerdida.getText().isEmpty() && !FechaPerdida.getText().isEmpty();
    }

    public void tipoConsulta()
    {
        Date fecha = fechaValida();


        if(camposLlenos())
        {
            boolean consultaExitosa = false;
            CargadorEscenas cargadorEscenas = new CargadorEscenas(stage);
            ResultadoConsultaController controllerlocal = (ResultadoConsultaController) cargadorEscenas.controladorGeneral;
            consultaExitosa = controllerlocal.ActualizarTodosLLenos(TipoObjeto.getText(), UbicacionPerdida.getText(), fecha);
            resultadoConsulta(consultaExitosa, cargadorEscenas);
        }
        else if(!TipoObjeto.getText().isEmpty())
        {
            boolean consultaExitosa = false;
            CargadorEscenas cargadorEscenas = new CargadorEscenas(stage);
            ResultadoConsultaController controllerlocal = (ResultadoConsultaController) cargadorEscenas.controladorGeneral;
            resultadoConsulta(consultaExitosa, cargadorEscenas);
        }
        else if(!UbicacionPerdida.getText().isEmpty())
        {
            boolean consultaExitosa = false;
            CargadorEscenas cargadorEscenas = new CargadorEscenas(stage);
            ResultadoConsultaController controllerlocal = (ResultadoConsultaController) cargadorEscenas.controladorGeneral;
            resultadoConsulta(consultaExitosa, cargadorEscenas);
        }
        else if(!FechaPerdida.getText().isEmpty())
        {
            boolean consultaExitosa = false;
            CargadorEscenas cargadorEscenas = new CargadorEscenas(stage);
            ResultadoConsultaController controllerlocal = (ResultadoConsultaController) cargadorEscenas.controladorGeneral;
            resultadoConsulta(consultaExitosa, cargadorEscenas);
        }
        else
            Alertas.mostrarError("Error al consultar objeto");
    }

    public Date fechaValida()
    {
        Date fecha = null;

        try {
            fecha = Date.valueOf(FechaPerdida.getText());
        } catch (Exception e) {
            Alertas.mostrarError("Fecha no valida, digite la fecha en formato aaaa-mm-dd");
        }

        return fecha;
    }

    public void resultadoConsulta(boolean consultaExitosa, CargadorEscenas cargadorEscenas)
    {
        if (consultaExitosa)
            cargadorEscenas.CambiarEscenas("ResultadoConsulta.fxml", "Resultado de su Consulta");
        else
            Alertas.mostrarError("Error al consultar objeto");
    }
}
