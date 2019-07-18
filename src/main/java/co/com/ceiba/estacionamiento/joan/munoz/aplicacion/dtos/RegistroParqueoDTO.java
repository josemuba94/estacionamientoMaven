package co.com.ceiba.estacionamiento.joan.munoz.aplicacion.dtos;

import java.util.Calendar;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegistroParqueoDTO {
	
	private Long id;
	private Calendar fechaIngreso;
	private Calendar fechaSalida;
	private String tipoVehiculo;
	private boolean motoAltoCilindraje;
	private String placa;
	private double valor;
	
	public RegistroParqueoDTO() { // Requerido por el framework

	}

	public RegistroParqueoDTO(Long id, Calendar fechaIngreso, Calendar fechaSalida, String tipoVehiculo,
			boolean motoAltoCilindraje, String placa, double valor) {
		this.id = id;
		this.fechaIngreso = fechaIngreso;
		this.fechaSalida = fechaSalida;
		this.tipoVehiculo = tipoVehiculo;
		this.motoAltoCilindraje = motoAltoCilindraje;
		this.placa = placa;
		this.valor = valor;
	}
}
