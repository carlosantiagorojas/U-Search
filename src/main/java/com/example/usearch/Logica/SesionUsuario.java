package com.example.usearch.Logica;

import com.example.usearch.Persistencia.ConexionBD;

import java.util.ArrayList;

public class SesionUsuario {

    private static int id;

    private static String rol;

    private static String correoElectronico;

    private static String contrasena;

    private static ArrayList<ObjetoPerdido> objetosPerdidos;


    public static void cargarDatosUsuario(int id, String rol, String correoElectronico, String contrasena) {
        SesionUsuario.id = id;
        SesionUsuario.rol = rol;
        SesionUsuario.correoElectronico = correoElectronico;
        SesionUsuario.contrasena = contrasena;
    }

    public static void cargarObjetosPerdidos(ArrayList<ObjetoPerdido> objetosPerdidos) {
        SesionUsuario.objetosPerdidos = objetosPerdidos;
    }

    public static int getId() {
        return id;
    }

    public static void setId(int id) {
        SesionUsuario.id = id;
    }

    public static String getRol() {
        return rol;
    }

    public static void setRol(String rol) {
        SesionUsuario.rol = rol;
    }

    public static String getCorreoElectronico() {
        return correoElectronico;
    }

    public static void setCorreoElectronico(String correoElectronico) {
        SesionUsuario.correoElectronico = correoElectronico;
    }

    public static String getContrasena() {
        return contrasena;
    }

    public static void setContrasena(String contrasena) {
        SesionUsuario.contrasena = contrasena;
    }

    public static ArrayList<ObjetoPerdido> getObjetosPerdidos() {
        return objetosPerdidos;
    }

    public static void setObjetosPerdidos(ArrayList<ObjetoPerdido> objetosPerdidos) {
        SesionUsuario.objetosPerdidos = objetosPerdidos;
    }

    public static void mostrarDatosUsuario(){
        System.out.println("ID: " + SesionUsuario.id);
        System.out.println("Rol: " + SesionUsuario.rol);
        System.out.println("Correo: " + SesionUsuario.correoElectronico);
        System.out.println("Contraseña: " + SesionUsuario.contrasena);
    }
}
