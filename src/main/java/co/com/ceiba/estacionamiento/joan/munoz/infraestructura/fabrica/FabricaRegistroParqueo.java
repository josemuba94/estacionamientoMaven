package co.com.ceiba.estacionamiento.joan.munoz.infraestructura.fabrica;

import java.util.ArrayList;
import java.util.List;

import co.com.ceiba.estacionamiento.joan.munoz.dominio.modelo.RegistroParqueo;
import co.com.ceiba.estacionamiento.joan.munoz.infraestructura.entidades.RegistroParqueoEntity;

public class FabricaRegistroParqueo {

	public RegistroParqueoEntity convertiraDominioEntidad(RegistroParqueo registroParqueo) {
		RegistroParqueoEntity registroParqueoEntity = new RegistroParqueoEntity();
		registroParqueoEntity.setId(registroParqueo.getId());
		registroParqueoEntity.setFechaIngreso(registroParqueo.getFechaIngreso());
		registroParqueoEntity.setFechaSalida(registroParqueo.getFechaSalida());
		registroParqueoEntity.setTipoVehiculo(registroParqueo.getTipoVehiculo());
		registroParqueoEntity.setMotoAltoCilindraje(registroParqueo.isMotoAltoCilindraje());
		registroParqueoEntity.setPlaca(registroParqueo.getPlaca());
		registroParqueoEntity.setValor(registroParqueo.getValor());

		return registroParqueoEntity;
	}

	public RegistroParqueo convertirEntidadDominio(RegistroParqueoEntity registroParqueoEntity) {		
		return new RegistroParqueo(registroParqueoEntity.getId(), registroParqueoEntity.getFechaIngreso(),
				registroParqueoEntity.getFechaSalida(), registroParqueoEntity.getTipoVehiculo(),
				registroParqueoEntity.isMotoAltoCilindraje(), registroParqueoEntity.getPlaca(),
				registroParqueoEntity.getValor());
	}

	public List <RegistroParqueo> convertirListEntityListDominio(List<RegistroParqueoEntity> listadoVehiculosEntity) {
		List <RegistroParqueo> listadoVehiculos = new ArrayList<>();		
		listadoVehiculosEntity.forEach(vehiculoEntity -> listadoVehiculos.add(convertirEntidadDominio(vehiculoEntity)));
		
		return listadoVehiculos;
	}

}
