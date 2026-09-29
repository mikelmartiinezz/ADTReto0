package dao;

import excepciones.AccesoDatosException;
import modelo.Usuario;

/**
 * Aísla la capa de negocio de cómo se accede realmente a los datos de
 * Usuario (JDBC contra gamedb).
 */
public interface UsuarioDAO {

    void insertar(Usuario usuario) throws AccesoDatosException;

    Usuario buscarPorId(int id) throws AccesoDatosException;
}
