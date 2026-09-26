package co.edu.uniquindio.lenguajecafetero.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BeneficioTest {

    @Test
    void crearBeneficioCorrecto() {
        Beneficio beneficio = new Beneficio(TipoBeneficio.PLATAFORMA_VIRTUAL,
                "Plataforma", "Acceso 24/7");
        assertEquals(TipoBeneficio.PLATAFORMA_VIRTUAL, beneficio.getTipo());
        assertEquals("Plataforma", beneficio.getNombre());
        assertEquals("Acceso 24/7", beneficio.getDescripcion());
    }

    @Test
    void settersBeneficio() {
        Beneficio beneficio = new Beneficio(TipoBeneficio.MATERIAL_DIDACTICO, "Mat", "D");
        beneficio.setNombre("Nuevo");
        beneficio.setTipo(TipoBeneficio.CLUBES_CONVERSACION);
        beneficio.setDescripcion("Nueva desc");
        assertEquals("Nuevo", beneficio.getNombre());
        assertEquals(TipoBeneficio.CLUBES_CONVERSACION, beneficio.getTipo());
        assertEquals("Nueva desc", beneficio.getDescripcion());
    }
}
