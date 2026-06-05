package test;

import modelo.Tablero;
import excepcion.CasillaYaDescubiertaException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TableroTest {
	// Pruebas unitarias de JUnit (barra verde completada) para la Fase 4
    private Tablero tablero;

    @BeforeEach
    public void setUp() {
        // Garantiza un tablero limpio y nuevo antes de cada test
        tablero = new Tablero();
    }

    @Test
    public void testDimensionesTablero() {
        // Valida que cumpla el tamaño estricto de 10x10
        assertEquals(10, tablero.getFILAS(), "El tablero debe tener exactamente 10 filas");
        assertEquals(10, tablero.getCOLUMNAS(), "El tablero debe tener exactamente 10 columnas");
    }

    @Test
    public void testCantidadDeMinas() {
        int contadorMinas = 0;
        for (int i = 0; i < tablero.getFILAS(); i++) {
            for (int j = 0; j < tablero.getCOLUMNAS(); j++) {
                if (tablero.getCasillas()[i][j].isEsMina()) {
                    contadorMinas++;
                }
            }
        }
        // Valida la regla de negocio de colocar exactamente 10 minas
        assertEquals(10, contadorMinas, "El tablero inicializado debe contener exactamente 10 minas");
    }

    @Test
    public void testExcepcionCasillaDescubierta() {
        // Busca una coordenada segura para no detonar el fin del test antes de tiempo
        int f = 0;
        int c = 0;
        while (tablero.getCasillas()[f][c].isEsMina()) {
            c++;
        }

        try {
            // la primera vez: debe descubrirse con exito
            tablero.descubrirCasilla(f, c);
        } catch (CasillaYaDescubiertaException e) {
            fail("No debería lanzar excepción en la primera descubierta.");
        }
        final int filaFinal = f;
        final int colFinal = c;
        // la segunda vez: debe lanzar obligatoriamente la excepción personalizada
        assertThrows(CasillaYaDescubiertaException.class, () -> {
            tablero.descubrirCasilla(filaFinal, colFinal);
        }, "Debe lanzar CasillaDescubiertaException si se intenta abrir dos veces.");
    }
}