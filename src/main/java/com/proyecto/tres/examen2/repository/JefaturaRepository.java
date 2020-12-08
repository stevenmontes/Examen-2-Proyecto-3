package com.proyecto.tres.examen2.repository;

import com.proyecto.tres.examen2.domain.Jefatura;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Jefatura entity.
 */
@SuppressWarnings("unused")
@Repository
public interface JefaturaRepository extends JpaRepository<Jefatura, Long> {
}
