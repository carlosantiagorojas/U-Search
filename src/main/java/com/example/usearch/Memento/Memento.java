package com.example.usearch.Memento;

import com.example.usearch.Entidades.Consulta;

/**
 * Clase que implementa el memento a guardar
 */
public class Memento {

    private final Consulta consulta;

    public Memento(Consulta consulta) {
        this.consulta = consulta;
    }

    public Consulta getConsulta() {
        return consulta;
    }
}
