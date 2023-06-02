package com.example.usearch.logica;

import com.example.usearch.Entidades.ObjetoPerdido;
import java.util.ArrayList;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class ConsultaObjetos {

    private Predicate<ObjetoPerdido> obtenerFiltro(String tipo, String ubicacion, String fecha){

        Predicate<ObjetoPerdido> filtro = t -> true;

        if(tipo != null && !tipo.isEmpty())
        {
            filtro.and(t -> t.getTipo().equalsIgnoreCase(tipo));
        }
        return filtro;

    }

    public ArrayList<ObjetoPerdido> consultarObjetos(String tipo, String ubicacion, String fecha)
    {
        ArrayList<ObjetoPerdido> objetos = new ArrayList<>();

        Predicate<ObjetoPerdido> filtro = obtenerFiltro(tipo, ubicacion, fecha);
        objetos.stream().filter(filtro).collect(Collectors.toList());
        return objetos;
    }
}
