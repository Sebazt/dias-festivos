package diasfestivos.diasfestivos.interfaces;

import java.sql.Date;
import java.util.List;

public interface IDiaFestivo {
  public List<Date> obtenerDiasFestivos(int año);

  public boolean diaEsFestivo(Date Fecha);

  public boolean esFechaValida(String strFecha);

}
