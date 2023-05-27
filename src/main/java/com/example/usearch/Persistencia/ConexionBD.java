package com.example.usearch.Persistencia;

import com.example.usearch.Logica.ObjetoPerdido;
import java.sql.*;
import java.sql.Date;
import java.util.ArrayList;

public class ConexionBD {

    private static ConexionBD instance;
    public static Connection conexion;

    private ConexionBD() {

    }

    public ConexionBD(Connection conexion) {
        ConexionBD.conexion = conexion;
    }

    public static ConexionBD getInstance() {
        if (instance == null) {
            try {
                Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/u-search", "root", "1234");
                instance = new ConexionBD(conexion);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }

        return instance;
    }

    public ArrayList<ObjetoPerdido> cargarObjetosPerdidosTipo(String Tipo){

        ArrayList<ObjetoPerdido> objetosPerdidosAr = new ArrayList<>();
        String query = "SELECT * FROM objetosperdidos WHERE tipo = ?";

        try (PreparedStatement statement = conexion.prepareStatement(query);){
            statement.setString(1, Tipo);
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

    public ArrayList<ObjetoPerdido> cargarObjetosPerdidosUbicacion(String ubicacion){

        ArrayList<ObjetoPerdido> objetosPerdidosAr = new ArrayList<>();
        String query = "SELECT * FROM objetosperdidos WHERE ubicacion = ?";

        try (PreparedStatement statement = conexion.prepareStatement(query);){
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

    public ArrayList<ObjetoPerdido> cargarObjetosPerdidosFecha(Date fechaPerdida){

        ArrayList<ObjetoPerdido> objetosPerdidosAr = new ArrayList<>();
        String query = "SELECT * FROM objetosperdidos WHERE fechaPerdida = ?";

        try (PreparedStatement statement = conexion.prepareStatement(query);){
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

    public boolean actualizarEncontrado(int idObjeto){

        boolean actualizar = false;
        String query = "UPDATE objetosperdidos SET estado = 'encontrado' WHERE idObjetosPerdidos = ?";

        try (PreparedStatement statement = conexion.prepareStatement(query);){
            statement.setInt(1, idObjeto);
            int filasAfectadas = statement.executeUpdate();
            actualizar = (filasAfectadas > 0);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return actualizar;
    }

    public boolean enviarNotificacion(int usuarios_idUsuarios, String mensaje)
    {
        boolean insertado = false;

        String query = "INSERT INTO notificaciones (usuarios_idUsuarios, mensaje) VALUES (?, ?)";

        try (PreparedStatement statement = conexion.prepareStatement(query);){
            statement.setInt(1, usuarios_idUsuarios);
            statement.setString(2, mensaje);
            int filasAfectadas = statement.executeUpdate();
            insertado = (filasAfectadas > 0);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return insertado;
    }
}
