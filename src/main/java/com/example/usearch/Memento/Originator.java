package com.example.usearch.Memento;

import com.example.usearch.Logica.ObjetoPerdido;
import javafx.scene.control.TableView;

public class Originator {

    private TableView<ObjetoPerdido> tablaObjetosPerdidos;

    public TableView<ObjetoPerdido> getTablaObjetosPerdidos() {
        return tablaObjetosPerdidos;
    }

    public void setTablaObjetosPerdidos(TableView<ObjetoPerdido> tablaObjetosPerdidos) {
        this.tablaObjetosPerdidos = tablaObjetosPerdidos;
    }

    public Memento guardar() {
        return new Memento(tablaObjetosPerdidos);
    }

    public void restaurar(Memento memento) {
        tablaObjetosPerdidos = memento.getTablaObjetosPerdidos();
    }
}
