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
import modelo.Compra;
import modelo.Desarrollador;
import modelo.Juego;
import modelo.Usuario;

/**
 * Capa de lógica de negocio. La capa de IU solo habla con esta clase,
 * nunca con los DAO directamente.
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

    public void registrarUsuario(Usuario usuario) throws AccesoDatosException {
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

}