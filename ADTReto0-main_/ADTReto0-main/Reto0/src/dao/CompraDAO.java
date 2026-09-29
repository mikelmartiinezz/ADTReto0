package dao;

import java.util.List;

import excepciones.AccesoDatosException;
import modelo.Compra;

public interface CompraDAO {

    void insertar(Compra compra) throws AccesoDatosException;

    List<Compra> listarPorUsuario(int idUsuario) throws AccesoDatosException;
}
