package com.example.usearch.Persistencia;

import com.example.usearch.Logica.ObjetoPerdido;
import com.example.usearch.Logica.SesionUsuario;

import java.sql.*;
import java.sql.Date;
import java.util.ArrayList;

public class ConexionBD {
    private static Connection conexion;
    private static ConexionBD instance;

    public static Connection conectar()  {
        try {
            conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/u-search", "root", "1234");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return conexion;
    }

    public boolean registrarUsuario(String rol, String correo, String contrasena) {
        boolean registroExitoso = false;
        String query = "INSERT INTO usuarios (rol, correo, contrasena) VALUES (?, ?, ?)";

        try (PreparedStatement statement = conexion.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, rol);
            statement.setString(2, correo);
            statement.setString(3, contrasena);
            int filasAfectadas = statement.executeUpdate();
            if (filasAfectadas > 0) {
                registroExitoso = true;
                ResultSet resultSet = statement.getGeneratedKeys();
                if (resultSet.next()) {
                    SesionUsuario.cargarDatosUsuario(resultSet.getInt(1), rol, correo, contrasena);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return registroExitoso;
    }


    public boolean consultarUsuarioSesion(String correo, String contrasena) {
        boolean usuarioEncontrado = false;
        String query = "SELECT * FROM usuarios WHERE correo = ? AND contrasena = ?";

        try (PreparedStatement statement = conexion.prepareStatement(query)) {
            statement.setString(1, correo);
            statement.setString(2, contrasena);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                SesionUsuario.cargarDatosUsuario(resultSet.getInt("idUsuarios"), resultSet.getString("rol"), resultSet.getString("correo"), resultSet.getString("contrasena"));
                usuarioEncontrado = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return usuarioEncontrado;
    }


    public boolean registrarObjeto(Date fechaPerdida, String ubicacion, String tipo, String caracteristicas, String estado, int usuarios_idUsuarios){
        boolean registroObjeto = false;

        String query = "INSERT INTO objetosperdidos (fechaPerdida, ubicacion, tipo, caracteristicas, estado, usuarios_idUsuarios) VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement statement = conexion.prepareStatement(query)) {
            statement.setDate(1, fechaPerdida);
            statement.setString(2, ubicacion);
            statement.setString(3, tipo);
            statement.setString(4, caracteristicas);
            statement.setString(5, estado);
            statement.setInt(6, usuarios_idUsuarios);
            int filasAfectadas = statement.executeUpdate();
            registroObjeto = (filasAfectadas > 0);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return registroObjeto;
    }

    public ArrayList<ObjetoPerdido> cargarObjetosPerdidos(int usuarios_idUsuarios){

        ArrayList<ObjetoPerdido> objetosPerdidosAr = new ArrayList<>();
        String query = "SELECT * FROM objetosperdidos WHERE usuarios_idUsuarios = ?";

        try (PreparedStatement statement = conexion.prepareStatement(query);){
            statement.setInt(1, usuarios_idUsuarios);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                ObjetoPerdido objeto = new ObjetoPerdido();
                objeto.setId(rs.getInt("idObjetosPerdidos"));
                objeto.setFechaPerdida(rs.getDate("fechaPerdida"));
                objeto.setUbicacion(rs.getString("ubicacion"));
                objeto.setTipo(rs.getString("tipo"));
                objeto.setCaracteristicas(rs.getString("caracteristicas"));
                objeto.setEstado(rs.getString("estado"));

                objetosPerdidosAr.add(objeto);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return objetosPerdidosAr;
    }
    public ArrayList<ObjetoPerdido> cargarObjetosPerdidosPer(String tipo, String ubicacion, String fecha){

        ArrayList<ObjetoPerdido> objetosPerdidosAr = new ArrayList<>();
        String query = "SELECT * FROM objetosperdidos WHERE tipo = ? AND ubicacion = ? AND fechaPerdida=?";

        try (PreparedStatement statement = conexion.prepareStatement(query);){
            statement.setString(1, tipo);
            statement.setString(2, ubicacion);
            statement.setString(3, fecha);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                ObjetoPerdido objeto = new ObjetoPerdido();
                objeto.setId(rs.getInt("idObjetosPerdidos"));
                objeto.setFechaPerdida(rs.getDate("fechaPerdida"));
                objeto.setUbicacion(rs.getString("ubicacion"));
                objeto.setTipo(rs.getString("tipo"));
                objeto.setCaracteristicas(rs.getString("caracteristicas"));
                objeto.setEstado(rs.getString("estado"));

                objetosPerdidosAr.add(objeto);
            }

        } catch (SQLException e) {
        }

        return objetosPerdidosAr;
    }

}
