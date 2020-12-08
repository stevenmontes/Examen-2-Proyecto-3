package com.proyecto.tres.examen2.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.proyecto.tres.examen2.web.rest.TestUtil;

public class PuestoTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Puesto.class);
        Puesto puesto1 = new Puesto();
        puesto1.setId(1L);
        Puesto puesto2 = new Puesto();
        puesto2.setId(puesto1.getId());
        assertThat(puesto1).isEqualTo(puesto2);
        puesto2.setId(2L);
        assertThat(puesto1).isNotEqualTo(puesto2);
        puesto1.setId(null);
        assertThat(puesto1).isNotEqualTo(puesto2);
    }
}
