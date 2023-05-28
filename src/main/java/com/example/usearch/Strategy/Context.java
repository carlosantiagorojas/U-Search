package com.example.usearch.Strategy;

import com.example.usearch.Entidades.ObjetoPerdido;
import com.example.usearch.Entidades.Consulta;
import java.util.ArrayList;

public class Context {

    private IStrategy strategy;

    public Context(IStrategy strategy) {
        this.strategy = strategy;
    }

    public ArrayList<ObjetoPerdido> actualizar(Consulta consulta) {
        return strategy.actualizar(consulta);
    }
}
