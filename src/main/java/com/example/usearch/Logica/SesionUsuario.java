package com.example.usearch.Logica;

import com.example.usearch.Persistencia.ConexionBD;

import java.util.ArrayList;

public class SesionUsuario {

    private static int id;

    private static String rol;

    private static String correoElectronico;

    private static String contrasena;

    private static ArrayList<ObjetoPerdido> objetosPerdidos;

    public static void cargarDatosUsuario() {
        ConexionBD conexion = new ConexionBD();
        conexion.cargarObjetosPerdidos();
    }


}
