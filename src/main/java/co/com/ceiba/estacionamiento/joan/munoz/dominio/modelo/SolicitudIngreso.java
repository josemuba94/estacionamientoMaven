package co.com.ceiba.estacionamiento.joan.munoz.dominio.modelo;

import java.util.Calendar;

import co.com.ceiba.estacionamiento.joan.munoz.dominio.excepciones.EstacionamientoException;
import lombok.Getter;

@Getter
public class SolicitudIngreso {

	public static final String TIPO_INVALIDO = "El estacionamiento solo acepta motos y carros.";
	public static final String DATOS_INCOMPLETOS = "Se deben ingresar todos los campos requeridos.";
	public static final String FECHA_INVALIDA = "La fecha de solicitud de ingreso es mayor a la fecha actual.";
	
	private String tipoVehiculo;
	private String placa;
	private boolean motoAltoCilindraje;
	private Calendar fecha;

	public SolicitudIngreso(String tipoVehiculo, String placa, boolean motoAltoCilindraje, Calendar fecha) {
		validarDatosIngresados(tipoVehiculo, placa, fecha);
		this.tipoVehiculo = tipoVehiculo;
		this.placa = placa;
		this.motoAltoCilindraje = motoAltoCilindraje;
		this.fecha = fecha;
	}

	public void validarDatosIngresados(String tipoVehiculo, String placa, Calendar fecha) {
		if(placa == null || tipoVehiculo == null || fecha == null )
			throw new EstacionamientoException(DATOS_INCOMPLETOS);
			
		if(fecha.getTimeInMillis() > Calendar.getInstance().getTimeInMillis())
			throw new EstacionamientoException(FECHA_INVALIDA);
		
		try {
			TipoVehiculoEnum.valueOf(tipoVehiculo);			
		} catch (IllegalArgumentException exception) {
			throw new EstacionamientoException(TIPO_INVALIDO);
		}	
	}
}
