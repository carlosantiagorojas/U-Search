package com.example.usearch.Persistencia.Repository;

import com.example.usearch.Utilidades.Alertas;
import com.example.usearch.Entidades.Notificacion;
import com.example.usearch.Persistencia.Utilidades.ConexionBD;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Clase que implementa la interfaz del repository para la entidad Notificacion
 */
public class RepositoryNotificacion implements IRepository<Notificacion> {

    private static RepositoryNotificacion instance;

    /**
     * Constructor privado para implementar el patron singleton
     * @return instancia de la clase
     */
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
            throw new RuntimeException(e);
        }

        return insertado;
    }

    @Override
    public boolean actualizarPorId(int id) {
        return false;
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
            throw new RuntimeException(e);
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
            Alertas.mostrarError("Error al cargar notificaciones");
            throw new RuntimeException(e);
        }

        return notifaciones;
    }
}
