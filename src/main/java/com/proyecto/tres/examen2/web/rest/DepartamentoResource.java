package com.proyecto.tres.examen2.web.rest;

import com.proyecto.tres.examen2.domain.Departamento;
import com.proyecto.tres.examen2.repository.DepartamentoRepository;
import com.proyecto.tres.examen2.web.rest.errors.BadRequestAlertException;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * REST controller for managing {@link com.proyecto.tres.examen2.domain.Departamento}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class DepartamentoResource {

    private final Logger log = LoggerFactory.getLogger(DepartamentoResource.class);

    private static final String ENTITY_NAME = "departamento";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final DepartamentoRepository departamentoRepository;

    public DepartamentoResource(DepartamentoRepository departamentoRepository) {
        this.departamentoRepository = departamentoRepository;
    }

    /**
     * {@code POST  /departamentos} : Create a new departamento.
     *
     * @param departamento the departamento to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new departamento, or with status {@code 400 (Bad Request)} if the departamento has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/departamentos")
    public ResponseEntity<Departamento> createDepartamento(@RequestBody Departamento departamento) throws URISyntaxException {
        log.debug("REST request to save Departamento : {}", departamento);
        if (departamento.getId() != null) {
            throw new BadRequestAlertException("A new departamento cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Departamento result = departamentoRepository.save(departamento);
        return ResponseEntity.created(new URI("/api/departamentos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /departamentos} : Updates an existing departamento.
     *
     * @param departamento the departamento to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated departamento,
     * or with status {@code 400 (Bad Request)} if the departamento is not valid,
     * or with status {@code 500 (Internal Server Error)} if the departamento couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/departamentos")
    public ResponseEntity<Departamento> updateDepartamento(@RequestBody Departamento departamento) throws URISyntaxException {
        log.debug("REST request to update Departamento : {}", departamento);
        if (departamento.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Departamento result = departamentoRepository.save(departamento);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, departamento.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /departamentos} : get all the departamentos.
     *
     * @param eagerload flag to eager load entities from relationships (This is applicable for many-to-many).
     * @param filter the filter of the request.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of departamentos in body.
     */
    @GetMapping("/departamentos")
    public List<Departamento> getAllDepartamentos(@RequestParam(required = false) String filter,@RequestParam(required = false, defaultValue = "false") boolean eagerload) {
        if ("jefatura-is-null".equals(filter)) {
            log.debug("REST request to get all Departamentos where jefatura is null");
            return StreamSupport
                .stream(departamentoRepository.findAll().spliterator(), false)
                .filter(departamento -> departamento.getJefatura() == null)
                .collect(Collectors.toList());
        }
        log.debug("REST request to get all Departamentos");
        return departamentoRepository.findAllWithEagerRelationships();
    }

    /**
     * {@code GET  /departamentos/:id} : get the "id" departamento.
     *
     * @param id the id of the departamento to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the departamento, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/departamentos/{id}")
    public ResponseEntity<Departamento> getDepartamento(@PathVariable Long id) {
        log.debug("REST request to get Departamento : {}", id);
        Optional<Departamento> departamento = departamentoRepository.findOneWithEagerRelationships(id);
        return ResponseUtil.wrapOrNotFound(departamento);
    }

    /**
     * {@code DELETE  /departamentos/:id} : delete the "id" departamento.
     *
     * @param id the id of the departamento to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/departamentos/{id}")
    public ResponseEntity<Void> deleteDepartamento(@PathVariable Long id) {
        log.debug("REST request to delete Departamento : {}", id);
        departamentoRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
