package com.proyecto.tres.examen2.web.rest;

import com.proyecto.tres.examen2.domain.Puesto;
import com.proyecto.tres.examen2.repository.PuestoRepository;
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

/**
 * REST controller for managing {@link com.proyecto.tres.examen2.domain.Puesto}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class PuestoResource {

    private final Logger log = LoggerFactory.getLogger(PuestoResource.class);

    private static final String ENTITY_NAME = "puesto";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final PuestoRepository puestoRepository;

    public PuestoResource(PuestoRepository puestoRepository) {
        this.puestoRepository = puestoRepository;
    }

    /**
     * {@code POST  /puestos} : Create a new puesto.
     *
     * @param puesto the puesto to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new puesto, or with status {@code 400 (Bad Request)} if the puesto has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/puestos")
    public ResponseEntity<Puesto> createPuesto(@RequestBody Puesto puesto) throws URISyntaxException {
        log.debug("REST request to save Puesto : {}", puesto);
        if (puesto.getId() != null) {
            throw new BadRequestAlertException("A new puesto cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Puesto result = puestoRepository.save(puesto);
        return ResponseEntity.created(new URI("/api/puestos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /puestos} : Updates an existing puesto.
     *
     * @param puesto the puesto to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated puesto,
     * or with status {@code 400 (Bad Request)} if the puesto is not valid,
     * or with status {@code 500 (Internal Server Error)} if the puesto couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/puestos")
    public ResponseEntity<Puesto> updatePuesto(@RequestBody Puesto puesto) throws URISyntaxException {
        log.debug("REST request to update Puesto : {}", puesto);
        if (puesto.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Puesto result = puestoRepository.save(puesto);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, puesto.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /puestos} : get all the puestos.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of puestos in body.
     */
    @GetMapping("/puestos")
    public List<Puesto> getAllPuestos() {
        log.debug("REST request to get all Puestos");
        return puestoRepository.findAll();
    }

    /**
     * {@code GET  /puestos/:id} : get the "id" puesto.
     *
     * @param id the id of the puesto to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the puesto, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/puestos/{id}")
    public ResponseEntity<Puesto> getPuesto(@PathVariable Long id) {
        log.debug("REST request to get Puesto : {}", id);
        Optional<Puesto> puesto = puestoRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(puesto);
    }

    /**
     * {@code DELETE  /puestos/:id} : delete the "id" puesto.
     *
     * @param id the id of the puesto to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/puestos/{id}")
    public ResponseEntity<Void> deletePuesto(@PathVariable Long id) {
        log.debug("REST request to delete Puesto : {}", id);
        puestoRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
