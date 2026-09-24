package dao;

import java.util.List;

import excepciones.AccesoDatosException;
import modelo.Juego;

/**
 * Aísla la capa de negocio de cómo se accede a los datos de Juego
 * (lectura/escritura del fichero juegos.dat).
 */
public interface JuegoDAO {

    void insertar(Juego juego) throws AccesoDatosException;

    void actualizar(Juego juego) throws AccesoDatosException;

    Juego buscarPorId(int id) throws AccesoDatosException;

    List<Juego> listarDisponibles() throws AccesoDatosException;

    List<Juego> listarPorDesarrollador(int idDesarrollador) throws AccesoDatosException;
}


