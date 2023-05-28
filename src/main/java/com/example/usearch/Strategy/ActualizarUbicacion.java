package com.example.usearch.Strategy;

import com.example.usearch.Entidades.Consulta;
import com.example.usearch.Entidades.ObjetoPerdido;
import com.example.usearch.Persistencia.Repository.RepositoryObjetoPerdido;
import java.util.ArrayList;

/**
 * Clase que define la estrategia de actualizar por ubicación
 */
public class ActualizarUbicacion implements IStrategy{

    RepositoryObjetoPerdido repositoryObjetoPerdido = RepositoryObjetoPerdido.getInstance();

    @Override
    public ArrayList<ObjetoPerdido> actualizar(Consulta consulta) {
        return repositoryObjetoPerdido.consultarListaPorUbicacion(consulta.getUbicacion());
    }
}
