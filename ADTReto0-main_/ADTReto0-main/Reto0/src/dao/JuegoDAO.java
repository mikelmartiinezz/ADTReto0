package dao;

import java.util.List;

import excepciones.AccesoDatosException;
import modelo.Juego;

public interface JuegoDAO {

    void insertar(Juego juego) throws AccesoDatosException;

    void actualizar(Juego juego) throws AccesoDatosException;

    Juego buscarPorId(int id) throws AccesoDatosException;

    List<Juego> listarDisponibles() throws AccesoDatosException;

    List<Juego> listarPorDesarrollador(int idDesarrollador) throws AccesoDatosException;
}


