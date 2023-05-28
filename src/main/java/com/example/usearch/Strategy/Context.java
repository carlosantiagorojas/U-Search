package com.example.usearch.Strategy;

import com.example.usearch.Entidades.ObjetoPerdido;
import com.example.usearch.Entidades.Consulta;
import java.util.ArrayList;

/**
 * Clase que define el contexto de las estrategias
 */
public class Context {

    private IStrategy strategy;

    public Context(IStrategy strategy) {
        this.strategy = strategy;
    }

    /**
     * Método que ejecuta la estrategia
     * @param consulta Consulta que se desea realizar
     * @return Lista de objetos perdidos que cumplen con la consulta
     */
    public ArrayList<ObjetoPerdido> actualizar(Consulta consulta) {
        return strategy.actualizar(consulta);
    }
}
