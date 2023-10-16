package diasfestivos.diasfestivos.entidades;

import jakarta.persistence.Entity;
import java.util.Date;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import org.hibernate.annotations.GenericGenerator;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Festivo")
public class Festivo {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO, generator = "secuencia_festivo")
  @GenericGenerator(name = "secuencia_festivo", strategy = "increment")
  @Column(name = "id")
  private int id;

  @Column(name = "nombre", length = 100)
  private String nombre;

  @Column(name = "dia")
  private int dia;

  @Column(name = "mes")
  private int mes;

  @Column(name = "diaspascua")
  private int diasPascua;

  @ManyToOne
  @JoinColumn(name = "idtipo", referencedColumnName = "id")
  private Tipo tipo;

  private Date fecha;

  public Festivo() {
  }

  public Festivo(int id, int dia, int mes, String nombre, int diasPascua, Tipo tipo) {
    this.id = id;
    this.dia = dia;
    this.mes = mes;
    this.nombre = nombre;
    this.diasPascua = diasPascua;
    this.tipo = tipo;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public int getDia() {
    return dia;
  }

  public void setDia(int dia) {
    this.dia = dia;
  }

  public int getMes() {
    return mes;
  }

  public void setMes(int mes) {
    this.mes = mes;
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public int getDiasPascua() {
    return diasPascua;
  }

  public void setDiasPascua(int diasPascua) {
    this.diasPascua = diasPascua;
  }

  public Tipo getTipo() {
    return tipo;
  }

  public void setTipo(Tipo tipo) {
    this.tipo = tipo;
  }

  public Date getFecha() {
    return fecha;
  }

  public void setFecha(Date fecha) {
    this.fecha = fecha;
  }

}
