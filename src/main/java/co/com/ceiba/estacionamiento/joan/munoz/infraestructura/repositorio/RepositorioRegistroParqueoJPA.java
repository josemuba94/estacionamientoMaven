package co.com.ceiba.estacionamiento.joan.munoz.infraestructura.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import co.com.ceiba.estacionamiento.joan.munoz.infraestructura.entidades.RegistroParqueoEntity;

@Repository
public interface RepositorioRegistroParqueoJPA extends JpaRepository<RegistroParqueoEntity, Long> {

	@Query(value = "select count(r.tipoVehiculo) FROM RegistroParqueoEntity r where r.fechaSalida is null and r.tipoVehiculo=?1")
	public int cantidadVehiculosPorTipo(String tipo);

	@Query(value = "select r FROM RegistroParqueoEntity r where r.fechaSalida is null and r.placa=?1")
	public RegistroParqueoEntity buscarVehiculoIngresado(String placa);

	@Query(value = "select r FROM RegistroParqueoEntity r where r.fechaSalida is null")
	public List<RegistroParqueoEntity> darVehiculosIngresados();
}