package com.proyecto.tres.examen2.web.rest;

import com.proyecto.tres.examen2.Examen2App;
import com.proyecto.tres.examen2.domain.Puesto;
import com.proyecto.tres.examen2.repository.PuestoRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.proyecto.tres.examen2.domain.enumeration.Estado;
/**
 * Integration tests for the {@link PuestoResource} REST controller.
 */
@SpringBootTest(classes = Examen2App.class)
@AutoConfigureMockMvc
@WithMockUser
public class PuestoResourceIT {

    private static final String DEFAULT_NOMBRE = "AAAAAAAAAA";
    private static final String UPDATED_NOMBRE = "BBBBBBBBBB";

    private static final Estado DEFAULT_ESTADO = Estado.Activo;
    private static final Estado UPDATED_ESTADO = Estado.Inactivo;

    @Autowired
    private PuestoRepository puestoRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restPuestoMockMvc;

    private Puesto puesto;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Puesto createEntity(EntityManager em) {
        Puesto puesto = new Puesto()
            .nombre(DEFAULT_NOMBRE)
            .estado(DEFAULT_ESTADO);
        return puesto;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Puesto createUpdatedEntity(EntityManager em) {
        Puesto puesto = new Puesto()
            .nombre(UPDATED_NOMBRE)
            .estado(UPDATED_ESTADO);
        return puesto;
    }

    @BeforeEach
    public void initTest() {
        puesto = createEntity(em);
    }

    @Test
    @Transactional
    public void createPuesto() throws Exception {
        int databaseSizeBeforeCreate = puestoRepository.findAll().size();
        // Create the Puesto
        restPuestoMockMvc.perform(post("/api/puestos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(puesto)))
            .andExpect(status().isCreated());

        // Validate the Puesto in the database
        List<Puesto> puestoList = puestoRepository.findAll();
        assertThat(puestoList).hasSize(databaseSizeBeforeCreate + 1);
        Puesto testPuesto = puestoList.get(puestoList.size() - 1);
        assertThat(testPuesto.getNombre()).isEqualTo(DEFAULT_NOMBRE);
        assertThat(testPuesto.getEstado()).isEqualTo(DEFAULT_ESTADO);
    }

    @Test
    @Transactional
    public void createPuestoWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = puestoRepository.findAll().size();

        // Create the Puesto with an existing ID
        puesto.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restPuestoMockMvc.perform(post("/api/puestos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(puesto)))
            .andExpect(status().isBadRequest());

        // Validate the Puesto in the database
        List<Puesto> puestoList = puestoRepository.findAll();
        assertThat(puestoList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllPuestos() throws Exception {
        // Initialize the database
        puestoRepository.saveAndFlush(puesto);

        // Get all the puestoList
        restPuestoMockMvc.perform(get("/api/puestos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(puesto.getId().intValue())))
            .andExpect(jsonPath("$.[*].nombre").value(hasItem(DEFAULT_NOMBRE)))
            .andExpect(jsonPath("$.[*].estado").value(hasItem(DEFAULT_ESTADO.toString())));
    }
    
    @Test
    @Transactional
    public void getPuesto() throws Exception {
        // Initialize the database
        puestoRepository.saveAndFlush(puesto);

        // Get the puesto
        restPuestoMockMvc.perform(get("/api/puestos/{id}", puesto.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(puesto.getId().intValue()))
            .andExpect(jsonPath("$.nombre").value(DEFAULT_NOMBRE))
            .andExpect(jsonPath("$.estado").value(DEFAULT_ESTADO.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingPuesto() throws Exception {
        // Get the puesto
        restPuestoMockMvc.perform(get("/api/puestos/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updatePuesto() throws Exception {
        // Initialize the database
        puestoRepository.saveAndFlush(puesto);

        int databaseSizeBeforeUpdate = puestoRepository.findAll().size();

        // Update the puesto
        Puesto updatedPuesto = puestoRepository.findById(puesto.getId()).get();
        // Disconnect from session so that the updates on updatedPuesto are not directly saved in db
        em.detach(updatedPuesto);
        updatedPuesto
            .nombre(UPDATED_NOMBRE)
            .estado(UPDATED_ESTADO);

        restPuestoMockMvc.perform(put("/api/puestos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedPuesto)))
            .andExpect(status().isOk());

        // Validate the Puesto in the database
        List<Puesto> puestoList = puestoRepository.findAll();
        assertThat(puestoList).hasSize(databaseSizeBeforeUpdate);
        Puesto testPuesto = puestoList.get(puestoList.size() - 1);
        assertThat(testPuesto.getNombre()).isEqualTo(UPDATED_NOMBRE);
        assertThat(testPuesto.getEstado()).isEqualTo(UPDATED_ESTADO);
    }

    @Test
    @Transactional
    public void updateNonExistingPuesto() throws Exception {
        int databaseSizeBeforeUpdate = puestoRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPuestoMockMvc.perform(put("/api/puestos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(puesto)))
            .andExpect(status().isBadRequest());

        // Validate the Puesto in the database
        List<Puesto> puestoList = puestoRepository.findAll();
        assertThat(puestoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deletePuesto() throws Exception {
        // Initialize the database
        puestoRepository.saveAndFlush(puesto);

        int databaseSizeBeforeDelete = puestoRepository.findAll().size();

        // Delete the puesto
        restPuestoMockMvc.perform(delete("/api/puestos/{id}", puesto.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Puesto> puestoList = puestoRepository.findAll();
        assertThat(puestoList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
