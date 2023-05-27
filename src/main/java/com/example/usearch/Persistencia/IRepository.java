package com.example.usearch.Persistencia;

import java.util.ArrayList;

public interface IRepository <T>{

    boolean crear(T entity);

    void actualizar(T entity);

    void actualizarPorId(int id);

    void eliminar(T entity);

    boolean eliminarPorId(int id);

    boolean consultarPorCredenciales(T entity);

    ArrayList<T> consultarListaPorEntidad(T entity);

    ArrayList<T> consultarListaPorId(int id);
}
