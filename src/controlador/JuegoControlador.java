package controlador;

import modelo.Tablero;
import vista.VistaConsola;
import excepcion.CasillaYaDescubiertaException;
import servicio.ArchivoServicio; //  Importar el nuevo servicio
import java.util.Scanner;
import java.util.InputMismatchException;

public class JuegoControlador {
    private Tablero tablero; // Se reasigna el tablero al cargar
    private final VistaConsola vista;
    private final Scanner scanner;
    private final ArchivoServicio archivoServicio; // atributo para el manejo de archivos

    public JuegoControlador(Tablero tablero, VistaConsola vista) {
        this.tablero = tablero;
        this.vista = vista;
        this.scanner = new Scanner(System.in);
        this.archivoServicio = new ArchivoServicio(); // Inicializa el servicio
    }

    public void iniciarJuego() {
        boolean juegoTerminado = false;
        vista.mostrarMensaje("Juego Buscaminas Examen Practico POO");
        vista.mostrarMensaje("Comandos: 'A5' (revelar casilla), 'FA5' (bandera), 'SAVE' (guardar), 'LOAD' (cargar)");

        while (!juegoTerminado) {
            vista.mostrarTablero(tablero, false);
            System.out.print("Introduzca el movimiento: ");
            String entrada = scanner.nextLine().trim().toUpperCase();

            if (entrada.isEmpty()) continue;

            // --- PROCESA COMANDO DE GUARDAR ---
            if (entrada.equals("SAVE")) {
                try {
                    archivoServicio.guardarPartida(tablero);
                    vista.mostrarMensaje("La partida se ha guardado exitosamente");
                } catch (Exception e) {
                    vista.mostrarMensaje("Error al guardar la partida: " + e.getMessage());
                }
                continue; // regresa al inicio del bucle para pedir otra jugada
            }

            // --- PROCESA COMANDO DE CARGAR ---
            if (entrada.equals("LOAD")) {
                try {
                    this.tablero = archivoServicio.cargarPartida();
                    vista.mostrarMensaje("-> Partida recuperada con exito. Siga jugando");
                } catch (Exception e) {
                    vista.mostrarMensaje("Error al cargar la partida: No hay datos guardados o archivo corrupto.");
                }
                continue; // regresa al inicio del bucle para mostrar el tablero cargado
            }

            // --- EL RESTO DE LA LOGICA DE MOVIMIENTOS SE MANTIENE IGUAL ---
            try {
                boolean colocarBandera = false;
                String coordenada = entrada;

                if (entrada.startsWith("F") && entrada.length() > 1) {
                    colocarBandera = true;
                    coordenada = entrada.substring(1);
                }

                if (!coordenada.matches("^[A-J]([1-9]|10)$")) {
                    throw new InputMismatchException("Formato invalido. Use una letra entre A-J y un numero entre 1-10 por favor");
                }

                int fila = coordenada.charAt(0) - 'A';
                int col = Integer.parseInt(coordenada.substring(1)) - 1;

                if (colocarBandera) {
                    tablero.alternarBandera(fila, col);
                } else {
                    boolean pisedMina = tablero.descubrirCasilla(fila, col);

                    if (pisedMina) {
                        vista.mostrarTablero(tablero, true);
                        vista.mostrarMensaje("!!BOOM!! Derrota, has tocado una mina. Fin de la partida");
                        juegoTerminado = true;
                    } else if (tablero.verificarVictoria()) {
                        vista.mostrarTablero(tablero, true);
                        vista.mostrarMensaje("Felicidades!!! Has limpiado el tablero. Ganaste!!!");
                        juegoTerminado = true;
                    }
                }

            } catch (InputMismatchException e) {
                vista.mostrarMensaje("Error de entrada: " + e.getMessage());
            } catch (ArrayIndexOutOfBoundsException e) {
                vista.mostrarMensaje("Error: Coordenadas fuera de los limites.");
            } catch (CasillaYaDescubiertaException e) {
                vista.mostrarMensaje("Aviso: " + e.getMessage());
            } catch (Exception e) {
                vista.mostrarMensaje("Ocurrio un error inesperado: " + e.getMessage());
            }
        }
    }
}