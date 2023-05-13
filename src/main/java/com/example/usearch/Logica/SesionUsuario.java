package com.example.usearch.Logica;

import java.util.ArrayList;

public class SesionUsuario {

    private static int id;

    private static String rol;

    private static String correoElectronico;

    private static String contrasena;

    private static ArrayList<ObjetoPerdido> objetosPerdidos;

    private static ArrayList<Notificacion> notificaciones;


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

    public static ArrayList<Notificacion> getNotificaciones() {
        return notificaciones;
    }

    public static void setNotificaciones(ArrayList<Notificacion> notificaciones) {
        SesionUsuario.notificaciones = notificaciones;
    }

    public static ArrayList<String> obtenerMensajes() {

        ArrayList<String> mensajes = new ArrayList<String>();
        for(Notificacion not: SesionUsuario.notificaciones){
            mensajes.add(not.getMensaje());
        }

        return mensajes;
    }

    public static void mostrarDatosUsuario(){
        System.out.println();
        System.out.println("ID: " + SesionUsuario.id);
        System.out.println("Rol: " + SesionUsuario.rol);
        System.out.println("Correo: " + SesionUsuario.correoElectronico);
        System.out.println("Contraseña: " + SesionUsuario.contrasena);
        System.out.println();
    }

    public static void mostrarObjetos(){
        for(ObjetoPerdido ob: SesionUsuario.objetosPerdidos){
            System.out.println("ID objeto: " + ob.getId());
            System.out.println("Fecha: " + ob.getFechaPerdida());
            System.out.println("Ubicacion: " + ob.getUbicacion());
            System.out.println("Tipo: " + ob.getTipo());
            System.out.println("Caracteristicas: " + ob.getCaracteristicas());
            System.out.println("Estado: " + ob.getEstado());
            System.out.println("ID usuario: " + ob.getIdUsuario());
            System.out.println();
        }
    }

    public static void mostrarNotificaciones(){
        for(Notificacion not: SesionUsuario.notificaciones){
            System.out.println("ID notificacion: " + not.getId());
            System.out.println("Mensaje: " + not.getMensaje());
            System.out.println("ID usuario: " + not.getIdUsuario());
            System.out.println();
        }
    }
}
