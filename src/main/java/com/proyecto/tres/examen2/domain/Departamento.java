package com.proyecto.tres.examen2.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import com.proyecto.tres.examen2.domain.enumeration.Estado;

/**
 * A Departamento.
 */
@Entity
@Table(name = "departamento")
public class Departamento implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "descripcion")
    private String descripcion;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado")
    private Estado estado;

    @ManyToMany
    @JoinTable(name = "departamento_empleado",
               joinColumns = @JoinColumn(name = "departamento_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "empleado_id", referencedColumnName = "id"))
    private Set<Empleado> empleados = new HashSet<>();

    @OneToOne(mappedBy = "departamento")
    @JsonIgnore
    private Jefatura jefatura;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public Departamento nombre(String nombre) {
        this.nombre = nombre;
        return this;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public Departamento descripcion(String descripcion) {
        this.descripcion = descripcion;
        return this;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Estado getEstado() {
        return estado;
    }

    public Departamento estado(Estado estado) {
        this.estado = estado;
        return this;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public Set<Empleado> getEmpleados() {
        return empleados;
    }

    public Departamento empleados(Set<Empleado> empleados) {
        this.empleados = empleados;
        return this;
    }

    public Departamento addEmpleado(Empleado empleado) {
        this.empleados.add(empleado);
        empleado.getDepartamentos().add(this);
        return this;
    }

    public Departamento removeEmpleado(Empleado empleado) {
        this.empleados.remove(empleado);
        empleado.getDepartamentos().remove(this);
        return this;
    }

    public void setEmpleados(Set<Empleado> empleados) {
        this.empleados = empleados;
    }

    public Jefatura getJefatura() {
        return jefatura;
    }

    public Departamento jefatura(Jefatura jefatura) {
        this.jefatura = jefatura;
        return this;
    }

    public void setJefatura(Jefatura jefatura) {
        this.jefatura = jefatura;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Departamento)) {
            return false;
        }
        return id != null && id.equals(((Departamento) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Departamento{" +
            "id=" + getId() +
            ", nombre='" + getNombre() + "'" +
            ", descripcion='" + getDescripcion() + "'" +
            ", estado='" + getEstado() + "'" +
            "}";
    }
}
