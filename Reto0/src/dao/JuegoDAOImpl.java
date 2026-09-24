package dao;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;

import excepciones.AccesoDatosException;
import modelo.Juego;

public class JuegoDAOImpl implements JuegoDAO {

    private static final String RUTA_FICHERO = "juegos.dat";

    @Override
    public void insertar(Juego juego) throws AccesoDatosException {
        // TODO (Iker)
    }

    @Override
    public void actualizar(Juego juego) throws AccesoDatosException {
        // TODO (Iker)
    }

    @Override
    public Juego buscarPorId(int id) throws AccesoDatosException {
        // TODO (Iker)
        return null;
    }

    @Override
    public List<Juego> listarDisponibles() throws AccesoDatosException {
        List<Juego> disponibles = new ArrayList<>();
        for (Juego j : leerTodos()) {
            if (j.getStock() > 0) {
                disponibles.add(j);
            }
        }
        return disponibles;
    }

    @Override
    public List<Juego> listarPorDesarrollador(int idDesarrollador) throws AccesoDatosException {
        List<Juego> resultado = new ArrayList<>();
        for (Juego j : leerTodos()) {
            if (j.getIdDesarrollador() == idDesarrollador) {
                resultado.add(j);
            }
        }
        return resultado;
    }

    private List<Juego> leerTodos() throws AccesoDatosException {
        File fichero = new File(RUTA_FICHERO);
        if (!fichero.exists()) {
            return new ArrayList<>();
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fichero))) {
            return (List<Juego>) ois.readObject();
        } catch (EOFException e) {
            return new ArrayList<>();
        } catch (IOException | ClassNotFoundException e) {
            throw new AccesoDatosException("Error al leer el fichero " + RUTA_FICHERO, e);
        }
    }
}
