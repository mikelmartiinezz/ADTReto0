package conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import excepciones.AccesoDatosException;

/**
 * Gestiona la conexión JDBC a gamedb como Singleton: toda la aplicación
 * comparte una única Connection en vez de abrir una nueva cada vez que
 * un DAO necesita hablar con la BD. Esto es lo que pide la rúbrica en
 * IL5.5 para el 10 ("Implementación del patrón de diseño Singleton
 * correcta").
 *
 * CAMBIA usuario/contraseña por los de tu MySQL antes de ejecutar.
 *
 * @author Ike.Bosquez
 */
public class ConexionBD {

    private static final String URL =
            "jdbc:mysql://localhost:3306/gamedb?useSSL=false&serverTimezone=UTC&useUnicode=true&characterEncoding=UTF-8";
    private static final String USUARIO = "root";
    private static final String PASSWORD = "abcd*1234";

    private static ConexionBD instancia;
    private Connection conexion;

    // Constructor privado: nadie puede hacer "new ConexionBD()" desde fuera,
    // solo se puede obtener la instancia a través de getInstancia().
    private ConexionBD() throws AccesoDatosException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conexion = DriverManager.getConnection(URL, USUARIO, PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            throw new AccesoDatosException("No se ha podido conectar a la base de datos gamedb", e);
        }
    }

    public static synchronized ConexionBD getInstancia() throws AccesoDatosException {
        if (instancia == null || instancia.conexionCerrada()) {
            instancia = new ConexionBD();
        }
        return instancia;
    }

    private boolean conexionCerrada() {
        try {
            return conexion == null || conexion.isClosed();
        } catch (SQLException e) {
            return true;
        }
    }

    public Connection getConexion() {
        return conexion;
    }

    public void cerrar() throws AccesoDatosException {
        try {
            if (conexion != null && !conexion.isClosed()) {
                conexion.close();
            }
        } catch (SQLException e) {
            throw new AccesoDatosException("Error al cerrar la conexión con la base de datos", e);
        }
    }
}

