package excepcion;

/**
 * Excepcion personalizada para controlar si una casilla ya fue revelada
 */
public class CasillaYaDescubiertaException extends Exception {
   
	private static final long serialVersionUID = 1L;

	public CasillaYaDescubiertaException(String mensaje) {
		// TODO Auto-generated constructor stub
		super(mensaje);
	}
}