package modelo;
import java.io.Serializable;  // se agrego para implementar interfaz serializable

public class Casilla implements Serializable {
    private static final long serialVersionUID = 1L;
    private boolean esMina;
    private boolean estaRevelada;
    private boolean tieneBandera;
    private int minasAlrededor;

    public Casilla() {
        this.esMina = false;
        this.estaRevelada = false;
        this.tieneBandera = false;
        this.minasAlrededor = 0;
    }

    // Getters y Setters (Encapsulamiento)
    public boolean isEsMina() { return esMina; }
    public void setEsMina(boolean esMina) { this.esMina = esMina; }

    public boolean isEstaRevelada() { return estaRevelada; }
    public void setEstaRevelada(boolean estaRevelada) { this.estaRevelada = estaRevelada; }

    public boolean isTieneBandera() { return tieneBandera; }
    public void setTieneBandera(boolean tieneBandera) { this.tieneBandera = tieneBandera; }

    public int getMinasAlrededor() { return minasAlrededor; }
    public void setMinasAlrededor(int minasAlrededor) { this.minasAlrededor = minasAlrededor; }
}