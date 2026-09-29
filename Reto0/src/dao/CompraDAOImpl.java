package dao;

import conexion.ConexionBD;
import excepciones.AccesoDatosException;
import modelo.Compra;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Date;
import java.util.List;

public class CompraDAOImpl implements CompraDAO {

    @Override
    public void insertar(Compra compra) throws AccesoDatosException {
        String sql = "INSERT INTO compra "
                + "(fecha, cantidad, usuario_id, juego_id) "
                + "VALUES (?, ?, ?, ?)";
        try {
            Connection conexion = ConexionBD.getInstancia().getConexion();
            try (PreparedStatement ps = conexion.prepareStatement(sql)) {
                ps.setDate(1, Date.valueOf(compra.getFecha()));
                ps.setInt(2, compra.getCantidad());
                ps.setInt(3, compra.getUsuarioId());
                ps.setInt(4, compra.getJuegoId());
                ps.executeUpdate();
            }
        } catch (SQLException e) {
            throw new AccesoDatosException(
                    "Error al registrar la compra.", e
            );
        }
    }

    @Override
    public List<Compra> listarPorUsuario(int idUsuario)
            throws AccesoDatosException {

        // TODO (Anurag)
        return null;
    }
}
