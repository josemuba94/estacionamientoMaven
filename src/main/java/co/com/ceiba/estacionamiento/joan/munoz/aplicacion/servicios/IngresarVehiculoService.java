package co.com.ceiba.estacionamiento.joan.munoz.aplicacion.servicios;

import co.com.ceiba.estacionamiento.joan.munoz.aplicacion.dtos.RegistroParqueoDTO;
import co.com.ceiba.estacionamiento.joan.munoz.aplicacion.dtos.SolicitudIngresoDTO;

public interface IngresarVehiculoService {

	public RegistroParqueoDTO ingresarVehiculo(SolicitudIngresoDTO solicitudIngresoDTO);
}
