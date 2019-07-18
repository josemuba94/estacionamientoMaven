package co.com.ceiba.estacionamiento.joan.munoz.datatestbuilder;

import java.util.Calendar;
import java.util.GregorianCalendar;

import co.com.ceiba.estacionamiento.joan.munoz.dominio.modelo.SolicitudIngreso;
import co.com.ceiba.estacionamiento.joan.munoz.dominio.modelo.TipoVehiculoEnum;

public class SolicitudIngresoDataTestBuilder {

	static final String TIPO_VEHICULO = TipoVehiculoEnum.MOTO.name();
	static final boolean MOTO_ALTO_CILINDRAJE = true;
	static final String PLACA_INICIAL = "DTN28C";

	String tipoVehiculo;
	boolean motoAltoCilindraje;
	String placa;
	Calendar fecha;

	UtilitarioTransformarFechas utilitarioTransformarFechas;

	public SolicitudIngresoDataTestBuilder() {
		this.tipoVehiculo = TIPO_VEHICULO;
		this.motoAltoCilindraje = MOTO_ALTO_CILINDRAJE;
		this.placa = PLACA_INICIAL;
		this.fecha = new GregorianCalendar(2019, Calendar.JUNE, 13, 12, 12);

		utilitarioTransformarFechas = new UtilitarioTransformarFechas();
	}

	public SolicitudIngresoDataTestBuilder conTipoVehiculo(String tipoVehiculo) {
		this.tipoVehiculo = tipoVehiculo;
		return this;
	}

	public SolicitudIngresoDataTestBuilder conMotoAltoCilindraje(boolean motoAltoCilindraje) {
		this.motoAltoCilindraje = motoAltoCilindraje;
		return this;
	}

	public SolicitudIngresoDataTestBuilder conPlaca(String placa) {
		this.placa = placa;
		return this;
	}

	public SolicitudIngresoDataTestBuilder conFecha(Calendar fecha) {
		this.fecha = fecha;
		return this;
	}

	public SolicitudIngreso construirSolicitudIngreso() {
		return new SolicitudIngreso(tipoVehiculo, placa, motoAltoCilindraje, fecha);
	}

	public String construirSolicitudJson() {
		return "{ \"tipoVehiculo\": \"" + tipoVehiculo + "\", \"motoAltoCilindraje\":" + motoAltoCilindraje
				+ ", \"placa\":\"" + placa + "\", \"fecha\":\""
				+ utilitarioTransformarFechas.convertirFechaAcadena(fecha) + "\" }";
	}
}
