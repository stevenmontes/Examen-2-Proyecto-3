package com.proyecto.tres.examen2.repository;

import com.proyecto.tres.examen2.domain.Puesto;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Puesto entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PuestoRepository extends JpaRepository<Puesto, Long> {
}
