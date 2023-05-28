package com.example.usearch.Strategy;

import com.example.usearch.Entidades.Consulta;
import com.example.usearch.Entidades.ObjetoPerdido;
import com.example.usearch.Persistencia.Repository.RepositoryObjetoPerdido;
import java.util.ArrayList;

public class ActualizarTodosLlenos implements IStrategy {

    public ActualizarTodosLlenos() {
    }

    RepositoryObjetoPerdido repositoryObjetoPerdido = RepositoryObjetoPerdido.getInstance();

    @Override
    public ArrayList<ObjetoPerdido> actualizar(Consulta consulta) {

        return repositoryObjetoPerdido.consultarListaPorEntidad(new ObjetoPerdido(consulta.getTipo(), consulta.getUbicacion(), consulta.getFecha()));
    }
}
