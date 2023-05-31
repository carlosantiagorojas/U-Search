package com.example.usearch.Persistencia.Repository;

import com.example.usearch.Entidades.Usuario;
import com.example.usearch.Persistencia.Utilidades.ConexionBD;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RepositoryUsuarioTest {


    private RepositoryUsuario repositoryUsuario;

    @BeforeEach
    public void setUp(){
        repositoryUsuario = RepositoryUsuario.getInstance();
        ConexionBD.conectarBaseDeDatos();
    }

    @Test
    void crearNuevoUsuario() {
        //arrange
        Usuario usuario = new Usuario("usuario","ejemplotest@javeriana.edu.co", "1234");
        boolean expected;
        //act
        expected = repositoryUsuario.crear(usuario);
        //assert
        assertTrue(expected);
    }

    @Test
    void consultarPorCredencialesUsuarioExistente() {
        //arrange
        Usuario usuario = new Usuario("usuario","prueba@javeriana.edu.co", "1");
        boolean expected;
        //act
        expected = repositoryUsuario.consultarPorCredenciales(usuario);
        //assert
        assertTrue(expected);
    }

    @Test
    void consultarPorCredencialesUsuarioInexistente() {
        //arrange
        Usuario usuario = new Usuario("usuario","noexiste@javeriana.edu.co", "1");
        boolean expected;
        //act
        expected = repositoryUsuario.consultarPorCredenciales(usuario);
        //assert
        assertFalse(expected);
    }
}