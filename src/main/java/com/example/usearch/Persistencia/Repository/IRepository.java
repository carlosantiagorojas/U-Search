package com.example.usearch.Persistencia.Repository;

import java.sql.Date;
import java.util.ArrayList;

public interface IRepository <T>{

    boolean crear(T entity);

    void actualizar(T entity);

    boolean actualizarPorId(int id);

    void eliminar(T entity);

    boolean eliminarPorId(int id);

    boolean consultarPorCredenciales(T entity);

    ArrayList<T> consultarListaFecha(Date fechaPerdida);

    ArrayList<T> consultarListaPorUbicacion(String ubicacion);

    ArrayList<T> consultarListaPorTipo(String tipo);

    ArrayList<T> consultarListaPorEntidad(T entity);

    ArrayList<T> consultarListaPorId(int id);
}
