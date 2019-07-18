package co.com.ceiba.estacionamiento.joan.munoz.dominio.repositorio;

import java.util.List;

import co.com.ceiba.estacionamiento.joan.munoz.dominio.modelo.RegistroParqueo;

public interface RepositorioRegistroParqueo {

	public RegistroParqueo guardarRegistroParqueo(RegistroParqueo registroParqueo);

	public int cantidadVehiculosPorTipo(String tipo);
	
	public RegistroParqueo buscarVehiculoIngresado(String placa);
	
	public List<RegistroParqueo> darVehiculosIngresados();
}
