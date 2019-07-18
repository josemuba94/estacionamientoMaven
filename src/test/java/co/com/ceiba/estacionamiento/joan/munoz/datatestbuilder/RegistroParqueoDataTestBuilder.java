package co.com.ceiba.estacionamiento.joan.munoz.datatestbuilder;

import java.util.Calendar;
import java.util.GregorianCalendar;

import co.com.ceiba.estacionamiento.joan.munoz.dominio.modelo.RegistroParqueo;
import co.com.ceiba.estacionamiento.joan.munoz.dominio.modelo.TipoVehiculoEnum;

public class RegistroParqueoDataTestBuilder {

	static final String TIPO_VEHICULO = TipoVehiculoEnum.CARRO.name();
	static final boolean MOTO_ALTO_CILINDRAJE = false;
	static final String PLACA_INICIAL = "DTN287";

	private Long id;
	private Calendar fechaIngreso;
	private Calendar fechaSalida;
	private String tipoVehiculo;
	private boolean motoAltoCilindraje;
	private String placa;
	private double valor;
	
	private UtilitarioTransformarFechas utilitarioTransformarFechas;

	public RegistroParqueoDataTestBuilder() {
		this.id = 1010L;
		this.tipoVehiculo = TIPO_VEHICULO;
		this.motoAltoCilindraje = MOTO_ALTO_CILINDRAJE;
		this.placa = PLACA_INICIAL;
		this.fechaIngreso = new GregorianCalendar(2019, Calendar.JUNE, 25, 14, 02);
		
		utilitarioTransformarFechas = new UtilitarioTransformarFechas();
	}

	public RegistroParqueoDataTestBuilder conFechaIngreso(Calendar fecha) {
		this.fechaIngreso = fecha;
		return this;
	}

	public RegistroParqueoDataTestBuilder conFechaSalida(Calendar fecha) {
		this.fechaSalida = fecha;
		return this;
	}

	public RegistroParqueoDataTestBuilder conTipoVehiculo(String tipoVehiculo) {
		this.tipoVehiculo = tipoVehiculo;
		return this;
	}

	public RegistroParqueoDataTestBuilder conMotoAltoCilindraje(boolean motoAltoCilindraje) {
		this.motoAltoCilindraje = motoAltoCilindraje;
		return this;
	}

	public RegistroParqueoDataTestBuilder conPlaca(String placa) {
		this.placa = placa;
		return this;
	}

	public RegistroParqueoDataTestBuilder conValor(double valor) {
		this.valor = valor;
		return this;
	}

	public RegistroParqueo construirRegistroParqueo() {
		return new RegistroParqueo(id, fechaIngreso, fechaSalida, tipoVehiculo, motoAltoCilindraje, placa, valor);
	}
	
	public String construirRegistroJson() {
		return "{ \"id\":" + id.intValue() + ", \"fechaIngreso\":\""
				+ utilitarioTransformarFechas.convertirFechaAcadena(fechaIngreso) + "\", \"fechaSalida\":\""
				+ utilitarioTransformarFechas.convertirFechaAcadena(fechaSalida) + "\", \"tipoVehiculo\":\""
				+ tipoVehiculo + "\", \"motoAltoCilindraje\":" + motoAltoCilindraje + ", \"placa\":\"" + placa
				+ "\", \"valor\":" + valor + " }";
	}
}
