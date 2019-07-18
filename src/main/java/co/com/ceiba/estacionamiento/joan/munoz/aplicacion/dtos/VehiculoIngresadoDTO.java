package co.com.ceiba.estacionamiento.joan.munoz.aplicacion.dtos;

import java.util.Calendar;

import lombok.Getter;

@Getter
public class VehiculoIngresadoDTO {

	private String placa;
	private String tipoVehiculo;
	private Calendar fechaIngreso;

	public VehiculoIngresadoDTO(String placa, String tipoVehiculo, Calendar fechaIngreso) {
		this.placa = placa;
		this.tipoVehiculo = tipoVehiculo;
		this.fechaIngreso = fechaIngreso;
	}
}
