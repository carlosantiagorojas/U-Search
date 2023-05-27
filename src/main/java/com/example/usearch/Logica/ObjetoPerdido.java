package com.example.usearch.Logica;

import java.sql.Date;

public class ObjetoPerdido {

    private int id;

    private Date fechaPerdida;

    private String ubicacion;

    private String tipo;

    private String caracteristicas;

    private String estado;

    private int idUsuario;

    public ObjetoPerdido() {
    }

    public ObjetoPerdido(int id, Date fechaPerdida, String ubicacion, String tipo, String caracteristicas, String estado, int idusuario) {
        this.id = id;
        this.fechaPerdida = fechaPerdida;
        this.ubicacion = ubicacion;
        this.tipo = tipo;
        this.caracteristicas = caracteristicas;
        this.estado = estado;
        this.idUsuario = idusuario;
    }

    public ObjetoPerdido(Date fechaPerdida, String ubicacion, String tipo, String caracteristicas, String estado, int idUsuario) {
        this.fechaPerdida = fechaPerdida;
        this.ubicacion = ubicacion;
        this.tipo = tipo;
        this.caracteristicas = caracteristicas;
        this.estado = estado;
        this.idUsuario = idUsuario;
    }

    public ObjetoPerdido(String tipo, String ubicacion,Date fechaPerdida) {
        this.fechaPerdida = fechaPerdida;
        this.ubicacion = ubicacion;
        this.tipo = tipo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getFechaPerdida() {
        return fechaPerdida;
    }

    public void setFechaPerdida(Date fechaPerdida) {
        this.fechaPerdida = fechaPerdida;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getCaracteristicas() {
        return caracteristicas;
    }

    public void setCaracteristicas(String carateristicas) {
        this.caracteristicas = carateristicas;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public void verInformacion()
    {
        System.out.println("ID: " + this.getId());
        System.out.println("Estado: " + this.getEstado());
        System.out.println("Fecha: " + this.getFechaPerdida());
        System.out.println("Ubicacion: " + this.getUbicacion());
        System.out.println("Tipo: " + this.getTipo());
        System.out.println("Caracteristicas: " + this.getCaracteristicas());
        System.out.println("ID Usuario: " + this.getIdUsuario());
        System.out.println("--------------------------------------------------");
    }
}
