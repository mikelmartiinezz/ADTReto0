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
import modelo.Compra;

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
                        registrarJuego(servicio);
                        break;
                    case 4:
                        comprarJuego(servicio);
                        break;
                    case 5:
                        consultarJuegosDisponibles(servicio);
                        break;
                    case 6:
                        consultarJuegosDesarrollador(servicio);
                        break;
                    case 7:
                        verHistorialCompras(servicio);
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
    
     private static void registrarJuego(TiendaService servicio) throws AccesoDatosException {
        System.out.println("\nREGISTRAR JUEGO");
        int id;
        do {
            id = Util.leerInt("ID del juego: ");
            if (id <= 0) {
                System.out.println("El ID debe ser mayor que 0.");
            }
        } while (id <= 0);
        String titulo;
        do {
            titulo = Util.introducirCadena("Título del juego: ");
            if (titulo.trim().isEmpty()) {
                System.out.println("El título no puede estar vacío.");
            }
        } while (titulo.isEmpty());
        double precio;
        do {
            precio = Util.leerDouble("Precio: ");
            if (precio <= 0) {
                System.out.println("El precio debe ser mayor que 0.");
            }
        } while (precio <= 0);
        int stock;
        do {
            stock = Util.leerInt("Stock: ");
            if (stock < 0) {
                System.out.println("El stock no puede ser negativo.");
            }
        } while (stock < 0);
        Genero genero = null;
        do {
            System.out.println("\nGéneros disponibles:");
            System.out.println("1. ACCION");
            System.out.println("2. DEPORTES");
            System.out.println("3. RPG");
            int opcionGenero = Util.leerInt("Elige género: ");
            switch (opcionGenero) {
                case 1 -> genero = Genero.ACCION;
                case 2 -> genero = Genero.DEPORTES;
                case 3 -> genero = Genero.RPG;
                default -> System.out.println("Género no válido.");
            }
        } while (genero == null);
        int idDesarrollador;
        do {
            idDesarrollador = Util.leerInt("ID del desarrollador: ");
            if (idDesarrollador <= 0) {
                System.out.println("El ID del desarrollador debe ser mayor que 0.");
            } else {
                try {
                    if (servicio.consultarJuegosDesarrollador(idDesarrollador) == null) {
                        System.out.println("El desarrollador no existe.");
                    }
                } catch (AccesoDatosException e) {
                    System.out.println("Error al comprobar el desarrollador.");
                }
            }
        } while (idDesarrollador <= 0);
        Juego juego = new Juego(id, titulo, precio, stock, genero, idDesarrollador);
        servicio.registrarJuego(juego);
        System.out.println("Juego registrado.");
    }
     
     private static void comprarJuego(TiendaService servicio) throws AccesoDatosException {
        System.out.println("\nCOMPRAR JUEGO");
        int idUsuario;
        do {
            idUsuario = Util.leerInt("ID del usuario: ");
            if (idUsuario <= 0) {
                System.out.println("El ID del usuario debe ser mayor que 0.");
            }
        } while (idUsuario <= 0);
        int idJuego;
        do {
            idJuego = Util.leerInt("ID del juego: ");
            if (idJuego <= 0) {
                System.out.println("El ID del juego debe ser mayor que 0.");
            }
        } while (idJuego <= 0);
        int cantidad;
        do {
            cantidad = Util.leerInt("Cantidad a comprar: ");
            if (cantidad <= 0) {
                System.out.println("La cantidad debe ser mayor que 0.");
            }
        } while (cantidad <= 0);
        servicio.comprarJuego(idUsuario, idJuego, cantidad);
        System.out.println("Compra realizada.");
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
    
    private static void verHistorialCompras(TiendaService servicio)
        throws AccesoDatosException {

    int idUsuario = Util.leerInt("ID del usuario: ");

    List<Compra> compras = servicio.verHistorialCompras(idUsuario);

    System.out.println();
    System.out.println("===== HISTORIAL DE COMPRAS =====");

    if (compras.isEmpty()) {

        System.out.println("El usuario no tiene compras.");

    } else {

        for (Compra compra : compras) {

            System.out.println(
                    "ID Compra: " + compra.getId()
                    + " | Fecha: " + compra.getFecha()
                    + " | Cantidad: " + compra.getCantidad()
                    + " | Usuario: " + compra.getUsuarioId()
                    + " | Juego: " + compra.getJuegoId()
            );
        }
    }
}
    
}
