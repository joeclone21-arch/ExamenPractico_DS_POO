package principal;

import modelo.Tablero;
import vista.VistaConsola;
import controlador.JuegoControlador;

public class Main {
    public static void main(String[] args) {
        // 1. Inicializa el Modelo, es decir se crean las casillas y se ocultan las 10 minas
        Tablero modelo = new Tablero();
        
        // 2. Inicializa la Vista, es decir se imprime en la terminal
        VistaConsola vista = new VistaConsola();
        
        // 3. Inicializa el Controlador conectando el modelo con la vista
        JuegoControlador controlador = new JuegoControlador(modelo, vista);
        
        // 4. Arranca el bucle principal del juego Buscaminas
        controlador.iniciarJuego();
    }
}