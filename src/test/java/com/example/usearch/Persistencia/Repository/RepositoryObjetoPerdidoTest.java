package com.example.usearch.Persistencia.Repository;

import com.example.usearch.Entidades.ObjetoPerdido;
import com.example.usearch.Persistencia.Utilidades.ConexionBD;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class RepositoryObjetoPerdidoTest {

    private RepositoryObjetoPerdido repositoryObjetoPerdido;

    @BeforeEach
    public void setUp(){
        repositoryObjetoPerdido = RepositoryObjetoPerdido.getInstance();
        ConexionBD.conectarBaseDeDatos();
    }

    @Test
    void crearObjetoUsuarioNoExistente() {
        //arrange
        ObjetoPerdido obj = new ObjetoPerdido((new Date(2020,11, 10)), "ed baron", "Celular", "Iphone 11", "Perdido", 1000);
        //act & assert
        assertThrows(RuntimeException.class, () -> repositoryObjetoPerdido.crear(obj));
    }

    @Test
    void consultarListaFechaNoExistente() {
        //arrange
        LocalDate fecha = LocalDate.of(2019, 5, 11);
        //act
        ArrayList<ObjetoPerdido> array = repositoryObjetoPerdido.consultarListaFecha(Date.valueOf(fecha));
        //assert
        assertTrue(array.isEmpty(), "Hay objetos registrados con esa fecha");
    }

    @Test
    void consultarListaPorUbicacionNoExistente() {
        //arrange
        String ubicacion = "Basurero";
        //act
        ArrayList<ObjetoPerdido> array = repositoryObjetoPerdido.consultarListaPorUbicacion(ubicacion);
        //assert
        assertTrue(array.isEmpty(), "Hay objetos registrados con esa ubicacion");
    }

    @Test
    void consultarListaPorTipoNoExistente() {
        //arrange
        String tipo = "pera";
        //act
        ArrayList<ObjetoPerdido> array = repositoryObjetoPerdido.consultarListaPorTipo(tipo);
        //assert
        assertTrue(array.isEmpty(), "Hay objetos registrados con ese tipo");
    }

    @Test
    void consultarListaPorEntidadNoExistente() {
        //arrange
        ObjetoPerdido obj = new ObjetoPerdido((new Date(2010,11, 10)), "ed baron", "Celular", "Iphone 11", "Perdido", 1000);
        //act
        ArrayList<ObjetoPerdido> array = repositoryObjetoPerdido.consultarListaPorEntidad(obj);
        //assert
        assertTrue(array.isEmpty(), "Hay objetos registrados con esas especificaciones");
    }

    @Test
    void consultarListaPorIdUsuarioSinObjetos() {
        //arrange
        int id = 1000;
        //act
        ArrayList<ObjetoPerdido> array = repositoryObjetoPerdido.consultarListaPorId(id);
        //assert
        assertTrue(array.isEmpty(), "El usuario tiene objetos registrados");
    }

    @Test
    void crearObjetoUsuarioxistente() {
        //arrange
        ObjetoPerdido obj = new ObjetoPerdido((new Date(2023,05, 10)), "ed baron", "Celular", "Iphone 11", "Perdido", 14);
        boolean expected;
        //act
        expected = repositoryObjetoPerdido.crear(obj);
        //assert
        assertTrue(expected);
    }

}