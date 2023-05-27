package com.example.usearch.Persistencia.Repository;

import com.example.usearch.Controladores.Alertas;
import com.example.usearch.Entidades.Notificacion;
import com.example.usearch.Persistencia.Utilidades.ConexionBD;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class RepositoryNotificacion implements IRepository<Notificacion> {

    private static RepositoryNotificacion instance;

    public static RepositoryNotificacion getInstance() {
        if (instance == null) {
            instance = new RepositoryNotificacion();
        }
        return instance;
    }

    @Override
    public boolean crear(Notificacion entity) {
        boolean insertado = false;

        String query = "INSERT INTO notificaciones (usuarios_idUsuarios, mensaje) VALUES (?, ?)";

        try (PreparedStatement statement = ConexionBD.conexion.prepareStatement(query);){
            statement.setInt(1, entity.getIdUsuario());
            statement.setString(2, entity.getMensaje());
            int filasAfectadas = statement.executeUpdate();
            insertado = (filasAfectadas > 0);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return insertado;
    }

    @Override
    public void actualizar(Notificacion entity) {

    }

    @Override
    public boolean actualizarPorId(int id) {
        return false;
    }

    @Override
    public void eliminar(Notificacion entity) {

    }

    @Override
    public boolean eliminarPorId(int id) {

        boolean eliminar = false;
        String query = "DELETE FROM notificaciones WHERE usuarios_idUsuarios = ?";

        try (PreparedStatement statement = ConexionBD.conexion.prepareStatement(query);){
            statement.setInt(1, id);
            int filasAfectadas = statement.executeUpdate();
            eliminar = (filasAfectadas > 0);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return eliminar;
    }

    @Override
    public boolean consultarPorCredenciales(Notificacion entity) {
        return false;
    }

    @Override
    public ArrayList<Notificacion> consultarListaFecha(Date fechaPerdida) {
        return null;
    }

    @Override
    public ArrayList<Notificacion> consultarListaPorUbicacion(String ubicacion) {
        return null;
    }

    @Override
    public ArrayList<Notificacion> consultarListaPorTipo(String tipo) {
        return null;
    }

    @Override
    public ArrayList<Notificacion> consultarListaPorEntidad(Notificacion entity) {
        return null;
    }

    @Override
    public ArrayList<Notificacion> consultarListaPorId(int id) {
        ArrayList<Notificacion> notifaciones = new ArrayList<>();
        String query = "SELECT * FROM notificaciones WHERE usuarios_idUsuarios = ?";

        try (PreparedStatement statement = ConexionBD.conexion.prepareStatement(query);) {
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                Notificacion notificacion = new Notificacion();
                notificacion.setId(rs.getInt("idNotificaciones"));
                notificacion.setMensaje(rs.getString("Mensaje"));
                notificacion.setIdUsuario(rs.getInt("Usuarios_idUsuarios"));

                notifaciones.add(notificacion);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            Alertas.mostrarError("Error al cargar notificaciones");
        }

        return notifaciones;
    }
}
