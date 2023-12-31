package diasfestivos.diasfestivos.entidades;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import org.hibernate.annotations.GenericGenerator;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

@Entity
@Table(name = "Tipo")
public class Tipo {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO, generator = "secuencia_tipo")
  @GenericGenerator(name = "secuencia_tipo", strategy = "increment")
  @Column(name = "id")
  private int id;

  @Column(name = "tipo", length = 100, unique = true)
  private String tipo;

  public Tipo(int id, String tipo) {
    this.id = id;
    this.tipo = tipo;
  }

  public Tipo() {
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getTipo() {
    return tipo;
  }

  public void setTipo(String tipo) {
    this.tipo = tipo;
  }

}
