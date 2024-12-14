package co.edu.uniminuto.entity;

import java.io.Serializable;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;

import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;


/**
 * The persistent class for the recompensas database table.
 * 
 */
@Entity
@Table(name="recompensas")
@NamedQuery(name="Recompensa.findAll", query="SELECT r FROM Recompensa r")
public class Recompensa implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Size(max = 200, message = "La descripción no puede tener más de 200 caracteres")
    @Column(name="descripcion")
    private String descripcion;
    
    @Column(name="fecha_creacion")
	private Timestamp fechaCreacion;

    @Min(value = 0, message = "Los puntos deben ser un valor positivo o cero")
    @Column(name="valor_puntos", nullable = false)
    private int valorPuntos;

    // Bi-directional many-to-one association to Usuario
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    @JoinColumn(name="usuario_id")
    private Usuario usuario;

	public Recompensa() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Timestamp getFechaCreacion() {
		return this.fechaCreacion;
	}

	public void setFechaCreacion(Timestamp fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public int getValorPuntos() {
		return this.valorPuntos;
	}

	public void setValorPuntos(int valorPuntos) {
		this.valorPuntos = valorPuntos;
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}