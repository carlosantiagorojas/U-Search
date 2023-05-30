package com.example.usearch.Persistencia.Utilidades;

import java.sql.*;

/**
 * Clase utilitaria para hacer la conexion con la base de datos
 */
public class ConexionBD {

    public static Connection conexion;

    private ConexionBD() {}

    public ConexionBD(Connection conexion) {
        ConexionBD.conexion = conexion;
    }

    /**
     * Conexion con la base de datos
     */
    public static void conectarBaseDeDatos() {
        try {
            ConexionBD.conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/u-search", "root", "1234");
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
}
