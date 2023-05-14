package com.example.usearch.Controladores;

import com.example.usearch.Logica.CargadorEscenas;
import com.example.usearch.Memento.Caretaker;
import com.example.usearch.Memento.Originator;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

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
    private ListView<?> ListaConsultas;

    @FXML
    private ImageView RegresarButton;

    @FXML
    private Button RestaurarButton;

    @FXML
    void AccionRegresar(MouseEvent event) {
        CargadorEscenas cargadorEscenas = new CargadorEscenas(stage);
        cargadorEscenas.CambiarEscenas("InterfazPersonal.fxml", "Menu Personal");
    }

    public void mostrarHistorial()
    {
        ListaConsultas.getItems().addAll();
    }
}
