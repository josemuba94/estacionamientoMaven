package co.com.ceiba.estacionamiento.joan.munoz.dominio.modelo;

import java.util.Calendar;

import lombok.Getter;
import lombok.Setter;

@Getter
public class RegistroParqueo {

	private Long id;
	private Calendar fechaIngreso;
	@Setter private Calendar fechaSalida;
	private String tipoVehiculo;
	private boolean motoAltoCilindraje;
	private String placa;
	@Setter private double valor;

	public RegistroParqueo(Long id, Calendar fechaIngreso, Calendar fechaSalida, String tipoVehiculo,
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
