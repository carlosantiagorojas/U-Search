package com.example.usearch.Strategy;

import com.example.usearch.Entidades.Consulta;
import com.example.usearch.Entidades.ObjetoPerdido;
import com.example.usearch.Persistencia.Repository.RepositoryObjetoPerdido;
import java.util.ArrayList;

/**
 * Clase que define la estrategia de actualizar por tipo
 */
public class ActualizarTipo implements IStrategy {

    RepositoryObjetoPerdido repositoryObjetoPerdido = RepositoryObjetoPerdido.getInstance();

    @Override
    public ArrayList<ObjetoPerdido> actualizar(Consulta consulta) {
        return repositoryObjetoPerdido.consultarListaPorTipo(consulta.getTipo());
    }
}
