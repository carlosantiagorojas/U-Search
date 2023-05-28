package com.example.usearch.Persistencia.Repository;

import com.example.usearch.Entidades.ObjetoPerdido;
import com.example.usearch.Persistencia.Utilidades.ConexionBD;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Clase que implementa la interfaz del repository para la entidad ObjetoPerdido
 */
public class RepositoryObjetoPerdido implements IRepository<ObjetoPerdido> {

    private static RepositoryObjetoPerdido instance;

    /**
     * Constructor privado para implementar el patron singleton
     * @return instancia de la clase
     */
    public static RepositoryObjetoPerdido getInstance() {
        if (instance == null) {
            instance = new RepositoryObjetoPerdido();
        }
        return instance;
    }

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
    public boolean actualizarPorId(int id) {
        boolean actualizar = false;
        String query = "UPDATE objetosperdidos SET estado = 'encontrado' WHERE idObjetosPerdidos = ?";

        try (PreparedStatement statement = ConexionBD.conexion.prepareStatement(query);){
            statement.setInt(1, id);
            int filasAfectadas = statement.executeUpdate();
            actualizar = (filasAfectadas > 0);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return actualizar;
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
    public ArrayList<ObjetoPerdido> consultarListaFecha(Date fechaPerdida) {
        ArrayList<ObjetoPerdido> objetosPerdidosAr = new ArrayList<>();
        String query = "SELECT * FROM objetosperdidos WHERE fechaPerdida = ?";

        try (PreparedStatement statement = ConexionBD.conexion.prepareStatement(query);){
            statement.setDate(1, fechaPerdida);
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
    public ArrayList<ObjetoPerdido> consultarListaPorUbicacion(String ubicacion) {
        ArrayList<ObjetoPerdido> objetosPerdidosAr = new ArrayList<>();
        String query = "SELECT * FROM objetosperdidos WHERE ubicacion = ?";

        try (PreparedStatement statement = ConexionBD.conexion.prepareStatement(query);){
            statement.setString(1, ubicacion);
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
    public ArrayList<ObjetoPerdido> consultarListaPorTipo(String tipo) {

        ArrayList<ObjetoPerdido> objetosPerdidosAr = new ArrayList<>();
        String query = "SELECT * FROM objetosperdidos WHERE tipo = ?";

        try (PreparedStatement statement = ConexionBD.conexion.prepareStatement(query);){
            statement.setString(1, tipo);
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
