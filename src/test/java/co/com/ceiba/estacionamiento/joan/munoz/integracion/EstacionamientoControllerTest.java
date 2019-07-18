package co.com.ceiba.estacionamiento.joan.munoz.integracion;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.nio.charset.Charset;
import java.util.Calendar;
import java.util.GregorianCalendar;

import org.json.JSONObject;
import org.json.JSONArray;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import co.com.ceiba.estacionamiento.joan.munoz.datatestbuilder.RegistroParqueoDataTestBuilder;
import co.com.ceiba.estacionamiento.joan.munoz.datatestbuilder.SolicitudIngresoDataTestBuilder;
import co.com.ceiba.estacionamiento.joan.munoz.dominio.excepciones.EstacionamientoException;
import co.com.ceiba.estacionamiento.joan.munoz.dominio.modelo.TipoVehiculoEnum;
import co.com.ceiba.estacionamiento.joan.munoz.dominio.modelo.Vigilante;
import co.com.ceiba.estacionamiento.joan.munoz.infraestructura.controlador.EstacionamientoController;
import co.com.ceiba.estacionamiento.joan.munoz.infraestructura.repositorio.RepositorioRegistroParqueoJPA;

@SpringBootTest
@RunWith(SpringRunner.class)
@Transactional
@TestPropertySource(locations = "classpath:application-test.properties")
public class EstacionamientoControllerTest {

	public static final String LOCAL_HOST_API = "http://localhost:8080/api";
	public static final String PLACA = "placa";
	public static final String PLACA_INGRESADA = "LVS98A";
	public static final MediaType APLICACION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(),
			MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

	@Autowired
	private EstacionamientoController estacionamientoController;
	@Autowired
	private Vigilante vigilante;
	@Autowired
	private RepositorioRegistroParqueoJPA repositorioRegistroParqueo;
	private MockMvc mockMvc;

	@Before
	public void setUp() {
		mockMvc = MockMvcBuilders.standaloneSetup(estacionamientoController).build();
	}

	@After
	public void tearDown() {
		repositorioRegistroParqueo.deleteAll();
	}

	@Test
	public void ingresarVehiculoTest() throws Exception {
		// Arrange
		String placa = "BKY61C";
		String solicitudIngresoJson = new SolicitudIngresoDataTestBuilder().conPlaca(placa).construirSolicitudJson();
		// Act
		String respuesta = mockMvc
				.perform(post(LOCAL_HOST_API + EstacionamientoController.URL_INGRESAR_VEHICULO)
						.contentType(APLICACION_JSON_UTF8).content(solicitudIngresoJson))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

		JSONObject json = new JSONObject(respuesta);

		// Assert
		assertEquals(placa, json.getString(PLACA));
	}

	@Test
	public void vehiculoNoEncontradoTest() {
		// Arrange
		String placa = "#%$/(/$#";

		try {
			// Act
			vigilante.calcularSalida(placa);
			fail();
		} catch (EstacionamientoException excepcion) {
			// Assert
			assertEquals(excepcion.getMessage(), (Vigilante.VEHICULO_NO_INGRESADO));
		}
	}

	@Test
	public void calcularSalidaTest() throws Exception {
		// Act
		String respuesta = mockMvc
				.perform(get(LOCAL_HOST_API + EstacionamientoController.URL_CALCULAR_SALIDA, PLACA_INGRESADA))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

		JSONObject json = new JSONObject(respuesta);

		// Assert
		assertEquals(PLACA_INGRESADA, json.getString(PLACA));
		assertTrue(Double.parseDouble(json.getString("valor")) > 0.0);
	}

	@Test
	public void sacarVehiculoTest() throws Exception {
		// Arrange
		String registroParqueoJSON = new RegistroParqueoDataTestBuilder().conPlaca(PLACA_INGRESADA)
				.conFechaIngreso(new GregorianCalendar(2019, Calendar.JUNE, 01, 12, 19))
				.conFechaSalida(new GregorianCalendar(2019, Calendar.JUNE, 03, 14, 22))
				.conValor(11500).construirRegistroJson();
		
		// Act
		String respuesta = mockMvc
				.perform(put(LOCAL_HOST_API + EstacionamientoController.URL_SACAR_VEHICULO)
						.contentType(APLICACION_JSON_UTF8).content(registroParqueoJSON))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

		JSONObject json = new JSONObject(respuesta);

		// Assert
		assertEquals(PLACA_INGRESADA, json.getString(PLACA));
		assertEquals(11500, json.getDouble("valor"), 0);
	}

	@Test
	public void darVehiculosIngresadosTest() throws Exception {
		// Arrange
		JSONObject respuestaJson;
		JSONArray tiposVehiculo;
		JSONArray listadoVehiculos;
		JSONObject vehiculoIngresado;

		// Act
		String respuesta = mockMvc.perform(get(LOCAL_HOST_API + EstacionamientoController.URL_ESTACIONAMIENTO))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

		respuestaJson = new JSONObject(respuesta);
		tiposVehiculo = new JSONArray(respuestaJson.get("tiposVehiculo").toString());
		listadoVehiculos = new JSONArray(respuestaJson.get("vehiculosIngresados").toString());
		vehiculoIngresado = new JSONObject(listadoVehiculos.get(0).toString());

		// Assert
		assertEquals(TipoVehiculoEnum.CARRO.name(), tiposVehiculo.get(0));
		assertEquals(TipoVehiculoEnum.MOTO.name(), tiposVehiculo.get(1));
		assertEquals(PLACA_INGRESADA, vehiculoIngresado.getString(PLACA));
	}
}
