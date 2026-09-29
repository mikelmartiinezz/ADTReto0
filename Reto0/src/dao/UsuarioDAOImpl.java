package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import conexion.ConexionBD;
import excepciones.AccesoDatosException;
import modelo.Usuario;

/**
 * insertar(...) -> lógica de Inés (Registrar usuario), adaptada para
 * usar ConexionBD (Singleton) y propagar AccesoDatosException en vez
 * de tragarse el SQLException y devolver false.
 */
public class UsuarioDAOImpl implements UsuarioDAO {

    @Override
    public void insertar(Usuario usuario) throws AccesoDatosException {
        String sql = "INSERT INTO usuario (nombre, email, telefono, fechaAlta, ruta) VALUES (?, ?, ?, ?, ?)";
        Connection con = ConexionBD.getInstancia().getConexion();

        try (PreparedStatement ps = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, usuario.getNombre());
            ps.setString(2, usuario.getEmail());
            ps.setString(3, usuario.getTelefono());
            ps.setDate(4, java.sql.Date.valueOf(usuario.getFechaAlta()));
            ps.setString(5, usuario.getRuta());
            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    usuario.setId(rs.getInt(1));
                }
            }
        } catch (SQLException e) {
            throw new AccesoDatosException("Error al registrar el usuario " + usuario.getEmail(), e);
        }
    }

    @Override
    public Usuario buscarPorId(int id) throws AccesoDatosException {
        // TODO (Iker)
        return null;
    }
}
