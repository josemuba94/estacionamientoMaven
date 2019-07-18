package co.com.ceiba.estacionamiento.joan.munoz.unitarias;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.when;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import co.com.ceiba.estacionamiento.joan.munoz.datatestbuilder.RegistroParqueoDataTestBuilder;
import co.com.ceiba.estacionamiento.joan.munoz.datatestbuilder.SolicitudIngresoDataTestBuilder;
import co.com.ceiba.estacionamiento.joan.munoz.dominio.excepciones.EstacionamientoException;
import co.com.ceiba.estacionamiento.joan.munoz.dominio.modelo.RegistroParqueo;
import co.com.ceiba.estacionamiento.joan.munoz.dominio.modelo.SolicitudIngreso;
import co.com.ceiba.estacionamiento.joan.munoz.dominio.modelo.TipoVehiculoEnum;
import co.com.ceiba.estacionamiento.joan.munoz.dominio.modelo.Vigilante;
import co.com.ceiba.estacionamiento.joan.munoz.dominio.repositorio.RepositorioRegistroParqueo;

@SpringBootTest
@RunWith(SpringRunner.class)
public class VigilanteTest {

	public static final String PLACA_NO_HABIL = "AKY12A";
	
	@Mock
	private RepositorioRegistroParqueo repositorioRegistroParqueo;
	@InjectMocks
	private Vigilante vigilante;

	@Test
	public void diaNoHabilTest() {
		// Arrange
		SolicitudIngreso solicitudIngreso = new SolicitudIngresoDataTestBuilder().conPlaca(PLACA_NO_HABIL)
				.construirSolicitudIngreso();
		try {
			// Act
			vigilante.validarDiaHabil(solicitudIngreso);
			fail();
		} catch (EstacionamientoException excepcion) {
			// Assert
			assertEquals(Vigilante.DIA_NO_HABIL, excepcion.getMessage());
		}
	}
	
	@Test
	public void diasHabilesTest() {
		// Arrange
		SolicitudIngreso solicitudIngresoDomingo = new SolicitudIngresoDataTestBuilder().conPlaca(PLACA_NO_HABIL)
				.conFecha(new GregorianCalendar(2019, Calendar.JUNE, 23))
				.construirSolicitudIngreso();
		
		SolicitudIngreso solicitudIngresoLunes = new SolicitudIngresoDataTestBuilder().conPlaca(PLACA_NO_HABIL)
				.conFecha(new GregorianCalendar(2019, Calendar.JUNE, 24))
				.construirSolicitudIngreso();
		// Act
		vigilante.validarDiaHabil(solicitudIngresoDomingo);	
		vigilante.validarDiaHabil(solicitudIngresoLunes);			
		// Si la prueba no revienta por alguna excepción es porque se cumplió satisfactoriamente
	}

	@Test
	public void sinEspacioParaMotosTest() {
		// Arrange
		SolicitudIngreso solicitudIngreso = new SolicitudIngresoDataTestBuilder().construirSolicitudIngreso();

		when(repositorioRegistroParqueo.cantidadVehiculosPorTipo(solicitudIngreso.getTipoVehiculo()))
				.thenReturn(Vigilante.CUPO_MOTOS);
		try {
			// Act
			vigilante.validarCupo(solicitudIngreso);
			fail();
		} catch (EstacionamientoException excepcion) {
			// Assert
			assertEquals(Vigilante.MOTOS_SIN_CUPO, excepcion.getMessage());
		}
	}

	@Test
	public void conEspacioParaMotosTest() {
		// Arrange
		SolicitudIngreso solicitudIngreso = new SolicitudIngresoDataTestBuilder().construirSolicitudIngreso();
		when(repositorioRegistroParqueo.cantidadVehiculosPorTipo(solicitudIngreso.getTipoVehiculo()))
				.thenReturn(Vigilante.CUPO_MOTOS-1);
		
		// Act
		vigilante.validarCupo(solicitudIngreso);		
		// Si la prueba no revienta por alguna excepción es porque se cumplió satisfactoriamente
	}

	@Test
	public void sinEspacioParaCarrosTest() {
		// Arrange
		SolicitudIngreso solicitudIngreso = new SolicitudIngresoDataTestBuilder()
				.conTipoVehiculo(TipoVehiculoEnum.CARRO.name()).construirSolicitudIngreso();

		when(repositorioRegistroParqueo.cantidadVehiculosPorTipo(solicitudIngreso.getTipoVehiculo()))
				.thenReturn(Vigilante.CUPO_CARROS);
		try {
			// Act
			vigilante.validarCupo(solicitudIngreso);
			fail();
		} catch (EstacionamientoException excepcion) {
			// Assert
			assertEquals(Vigilante.CARROS_SIN_CUPO, excepcion.getMessage());
		}
	}

	@Test
	public void conEspacioParaCarrosTest() {
		// Arrange
		SolicitudIngreso solicitudIngreso = new SolicitudIngresoDataTestBuilder()
				.conTipoVehiculo(TipoVehiculoEnum.CARRO.name()).construirSolicitudIngreso();
		
		when(repositorioRegistroParqueo.cantidadVehiculosPorTipo(solicitudIngreso.getTipoVehiculo()))
				.thenReturn(Vigilante.CUPO_CARROS-1);
		
		// Act
		vigilante.validarCupo(solicitudIngreso);		
		// Si la prueba no revienta por alguna excepción es porque se cumplió satisfactoriamente
	}

	@Test
	public void calcularValorMotoPesadaTest() {
		// Arrange
		Calendar fechaIngreso = new GregorianCalendar(2019, Calendar.JUNE, 13, 12, 00);
		Calendar fechaSalida  = new GregorianCalendar(2019, Calendar.JUNE, 13, 21, 01);
		
		// Act
		double valorFacturado = vigilante.calcularValor(fechaIngreso, fechaSalida, TipoVehiculoEnum.MOTO.name(), true);
		
		// Assert
		assertEquals(6000, valorFacturado, 0);
	}
	
	@Test
	public void calcularValorCarroTest() {
		// Arrange
		Calendar fechaIngreso = new GregorianCalendar(2019, Calendar.JUNE, 24, 17, 00);
		Calendar fechaSalida  = new GregorianCalendar(2019, Calendar.JUNE, 25, 17, 27);
		
		// Act
		double valorFacturado = vigilante.calcularValor(fechaIngreso, fechaSalida, TipoVehiculoEnum.CARRO.name(), false);
		
		// Assert
		assertEquals(9000, valorFacturado, 0);
	}
	
	@Test
	public void darTiposVehiculoTest() {
		// Arrange
		List<String> tiposVehiculo;
		
		// Act
		tiposVehiculo = vigilante.darTiposVehiculo();
		
		// Assert
		assertEquals(TipoVehiculoEnum.CARRO.name(), tiposVehiculo.get(0));
		assertEquals(TipoVehiculoEnum.MOTO.name(), tiposVehiculo.get(1));
	}
	
	@Test
	public void placaDuplicadatest() {
		// Arrange
		RegistroParqueo registroParqueo = new RegistroParqueoDataTestBuilder().construirRegistroParqueo();
		when(repositorioRegistroParqueo.buscarVehiculoIngresado(registroParqueo.getPlaca())).thenReturn(registroParqueo);

		try {
			// Act
			vigilante.validarPlacaDuplicada(registroParqueo.getPlaca());
			fail();
			
		} catch (Exception excepcion) {
			// Assert
			assertEquals(Vigilante.PLACA_DUPLICADA, excepcion.getMessage());
		}		
	}
}
