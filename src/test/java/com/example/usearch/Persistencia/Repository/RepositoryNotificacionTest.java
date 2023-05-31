package com.example.usearch.Persistencia.Repository;

import com.example.usearch.Entidades.Notificacion;
import com.example.usearch.Persistencia.Utilidades.ConexionBD;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

class RepositoryNotificacionTest {

    private RepositoryNotificacion repositoryNotificacion;

    @BeforeEach
    public void setUp(){
        repositoryNotificacion = RepositoryNotificacion.getInstance();
        ConexionBD.conectarBaseDeDatos();
    }

    @Test
    void eliminarPorIdUsuarioSinNotificaciones() {
        //arrange
        int id = 1000;
        boolean expected;
        //act
        expected = repositoryNotificacion.eliminarPorId(id);
        //assert
        assertFalse(expected);
    }

    @Test
    void consultarListaPorIdUsuarioSinNotifiaciones() {
        //arrange
        int id = 1000;
        //act
        ArrayList<Notificacion> array = repositoryNotificacion.consultarListaPorId(id);
        //assert
        assertTrue(array.isEmpty(), "Hay usuarios con ese id que tiene notificaciones");
    }

    @Test
    void crearNotificacionUsuarioNoExistente() {
        //arrange
        Notificacion notificacion = new Notificacion(1000,"prueba");
        //act & assert
        assertThrows(RuntimeException.class, () -> repositoryNotificacion.crear(notificacion));
    }
}