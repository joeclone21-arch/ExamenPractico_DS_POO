package servicio;

import modelo.Tablero;
import java.io.ObjectOutputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.FileInputStream;
import java.io.IOException;

public class ArchivoServicio {
    // nombre del archivo binario donde se guardará la partida congelada
    private static final String NOMBRE_ARCHIVO = "partida_buscaminas.dat";

   
     //Guarda el objeto Tablero completo en un archivo binario
     
    public void guardarPartida(Tablero tablero) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(NOMBRE_ARCHIVO))) {
            oos.writeObject(tablero);
        }
    }

    
    // Lee el archivo binario y reconstruye el objeto Tablero original
     
    public Tablero cargarPartida() throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(NOMBRE_ARCHIVO))) {
            return (Tablero) ois.readObject();
        }
    }
}