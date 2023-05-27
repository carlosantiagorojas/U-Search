package com.example.usearch.Persistencia;

import com.example.usearch.Controladores.Alertas;
import com.example.usearch.Logica.Notificacion;
import com.example.usearch.Logica.Usuario;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class RepositoryNotificacion implements IRepository<Notificacion> {

    @Override
    public boolean crear(Notificacion entity) {
        return false;
    }

    @Override
    public void actualizar(Notificacion entity) {

    }

    @Override
    public void actualizarPorId(int id) {

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
