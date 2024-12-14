package co.edu.uniminuto.entity;

import java.io.Serializable;
import java.util.List;
import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;

/**
 * The persistent class for the empresas_recicladoras database table.
 * 
 */
@Entity
@Table(name="empresas_recicladoras")
@NamedQuery(name="EmpresasRecicladora.findAll", query="SELECT e FROM EmpresasRecicladora e")
public class EmpresasRecicladora implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long id;

	private String direccion;

	@Column(name="email_contacto")
	@Pattern(regexp = "^[\\w!#$%&'*+/=?^_`{|}~-]+(?:\\.[\\w!#$%&'*+/=?^_`{|}~-]+)*@(?:[\\w](?:[\\w-]*[\\w])?\\.)+[\\w](?:[\\w-]*[\\w])?$", message = "El email no tiene un formato válido.")
	private String emailContacto;

	private String nombre;

	@Pattern(regexp = "^\\+?[1-9]\\d{1,14}$", message = "El teléfono debe tener un formato válido.")
	private String telefono;

	//bi-directional many-to-one association to ContactosEmpresa

	//bi-directional many-to-one association to PuntosReciclaje
	@OneToMany(mappedBy="empresasRecicladora")
	private List<PuntosReciclaje> puntosReciclajes;

	public EmpresasRecicladora() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDireccion() {
		return this.direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getEmailContacto() {
		return this.emailContacto;
	}

	public void setEmailContacto(String emailContacto) {
		this.emailContacto = emailContacto;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTelefono() {
		return this.telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public List<PuntosReciclaje> getPuntosReciclajes() {
		return this.puntosReciclajes;
	}

	public void setPuntosReciclajes(List<PuntosReciclaje> puntosReciclajes) {
		this.puntosReciclajes = puntosReciclajes;
	}

	public PuntosReciclaje addPuntosReciclaje(PuntosReciclaje puntosReciclaje) {
		getPuntosReciclajes().add(puntosReciclaje);
		puntosReciclaje.setEmpresasRecicladora(this);

		return puntosReciclaje;
	}

	public PuntosReciclaje removePuntosReciclaje(PuntosReciclaje puntosReciclaje) {
		getPuntosReciclajes().remove(puntosReciclaje);
		puntosReciclaje.setEmpresasRecicladora(null);

		return puntosReciclaje;
	}
}