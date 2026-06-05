package modelo;

import excepcion.CasillaYaDescubiertaException;
import java.util.Random;
import java.io.Serializable; // se agrego para implentar interfaz serializable

public class Tablero implements Serializable {
    private static final long serialVersionUID = 1L;
    private final int FILAS = 10; // filas y columnas 10x10
    private final int COLUMNAS = 10;
    private final int TOTAL_MINAS = 10; 
    private Casilla[][] casillas;

    public Tablero() {
        inicializarTablero();
        colocarMinas();
        calcularMinasAdyacentes();
    }

    private void inicializarTablero() {
        casillas = new Casilla[FILAS][COLUMNAS];
        for (int i = 0; i < FILAS; i++) {
            for (int j = 0; j < COLUMNAS; j++) {
                casillas[i][j] = new Casilla();
            }
        }
    }

    private void colocarMinas() {
        Random rand = new Random();
        int minasColocadas = 0;
        while (minasColocadas < TOTAL_MINAS) {
            int f = rand.nextInt(FILAS);
            int c = rand.nextInt(COLUMNAS);
            if (!casillas[f][c].isEsMina()) {
                casillas[f][c].setEsMina(true);
                minasColocadas++;
            }
        }
    }

    private void calcularMinasAdyacentes() {
        for (int i = 0; i < FILAS; i++) {
            for (int j = 0; j < COLUMNAS; j++) {
                if (!casillas[i][j].isEsMina()) {
                    casillas[i][j].setMinasAlrededor(contarMinasVecinas(i, j));
                }
            }
        }
    }

    private int contarMinasVecinas(int fila, int col) {
        int contador = 0;
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                int nf = fila + i;
                int nc = col + j;
                if (nf >= 0 && nf < FILAS && nc >= 0 && nc < COLUMNAS) {
                    if (casillas[nf][nc].isEsMina()) {
                        contador++;
                    }
                }
            }
        }
        return contador;
    }

    // Se usa el algoritmo Recursivo Flood Fill para descubrir casillas vacias
    public boolean descubrirCasilla(int fila, int col) throws CasillaYaDescubiertaException {
        if (fila < 0 || fila >= FILAS || col < 0 || col >= COLUMNAS) {
            throw new ArrayIndexOutOfBoundsException("Coordenadas fuera del tablero.");
        }

        Casilla casilla = casillas[fila][col];

        if (casilla.isEstaRevelada()) {
            throw new CasillaYaDescubiertaException("La casilla ya se encuentra descubierta.");
        }

        casilla.setEstaRevelada(true);

        // si es una mina, retorna true (indica derrota)
        if (casilla.isEsMina()) {
            return true;
        }

        // si no tiene minas alrededor, expande recursivamente
        if (casilla.getMinasAlrededor() == 0) {
            for (int i = -1; i <= 1; i++) {
                for (int j = -1; j <= 1; j++) {
                    int nf = fila + i;
                    int nc = col + j;
                    if (nf >= 0 && nf < FILAS && nc >= 0 && nc < COLUMNAS) {
                        if (!casillas[nf][nc].isEstaRevelada() && !casillas[nf][nc].isEsMina()) {
                            try {
                                descubrirCasilla(nf, nc);
                            } catch (CasillaYaDescubiertaException ignored) {}
                        }
                    }
                }
            }
        }
        return false;
    }

    public void alternarBandera(int fila, int col) {
        if (!casillas[fila][col].isEstaRevelada()) {
            boolean estadoActual = casillas[fila][col].isTieneBandera();
            casillas[fila][col].setTieneBandera(!estadoActual);
        }
    }

    public boolean verificarVictoria() {
        for (int i = 0; i < FILAS; i++) {
            for (int j = 0; j < COLUMNAS; j++) {
                // si hay una casilla sin mina que no ha sido descubierta no se ha ganado aún
                if (!casillas[i][j].isEsMina() && !casillas[i][j].isEstaRevelada()) {
                    return false;
                }
            }
        }
        return true;
    }

    // Getters necesarios para la Vista
    public int getFILAS() { return FILAS; }
    public int getCOLUMNAS() { return COLUMNAS; }
    public Casilla[][] getCasillas() { return casillas; }
}