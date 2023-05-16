package com.example.usearch.Persistencia;

import com.example.usearch.Controladores.Alertas;
import com.example.usearch.Logica.Notificacion;
import com.example.usearch.Logica.ObjetoPerdido;
import com.example.usearch.Logica.SesionUsuario;
import java.sql.*;
import java.sql.Date;
import java.util.ArrayList;

public class ConexionBD {
    private static Connection conexion;

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

            // Se cargan los datos del usuario si se encuentra en la base de datos
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
                objeto.setIdUsuario(rs.getInt("usuarios_idUsuarios"));

                objetosPerdidosAr.add(objeto);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return objetosPerdidosAr;
    }

    public ArrayList<ObjetoPerdido> cargarObjetosPerdidosPer(String tipo, String ubicacion, Date fechaPerdida){

        ArrayList<ObjetoPerdido> objetosPerdidosAr = new ArrayList<>();
        String query = "SELECT * FROM objetosperdidos WHERE tipo = ? AND ubicacion = ? AND fechaPerdida=?";

        try (PreparedStatement statement = conexion.prepareStatement(query);){
            statement.setString(1, tipo);
            statement.setString(2, ubicacion);
            statement.setDate(3, fechaPerdida);
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
        }

        return objetosPerdidosAr;
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

    public ArrayList<Notificacion> cargarNotificaciones(int usuarios_idUsuarios){

        ArrayList<Notificacion> notifaciones = new ArrayList<>();
        String query = "SELECT * FROM notificaciones WHERE usuarios_idUsuarios = ?";

        try (PreparedStatement statement = conexion.prepareStatement(query);){
            statement.setInt(1, usuarios_idUsuarios);
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

    public boolean eliminarNotificaciones(int usuarios_idUsuarios){

        boolean eliminar = false;

        String query = "DELETE FROM notificaciones WHERE usuarios_idUsuarios = ?";

        try (PreparedStatement statement = conexion.prepareStatement(query);){
            statement.setInt(1, usuarios_idUsuarios);
            int filasAfectadas = statement.executeUpdate();
            eliminar = (filasAfectadas > 0);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return eliminar;
    }
}
