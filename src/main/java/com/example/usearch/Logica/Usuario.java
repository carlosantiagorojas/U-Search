package com.example.usearch.Logica;

import java.util.ArrayList;

public class Usuario {

    private int id;

    private String rol;

    private String correoElectronico;

    private String contrasena;

    private ArrayList<ObjetoPerdido> objetosPerdidos;

    private ArrayList<Notificacion> notificaciones;

    public Usuario(String rol, String correoElectronico, String contrasena) {
        this.rol = rol;
        this.correoElectronico = correoElectronico;
        this.contrasena = contrasena;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public ArrayList<ObjetoPerdido> getObjetosPerdidos() {
        return objetosPerdidos;
    }

    public void setObjetosPerdidos(ArrayList<ObjetoPerdido> objetosPerdidos) {
        this.objetosPerdidos = objetosPerdidos;
    }

    public ArrayList<Notificacion> getNotificaciones() {
        return notificaciones;
    }

    public void setNotificaciones(ArrayList<Notificacion> notificaciones) {
        this.notificaciones = notificaciones;
    }
}
