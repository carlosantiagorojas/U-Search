package com.example.usearch.Persistencia;

import java.sql.*;
import java.sql.Date;

public class ConexionBD {
    private static Connection conexion;
    private static ConexionBD instance;

    public static synchronized ConexionBD getInstance()  {
        if (instance == null) {
            instance = new ConexionBD();
            try {
                conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/u-search", "root", "1234");
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return instance;
    }

    public Connection getConnection() {
        return conexion;
    }

    public boolean registrarUsuario(String rol, String correo, String contrasena) {
        boolean registroExitoso = false;
        String query = "INSERT INTO usuarios (rol, correo, contrasena) VALUES (?, ?, ?)";

        try (PreparedStatement statement = conexion.prepareStatement(query)) {
            statement.setString(1, rol);
            statement.setString(2, correo);
            statement.setString(3, contrasena);
            int filasAfectadas = statement.executeUpdate();
            registroExitoso = (filasAfectadas > 0);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return registroExitoso;
    }

    public String consultarUsuarioSesion(String correo, String contrasena) {
        String rolUsuario = null;
        String query = "SELECT rol FROM usuarios WHERE correo = ? AND contrasena = ?";

        try (PreparedStatement statement = conexion.prepareStatement(query)) {
            statement.setString(1, correo);
            statement.setString(2, contrasena);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                rolUsuario = resultSet.getString("rol");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return rolUsuario;
    }

    public boolean registrarObjeto(Date fechaPerdida, String ubicacion, String tipo, String carateristicas, String estado){
        boolean registroObjeto = false;

        String query = "INSERT INTO objetosperdidos (fechaPredida, ubicacion, tipo, caractersiticas, estado) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement statement = conexion.prepareStatement(query)) {
            statement.setDate(1, fechaPerdida);
            statement.setString(2, ubicacion);
            statement.setString(3, carateristicas);
            statement.setString(4, estado);
            statement.setString(5, tipo);
            int filasAfectadas = statement.executeUpdate();
            registroObjeto = (filasAfectadas > 0);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return registroObjeto;
    }
}
