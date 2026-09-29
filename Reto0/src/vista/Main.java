package vista;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import controlador.TiendaService;
import excepciones.AccesoDatosException;
import modelo.Desarrollador;
import modelo.Genero;
import modelo.Juego;
import modelo.Usuario;
import util.Util;

public class Main {

    // OJO: debe coincidir con la constante RUTA_FICHERO de JuegoDAOImpl
    // (ambas apuntan al mismo juegos.dat). Si Iker cambia esa ruta allí,
    // hay que cambiarla también aquí.
    private static final String RUTA_FICHERO_JUEGOS = "juegos.dat";

    public static void main(String[] args) {
        precargarJuegosSiHaceFalta();

        TiendaService servicio = new TiendaService();
        int opcion;

        do {
            mostrarMenu();
            opcion = Util.leerInt("Opción: ");

            try {
                switch (opcion) {
                    case 1:
                        registrarUsuario(servicio);
                        break;
                    case 2:
                        registrarDesarrollador(servicio);
                        break;
                    case 3:
                        // TODO (Iker)
                        break;
                    case 4:
                        // TODO (Iker)
                        break;
                    case 5:
                        consultarJuegosDisponibles(servicio);
                        break;
                    case 6:
                        consultarJuegosDesarrollador(servicio);
                        break;
                    case 7:
                        // TODO (Anurag)
                        break;
                    case 0:
                        System.out.println("Saliendo...");
                        break;
                    default:
                        System.out.println("Opción no válida.");
                }
            } catch (AccesoDatosException e) {
                System.out.println("Ha ocurrido un error: " + e.getMessage());
            }

        } while (opcion != 0);
    }

    /**
     * Se ejecuta una sola vez, la primera vez que alguien arranca el
     * proyecto (cuando juegos.dat todavía no existe). Crea 5 juegos de
     * prueba cuyos ids (1-5) coinciden con los juego_id que ya usan las
     * compras de ejemplo de gamedb.sql, y cuyos idDesarrollador (1-5)
     * coinciden con los desarrolladores precargados en la BD. Así el
     * historial de compras con avatar (usuario.ruta) siempre tiene
     * datos coherentes con los que ve, sin depender de que alguien se
     * acuerde de ejecutar nada aparte.
     *
     * Si el fichero ya existe (porque alguien ya jugó con la app, o
     * porque Iker ya registró juegos de verdad con su
     * JuegoDAOImpl.insertar), no se toca: no se sobrescriben datos
     * reales.
     */
    private static void precargarJuegosSiHaceFalta() {
        File fichero = new File(RUTA_FICHERO_JUEGOS);
        if (fichero.exists()) {
            return;
        }

        List<Juego> juegos = new ArrayList<>();
        juegos.add(new Juego(1, "Super Mario Odyssey", 59.99, 10, Genero.ACCION, 1));      // Nintendo
        juegos.add(new Juego(2, "FIFA 24", 69.99, 15, Genero.DEPORTES, 2));                // Electronic Arts
        juegos.add(new Juego(3, "Assassin's Creed Valhalla", 49.99, 8, Genero.ACCION, 3)); // Ubisoft
        juegos.add(new Juego(4, "Grand Theft Auto V", 29.99, 20, Genero.ACCION, 4));       // Rockstar Games
        juegos.add(new Juego(5, "Cyberpunk 2077", 39.99, 5, Genero.RPG, 5));               // CD Projekt Red

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fichero))) {
            oos.writeObject(juegos);
        } catch (IOException e) {
            System.out.println("No se ha podido crear la precarga de juegos.dat: " + e.getMessage());
        }
    }

    private static void mostrarMenu() {
        System.out.println();
        System.out.println("===== TIENDA DE VIDEOJUEGOS =====");
        System.out.println("1. Registrar usuario");
        System.out.println("2. Registrar desarrollador");
        System.out.println("3. Registrar juego");
        System.out.println("4. Comprar juego");
        System.out.println("5. Consultar juegos disponibles");
        System.out.println("6. Consultar juegos de un desarrollador");
        System.out.println("7. Ver historial de compras de un usuario");
        System.out.println("0. Salir");
    }

    private static void registrarUsuario(TiendaService servicio) throws AccesoDatosException {
        String nombre = Util.introducirCadena("Nombre: ");
        String email = Util.introducirCadena("Email: ");
        String telefono = Util.introducirCadena("Teléfono: ");

        // ruta se deja a null: solo la tienen los usuarios precargados en la BD
        Usuario usuario = new Usuario(0, nombre, email, telefono, LocalDate.now(), null);
        servicio.registrarUsuario(usuario);
        System.out.println("Usuario registrado con id " + usuario.getId());
    }

    private static void registrarDesarrollador(TiendaService servicio) throws AccesoDatosException {
        String nombre = Util.introducirCadena("Nombre: ");
        String pais = Util.introducirCadena("País: ");
        int anio = Util.leerInt("Año de fundación: ");

        Desarrollador desarrollador = new Desarrollador(0, nombre, pais, anio);
        servicio.registrarDesarrollador(desarrollador);
        System.out.println("Desarrollador registrado con id " + desarrollador.getId());
    }

    private static void consultarJuegosDisponibles(TiendaService servicio) throws AccesoDatosException {
        imprimirJuegos(servicio.consultarJuegosDisponibles());
    }

    private static void consultarJuegosDesarrollador(TiendaService servicio) throws AccesoDatosException {
        int idDesarrollador = Util.leerInt("Id del desarrollador: ");
        imprimirJuegos(servicio.consultarJuegosDesarrollador(idDesarrollador));
    }

    private static void imprimirJuegos(List<Juego> juegos) {
        if (juegos.isEmpty()) {
            System.out.println("No hay juegos que mostrar.");
            return;
        }
        for (Juego j : juegos) {
System.out.println("[" + j.getId() + "] " + j.getTitulo() + " - " + j.getPrecio() + "€ - stock: " + j.getStock() +
        " - "+ j.getGenero() + " (dev id " + j.getIdDesarrollador() + ")");
        }
    }
}
