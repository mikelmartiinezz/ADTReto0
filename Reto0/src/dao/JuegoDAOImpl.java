package dao;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;

import excepciones.AccesoDatosException;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import modelo.Juego;

public class JuegoDAOImpl implements JuegoDAO {

    private static final String RUTA_FICHERO = "juegos.dat";

    @Override
    public void insertar(Juego juego) throws AccesoDatosException {
        List<Juego> juegos = leerTodos();
        for (Juego j : juegos) {
            if (j.getId() == juego.getId()) {
                throw new AccesoDatosException(
                        "Ya existe un juego con ese ID."
                );
            }
        }
        juegos.add(juego);
        guardarTodos(juegos);
    }

    @Override
    public void actualizar(Juego juegoActualizado) throws AccesoDatosException {
        List<Juego> juegos = leerTodos();
        boolean encontrado = false;
        for (int i = 0; i < juegos.size(); i++) {
            if (juegos.get(i).getId() == juegoActualizado.getId()) {

                juegos.set(i, juegoActualizado);
                encontrado = true;
                break;
            }
        }
        if (!encontrado) {
            throw new AccesoDatosException("El juego no existe.");
        }
        guardarTodos(juegos);
    }

    @Override
    public Juego buscarPorId(int id) throws AccesoDatosException {
        List<Juego> juegos = leerTodos();
        for (Juego juego : juegos) {
            if (juego.getId() == id) {
                return juego;
            }
        }
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

    private void guardarTodos(List<Juego> juegos)
            throws AccesoDatosException {

        try (ObjectOutputStream oos
                = new ObjectOutputStream(
                        new FileOutputStream(RUTA_FICHERO))) {

            oos.writeObject(juegos);

        } catch (IOException e) {

            throw new AccesoDatosException(
                    "Error al guardar el fichero " + RUTA_FICHERO, e
            );
        }
    }
}
