package co.com.ceiba.estacionamiento.joan.munoz.aplicacion.fabricas;

import co.com.ceiba.estacionamiento.joan.munoz.aplicacion.dtos.RegistroParqueoDTO;
import co.com.ceiba.estacionamiento.joan.munoz.dominio.modelo.RegistroParqueo;

public class FabricaRegistroParqueoDTO {

	public RegistroParqueo convertirDTODominio(RegistroParqueoDTO registroParqueoDTO) {
		return new RegistroParqueo(registroParqueoDTO.getId(), registroParqueoDTO.getFechaIngreso(),
				registroParqueoDTO.getFechaSalida(), registroParqueoDTO.getTipoVehiculo(),
				registroParqueoDTO.isMotoAltoCilindraje(), registroParqueoDTO.getPlaca(),
				registroParqueoDTO.getValor());
	}

	public RegistroParqueoDTO convertirDominioDTO(RegistroParqueo registroParqueo) {
		return new RegistroParqueoDTO(registroParqueo.getId(), registroParqueo.getFechaIngreso(),
				registroParqueo.getFechaSalida(), registroParqueo.getTipoVehiculo(),
				registroParqueo.isMotoAltoCilindraje(), registroParqueo.getPlaca(), registroParqueo.getValor());
	}
}
