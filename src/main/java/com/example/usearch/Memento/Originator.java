package com.example.usearch.Memento;

import com.example.usearch.Logica.Consulta;
import com.example.usearch.Logica.ObjetoPerdido;
import javafx.scene.control.TableView;

public class Originator {

    private TableView<ObjetoPerdido> tablaObjetosPerdidos;

    private Consulta consulta;

    public TableView<ObjetoPerdido> getTablaObjetosPerdidos() {
        return tablaObjetosPerdidos;
    }

    public void setTablaObjetosPerdidos(TableView<ObjetoPerdido> tablaObjetosPerdidos) {
        this.tablaObjetosPerdidos = tablaObjetosPerdidos;
    }

    public Consulta getConsulta() {
        return consulta;
    }

    public void setConsulta(Consulta consulta) {
        this.consulta = consulta;
    }

    public Memento guardar() {
        return new Memento(tablaObjetosPerdidos, consulta);
    }

    public void restaurar(Memento memento) {
        tablaObjetosPerdidos = memento.getTablaObjetosPerdidos();
    }
}
