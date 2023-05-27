package com.example.usearch.Persistencia;

import com.example.usearch.Logica.Notificacion;
import com.example.usearch.Logica.ObjetoPerdido;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class RepositoryObjetoPerdido implements IRepository<ObjetoPerdido>{

    @Override
    public boolean crear(ObjetoPerdido entity) {
        boolean registroObjeto = false;

        String query = "INSERT INTO objetosperdidos (fechaPerdida, ubicacion, tipo, caracteristicas, estado, usuarios_idUsuarios) VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement statement = ConexionBD.conexion.prepareStatement(query)) {
            statement.setDate(1, entity.getFechaPerdida());
            statement.setString(2, entity.getUbicacion());
            statement.setString(3, entity.getTipo());
            statement.setString(4, entity.getCaracteristicas());
            statement.setString(5, entity.getEstado());
            statement.setInt(6, entity.getIdUsuario());
            int filasAfectadas = statement.executeUpdate();
            registroObjeto = (filasAfectadas > 0);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return registroObjeto;
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
    public boolean eliminarPorId(int id) {
        return false;
    }

    @Override
    public boolean consultarPorCredenciales(ObjetoPerdido entity) {
        return false;
    }

    @Override
    public ArrayList<ObjetoPerdido> consultarListaPorEntidad(ObjetoPerdido entity) {

        ArrayList<ObjetoPerdido> objetosPerdidosAr = new ArrayList<>();
        String query = "SELECT * FROM objetosperdidos WHERE tipo = ? AND ubicacion = ? AND fechaPerdida=?";

        try (PreparedStatement statement = ConexionBD.conexion.prepareStatement(query);){
            statement.setString(1, entity.getTipo());
            statement.setString(2, entity.getUbicacion());
            statement.setDate(3, entity.getFechaPerdida());
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                ObjetoPerdido objeto = new ObjetoPerdido();
                objeto.setId(rs.getInt("idObjetosPerdidos"));
                objeto.setFechaPerdida(rs.getDate("fechaPerdida"));
                objeto.setUbicacion(rs.getString("ubicacion"));
                objeto.setTipo(rs.getString("tipo"));
                objeto.setCaracteristicas(rs.getString("caracteristicas"));
                objeto.setEstado(rs.getString("estado"));
                objeto.setIdUsuario(rs.getInt("usuarios_idUsuarios"));

                objetosPerdidosAr.add(objeto);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return objetosPerdidosAr;
    }

    @Override
    public ArrayList<ObjetoPerdido> consultarListaPorId(int id) {

        ArrayList<ObjetoPerdido> objetosPerdidosAr = new ArrayList<>();
        String query = "SELECT * FROM objetosperdidos WHERE usuarios_idUsuarios = ?";

        try (PreparedStatement statement = ConexionBD.conexion.prepareStatement(query);){
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                ObjetoPerdido objeto = new ObjetoPerdido();
                objeto.setId(rs.getInt("idObjetosPerdidos"));
                objeto.setFechaPerdida(rs.getDate("fechaPerdida"));
                objeto.setUbicacion(rs.getString("ubicacion"));
                objeto.setTipo(rs.getString("tipo"));
                objeto.setCaracteristicas(rs.getString("caracteristicas"));
                objeto.setEstado(rs.getString("estado"));
                objeto.setIdUsuario(rs.getInt("usuarios_idUsuarios"));

                objetosPerdidosAr.add(objeto);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return objetosPerdidosAr;
    }
}