package co.com.ceiba.estacionamiento.joan.munoz.unitarias;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

import java.util.Calendar;
import java.util.GregorianCalendar;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import co.com.ceiba.estacionamiento.joan.munoz.dominio.modelo.SolicitudIngreso;

@SpringBootTest
@RunWith(SpringRunner.class)
public class SolicitudIngresoTest {
	
	public static final String PLACA = "QWE321";
	public static final String TIPO_VEHICULO = "CARRO";

	@Test
	public void datosIncompletosTest() {
		// Arrange
		SolicitudIngreso solicitudIngreso = null;

		try {
			// Act
			solicitudIngreso = new SolicitudIngreso(null, null, false, null);
			fail();
		} catch (Exception exception) {
			// Assert
			assertNull(solicitudIngreso);
			assertEquals(SolicitudIngreso.DATOS_INCOMPLETOS, exception.getMessage());
		}
	}

	@Test
	public void fechaInvalidaTest() {
		// Arrange
		SolicitudIngreso solicitudIngreso = null;

		try {
			// Act
			solicitudIngreso = new SolicitudIngreso(TIPO_VEHICULO, PLACA, false,
					new GregorianCalendar(2500, Calendar.JULY, 1));
			fail();
		} catch (Exception exception) {
			// Assert
			assertNull(solicitudIngreso);
			assertEquals(SolicitudIngreso.FECHA_INVALIDA, exception.getMessage());
		}
	}

	@Test
	public void tipoInvalidoTest() {
		// Arrange
		SolicitudIngreso solicitudIngreso = null;

		try {
			// Act
			solicitudIngreso = new SolicitudIngreso("BICICLETA", PLACA, false, Calendar.getInstance());
			fail();
		} catch (Exception exception) {
			// Assert
			assertNull(solicitudIngreso);
			assertEquals(SolicitudIngreso.TIPO_INVALIDO, exception.getMessage());
		}
	}

	@Test
	public void solicitudValidaTest() {
		// Arrange
		SolicitudIngreso solicitudIngreso = null;

		// Act
		solicitudIngreso = new SolicitudIngreso(TIPO_VEHICULO, PLACA, false, Calendar.getInstance());
		
		// Assert
		assertNotNull(solicitudIngreso);
	}
}
