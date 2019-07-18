package co.com.ceiba.estacionamiento.joan.munoz.dominio.modelo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import org.springframework.stereotype.Component;

import co.com.ceiba.estacionamiento.joan.munoz.dominio.excepciones.EstacionamientoException;
import co.com.ceiba.estacionamiento.joan.munoz.dominio.repositorio.RepositorioRegistroParqueo;

@Component
public class Vigilante {

	public static final String MOTOS_SIN_CUPO = "Actualmente no hay espacio disponible para motos.";
	public static final String CARROS_SIN_CUPO = "Actualmente no hay espacio disponible para carros.";
	public static final String DIA_NO_HABIL = "El vehículo no puede ingresar porque no es un día hábil.";
	public static final String VEHICULO_NO_INGRESADO = "El vehículo no se encuentra dentro del estacionamiento.";
	public static final String PLACA_DUPLICADA = "Actualmente hay un vehículo ingresado con la misma placa.";

	public static final String INICIAL_NO_PERMITIDA = "A";

	public static final int CUPO_MOTOS = 10;
	public static final int CUPO_CARROS = 20;
	
	public static final int MAXIMO_HORAS_DIA = 9;

	public static final double VALOR_DIA_MOTO = 4000;
	public static final double VALOR_DIA_CARRO = 8000;
	public static final double VALOR_HORA_MOTO = 500;
	public static final double VALOR_HORA_CARRO = 1000;
	public static final double ADICION_MOTO_PESADA = 2000;

	private RepositorioRegistroParqueo repositorioRegistroParqueo;

	public Vigilante(RepositorioRegistroParqueo repositorioRegistroParqueo) {
		this.repositorioRegistroParqueo = repositorioRegistroParqueo;
	}

	public RegistroParqueo ingresarVehiculo(SolicitudIngreso solicitudIngreso) {
		validarPlacaDuplicada(solicitudIngreso.getPlaca());
		validarDiaHabil(solicitudIngreso);
		validarCupo(solicitudIngreso);

		RegistroParqueo registroParqueo = new RegistroParqueo(null, solicitudIngreso.getFecha(), null,
				solicitudIngreso.getTipoVehiculo(), solicitudIngreso.isMotoAltoCilindraje(),
				solicitudIngreso.getPlaca(), 0.0);

		return repositorioRegistroParqueo.guardarRegistroParqueo(registroParqueo);
	}

	public void validarPlacaDuplicada(String placa) {
		RegistroParqueo registroParqueo = repositorioRegistroParqueo.buscarVehiculoIngresado(placa);
		if (registroParqueo != null)
			throw new EstacionamientoException(PLACA_DUPLICADA);
	}

	public void validarDiaHabil(SolicitudIngreso solicitudIngreso) {
		if (solicitudIngreso.getPlaca().startsWith(INICIAL_NO_PERMITIDA)
				&& solicitudIngreso.getFecha().get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY
				&& solicitudIngreso.getFecha().get(Calendar.DAY_OF_WEEK) != Calendar.MONDAY)
			throw new EstacionamientoException(DIA_NO_HABIL);
	}

	public void validarCupo(SolicitudIngreso solicitudIngreso) {
		String tipo = solicitudIngreso.getTipoVehiculo();
		int cantidadVehiculos = repositorioRegistroParqueo.cantidadVehiculosPorTipo(tipo);

		if (tipo.equals(TipoVehiculoEnum.MOTO.name()) && cantidadVehiculos == CUPO_MOTOS)
			throw new EstacionamientoException(MOTOS_SIN_CUPO);
		if (tipo.equals(TipoVehiculoEnum.CARRO.name()) && cantidadVehiculos == CUPO_CARROS)
			throw new EstacionamientoException(CARROS_SIN_CUPO);
	}

	public RegistroParqueo calcularSalida(String placa) {
		RegistroParqueo registroParqueo = repositorioRegistroParqueo.buscarVehiculoIngresado(placa);
		if (registroParqueo == null)
			throw new EstacionamientoException(VEHICULO_NO_INGRESADO);

		registroParqueo.setFechaSalida(Calendar.getInstance());
		registroParqueo.setValor(calcularValor(registroParqueo.getFechaIngreso(), registroParqueo.getFechaSalida(),
				registroParqueo.getTipoVehiculo(), registroParqueo.isMotoAltoCilindraje()));
		
		return registroParqueo;
	}

	public double calcularValor(Calendar fechaIngreso, Calendar fechaSalida, String tipoVehiculo,
			boolean esMotoAltoCilindraje) {

		final long miliSegundosPorHora = 3600000;
		final long miliSegundosPorDia = miliSegundosPorHora * 24;

		long diferencia = fechaSalida.getTimeInMillis() - fechaIngreso.getTimeInMillis();
		int dias = (int) (diferencia / miliSegundosPorDia);
		diferencia = diferencia - (dias * miliSegundosPorDia);
		int horas = (int) (diferencia / miliSegundosPorHora);
		
		if (horas >= MAXIMO_HORAS_DIA) {
			horas = 0;
			dias++;
		} else {
			diferencia = diferencia - (horas * miliSegundosPorHora);
			if (diferencia > 0)
				horas++;
		}

		double valorFacturado = tipoVehiculo.equals(TipoVehiculoEnum.MOTO.name())
				? dias * VALOR_DIA_MOTO + horas * VALOR_HORA_MOTO
				: dias * VALOR_DIA_CARRO + horas * VALOR_HORA_CARRO;

		return esMotoAltoCilindraje ? valorFacturado + ADICION_MOTO_PESADA : valorFacturado;
	}

	public RegistroParqueo sacarVehiculo(RegistroParqueo registroParqueo) {
		return repositorioRegistroParqueo.guardarRegistroParqueo(registroParqueo);
	}

	public List<RegistroParqueo> darVehiculosIngresados() {
		return repositorioRegistroParqueo.darVehiculosIngresados();
	}

	public List<String> darTiposVehiculo() {
		List<String> tiposVehiculo = new ArrayList<>();
		Arrays.asList(TipoVehiculoEnum.values()).forEach(tipoVehiculo -> tiposVehiculo.add(tipoVehiculo.name()));

		return tiposVehiculo;
	}

}
