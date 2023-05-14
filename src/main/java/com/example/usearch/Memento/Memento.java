package com.example.usearch.Memento;

import com.example.usearch.Logica.Consulta;
import com.example.usearch.Logica.ObjetoPerdido;
import javafx.scene.control.TableView;

public class Memento {

    private final TableView<ObjetoPerdido> tablaObjetosPerdidos;

    private final Consulta consulta;

    public Memento(TableView<ObjetoPerdido> tablaObjetosPerdidos, Consulta consulta) {
        this.tablaObjetosPerdidos = tablaObjetosPerdidos;
        this.consulta = consulta;
    }

    public TableView<ObjetoPerdido> getTablaObjetosPerdidos() {
        return tablaObjetosPerdidos;
    }
}
