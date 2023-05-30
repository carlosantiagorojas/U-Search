package com.example.usearch.Utilidades;

import java.sql.Date;

/**
 * Clase que representa una fecha
 */
public class Fecha {

    private Date fecha;

    public Fecha(Date fecha)
    {
        this.fecha = fecha;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    /**
     * Comprobar que la fecha ingresada en formato String sea valida
     * @return true si la fecha es valida, false si no lo es
     */
    public static boolean fechaValidaCampo(String fechaString)
    {
        try {
            Date fecha = Date.valueOf(fechaString);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
