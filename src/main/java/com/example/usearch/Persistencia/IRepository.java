package com.example.usearch.Persistencia;

import java.util.ArrayList;

public interface IRepository <T>{

    boolean crear(T entity);

    void actualizar(T entity);

    void actualizarPorId(int id);

    void eliminar(T entity);

    void eliminarPorId(int id);

    T consultarPorCredenciales(String correo, String contrasena);

    ArrayList<T> consultarTodos();

    ArrayList<T> consultarListaPorParametro(T parametro);
}
