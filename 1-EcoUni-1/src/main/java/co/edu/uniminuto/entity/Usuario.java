package co.edu.uniminuto.entity;

import java.io.Serializable;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the usuarios database table.
 * 
 */



@Entity
@Table(name="usuarios")
@NamedQuery(name="Usuario.findAll", query="SELECT u FROM Usuario u")
@NotNull(message = "El nombre no puede ser nulo")
@NotEmpty(message = "El nombre no puede estar vacío")
public class Usuario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long id;

	private String apellido;

	private String email;

	@Column(name="fecha_creacion")
	private Timestamp fechaCreacion;

	private String nombre;

	private String password;

	private String telefono;

	//bi-directional many-to-one association to ContactosEmpresa

	//bi-directional many-to-one association to Recompensa
	@OneToMany(mappedBy="usuario")
	private List<Recompensa> recompensas;

	public Usuario() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getApellido() {
		return this.apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Timestamp getFechaCreacion() {
		return this.fechaCreacion;
	}

	public void setFechaCreacion(Timestamp fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getTelefono() {
		return this.telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public List<Recompensa> getRecompensas() {
		return this.recompensas;
	}

	public void setRecompensas(List<Recompensa> recompensas) {
		this.recompensas = recompensas;
	}

	public Recompensa addRecompensa(Recompensa recompensa) {
		getRecompensas().add(recompensa);
		recompensa.setUsuario(this);

		return recompensa;
	}

	public Recompensa removeRecompensa(Recompensa recompensa) {
		getRecompensas().remove(recompensa);
		recompensa.setUsuario(null);

		return recompensa;
	}

}