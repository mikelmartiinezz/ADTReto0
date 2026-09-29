package controlador;

import java.time.LocalDate;
import java.util.List;

import dao.CompraDAO;
import dao.CompraDAOImpl;
import dao.DesarrolladorDAO;
import dao.DesarrolladorDAOImpl;
import dao.JuegoDAO;
import dao.JuegoDAOImpl;
import dao.UsuarioDAO;
import dao.UsuarioDAOImpl;
import excepciones.AccesoDatosException;
import excepciones.EmailInvalidoException;
import excepciones.TelefonoInvalidoException;
import modelo.Compra;
import modelo.Desarrollador;
import modelo.Juego;
import modelo.Usuario;

/**
 * Capa de lógica de negocio. La capa de IU solo habla con esta clase, nunca con
 * los DAO directamente.
 */
public class TiendaService {

    private final UsuarioDAO usuarioDAO;
    private final DesarrolladorDAO desarrolladorDAO;
    private final JuegoDAO juegoDAO;
    private final CompraDAO compraDAO;

    public TiendaService() {
        this.usuarioDAO = new UsuarioDAOImpl();
        this.desarrolladorDAO = new DesarrolladorDAOImpl();
        this.juegoDAO = new JuegoDAOImpl();
        this.compraDAO = new CompraDAOImpl();
    }

    public void registrarUsuario(Usuario usuario) throws AccesoDatosException, EmailInvalidoException, TelefonoInvalidoException {
        String email = usuario.getEmail();
        if (email == null || !email.matches("^[^\\s@]+@[^\\s@]+\\.[^\\s@]+$")) {
            throw new EmailInvalidoException("El email no es válido. Debe tener un formato como usuario@dominio.com.");
        }
        String telefono = usuario.getTelefono();
        if (telefono == null || !telefono.matches("\\d{9}")) {
            throw new TelefonoInvalidoException("El teléfono no es válido. Debe tener exactamente 9 dígitos.");
        }
        usuarioDAO.insertar(usuario);
    }

    public void registrarDesarrollador(Desarrollador desarrollador) throws AccesoDatosException {
        desarrolladorDAO.insertar(desarrollador);
    }

    public void registrarJuego(Juego juego) throws AccesoDatosException {
        if (desarrolladorDAO.buscarPorId(juego.getIdDesarrollador()) == null) {
            throw new AccesoDatosException("No existe el desarrollador con id " + juego.getIdDesarrollador());
        }
        juegoDAO.insertar(juego);
    }

    public void comprarJuego(int idUsuario, int idJuego, int cantidad) throws AccesoDatosException {
        if (usuarioDAO.buscarPorId(idUsuario) == null) {
            throw new AccesoDatosException("No existe el usuario con id " + idUsuario);
        }

        Juego juego = juegoDAO.buscarPorId(idJuego);
        if (juego == null) {
            throw new AccesoDatosException("No existe el juego con id " + idJuego);
        }
        if (juego.getStock() < cantidad) {
            throw new AccesoDatosException("No hay stock suficiente del juego " + juego.getTitulo());
        }

        juego.setStock(juego.getStock() - cantidad);
        juegoDAO.actualizar(juego);

        Compra compra = new Compra();
        compra.setFecha(LocalDate.now());
        compra.setCantidad(cantidad);
        compra.setUsuarioId(idUsuario);
        compra.setJuegoId(idJuego);
        compraDAO.insertar(compra);
    }

    public List<Juego> consultarJuegosDisponibles() throws AccesoDatosException {
        return juegoDAO.listarDisponibles();
    }

    public List<Juego> consultarJuegosDesarrollador(int idDesarrollador) throws AccesoDatosException {
        return juegoDAO.listarPorDesarrollador(idDesarrollador);
    }

    public List<Compra> verHistorialCompras(int idUsuario)
            throws AccesoDatosException {

        return compraDAO.listarPorUsuario(idUsuario);
    }

    public Usuario buscarUsuario(int id) throws AccesoDatosException {
        return usuarioDAO.buscarPorId(id);
    }
}
