package com.example.usearch.Entidades;

public class Notificacion {

    private int id;

    private String mensaje;

    private int IdUsuario;

    public Notificacion() {
    }

    public Notificacion(int idusuario, String mensaje) {
        this.IdUsuario = idusuario;
        this.mensaje = mensaje;
    }

    public Notificacion(String mensaje) {
        this.mensaje = mensaje;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public int getIdUsuario() {
        return IdUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        IdUsuario = idUsuario;
    }
}
