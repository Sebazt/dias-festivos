package diasfestivos.diasfestivos.servicios;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import diasfestivos.diasfestivos.entidades.Festivo;
import diasfestivos.diasfestivos.interfaces.IDiaFestivo;
import diasfestivos.diasfestivos.repositorios.DiasFestivoRepositorio;

@Service
public class DiaFestivoServicio implements IDiaFestivo {

  @Autowired
  DiasFestivoRepositorio repositorio;

  public List<Date> obtenerDiasFestivos(int año) {
    List<Festivo> festivos = repositorio.findAll();
    festivos = calcularFestivos(festivos, año);
    return obtenerFechasDeFestivos(festivos);
  }

  @Override
  public boolean diaEsFestivo(Date fecha) {
    List<Festivo> festivos = repositorio.findAll();
    festivos = calcularFestivos(festivos, fecha.toLocalDate().getYear());
    return esFestivo(festivos, fecha);
  }

  private List<Date> obtenerFechasDeFestivos(List<Festivo> festivos) {
    List<Date> fechas = new ArrayList<>();
    for (Festivo festivo : festivos) {
      fechas.add((Date) festivo.getFecha());
    }
    return fechas;
  }

  private boolean esFestivo(List<Festivo> festivos, Date fecha) {
    for (Festivo festivo : festivos) {
      if (festivo.getFecha().toLocaleString().equals(fecha.toLocalDate()) || esLunes(festivo.getFecha(), fecha)) {
        return true;
      }
    }
    return false;
  }

  private boolean esLunes(java.util.Date date, Date fecha) {
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(date);
    int festivoDayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);

    calendar.setTime(fecha);
    int fechaDayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);

    return festivoDayOfWeek == Calendar.MONDAY && fechaDayOfWeek == Calendar.MONDAY;
  }

  private List<Festivo> calcularFestivos(List<Festivo> festivos, int año) {
    Date pascua = obtenerDomingoPascua(año);
    for (Festivo festivo : festivos) {
      festivo.setFecha(calcularFechaFestivo(festivo, año, pascua));
    }
    return festivos;
  }

  private Date obtenerDomingoPascua(int año) {
    int a = año % 19;
    int b = año / 100;
    int c = año % 100;
    int d = b / 4;
    int e = b % 4;
    int f = (b + 8) / 25;
    int g = (b - f + 1) / 3;
    int h = (19 * a + b - d - g + 15) % 30;
    int i = c / 4;
    int k = c % 4;
    int L = (32 + 2 * e + 2 * i - h - k) % 7;
    int m = (a + 11 * h + 22 * L) / 451;
    int mes = (h + L - 7 * m + 114) / 31;
    int dia = ((h + L - 7 * m + 114) % 31) + 1;

    
    mes--;
    dia--;

    return new Date(año - 1900, mes, dia);
  }

  private Date calcularFechaFestivo(Festivo festivo, int año, Date pascua) {
    switch (festivo.getTipo().getId()) {
      case 1:
        return new Date(año - 1900, festivo.getMes() - 1, festivo.getDia());
      case 2:
        return obtenerLunesSiguiente(new Date(año - 1900, festivo.getMes() - 1, festivo.getDia()));
      case 3:
        return agregarDias(pascua, festivo.getDiasPascua());
      case 4:
        return obtenerLunesSiguiente(agregarDias(pascua, festivo.getDiasPascua()));
      default:
        return (Date) festivo.getFecha();
    }
  }

  private Date agregarDias(Date fecha, int dias) {
    Calendar cal = Calendar.getInstance();
    cal.setTime(fecha);
    cal.add(Calendar.DATE, dias);
    return new Date(cal.getTime().getTime());
  }

  private Date obtenerLunesSiguiente(Date fecha) {
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(fecha);
    if (calendar.get(Calendar.DAY_OF_WEEK) > Calendar.MONDAY) {
      fecha = agregarDias(fecha, 8 - calendar.get(Calendar.DAY_OF_WEEK));
    } else if (calendar.get(Calendar.DAY_OF_WEEK) < Calendar.MONDAY) {
      fecha = agregarDias(fecha, 1);
    }
    return fecha;
  }
}


