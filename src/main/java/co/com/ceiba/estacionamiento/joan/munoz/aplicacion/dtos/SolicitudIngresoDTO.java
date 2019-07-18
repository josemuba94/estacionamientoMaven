package co.com.ceiba.estacionamiento.joan.munoz.aplicacion.dtos;

import java.util.Calendar;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SolicitudIngresoDTO {
	
	private String tipoVehiculo;
	private String placa;
	private boolean motoAltoCilindraje;
	private Calendar fecha;
	
	public SolicitudIngresoDTO() { // Requerido por el framework
		
	}
}
