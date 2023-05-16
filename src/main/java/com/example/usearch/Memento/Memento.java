package com.example.usearch.Memento;

import com.example.usearch.Logica.Consulta;

public class Memento {

    private final Consulta consulta;

    public Memento(Consulta consulta) {
        this.consulta = consulta;
    }

    public Consulta getConsulta() {
        return consulta;
    }
}
