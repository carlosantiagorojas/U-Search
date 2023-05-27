package com.example.usearch.Persistencia;

import com.example.usearch.Logica.ObjetoPerdido;
import java.util.ArrayList;

public class RepositoryObjetoPerdido implements IRepository<ObjetoPerdido>{

    @Override
    public boolean crear(ObjetoPerdido entity) {
        return false;
    }

    @Override
    public void actualizar(ObjetoPerdido entity) {

    }

    @Override
    public void actualizarPorId(int id) {

    }

    @Override
    public void eliminar(ObjetoPerdido entity) {

    }

    @Override
    public void eliminarPorId(int id) {

    }

    @Override
    public ObjetoPerdido consultarPorCredenciales(String correo, String contrasena) {
        return null;
    }

    @Override
    public ArrayList<ObjetoPerdido> consultarTodos() {
        return null;
    }

    @Override
    public ArrayList<ObjetoPerdido> consultarListaPorParametro(ObjetoPerdido parametro) {
        return null;
    }
}
