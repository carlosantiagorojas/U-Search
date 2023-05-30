package com.example.usearch.Persistencia.Repository;

import java.sql.Date;
import java.util.ArrayList;

/**
 * Interfaz que define los metodos que debe implementar un repository
 * Se hace la implentacion del patron repository
 * @param <T> Entidad para sobre la que se va a hacer la persistencia
 */
public interface IRepository <T>{

    /**
     * Metodo que crea un registro en la base de datos
     * @param entity entidad que se va a persistir
     * @return true si se creo el registro, false en caso contrario
     */
    boolean crear(T entity);

    /**
     * Metodo que actualiza un registro en la base de datos
     * @param id id del registro que se va a actualizar
     * @return true si se actualizo el registro, false en caso contrario
     */
    boolean actualizarPorId(int id);

    /**
     * Metodo que elimina un registro en la base de datos
     * @param id id del registro que se va a eliminar
     * @return true si se elimino el registro, false en caso contrario
     */
    boolean eliminarPorId(int id);

    /**
     * Metodo que consulta un registro en la base de datos
     * @param entity entidad que se va a consultar
     * @return true si se encontro el registro, false en caso contrario
     */
    boolean consultarPorCredenciales(T entity);

    /**
     * Metodo que consulta un registro en la base de datos
     * @param fechaPerdida parametro sobre el cual se va a consultar
     * @return lista de registros que se encontraron
     */
    ArrayList<T> consultarListaFecha(Date fechaPerdida);

    /**
     * Metodo que consulta un registro en la base de datos
     * @param ubicacion parametro sobre el cual se va a consultar
     * @return lista de registros que se encontraron
     */
    ArrayList<T> consultarListaPorUbicacion(String ubicacion);

    /**
     * Metodo que consulta un registro en la base de datos
     * @param tipo parametro sobre el cual se va a consultar
     * @return lista de registros que se encontraron
     */
    ArrayList<T> consultarListaPorTipo(String tipo);

    /**
     * Metodo que consulta un registro en la base de datos
     * @param entity entidad sobre el cual se va a consultar
     * @return lista de registros que se encontraron
     */
    ArrayList<T> consultarListaPorEntidad(T entity);

    /**
     * Metodo que consulta un registro en la base de datos
     * @param id parametro sobre el cual se va a consultar
     * @return lista de registros que se encontraron
     */
    ArrayList<T> consultarListaPorId(int id);
}
