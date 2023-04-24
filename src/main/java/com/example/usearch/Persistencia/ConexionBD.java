package com.example.usearch.Persistencia;

import com.example.usearch.Logica.ObjetoPerdido;
import com.example.usearch.Logica.SesionUsuario;
import com.example.usearch.Logica.Usuario;

import java.sql.*;
import java.sql.Date;
import java.util.ArrayList;

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

        try (PreparedStatement statement = conexion.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, rol);
            statement.setString(2, correo);
            statement.setString(3, contrasena);
            int filasAfectadas = statement.executeUpdate();
            if (filasAfectadas > 0) {
                registroExitoso = true;
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
            statement.setString(3, caracteristicas);
            statement.setString(4, estado);
            statement.setString(5, tipo);
            statement.setInt(6, usuarios_idUsuarios);
            int filasAfectadas = statement.executeUpdate();
            registroObjeto = (filasAfectadas > 0);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return registroObjeto;
    }

    public ArrayList<ObjetoPerdido> cargarObjetosPerdidos(){
        ArrayList<ObjetoPerdido> objetosPerdidos = new ArrayList<>();


        return objetosPerdidos;
    }
}
