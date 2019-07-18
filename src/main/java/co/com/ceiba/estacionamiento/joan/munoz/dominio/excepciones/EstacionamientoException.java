package co.com.ceiba.estacionamiento.joan.munoz.dominio.excepciones;

public class EstacionamientoException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public EstacionamientoException(String mensaje) {
		super(mensaje);
	}
}
