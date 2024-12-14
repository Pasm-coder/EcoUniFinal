package co.edu.uniminuto.entity;

import java.io.Serializable;
import jakarta.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * The persistent class for the puntos_reciclaje database table.
 * 
 */
@Entity
@Table(name="puntos_reciclaje")
@NamedQuery(name="PuntosReciclaje.findAll", query="SELECT p FROM PuntosReciclaje p")
public class PuntosReciclaje implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Si la base de datos tiene autoincremento
    private long id;

    @NotBlank(message = "La dirección no puede estar vacía")
    private String direccion;

    @NotBlank(message = "El tipo de material no puede estar vacío")
    @Column(name="tipo_material")
    private String tipoMaterial;

    // Bi-directional many-to-one association to EmpresasRecicladora
    @NotNull(message = "La empresa recicladora no puede ser nula")
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name="empresa_id")
    private EmpresasRecicladora empresasRecicladora;

    public PuntosReciclaje() {
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

    public String getTipoMaterial() {
        return this.tipoMaterial;
    }

    public void setTipoMaterial(String tipoMaterial) {
        this.tipoMaterial = tipoMaterial;
    }

    public EmpresasRecicladora getEmpresasRecicladora() {
        return this.empresasRecicladora;
    }

    public void setEmpresasRecicladora(EmpresasRecicladora empresasRecicladora) {
        this.empresasRecicladora = empresasRecicladora;
    }
}