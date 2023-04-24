package com.example.usearch.Logica;

import java.util.ArrayList;

public class Usuario {

    private int id;

    private String rol;

    private String correoElectronico;

    private String contrasena;

    private ArrayList<ObjetoPerdido> objetosPerdidos;

    public Usuario(int id, String correoElectronico, String contrasena, ArrayList<ObjetoPerdido> objetosPerdidos, String rol) {
        this.id = id;
        this.correoElectronico = correoElectronico;
        this.contrasena = contrasena;
        this.objetosPerdidos = objetosPerdidos;
        this.rol = rol;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }
}
