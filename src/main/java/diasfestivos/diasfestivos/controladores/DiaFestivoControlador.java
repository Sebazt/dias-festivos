package diasfestivos.diasfestivos.controladores;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import diasfestivos.diasfestivos.interfaces.IDiaFestivo;

@RestController
@RequestMapping("/festivos")
public class DiaFestivoControlador {

    @Autowired
    private IDiaFestivo servicio;

    @RequestMapping(value = "/verificar/{año}/{mes}/{dia}", method = RequestMethod.GET)
    public String verificarFestivo(@PathVariable int año, @PathVariable int mes, @PathVariable int dia) {
        try {
            Date fecha = new Date(año - 1900, mes - 1, dia);

            return servicio.diaEsFestivo(fecha) ? "Es Festivo" : "No es festivo";

        } catch (Exception ex) {
            return ex.getMessage();
        }
    }

    @RequestMapping(value = "/obtener/{año}", method = RequestMethod.GET)
    public List<Date> obtener(@PathVariable int año) {
        return servicio.obtenerDiasFestivos(año);
    }
}