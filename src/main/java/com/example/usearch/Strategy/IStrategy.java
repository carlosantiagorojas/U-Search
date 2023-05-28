package com.example.usearch.Strategy;

import com.example.usearch.Entidades.Consulta;
import com.example.usearch.Entidades.ObjetoPerdido;
import java.util.ArrayList;

/**
 * Interfaz que define el comportamiento de las estrategias
 */
public interface IStrategy {
    ArrayList<ObjetoPerdido> actualizar(Consulta consulta);
}
