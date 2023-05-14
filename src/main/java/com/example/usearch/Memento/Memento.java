package com.example.usearch.Memento;

import com.example.usearch.Logica.ObjetoPerdido;
import javafx.scene.control.TableView;

public class Memento {

    private final TableView<ObjetoPerdido> tablaObjetosPerdidos;

    public Memento(TableView<ObjetoPerdido> tablaObjetosPerdidos) {
        this.tablaObjetosPerdidos = tablaObjetosPerdidos;
    }

    public TableView<ObjetoPerdido> getTablaObjetosPerdidos() {
        return tablaObjetosPerdidos;
    }
}
