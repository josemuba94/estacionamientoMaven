package co.com.ceiba.estacionamiento.joan.munoz.datatestbuilder;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class UtilitarioTransformarFechas {

	public String convertirFechaAcadena(Calendar fechaIngresada) {
		SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat formatoHora = new SimpleDateFormat("HH:mm:ss");
		return formatoFecha.format(fechaIngresada.getTime()) + "T" + formatoHora.format(fechaIngresada.getTime());
	}
}
