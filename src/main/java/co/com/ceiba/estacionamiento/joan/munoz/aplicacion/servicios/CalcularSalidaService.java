package co.com.ceiba.estacionamiento.joan.munoz.aplicacion.servicios;

import co.com.ceiba.estacionamiento.joan.munoz.aplicacion.dtos.RegistroParqueoDTO;

public interface CalcularSalidaService {
	
	public RegistroParqueoDTO calcularSalida(String placa);
}
