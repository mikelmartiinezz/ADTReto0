package dao;

import excepciones.AccesoDatosException;
import modelo.Desarrollador;

public interface DesarrolladorDAO {

    void insertar(Desarrollador desarrollador) throws AccesoDatosException;

    Desarrollador buscarPorId(int id) throws AccesoDatosException;
    
}

