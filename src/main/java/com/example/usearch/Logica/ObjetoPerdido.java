package com.example.usearch.Logica;

import java.sql.Date;

public class ObjetoPerdido {

    private int id;

    private Date fechaPerdida;

    private String ubicacion;

    private String tipo;

    private String caracteristicas;

    private String estado;

    public ObjetoPerdido() {
    }

    public ObjetoPerdido(int id, Date fechaPerdida, String ubicacion, String tipo, String caracteristicas, String estado) {
        this.id = id;
        this.fechaPerdida = fechaPerdida;
        this.ubicacion = ubicacion;
        this.tipo = tipo;
        this.caracteristicas = caracteristicas;
        this.estado = estado;
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
}
