package vista;

import modelo.Casilla;
import modelo.Tablero;

public class VistaConsola {

    public void mostrarTablero(Tablero tablero, boolean mostrarMinas) {
        // imprime la cabecera de columnas: 1 2 3 al 10
        System.out.print("    ");
        for (int c = 1; c <= tablero.getCOLUMNAS(); c++) {
        	//System.out.print("\n");
        	System.out.print(c + " ");
        }
        System.out.println("\n-------------------------");

        Casilla[][] casillas = tablero.getCasillas();
        
        for (int i = 0; i < tablero.getFILAS(); i++) {
            // Convierte el indice de la fila en letra (0 -> A, 1 -> B etc)
            char letraFila = (char) ('A' + i);
            System.out.print(letraFila + " | ");

            for (int j = 0; j < tablero.getCOLUMNAS(); j++) {
                Casilla c = casillas[i][j];

                if (c.isEstaRevelada()) {
                    if (c.isEsMina()) {
                        System.out.print("X "); // Marca X si es una mina explotada
                    } else if (c.getMinasAlrededor() == 0) {
                        System.out.print("V "); // Marca V para un espacio vacío sin peligro
                    } else {
                        System.out.print(c.getMinasAlrededor() + " "); // Numero de minas adyacentes 
                    }
                } else if (c.isTieneBandera()) {
                    System.out.print("F "); // Marca F para casilla marcada con bandera
                } else {
                    if (mostrarMinas && c.isEsMina()) {
                        System.out.print("* "); // Para cuando se pierde, muestra donde estaban las otras minas
                    } else {
                        System.out.print(". "); // Casilla cubierta mediante .
                    }
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    public void mostrarMensaje(String mensaje) {
        System.out.println(mensaje);
    }
}