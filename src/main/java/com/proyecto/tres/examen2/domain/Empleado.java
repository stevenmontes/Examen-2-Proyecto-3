package com.proyecto.tres.examen2.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;

import com.proyecto.tres.examen2.domain.enumeration.Sexo;

import com.proyecto.tres.examen2.domain.enumeration.Puesto;

import com.proyecto.tres.examen2.domain.enumeration.Estado;

/**
 * A Empleado.
 */
@Entity
@Table(name = "empleado")
public class Empleado implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "primer_apellido")
    private String primerApellido;

    @Column(name = "segundo_apellido")
    private String segundoApellido;

    @Enumerated(EnumType.STRING)
    @Column(name = "sexo")
    private Sexo sexo;

    @Column(name = "fecha_nacimiento")
    private ZonedDateTime fechaNacimiento;

    @Column(name = "fecha_ingreso")
    private ZonedDateTime fechaIngreso;

    @Column(name = "salario")
    private Double salario;

    @Enumerated(EnumType.STRING)
    @Column(name = "puesto")
    private Puesto puesto;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado")
    private Estado estado;

    @OneToOne(mappedBy = "empleado")
    @JsonIgnore
    private Jefatura jefatura;

    @ManyToMany(mappedBy = "empleados")
    @JsonIgnore
    private Set<Departamento> departamentos = new HashSet<>();

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

    public Empleado nombre(String nombre) {
        this.nombre = nombre;
        return this;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPrimerApellido() {
        return primerApellido;
    }

    public Empleado primerApellido(String primerApellido) {
        this.primerApellido = primerApellido;
        return this;
    }

    public void setPrimerApellido(String primerApellido) {
        this.primerApellido = primerApellido;
    }

    public String getSegundoApellido() {
        return segundoApellido;
    }

    public Empleado segundoApellido(String segundoApellido) {
        this.segundoApellido = segundoApellido;
        return this;
    }

    public void setSegundoApellido(String segundoApellido) {
        this.segundoApellido = segundoApellido;
    }

    public Sexo getSexo() {
        return sexo;
    }

    public Empleado sexo(Sexo sexo) {
        this.sexo = sexo;
        return this;
    }

    public void setSexo(Sexo sexo) {
        this.sexo = sexo;
    }

    public ZonedDateTime getFechaNacimiento() {
        return fechaNacimiento;
    }

    public Empleado fechaNacimiento(ZonedDateTime fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
        return this;
    }

    public void setFechaNacimiento(ZonedDateTime fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public ZonedDateTime getFechaIngreso() {
        return fechaIngreso;
    }

    public Empleado fechaIngreso(ZonedDateTime fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
        return this;
    }

    public void setFechaIngreso(ZonedDateTime fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public Double getSalario() {
        return salario;
    }

    public Empleado salario(Double salario) {
        this.salario = salario;
        return this;
    }

    public void setSalario(Double salario) {
        this.salario = salario;
    }

    public Puesto getPuesto() {
        return puesto;
    }

    public Empleado puesto(Puesto puesto) {
        this.puesto = puesto;
        return this;
    }

    public void setPuesto(Puesto puesto) {
        this.puesto = puesto;
    }

    public Estado getEstado() {
        return estado;
    }

    public Empleado estado(Estado estado) {
        this.estado = estado;
        return this;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public Jefatura getJefatura() {
        return jefatura;
    }

    public Empleado jefatura(Jefatura jefatura) {
        this.jefatura = jefatura;
        return this;
    }

    public void setJefatura(Jefatura jefatura) {
        this.jefatura = jefatura;
    }

    public Set<Departamento> getDepartamentos() {
        return departamentos;
    }

    public Empleado departamentos(Set<Departamento> departamentos) {
        this.departamentos = departamentos;
        return this;
    }

    public Empleado addDepartamento(Departamento departamento) {
        this.departamentos.add(departamento);
        departamento.getEmpleados().add(this);
        return this;
    }

    public Empleado removeDepartamento(Departamento departamento) {
        this.departamentos.remove(departamento);
        departamento.getEmpleados().remove(this);
        return this;
    }

    public void setDepartamentos(Set<Departamento> departamentos) {
        this.departamentos = departamentos;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Empleado)) {
            return false;
        }
        return id != null && id.equals(((Empleado) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Empleado{" +
            "id=" + getId() +
            ", nombre='" + getNombre() + "'" +
            ", primerApellido='" + getPrimerApellido() + "'" +
            ", segundoApellido='" + getSegundoApellido() + "'" +
            ", sexo='" + getSexo() + "'" +
            ", fechaNacimiento='" + getFechaNacimiento() + "'" +
            ", fechaIngreso='" + getFechaIngreso() + "'" +
            ", salario=" + getSalario() +
            ", puesto='" + getPuesto() + "'" +
            ", estado='" + getEstado() + "'" +
            "}";
    }
}
