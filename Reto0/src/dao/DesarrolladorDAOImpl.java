package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import conexion.ConexionBD;
import excepciones.AccesoDatosException;
import modelo.Desarrollador;

/**
 * NOTA: el código que se compartió para este archivo era en realidad
 * un duplicado de la interfaz UsuarioDAO, no la implementación real de
 * Inés para Desarrollador. Este insertar(...) sigue el mismo patrón
 * que UsuarioDAOImpl - pídele a Inés el código real para comparar.
 */
public class DesarrolladorDAOImpl implements DesarrolladorDAO {

    @Override
    public void insertar(Desarrollador desarrollador) throws AccesoDatosException {
        String sql = "INSERT INTO desarrollador (nombre, pais, añoFundacion) VALUES (?, ?, ?)";
        Connection con = ConexionBD.getInstancia().getConexion();

        try (PreparedStatement ps = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, desarrollador.getNombre());
            ps.setString(2, desarrollador.getPais());
            ps.setInt(3, desarrollador.getAñoFundacion());
            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    desarrollador.setId(rs.getInt(1));
                }
            }
        } catch (SQLException e) {
            throw new AccesoDatosException("Error al registrar el desarrollador " + desarrollador.getNombre(), e);
        }
    }

    @Override
    public Desarrollador buscarPorId(int id) throws AccesoDatosException {
        // TODO (Iker)
        return null;
    }
}