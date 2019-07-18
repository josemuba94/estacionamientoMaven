package co.com.ceiba.estacionamiento.joan.munoz.infraestructura.entidades;

import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "Registros_Parqueo")
@Getter
@Setter
public class RegistroParqueoEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonIgnore
	private Long id;
	@Temporal(TemporalType.TIMESTAMP)
	@NotNull
	private Calendar fechaIngreso;
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar fechaSalida;
	@NotNull
	private String tipoVehiculo;
	@NotNull
	private boolean motoAltoCilindraje;
	@NotNull
	private String placa;
	private double valor;

}