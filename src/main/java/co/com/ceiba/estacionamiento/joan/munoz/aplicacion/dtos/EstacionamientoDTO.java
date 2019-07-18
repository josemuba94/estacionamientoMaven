package co.com.ceiba.estacionamiento.joan.munoz.aplicacion.dtos;

import java.util.ArrayList;
import java.util.List;

import co.com.ceiba.estacionamiento.joan.munoz.dominio.modelo.RegistroParqueo;
import lombok.Getter;

@Getter
public class EstacionamientoDTO {

	private List<String> tiposVehiculo;
	private List<VehiculoIngresadoDTO> vehiculosIngresados;

	public EstacionamientoDTO(List<String> tiposVehiculo, List<RegistroParqueo> vehiculosIngresados) {
		this.tiposVehiculo = tiposVehiculo;
		this.vehiculosIngresados = new ArrayList<>();
		vehiculosIngresados.forEach(
				vehiculoIngresado -> this.vehiculosIngresados.add(new VehiculoIngresadoDTO(vehiculoIngresado.getPlaca(),
						vehiculoIngresado.getTipoVehiculo(), vehiculoIngresado.getFechaIngreso())));
	}
}
