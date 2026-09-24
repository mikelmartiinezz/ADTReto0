package vista;

import controlador.TiendaService;
import excepciones.AccesoDatosException;
import util.Util;

public class Main {

    public static void main(String[] args) {
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

    }

    private static void registrarDesarrollador(TiendaService servicio) throws AccesoDatosException {

    }

    private static void registrarJuego(TiendaService servicio) throws AccesoDatosException {

    }

    private static void comprarJuego(TiendaService servicio) throws AccesoDatosException {

    }

    private static void consultarJuegosDisponibles(TiendaService servicio) throws AccesoDatosException {

    }

    private static void consultarJuegosDesarrollador(TiendaService servicio) throws AccesoDatosException {

    }

    private static void verHistorialCompras(TiendaService servicio) throws AccesoDatosException {

    }

}
