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

    public int registrarUsuario(String rol, String correo, String contrasena) {
        int idUsuario = -1;
        String query = "INSERT INTO usuarios (rol, correo, contrasena) VALUES (?, ?, ?)";

        try (PreparedStatement statement = conexion.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, rol);
            statement.setString(2, correo);
            statement.setString(3, contrasena);
            int filasAfectadas = statement.executeUpdate();
            if (filasAfectadas > 0) {
                try (ResultSet rs = statement.getGeneratedKeys()) {
                    if (rs.next()) {
                        idUsuario = rs.getInt(1);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return idUsuario;
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

    public boolean registrarObjeto(Date fechaPerdida, String ubicacion, String tipo, String carateristicas, String estado, int idUsuarios){
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

    public void cargarObjetosPerdidos(){

    }
}
