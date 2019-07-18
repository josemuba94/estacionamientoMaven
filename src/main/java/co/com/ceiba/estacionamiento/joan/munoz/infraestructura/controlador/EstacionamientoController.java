package co.com.ceiba.estacionamiento.joan.munoz.infraestructura.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.com.ceiba.estacionamiento.joan.munoz.aplicacion.dtos.EstacionamientoDTO;
import co.com.ceiba.estacionamiento.joan.munoz.aplicacion.dtos.RegistroParqueoDTO;
import co.com.ceiba.estacionamiento.joan.munoz.aplicacion.dtos.SolicitudIngresoDTO;
import co.com.ceiba.estacionamiento.joan.munoz.aplicacion.servicios.CalcularSalidaService;
import co.com.ceiba.estacionamiento.joan.munoz.aplicacion.servicios.ObtenerEstacionamiento;
import co.com.ceiba.estacionamiento.joan.munoz.aplicacion.servicios.IngresarVehiculoService;
import co.com.ceiba.estacionamiento.joan.munoz.aplicacion.servicios.SacarVehiculoService;

@RestController
@CrossOrigin(origins = { "http://localhost:4200" })
@RequestMapping("/api")
public class EstacionamientoController {

	public static final String URL_INGRESAR_VEHICULO = "/ingresarVehiculo";
	public static final String URL_CALCULAR_SALIDA   = "/calcularSalida/{placa}";
	public static final String URL_SACAR_VEHICULO    = "/sacarVehiculo";
	public static final String URL_ESTACIONAMIENTO   = "/obtenerEstacionamiento";
	
	@Autowired
	private IngresarVehiculoService vigilanteIngresarService;
	@Autowired
	private CalcularSalidaService vigilanteCalcularService;
	@Autowired
	private SacarVehiculoService vigilanteSacarService;
	@Autowired
	private ObtenerEstacionamiento vigilanteEstacionamientoService;

	@PostMapping(URL_INGRESAR_VEHICULO)
	public RegistroParqueoDTO ingresarVehiculo(@RequestBody SolicitudIngresoDTO solicitudIngresoDTO) {
		return vigilanteIngresarService.ingresarVehiculo(solicitudIngresoDTO);
	}
	
	@GetMapping(URL_CALCULAR_SALIDA)
	public RegistroParqueoDTO calcularSalida(@PathVariable String placa) {
		return vigilanteCalcularService.calcularSalida(placa);	
	}
	
	@PutMapping(URL_SACAR_VEHICULO) 
	public RegistroParqueoDTO sacarVehiculo(@RequestBody RegistroParqueoDTO registroParqueoDTO) {
		return vigilanteSacarService.sacarVehiculo(registroParqueoDTO);		
	}
	
	@GetMapping(URL_ESTACIONAMIENTO)
	public EstacionamientoDTO obtenerEstacionamiento () {
		return vigilanteEstacionamientoService.obtenerEstacionamiento();	
	}
	
	
}