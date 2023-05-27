package com.example.usearch.Persistencia;

import com.example.usearch.Controladores.Alertas;
import com.example.usearch.Logica.Notificacion;
import com.example.usearch.Logica.SesionUsuario;
import com.example.usearch.Logica.Usuario;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class RepositoryUsuario implements IRepository<Usuario> {
    @Override
    public boolean crear(Usuario entity) {
        boolean registroExitoso = false;
        String query = "INSERT INTO usuarios (rol, correo, contrasena) VALUES (?, ?, ?)";

        try (PreparedStatement statement = ConexionBD.conexion.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, entity.getRol());
            statement.setString(2, entity.getCorreoElectronico());
            statement.setString(3, entity.getContrasena());
            int filasAfectadas = statement.executeUpdate();
            if (filasAfectadas > 0) {
                registroExitoso = true;
                ResultSet resultSet = statement.getGeneratedKeys();
                if (resultSet.next()) {
                    SesionUsuario.cargarDatosUsuario(resultSet.getInt(1), entity.getRol(),  entity.getCorreoElectronico(), entity.getContrasena());
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return registroExitoso;
    }

    @Override
    public void actualizar(Usuario entity) {

    }

    @Override
    public void actualizarPorId(int id) {

    }

    @Override
    public void eliminar(Usuario entity) {

    }

    @Override
    public boolean eliminarPorId(int id) {
        return false;
    }

    @Override
    public boolean consultarPorCredenciales(Usuario entity) {
        boolean usuarioEncontrado = false;
        String query = "SELECT * FROM usuarios WHERE correo = ? AND contrasena = ?";

        try (PreparedStatement statement = ConexionBD.conexion.prepareStatement(query)) {
            statement.setString(1, entity.getCorreoElectronico());
            statement.setString(2, entity.getContrasena());
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

    @Override
    public ArrayList<Usuario> consultarListaPorEntidad(Usuario entity) {
        return null;
    }

    @Override
    public ArrayList<Usuario> consultarListaPorId(int id) {
        return null;
    }
}
