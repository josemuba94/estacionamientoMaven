package co.com.ceiba.estacionamiento.joan.munoz.infraestructura.repositorio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import co.com.ceiba.estacionamiento.joan.munoz.dominio.modelo.RegistroParqueo;
import co.com.ceiba.estacionamiento.joan.munoz.dominio.repositorio.RepositorioRegistroParqueo;
import co.com.ceiba.estacionamiento.joan.munoz.infraestructura.entidades.RegistroParqueoEntity;
import co.com.ceiba.estacionamiento.joan.munoz.infraestructura.fabrica.FabricaRegistroParqueo;

@Repository
public class RepositorioRegistroH2 implements RepositorioRegistroParqueo {
	
	@Autowired
	private RepositorioRegistroParqueoJPA repositorioRegistroParqueoJPA;
	private FabricaRegistroParqueo fabricaRegistroParqueo;	
	
	public RepositorioRegistroH2() {
		fabricaRegistroParqueo = new FabricaRegistroParqueo();
	}

	@Override
	public RegistroParqueo guardarRegistroParqueo(RegistroParqueo registroParqueo) {
		return fabricaRegistroParqueo.convertirEntidadDominio(repositorioRegistroParqueoJPA
				.saveAndFlush(fabricaRegistroParqueo.convertiraDominioEntidad(registroParqueo)));
	}

	@Override
	public int cantidadVehiculosPorTipo(String tipo) {
		return repositorioRegistroParqueoJPA.cantidadVehiculosPorTipo(tipo);
	}

	@Override
	public RegistroParqueo buscarVehiculoIngresado(String placa) {
		RegistroParqueoEntity registroParqueoEntity = repositorioRegistroParqueoJPA.buscarVehiculoIngresado(placa);		
		return (registroParqueoEntity == null) ? null : fabricaRegistroParqueo.convertirEntidadDominio(registroParqueoEntity);
	}

	@Override
	public List<RegistroParqueo> darVehiculosIngresados() {
		List<RegistroParqueoEntity> listadoVehiculosEntity = repositorioRegistroParqueoJPA.darVehiculosIngresados();
		return fabricaRegistroParqueo.convertirListEntityListDominio(listadoVehiculosEntity);
	}

}
