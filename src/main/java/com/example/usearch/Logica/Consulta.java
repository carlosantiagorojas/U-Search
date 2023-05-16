package com.example.usearch.Logica;

import java.sql.Date;

public class Consulta {

   private Date fecha;

   private String tipo;

   private String ubicacion;

   public Consulta(){};

    public Consulta(Date fecha, String tipo, String ubicacion) {
        this.fecha = fecha;
        this.tipo = tipo;
        this.ubicacion = ubicacion;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String obtenerConsulta()
    {
        String consulta = "";
        if(getFecha() != null && getTipo() != null && getUbicacion() != null)
        {
            consulta = "Fecha: " + getFecha() + "\n Tipo: " + getTipo() + "\n Ubicacion: " + getUbicacion();
        }
        else if(getFecha() != null)
        {
            consulta = "Fecha: " + getFecha();
        }
        else if(getTipo() != null)
        {
            consulta = "Tipo: " + getTipo();
        }
        else if(getUbicacion() != null)
        {
            consulta = "Ubicacion: " + getUbicacion();
        }

        return consulta;
    }

    public void MostrarConsulta()
    {
        System.out.println("Fecha: " + getFecha());
        System.out.println("Tipo: " + getTipo());
        System.out.println("Ubicacion: " + getUbicacion());
    }

}
